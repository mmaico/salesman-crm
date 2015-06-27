package br.com.kproj.salesman.infrastructure.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
//@EnableLoadTimeWeaving
@EnableSpringConfigured
public class SpringConfig {
}