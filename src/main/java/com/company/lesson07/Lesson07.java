package com.company.lesson07;

import java.lang.reflect.Method;

public class Lesson07 {

    public void start(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {

            } else if (method.isAnnotationPresent(Test.class)) {
                method.invoke(null);

                System.out.println(method.getName());
            } else if (method.isAnnotationPresent(AfterSuite.class)) {

            }
        }
    }

}
