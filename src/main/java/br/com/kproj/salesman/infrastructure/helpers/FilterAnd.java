package br.com.kproj.salesman.infrastructure.helpers;

public class FilterAnd<T> extends Filter<T> {

	public FilterAnd(T object, String name) {
		super(object, name);
	}

	public FilterAnd(T object, String name, Operator operator) {
		super(object, name, operator);
	}

	public static <T> FilterAnd<T> build(String alias, Operator operator, T value) {

		return new FilterAnd<T>(value, alias, operator);
	}
}
