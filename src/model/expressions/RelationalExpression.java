package model.expressions;

import exceptions.InvalidArguments;
import model.types.IntType;
import model.utilities.ADTs.IDictionary;
import model.values.BooleanValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExpression implements Expression{

    private final Expression left;
    private final Expression right;
    private final String operator;

    public RelationalExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> symbolTable) {
        Value valueLeft = left.evaluate(symbolTable);
        if(!(valueLeft.getType().equals(new IntType())))
            throw new InvalidArguments("Invalid left argument");
        Value valueRight = right.evaluate(symbolTable);
        if(!(valueRight.getType().equals(new IntType())))
            throw new InvalidArguments("Invalid right argument");
        int numberLeft = ((IntValue) valueLeft).getValue();
        int numberRight = ((IntValue) valueRight).getValue();
        return switch (operator) {
            case "<" -> new BooleanValue(numberLeft < numberRight);
            case "<=" -> new BooleanValue(numberLeft <= numberRight);
            case "==" -> new BooleanValue(numberLeft == numberRight);
            case "!=" -> new BooleanValue(numberLeft != numberRight);
            case ">" -> new BooleanValue(numberLeft > numberRight);
            case ">=" -> new BooleanValue(numberLeft >= numberRight);
            default -> throw new InvalidArguments("invalid operator");
        };
    }

    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }
}
