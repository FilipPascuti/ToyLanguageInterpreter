package model.statements;

import exceptions.InvalidArguments;
import exceptions.NotFoundException;
import model.ProgramState;
import model.expressions.Expression;
import model.types.RefType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.values.RefValue;
import model.values.Value;

public class NewStatement implements IStatement {

    private final String variableName;
    private final Expression expression;

    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) {
        synchronized (state) {
            IDictionary<String, Value> symbolTable = state.getSymbolTable();
            IHeap<Value> heap = state.getHeap();
            if (!symbolTable.containsKey(variableName))
                throw new NotFoundException("Variable is not defined.");
            Value variable = symbolTable.lookUp(variableName);
            if (!(variable.getType() instanceof RefType))
                throw new InvalidArguments("invalid type of variable");
            Value value = expression.evaluate(symbolTable, heap);
            if (!variable.getType().equals(new RefType(value.getType())))
                throw new InvalidArguments("the types of the variable and value are not equal");
            int newAddress = heap.put(value);
            symbolTable.replace(variableName, new RefValue(newAddress, ((RefValue) variable).getLocationType()));
        }
        return null;
    }

    @Override
    public String toString() {
        return variableName + "= new (" +
               expression + ")";
    }
}
