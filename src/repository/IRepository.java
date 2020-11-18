package repository;

import model.ProgramState;

public interface IRepository {

//    void addProgram(ProgramState newProgram);
    ProgramState getCrtProgram();
    void logProgramStateExecution();
}
