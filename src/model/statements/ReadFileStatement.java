package model.statements;

import exceptions.InvalidArguments;
import exceptions.NotFoundException;
import exceptions.ReadingException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.IntType;
import model.types.StringType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IFileTable;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {

    private final Expression expression;
    private final String variableName;

    public ReadFileStatement(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IFileTable fileTable = state.getFileTable();
        if(!symbolTable.containsKey(variableName))
            throw new NotFoundException("The variable does not exist");
        if(!(symbolTable.lookUp(variableName).getType().equals(new IntType())))
            throw new InvalidArguments("Invalid variable type");
        Value value = expression.evaluate(symbolTable);
        if(!(value.getType().equals(new StringType())))
            throw new InvalidArguments("The expresion is not of valid string type.");
        StringValue filename = ((StringValue) value);
        if(!fileTable.containsKey(filename))
            throw new NotFoundException("The file that you want to read from is not opened");
        BufferedReader reader = fileTable.lookUp(filename);
        String line;
        try {
            line = reader.readLine();
        } catch (IOException ioException) {
            throw new ReadingException("An error occured while trying to read from the file");
        }
        if(line == null)
            symbolTable.replace(variableName, new IntValue(0));
        else
            symbolTable.replace(variableName, new IntValue((Integer.parseInt(line))));
        return state;
    }

    @Override
    public String toString() {
        return variableName + "=read(" + expression + ")";
    }
}
