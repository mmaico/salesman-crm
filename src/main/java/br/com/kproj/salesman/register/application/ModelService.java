package br.com.kproj.salesman.register.application;


public interface ModelService<T> {

    T save(T entity);
}
