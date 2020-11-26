package model.statements;

import exceptions.InvalidArguments;
import model.ProgramState;
import model.expressions.Expression;
import model.utilities.ADTs.IDictionary;
import model.utilities.ADTs.IHeap;
import model.utilities.ADTs.VariableNotDefined;
import model.values.Value;

public class AssignmentStatement implements IStatement {
    String id;
    Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Value> symbolTable = state.getSymbolTable();
        IHeap<Value> heap = state.getHeap();
        if(!symbolTable.containsKey(id))
            throw new VariableNotDefined("Variable not declared");
        Value value = expression.evaluate(symbolTable, heap);
        if(!value.getType().equals(symbolTable.lookUp(id).getType()))
            throw new InvalidArguments("declared type of variable " + id +
                    " and type of the assigned expression do not match");
        symbolTable.replace(id, value);
        return null;
    }

    @Override
    public String toString() {
        return  id + "=" + expression;
    }
}
