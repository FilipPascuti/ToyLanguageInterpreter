package model.statements;

import model.ProgramState;

public interface IStatement extends Cloneable {
    ProgramState execute(ProgramState state);
}
