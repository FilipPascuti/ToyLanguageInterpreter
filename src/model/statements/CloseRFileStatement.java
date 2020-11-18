package model.statements;

import exceptions.AlreadyExistingVariable;
import exceptions.ClosingFileException;
import exceptions.InvalidArguments;
import model.ProgramState;
import model.expressions.Expression;
import model.types.StringType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IFileTable;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {

    private final Expression expression;

    public CloseRFileStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IFileTable fileTable = state.getFileTable();
        var value = expression.evaluate(symbolTable);
        if(!(value.getType().equals(new StringType())))
            throw new InvalidArguments("invalid expression type");
        StringValue filename = (StringValue) value;
        if(!fileTable.containsKey(filename))
            throw new AlreadyExistingVariable("File is already closed");
        BufferedReader reader = fileTable.lookUp(filename);
        try {
            reader.close();
        } catch (IOException ioException) {
            throw new ClosingFileException("An error occured while trying to close the file");
        }
        fileTable.remove(filename);
        return state;
    }

    @Override
    public String toString() {
        return "close(" + expression + ")";
    }
}
