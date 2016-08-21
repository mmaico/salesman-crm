package br.com.kproj.salesman.infrastructure.repository;


public interface Converter<IN, OUT> {

    OUT convert(IN in, Object... args);
}
