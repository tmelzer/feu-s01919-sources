package de.tomesoft.fuh.s01919;

import de.tomesoft.fuh.s01919.support.MethodSorter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({MethodSorter.class})
public class DependTest2 {
    @Test(groups = {"dep", "func"})
    void methodY(){
        System.out.println("Y");
    }
    @Test(groups = "dep", dependsOnMethods = "methodC")
    void methodB(){
        System.out.println("B");
    }
    @Test(groups = "dep", dependsOnGroups = "int")
    void methodC(){
        System.out.println("C");
    }
    @Test(groups = {"dep", "int"}, dependsOnGroups = "func")
    void methodX(){
        System.out.println("X");
    }
    @Test(groups = {"other"})
    void methodOtherA() {System.out.println("OA");}
    @Test(groups = {"other"})
    void methodOtherB() {System.out.println("OB");}
    @Test(groups = {"other"})
    void methodOtherC() {System.out.println("OC");}
}
