package model.statements;

import exceptions.InvalidArguments;
import model.ProgramState;
import model.expressions.Expression;
import model.types.RefType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.utilities.ADTs.IStack;
import model.values.RefValue;
import model.values.Value;

import java.sql.Ref;

public class HeapWriteStatement implements IStatement {

    private final String variableName;
    private final Expression expression;

    public HeapWriteStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) {
        synchronized (state){
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap<Value> heap = state.getHeap();
        if(!symbolTable.containsKey(variableName))
            throw new InvalidArguments("invalid variable name");
        if(!(symbolTable.lookUp(variableName).getType() instanceof RefType))
            throw new InvalidArguments("invalid type of variable");
        RefValue variable = (RefValue) symbolTable.lookUp(variableName);

            if (!heap.containsKey(variable.getAddress()))
                throw new InvalidArguments("Variable not in heap");
            Value value = expression.evaluate(symbolTable, heap);
            if (!variable.getType().equals(new RefType(value.getType())))
                throw new InvalidArguments("the expression and the variable and not of the same type");
            heap.replace(variable.getAddress(), value);
        }
        return null;
    }

    @Override
    public String toString() {
        return  "write(" + variableName + "," +
                expression + ")";
    }
}
