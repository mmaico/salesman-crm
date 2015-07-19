package br.com.kproj.salesman.infrastructure.entity.builders;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder<T> {

	protected T entity;

	public T build() {
		return this.entity;
	}

	public List<T> asList() {
		List<T> entities = new ArrayList<T>();
		entities.add(this.entity);

		return entities;
	}
}
