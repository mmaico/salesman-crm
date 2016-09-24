package br.com.kproj.salesman.infrastructure.helpers.files.annotations;

import java.lang.annotation.*;

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
