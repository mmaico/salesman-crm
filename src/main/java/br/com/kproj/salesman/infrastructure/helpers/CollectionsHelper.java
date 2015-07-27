package br.com.kproj.salesman.infrastructure.helpers;

import com.google.common.collect.Iterables;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class CollectionsHelper {

	public static <T> Iterable<T> safeIterable(Iterable<T> iterable) {
		return iterable == null ? Collections.<T> emptyList() : iterable;
	}

	public static <T> Iterator<T> safeIterator(Iterable<T> iterable) {
		return iterable == null ? Collections.<T> emptyList().iterator() : iterable.iterator();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] safeArray(T[] values, Class<T> clazz) {
		return values == null ? (T[])Array.newInstance(clazz, 0) : values;
	}

	public static <T> Boolean isEmptySafe(Iterable<T> iterable) {

		if (iterable == null) {
			return Boolean.TRUE;
		}

		int size = Iterables.size(iterable);

		return size < 1;
	}

	/**
	 * Returns the size of the {@link Iterable}. If null, returns 0.
	 */
	public static <T> int safeSize(Iterable<T> iterable) {
		int size = 0;

		if (iterable != null) {
			size = Iterables.size(iterable);
		}

		return size;
	}
	
	public static <T> T get(List<T> list, T item) {
		
		if (list.contains(item)) {
			int indexOf = list.indexOf(item);
			
			if (indexOf > -1) {
				return list.get(indexOf);
			}
		}
		
		return null;
	}

}
