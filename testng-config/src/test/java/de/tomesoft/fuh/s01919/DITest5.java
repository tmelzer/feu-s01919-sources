package de.tomesoft.fuh.s01919;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

// Setter DI
public class DITest5 {
    private SampleBean injectedBean;

    DITest5(SampleBean bean) {
        this.injectedBean = bean;
    }

    @Test
    void injected() {

        System.out.println(getClass().getName() + ": constructor injected instanz creation - with factory class");
        assertNotNull(injectedBean);
        System.out.println(injectedBean.getId());
    }
}
