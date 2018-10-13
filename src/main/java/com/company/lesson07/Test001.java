package com.company.lesson07;

public class Test001 {


    @BeforeSuite
    public static void test01(){
        //
        System.out.println("Test001.test01");
    }

    @Test
    public static void test02(){
        //
        System.out.println("Test001.test02");
    }

    @AfterSuite
    public static void test03(){
        //
        System.out.println("Test001.test03");
    }
}
