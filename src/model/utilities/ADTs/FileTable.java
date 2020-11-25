package model.utilities.ADTs;

import model.values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements  IFileTable{

    private final Map<StringValue, BufferedReader> files;

    public FileTable() {
        this.files = new HashMap<>();
    }

    public FileTable(Map<StringValue, BufferedReader> files) {
        this.files = files;
    }

    @Override
    public BufferedReader lookUp(StringValue key) {
        BufferedReader value = files.get(key);
        if( value == null )
            throw new VariableNotDefined("Inexistent key: " + key);
        return value;
    }

    @Override
    public boolean containsKey(StringValue key) {
        return files.containsKey(key);
    }

    @Override
    public void put(StringValue key, BufferedReader value) {
        files.put(key, value);
    }

    @Override
    public void remove(StringValue key) {
        files.remove(key);
    }

    @Override
    public String toString() {
        return files.toString();
    }
}