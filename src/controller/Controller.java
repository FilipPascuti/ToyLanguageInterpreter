package controller;

import exceptions.StackIsEmptyException;
import model.ProgramState;
import model.statements.IStatement;
import model.utilities.ADTs.IStack;
import repository.IRepository;

public class Controller {

    private final IRepository repository;
    private final boolean displayState;

    public Controller(IRepository repository, boolean displayState) {
        this.repository = repository;
        this.displayState = displayState;
    }

    private ProgramState oneStep(ProgramState state){
        IStack<IStatement> executionStack = state.getExecutionStack();
        if(executionStack.isEmpty())
            throw new StackIsEmptyException("The execution stack is empty.\n");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps(){
        ProgramState programState = repository.getCrtProgram();
        if(displayState) {
//            System.out.println(programState);
            this.repository.logProgramStateExecution();
        }
        while(!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            if(displayState) {
//                System.out.println(programState);
                this.repository.logProgramStateExecution();
            }
        }
    }
}
