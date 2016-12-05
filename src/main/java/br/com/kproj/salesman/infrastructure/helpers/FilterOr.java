package br.com.kproj.salesman.infrastructure.helpers;

public class FilterOr<T> extends Filter<T> {

	public FilterOr(T object, String name) {
		super(object, name);
	}

	public FilterOr(T object, String name, Operator operator) {
		super(object, name, operator);
	}

	public static <T> FilterOr<T> build(String alias, Operator operator, T value) {

		return new FilterOr<T>(value, alias, operator);
	}
}
