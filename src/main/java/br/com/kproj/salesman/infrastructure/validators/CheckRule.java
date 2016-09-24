package br.com.kproj.salesman.infrastructure.validators;

@FunctionalInterface
public interface CheckRule<T> {

    Boolean check(T t);
}
