package br.com.kproj.salesman.infrastructure.converters;


public interface TwoWayMerge<MODEL, ENTITY> {

    void merge(MODEL source, ENTITY target, Specification... specs);

    void mergeBack(ENTITY source, MODEL target, Specification... specs);
}
