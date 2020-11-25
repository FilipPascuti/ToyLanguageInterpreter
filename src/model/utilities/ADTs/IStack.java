package model.utilities.ADTs;

public interface IStack<E> {
    E pop();
    void push(E element);
    boolean isEmpty();
}
