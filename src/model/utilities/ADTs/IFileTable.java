package model.utilities.ADTs;

import model.values.StringValue;
import java.io.BufferedReader;

public interface IFileTable {
    BufferedReader lookUp(StringValue key);
    boolean containsKey(StringValue key);
    void put(StringValue key, BufferedReader value);
    void remove(StringValue key);
}
