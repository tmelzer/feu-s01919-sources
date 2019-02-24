package de.tomesoft.fuh.s01919;

import de.tomesoft.fuh.s01919.support.TestMonitorListener;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({TestMonitorListener.class})
public class ApiTest2 {
    @BeforeSuite
    void bSuite(){
        System.out.println(ApiTest2.class.getName() + " before suite");
    }

    @BeforeTest
    void bLogTest(){
        System.out.println(ApiTest2.class.getName() + " before logical test");
    }

    @BeforeClass
    void bClass(){
        System.out.println(ApiTest2.class.getName() + " before class");
    }

    @BeforeMethod
    void bMethod(Method m){
        System.out.println(ApiTest2.class.getName() + " before method " + m.getName());
    }

    @Test
    void bone() {
        System.out.println("test bone running");
    }
    @Test
    void btwo() {
        System.out.println("test btwo running");
    }

    @AfterMethod
    void aMethod(Method m){
        System.out.println(ApiTest2.class.getName() + " after method " + m.getName());
    }

    @AfterTest
    void aLogTest(){
        System.out.println(ApiTest2.class.getName() + " after logical test");
    }

    @AfterClass
    void aClass(){
        System.out.println(ApiTest2.class.getName() + " after class");
    }

    @AfterSuite
    void aSuite(){
        System.out.println(ApiTest2.class.getName() + "after suite");
    }
}
