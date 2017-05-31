package br.com.kproj.salesman.infrastructure.helpers;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.text.WordUtils;

public class Filter<T> {
	public enum Operator{EQ, GT, LT, LE, GE, NE}

	private T object;
	private String name;
	private Operator operator;
	
	public Filter(T object, String name) {
		this.object = object;
		this.name = name;
	}

	public Filter(T object, String name, Operator operator) {
		this.object = object;
		this.name = name;
		this.operator = operator;
	}
	
	public static <T> Filter<T> build(T object) {
		
		String nameUncapitalized = null;
		
		if (object == null) {
			nameUncapitalized = "";
		} else {
			nameUncapitalized = WordUtils.uncapitalize(object.getClass().getSimpleName());
		}
		
		return new Filter<T>(object, nameUncapitalized);
	}

	public static <T> Filter<T> build(String alias, T object) {
		
		return new Filter<T>(object, alias);
	}

	public static <T> Filter<T> build(String alias, Operator operator, T value) {

		return new Filter<T>(value, alias, operator);
	}
	
	public T getObject() {
		return object;
	}

	public String getName() {
		return name;
	}

	public Operator getOperator() {
		return operator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		
		return new EqualsBuilder().append(name, other.name).isEquals();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Filter nullObject() {
		return new Filter(null, null);
	}
	
	public Boolean isNullObject() {
		return equals(Filter.nullObject());
	}
}
