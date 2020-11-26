package model;

import model.statements.IStatement;
import model.utilities.ADTs.*;
import model.values.Value;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IDictionary<String, Value> symbolTable;
    private IList<Value> output;
    private IFileTable fileTable;
    private IStatement originalProgram;
    private IHeap<Value> heap;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Value> symbolTable, IList<Value> output, IFileTable fileTable, IStatement originalProgram, IHeap<Value> heap) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram;
        this.heap = heap;
        this.executionStack.push(originalProgram);
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(IDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IList<Value> getOutput() {
        return output;
    }

    public void setOutput(IList<Value> output) {
        this.output = output;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable fileTable) {
        this.fileTable = fileTable;
    }

    public IHeap<Value> getHeap() { return heap; }

    public void setHeap(IHeap<Value> heap) { this.heap = heap; }

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack +
                "\nSymbol Table: " + symbolTable +
                "\nOutput: " + output +
                "\nFileTable: " + fileTable +
                "\nHeap: " + heap + "\n";
    }
}
