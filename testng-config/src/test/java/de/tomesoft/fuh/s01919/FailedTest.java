package de.tomesoft.fuh.s01919;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FailedTest {
    @Test
    void succeeding() {
    }
    @Test
    void failing() {
        fail("Demo");
    }
}
