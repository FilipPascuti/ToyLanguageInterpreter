package controller;

import exceptions.StackIsEmptyException;
import model.ProgramState;
import model.statements.IStatement;
import model.utilities.ADTs.IHeap;
import model.utilities.ADTs.IStack;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;

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
//            Garbage Collection part
            IHeap<Value> heap = programState.getHeap();
            heap.setContent(garbageCollector(
                    getAddressesFromSymbolTableAndHeap(
                            programState.getSymbolTable().getContent().values(),
                            heap.getContent()),
                    programState.getHeap().getContent()));
//            Logging part
            if(displayState) {
//                System.out.println(programState);
                this.repository.logProgramStateExecution();
            }
        }
    }

    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> ((RefValue)value).getAddress())
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromSymbolTableAndHeap(
            Collection<Value> symbolTableValues, Map<Integer, Value> heap) {
        Set<Map.Entry<Integer, Value>> heapEntrySet = heap.entrySet();
        LinkedList<Integer> addressesList = new LinkedList<>(getAddressesFromSymbolTable(symbolTableValues));

        boolean doneFinding = false;
        while (!doneFinding) {
            doneFinding = true;
            List<Integer> addressesFromHeap = heap.entrySet().stream()
                    .filter(element -> addressesList.contains(element.getKey()))
                    .filter(element -> element.getValue() instanceof RefValue)
                    .map(element -> ((RefValue) element.getValue()).getAddress())
                    .filter(address -> !addressesList.contains(address))
                    .collect(Collectors.toList());
            if(!addressesFromHeap.isEmpty()) {
                addressesList.addAll(addressesFromHeap);
                doneFinding = false;
            }
        }
        return addressesList;
    }

    private Map<Integer, Value> garbageCollector(
            List<Integer> addresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(element -> addresses.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
