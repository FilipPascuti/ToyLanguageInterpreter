package model.expressions;

import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.values.Value;

public class VarExpression implements Expression{

    String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable, IHeap<Value> heap) {
        return symbolTable.lookUp(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
