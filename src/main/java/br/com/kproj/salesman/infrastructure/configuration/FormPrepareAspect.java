package br.com.kproj.salesman.infrastructure.configuration;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.view.NormalizeEntityRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static br.com.kproj.salesman.infrastructure.configuration.annotations.ModelAttrubuteOperations.CREATE;
import static br.com.kproj.salesman.infrastructure.configuration.annotations.ModelAttrubuteOperations.UPDATE;

@Component
@Aspect
public class FormPrepareAspect {

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    //TODO fazer funcionar com validador
    //@Around("execution (* br.com.kproj.salesman.*.*.*.*(@org.springframework.web.bind.annotation.ModelAttribute (*),..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method m = ms.getMethod();

        Annotation[][] parameterAnnotations = m.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];

            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == ModelAttribute.class) {
                    String operation = ((ModelAttribute)annotation).value();
                    Object value = args[i];
                    if (CREATE.getOperation().equalsIgnoreCase(operation)) {
                        if (value instanceof Identifiable) {
                            normalizeEntityRequest.doNestedReference((Identifiable)value);
                        }
                    } else if (UPDATE.getOperation().equalsIgnoreCase(operation)) {
                        if (value instanceof Identifiable) {
                            normalizeEntityRequest.addFieldsToUpdate((Identifiable)value);
                        }
                    }
                }
            }
        }

       return joinPoint.proceed();
    }

}

