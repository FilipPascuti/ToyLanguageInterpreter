package model.types;

import model.values.StringValue;
import model.values.Value;

public class StringType implements Type{

    public StringType() { }

    @Override
    public boolean equals(Object object){
        return object instanceof StringType;
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString(){
        return "String";
    }
}
