package com.company.lesson07;

import java.lang.reflect.Method;

public class Lesson07 {
    public static void main(String[] args) {
//        new Lesson07().start();
    }

//    public static void start() {
//        Test001.test01();
//        Test001.test02();
//        Test001.test03();
//    }

    public void start(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {

            } else if (method.isAnnotationPresent(Test.class)) {

            } else if (method.isAnnotationPresent(AfterSuite.class)) {

            }
        }
    }
}
