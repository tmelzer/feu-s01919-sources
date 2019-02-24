package de.tomesoft.fuh.s01919;

import static org.testng.Assert.*;

import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class ParamTest {
    @DataProvider(name = "valueProvider")
    private Object[][] generateSampleBeansInline() {
        return new Object[][] {
            {new SampleBean("1", 1.5, 2.0)}, {new SampleBean("2", -1.0, 1.0)}, {new SampleBean()}
        };
    }

    // Zugriff auf parameter native über dependency injection
    @DataProvider(name = "valueWithParamProvider")
    private Object[][] generateSampleBeansInlineWithParams(ITestContext context) {
        String freq = context.getCurrentXmlTest().getParameter("freq");
        System.out.println("valueProvider: " + freq);
        return new Object[][] {
                {new SampleBean("1", 1.5, 2.0)}, {new SampleBean("2", -1.0, 1.0)}, {new SampleBean()}
        };
    }

    @DataProvider(name = "fileProvider")
    private Object[][] generateSampleBeansFile() throws IOException {
        String propsName = "sample-beans.properties";
        Properties sampleProps = new Properties();
        InputStream propStream = getClass().getClassLoader().getResourceAsStream(propsName);
        sampleProps.load(propStream);

        ArrayList<SampleBean> beans = new ArrayList<>();
        Enumeration<?> propsEnum = sampleProps.propertyNames();
        while (propsEnum.hasMoreElements()) {
            String key = (String) propsEnum.nextElement();
            Object value = sampleProps.getProperty(key);

            SampleBean bean = new SampleBean();
            if (value != null && !((String) value).equals("")) {
                String[] params = ((String) value).split(",");
                bean.setId(key);
                bean.setX(Double.parseDouble(params[0]));
                bean.setY(Double.parseDouble(params[1]));
            }
            beans.add(bean);
        }

        Object[][] result = new Object[beans.size()][];
        for (int i = 0; i < beans.size(); i++) {
            result[i] = new Object[1];
            result[i][0] = beans.get(i);
        }
        return result;
    }

    @Parameters({"myName"})
    @Test
    public void param1(String myName) {
        System.out.println(myName);
    }

    @Parameters({"freq"})
    @Test()
    public void param2(int freq) {
        System.out.println(freq * 10);
    }

    // complex object
    @Test(dataProvider = "valueProvider")
    public void param3(SampleBean bean) {
        System.out.println(bean.getId());
        assertTrue(bean.getX() >= 0 && bean.getY() >= 0, "Koordinaten sind positiv");
    }

    @Test(dataProvider = "valueWithParamProvider")
    public void param5(SampleBean bean) {
        System.out.println(bean.getId());
        assertTrue(bean.getX() >= 0 && bean.getY() >= 0, "Koordinaten sind positiv");
    }

    // property file
    @Test(dataProvider = "fileProvider")
    public void param4(SampleBean bean) {
        System.out.println(bean.getId());
        assertTrue(bean.getX() >= 0 && bean.getY() >= 0, "Koordinaten sind positiv");
    }
}
