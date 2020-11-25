package model.statements;

import exceptions.AlreadyExistingVariable;
import exceptions.InvalidArguments;
import exceptions.NotFoundException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IFileTable;
import model.values.StringValue;
import model.values.Value;

import java.io.*;

public class OpenRFileStatement implements IStatement{

    private final Expression expression;

    public OpenRFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IFileTable fileTable = state.getFileTable();
        var value = expression.evaluate(symbolTable);
        if(!(value.getType().equals(new  StringType())))
            throw new InvalidArguments("invalid expression type");
        if(fileTable.containsKey((StringValue) value))
            throw new AlreadyExistingVariable("File was already opened");
        StringValue filename = ((StringValue) value);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename.getValue()));
            fileTable.put(filename, bufferedReader);
        } catch ( FileNotFoundException fileNotFoundException){
            throw new NotFoundException("File not found" + filename.getValue());
        }
        return state;
    }

    @Override
    public String toString() {
        return "open(" + expression + ')';
    }
}