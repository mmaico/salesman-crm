package br.com.kproj.salesman.infrastructure.model;

import java.util.Collection;
import java.util.Iterator;


public abstract class CollectionBehavior<T> implements Collection <T> {


    @Override
    public int size() {
        return getCollection().size();
    }

    @Override
    public boolean isEmpty() {
        return getCollection().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getCollection().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return getCollection().iterator();
    }

    @Override
    public Object[] toArray() {
        return getCollection().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return getCollection().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return getCollection().add(t);
    }

    @Override
    public boolean remove(Object o) {
        return getCollection().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getCollection().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getCollection().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getCollection().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getCollection().retainAll(c);
    }

    @Override
    public void clear() {
        getCollection().clear();
    }

    public abstract Collection<T> getCollection();
}
