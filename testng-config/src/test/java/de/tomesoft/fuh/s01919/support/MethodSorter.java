package de.tomesoft.fuh.s01919.support;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MethodSorter implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        Comparator<IMethodInstance> comp = (IMethodInstance m1, IMethodInstance m2 ) ->  m1.getMethod().getMethodName().compareTo(m2.getMethod().getMethodName());
        methods.sort(comp.reversed());

        return methods;
    }
}
