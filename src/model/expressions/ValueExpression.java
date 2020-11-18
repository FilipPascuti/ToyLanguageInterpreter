package model.expressions;

import model.utilities.ADTs.IDictionary;
import model.values.Value;

public class ValueExpression implements Expression {

    private Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
