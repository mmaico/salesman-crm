package br.com.kproj.salesman.infrastructure.helpers;


import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.BackedMirrorList;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionsHelper {

    private static final String GETTER_PREFIX = "get";

    private static final Log logger = LogFactory.getLog(ReflectionsHelper.class);

    public static void copyProperties(Object dest, Object origin) {
        try {
            BeanUtils.copyProperties(dest, origin);
        } catch (IllegalAccessException | InvocationTargetException e) {}
    }


    public static List<Field> getAllFields(Class<?> clazz) {

        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

        Class<?> superclass = clazz.getSuperclass();
        while (superclass != null && superclass != Object.class) {
            fields.addAll(Arrays.asList(superclass.getDeclaredFields()));
            superclass = superclass.getSuperclass();
        }

        return fields;
    }

    public static <T> T newInstance(Class<T> clazz) {

        try {
            return createNewInstance(clazz);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T createNewInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {

        return (T) clazz.newInstance();
    }

    public static Boolean hasAnnotation(Object object, Class clazzAnnotation) {

        if (object == null) {
            return Boolean.FALSE;
        }
        Annotation annotation = new Mirror().on(object.getClass()).reflect()
                .annotation(clazzAnnotation).atClass();

        return annotation != null;
    }

    @SuppressWarnings("rawtypes")
    public static MirrorList<Field> findFields(Object object, final Class annotation) {

        if (object == null) {
            return new BackedMirrorList<Field>(new ArrayList<Field>());
        }

        MirrorList<Field> matching = new Mirror().on(object.getClass()).reflectAll().fields().matching(new Matcher<Field>() {

            @SuppressWarnings("unchecked")
            @Override
            public boolean accepts(Field field) {
                Object obj = new Mirror().on(field).reflect().annotation(annotation);
                return obj != null;
            }
        });

        return matching;
    }

    public Object invokeGetterMethod(Object target, String name) {

        String getterMethodName = name;
        if (!name.startsWith(GETTER_PREFIX)) {
            getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
        }
        Method method = org.springframework.util.ReflectionUtils.findMethod(target.getClass(), getterMethodName);
        if (method == null && !getterMethodName.equals(name)) {
            getterMethodName = name;
            method = org.springframework.util.ReflectionUtils.findMethod(target.getClass(), getterMethodName);
        }
        if (method == null) {
            throw new IllegalArgumentException("Could not find getter method [" + getterMethodName + "] on target ["
                    + target + "]");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Invoking getter method [" + getterMethodName + "] on target [" + target + "]");
        }
        org.springframework.util.ReflectionUtils.makeAccessible(method);
        return org.springframework.util.ReflectionUtils.invokeMethod(method, target);
    }

    public static void setField(Object target, String name, Object value) {
        setField(target, name, value, null);
    }

    public static void setField(Object target, String name, Object value, Class<?> type) {

        Field field = org.springframework.util.ReflectionUtils.findField(target.getClass(), name, type);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + name + "] on target [" + target + "]");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Setting field [" + name + "] on target [" + target + "]");
        }

        org.springframework.util.ReflectionUtils.makeAccessible(field);
        org.springframework.util.ReflectionUtils.setField(field, target, value);
    }
}
