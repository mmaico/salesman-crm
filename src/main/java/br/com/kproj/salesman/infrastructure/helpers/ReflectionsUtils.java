package br.com.kproj.salesman.infrastructure.helpers;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class ReflectionsUtils {

    public static void copyProperties(Object dest, Object origin) {
        try {
            BeanUtils.copyProperties(dest, origin);
        } catch (IllegalAccessException | InvocationTargetException e) {}
    }

}
