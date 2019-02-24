package de.tomesoft.fuh.s01919;

import org.testng.annotations.Test;

public class DITest1 {
    @Test
    void noparam() {
        System.out.println(getClass().getName() + ": noparam instanz creation");
    }
}
