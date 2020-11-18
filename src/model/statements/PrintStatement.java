package model.statements;

import model.ProgramState;
import model.expressions.Expression;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IList;
import model.values.Value;

public class PrintStatement implements IStatement{

    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IList<Value> output = state.getOutput();
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value value = expression.evaluate(symbolTable);
        output.add(value);
        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression + ")";
    }
}
