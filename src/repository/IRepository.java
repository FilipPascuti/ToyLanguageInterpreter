package repository;

import model.ProgramState;
import model.utilities.ADTs.IList;

import java.util.List;

public interface IRepository {

    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programs);
    void logProgramStateExecution(ProgramState program);
}
