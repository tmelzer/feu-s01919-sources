package de.tomesoft.fuh.s01919;

import de.tomesoft.fuh.s01919.support.MethodSorter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class DependTest1 {
    @Test(groups = {"dep", "func"})
    void methodA(){
        System.out.println("A");
    }
    @Test(groups = "dep", dependsOnMethods = "methodC")
    void methodB(){
        System.out.println("B");
    }
    @Test(groups = {"dep", "int"}, dependsOnGroups = "func")
    void methodC(){
        System.out.println("C");
    }
}
