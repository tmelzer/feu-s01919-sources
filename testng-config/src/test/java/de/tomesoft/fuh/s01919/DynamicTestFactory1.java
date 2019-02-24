package de.tomesoft.fuh.s01919;

import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

public class DynamicTestFactory1 {
    @Parameters({"dynamicClasses"})
    @Factory
    Object[] buildSampleTests(String testClassNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] names = testClassNames.split(";");
        Object[] result = new Object[names.length];
        for(int i = 0; i < names.length; i++) {
            result[i] = Class.forName(names[i]).newInstance();
        }
        return result;
    }
}
