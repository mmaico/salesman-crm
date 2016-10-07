package br.com.kproj.salesman.infrastructure.http.response.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define os metodos no Endpoint que serão envelopados no formato Item/Items do cubus. 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceWrapper {

}
