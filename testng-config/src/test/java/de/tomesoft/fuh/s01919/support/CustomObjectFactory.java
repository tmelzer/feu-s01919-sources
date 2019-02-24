package de.tomesoft.fuh.s01919.support;

import de.tomesoft.fuh.s01919.DITest2;
import de.tomesoft.fuh.s01919.SampleBean;
import org.testng.IObjectFactory;
import org.testng.internal.ObjectFactoryImpl;

import java.lang.reflect.Constructor;

public class CustomObjectFactory implements IObjectFactory {
    private ObjectFactoryImpl wrappedFactory = new ObjectFactoryImpl();

    @Override
    public Object newInstance(Constructor constructor, Object... objects) {
        String name = constructor.getDeclaringClass().getSimpleName();
        Object o = wrappedFactory.newInstance(constructor, objects);
        //if (constructor.getDeclaringClass() == DITest2.class) {
        if (o instanceof DITest2) {
            ((DITest2)o).setInjectedBean(new SampleBean());
        }
        System.out.println("Created test " + o.getClass());
        System.out.println("");
        return o;
    }
}
