package model.types;

import model.values.IntValue;
import model.values.Value;

public class IntType implements Type {

    public IntType() { }

    @Override
    public boolean equals(Object object){
        return object instanceof IntType;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}