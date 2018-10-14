package com.company.lesson07;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Lesson07 {

    public void start(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();

        Method meth = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (meth == null) {
                    meth = method;
                } else {
                    throw new RuntimeException();
                }
            }
        }

        if (meth != null) {
            meth.invoke(null);
            System.out.println(meth.getName());
            meth = null;
        }


        List<Method> methodList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                methodList.add(method);
            }
        }

        Method tmpMethod;

        for (boolean basta = false;; basta = false) {

            for (int i = 0; i < methodList.size() - 1; i++) {

                int priority1 = methodList.get(i).getAnnotation(Test.class).priority();
                int priority2 = methodList.get(i + 1).getAnnotation(Test.class).priority();

                if (priority1 < priority2) {
                    tmpMethod = methodList.get(i);
                    methodList.set(i, methodList.get(i+1));
                    methodList.set(i+1, tmpMethod);
                    basta = true;
                }
            }

            if (!basta) break;
        }

        for (Method m: methodList) {
            System.out.println(m.getName());
            m.invoke(null);
        }









        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (meth == null) {
                    meth = method;
                } else {
                    throw new RuntimeException();
                }
            }
        }

        if (meth != null) {
            meth.invoke(null);
            System.out.println(meth.getName());
            meth = null;
        }

    }

}
