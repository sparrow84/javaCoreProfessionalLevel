package com.company.lesson07;

public class Test001 {


    @BeforeSuite
    public static void test01(){
        //

    }

    @Test(priority=1)
    public static void test02(){
        //
        System.out.println("Test001.test02");
    }

    @Test
    public static void test04(){
        //
        System.out.println("Test001.test02");
    }

    @AfterSuite
    public static void test03(){
        //
        System.out.println("Test001.test03");
    }
}
