package model.utilities.ADTs;

import exceptions.StackIsEmptyException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

public class MyStack<E> implements IStack<E>{
    private Deque<E> stack;

    public MyStack() {
        this.stack = new ArrayDeque<>();
    }

    @Override
    public E pop() {
        try {
            return stack.pop();
        } catch (Exception exception) {
            throw new StackIsEmptyException("The stack is empty");
        }
    }

    @Override
    public void push(E element) {
        this.stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
