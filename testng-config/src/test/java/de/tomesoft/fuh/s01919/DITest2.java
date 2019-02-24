package de.tomesoft.fuh.s01919;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

// Setter DI
public class DITest2 {
    private SampleBean injectedBean;

    public void setInjectedBean(SampleBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Test
    void injected() {

        System.out.println(getClass().getName() + ": setter injected instanz creation");
        assertNotNull(injectedBean);
        System.out.println(injectedBean.getId());
    }
}
