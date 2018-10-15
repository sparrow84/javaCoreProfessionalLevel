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
            System.out.println(meth.getName());
            meth.invoke(null);
            meth = null;
        }


        List<Method> testMethodList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethodList.add(method);
            }
        }

        Method tmpMethod;

        for (boolean basta = false;; basta = false) {
            for (int i = 0; i < testMethodList.size() - 1; i++) {

                int priority1 = testMethodList.get(i).getAnnotation(Test.class).priority();
                int priority2 = testMethodList.get(i + 1).getAnnotation(Test.class).priority();

                if (priority1 < priority2) {
                    tmpMethod = testMethodList.get(i);
                    testMethodList.set(i, testMethodList.get(i+1));
                    testMethodList.set(i+1, tmpMethod);
                    basta = true;
                }
            }

            if (!basta) break;
        }

        for (Method m: testMethodList) {
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
