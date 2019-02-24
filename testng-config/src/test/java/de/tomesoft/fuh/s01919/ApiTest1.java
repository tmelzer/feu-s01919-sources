package de.tomesoft.fuh.s01919;

import org.testng.annotations.*;

import java.lang.reflect.Method;

public class ApiTest1 {
    @BeforeSuite
    void bSuite(){
        System.out.println(ApiTest1.class.getName() + " before suite");
    }

    @BeforeTest
    void bLogTest(){
        System.out.println(ApiTest1.class.getName() + " before logical test");
    }

    @BeforeClass
    void bClass(){
        System.out.println(ApiTest1.class.getName() + " before class");
    }

    @BeforeMethod
    void bMethod(Method m){
        System.out.println(ApiTest1.class.getName() + " before method " + m.getName());
    }

    @Test
    void aone() {
        System.out.println("test aone running");
    }
    @Test
    void atwo() {
        System.out.println("test atwo running");
    }

    @AfterMethod
    void aMethod(Method m){
        System.out.println(ApiTest1.class.getName() + " after method " + m.getName());
    }

    @AfterTest
    void aLogTest(){
        System.out.println(ApiTest1.class.getName() + " after logical test");
    }

    @AfterClass
    void aClass(){
        System.out.println(ApiTest1.class.getName() + " after class");
    }

    @AfterSuite
    void aSuite(){
        System.out.println(ApiTest1.class.getName() + "after suite");
    }
}
