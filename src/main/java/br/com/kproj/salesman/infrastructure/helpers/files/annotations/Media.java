package br.com.kproj.salesman.infrastructure.helpers.files.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacao responsavel por marcar se a entidade tem medias que poderao 
 * ser salvas no filesystem.
 */
@Documented
@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Media {

	public String name();
}
