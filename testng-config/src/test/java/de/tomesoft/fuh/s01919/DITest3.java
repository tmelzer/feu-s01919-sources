package de.tomesoft.fuh.s01919;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

// Setter DI
public class DITest3 {
    private SampleBean injectedBean;

    private DITest3(SampleBean bean) {
        this.injectedBean = bean;
    }

    @Factory
    public static Object[] createInstance() {
        return new Object[] {new DITest3(new SampleBean())};
    }

    @Test
    void injected() {

        System.out.println(getClass().getName() + ": constructor injected instanz creation - with static factory");
        assertNotNull(injectedBean);
        System.out.println(injectedBean.getId());
    }
}
