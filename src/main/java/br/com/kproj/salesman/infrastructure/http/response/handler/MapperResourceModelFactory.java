package br.com.kproj.salesman.infrastructure.http.response.handler;


import br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MapperResourceModelFactory {

    private static final Map<Class<?>, Class<?>> cache = new HashMap<>();

    @PostConstruct
    public void scan() {

        Set<Class<?>> classFounded = ReflectionsHelper.findClassByAnn(ResourceItem.class);

        classFounded.stream().forEach(classItem -> {
            ResourceItem annotation = classItem.getAnnotation(ResourceItem.class);
            cache.put(annotation.modelReference(), classItem);
        });
    }


    public Class<?> getResource(Class<?> modelClass) {
        return cache.get(modelClass);
    }

}
