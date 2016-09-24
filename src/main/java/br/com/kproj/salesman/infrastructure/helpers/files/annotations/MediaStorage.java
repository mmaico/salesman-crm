package br.com.kproj.salesman.infrastructure.helpers.files.annotations;

import java.lang.annotation.*;

/**
 * Anotacao responsavel por marcar o atributo que tem arquivo para ser salvo
 * e o nome do diretorio que que este arquivo sera salvo.
 */
@Documented
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MediaStorage {

	public String name();
	
}
