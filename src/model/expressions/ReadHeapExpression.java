package model.expressions;

import exceptions.InvalidArguments;
import model.types.RefType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.values.RefValue;
import model.values.Value;

public class ReadHeapExpression implements Expression{

    private final Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public synchronized Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        Value value = expression.evaluate(symbolTable, heap);
        if(!(value.getType() instanceof RefType))
            throw new InvalidArguments("invalid type of variable");
        int address = ((RefValue) value).getAddress();
        synchronized (heap) {
            if (!heap.containsKey(address))
                throw new InvalidArguments("The variable is not declared in the heap");
            return heap.lookUp(address);
        }
    }

    @Override
    public String toString() {
        return "read(" + expression + ")";
    }
}
