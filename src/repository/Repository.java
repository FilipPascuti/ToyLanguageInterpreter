package repository;

import exceptions.WritingExeption;
import model.ProgramState;
import model.statements.IStatement;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IList;
import model.utilities.ADTs.IStack;
import model.utilities.ADTs.MyList;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Repository implements IRepository{

//    private IList<ProgramState> programs;
//
//    public Repository(IList<ProgramState> programs) {
//        this.programs = programs;
//    }
//
//    public Repository(ProgramState... programs) {
//        this.programs = new MyList<>(Arrays.asList(programs));
//    }
//
//    @Override
//    public void addProgram(ProgramState newProgram) {
//        programs.add(newProgram);
//    }

    private final ProgramState program;
    private final String filename;
    private boolean firstFileCall;

    public Repository(ProgramState program, String filename) {
        this.program = program;
        this.filename = filename;
        this.firstFileCall = true;
    }

    @Override
    public ProgramState getCrtProgram() {
        return program;
    }

    @Override
    public void logProgramStateExecution() {
        try{
            if (this.firstFileCall)
            {
                PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, false)));
                logFile.write("");
                logFile.close();
                this.firstFileCall = false;
            }
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
//            System.out.println(this.program.toString());
            logFile.write(this.program.toString() + "\n");
            logFile.close();

        }
        catch(IOException e){
            throw new WritingExeption("Couldn't write to file");
        }
    }
}
