package model.expressions;

import model.utilities.ADTs.IDictionary;
import model.values.Value;

public interface Expression {
    Value evaluate(IDictionary<String, Value> symbolTable);
}
