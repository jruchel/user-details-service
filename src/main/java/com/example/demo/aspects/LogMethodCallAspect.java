package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogMethodCallAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Before("@annotation(com.example.demo.aspects.LogMethodCall)")
    public void logParameters(JoinPoint joinPoint) {
        String parameters = getParametersAsString(getParameters(joinPoint));
        logger.info(String.format("%s(%s)", joinPoint.getSignature().getName(), parameters));
    }

    private String getParametersAsString(Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder();
        for (String param : parameters.keySet()) {
            sb.append(param).append(":").append(parameters.get(param)).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    private Map<String, String> getParameters(JoinPoint joinPoint) {
        return getParameters(
                (MethodSignature) (joinPoint.getSignature()),
                listToArray(Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.toList())));
    }

    private String[] listToArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private Map<String, String> getParameters(MethodSignature methodSignature, String[] parameterValues) {
        Map<String, String> params = new HashMap<>();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            String param = parameterNames[i];
            String value = parameterValues[i];
            params.put(param, value);
        }
        return params;
    }


}
