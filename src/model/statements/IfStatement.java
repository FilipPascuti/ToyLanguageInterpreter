package model.statements;

import exceptions.InvalidArguments;
import model.ProgramState;
import model.expressions.Expression;
import model.types.BooleanType;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.utilities.ADTs.IStack;
import model.values.BooleanValue;
import model.values.Value;

public class IfStatement implements IStatement {

    private Expression expression;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) {
        synchronized (state) {
            IDictionary<String, Value> symbolTable = state.getSymbolTable();
            IStack<IStatement> executionStack = state.getExecutionStack();
            IHeap<Value> heap = state.getHeap();
            Value value = expression.evaluate(symbolTable, heap);
            if (!value.getType().equals(new BooleanType()))
                throw new InvalidArguments("invalid type of if argument");
            boolean result = ((BooleanValue) value).getValue();
            if (result)
                executionStack.push(thenStatement);
            else
                executionStack.push(elseStatement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "if( " + expression + " ) then {"
                + thenStatement + "} else {"
                + elseStatement + "}";
    }
}
