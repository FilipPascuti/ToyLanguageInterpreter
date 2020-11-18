package model.statements;

import model.ProgramState;
import model.utilities.ADTs.IStack;
import model.utilities.ADTs.MyStack;

public class CompoundStatement implements IStatement {

    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> executionStack = state.getExecutionStack();
        executionStack.push(second);
        executionStack.push(first);
        return state;
    }

    @Override
    public String toString() {
        return  first + " ; " + second;
    }
}
