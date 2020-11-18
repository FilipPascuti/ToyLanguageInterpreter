package model.utilities.ADTs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyList<E> implements IList<E> {
    private List<E> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    public MyList(Collection<E> elements){
        this.list = new ArrayList<>(elements);
    }

    @Override
    public void add(E element) {
        list.add(element);
    }

    @Override
    public Iterator<E> getAll() {
        return list.iterator();
    }

    @Override
    public E pop() {
        return list.remove(list.size()-1);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
