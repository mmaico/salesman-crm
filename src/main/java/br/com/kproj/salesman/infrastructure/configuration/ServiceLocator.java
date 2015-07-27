package br.com.kproj.salesman.infrastructure.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public final class ServiceLocator implements ApplicationContextAware {


    @Autowired
	protected ApplicationContext appContext;
	
	private static final ServiceLocator instance = new ServiceLocator();
	private static Map<String, Object> registry = new HashMap<>();
	
	public static ServiceLocator getInstance() {
		return instance;
	}

	final public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		instance.appContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		Object service = registry.get(getName(clazz.getName()));
		
		if (service != null) {
			return (T) service;
		} else {
			service = instance.appContext.getBean(getName(clazz.getName()));
			registry.put(getName(clazz.getName()), service);
			
			return (T) service;
		}
	}
	
	public static Object getBean(String beanName) {
		Object service = registry.get(beanName);
		
		if (service != null) {
			return service;
		} else {
			service = instance.appContext.getBean(getName(beanName));
			registry.put(beanName, service);
			
			return service;
		}
	}
	
	public static void registry(Object service) {
		registry.put(getName(getName(service.getClass().getName())), service);
	}
	
	public static void registry(Class clazz, Object service) {
		registry.put(getName(getName(clazz.getName())), service);
	}
	
	public static void unregister(Class clazz) {
		registry.remove(getName(clazz.getName()));
	}
	
	private static String getName(String clazzName) {
		String simpleClassName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
		return simpleClassName.substring(0,1).toLowerCase() + simpleClassName.substring(1);
	}
}
