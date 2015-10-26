package br.com.kproj.salesman.register.domain.contract;


public interface Preconditions<T>  {

    void applyPreconditions(T object);
}
