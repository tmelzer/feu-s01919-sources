package de.tomesoft.fuh.s01919;

import org.testng.annotations.Factory;

public class DITest5Factory {
    @Factory
    public Object[] createInstance() {
        Object o = new DITest5(new SampleBean());
        System.out.println("Created test " + o.getClass());

        return new Object[] {o};
    }
}
