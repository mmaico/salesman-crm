package br.com.kproj.salesman.infrastructure.configuration;


import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableSpringConfigured
@EnableScheduling
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("br.com.kproj")
public class SpringConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {


    public SpringConfig(ResourceProperties resourceProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, HttpMessageConverters messageConverters, ObjectProvider resourceHandlerRegistrationCustomizerProvider) {
        super(resourceProperties, mvcProperties, beanFactory, messageConverters, resourceHandlerRegistrationCustomizerProvider);
    }

    /**
     * TODO: colocar resource location do properties.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/view-resource/**")
                   .addResourceLocations("file:/opt/templates/");
    }


}
