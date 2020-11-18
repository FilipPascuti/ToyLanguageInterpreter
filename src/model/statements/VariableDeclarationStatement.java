package model.statements;

import exceptions.AlreadyExistingVariable;
import model.ProgramState;
import model.types.BooleanType;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
import model.utilities.ADTs.IDictionary;
import model.values.BooleanValue;
import model.values.IntValue;
import model.values.Value;

public class VariableDeclarationStatement implements IStatement {
    private String name;
    private Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        if(symbolTable.containsKey(name))
            throw new AlreadyExistingVariable("variable is already declared");
        if(type instanceof IntType)
            symbolTable.put(name, type.defaultValue());
        else if(type instanceof BooleanType)
            symbolTable.put(name, type.defaultValue());
        else if(type instanceof StringType)
            symbolTable.put(name, type.defaultValue());
        return state;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
