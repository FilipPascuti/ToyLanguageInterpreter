package model.utilities.ADTs;

public interface IDictionary<K, V> {
    V lookUp(K key);
    boolean containsKey(K key);
    void put(K key, V value);
    void remove(K key);
    void replace(K key, V newValue);
}
