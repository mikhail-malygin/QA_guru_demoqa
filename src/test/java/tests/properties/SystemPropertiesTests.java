package tests.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void someTest1() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // null
    }

    @Test
    void someTest2() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");
        System.out.println(browser); // chrome
    }

    @Test
    void someTest3() {
        String browser = System.getProperty("browser", "yandex");
        System.out.println(browser); // yandex
    }

    @Test
    void someTest4() {
        System.setProperty("browser", "firefox");
        String browser = System.getProperty("browser", "chrome");
        System.out.println(browser); // firefox
    }

    @Test
    void someTest5() {
        System.setProperty("value", "any text");
        String browser = System.getProperty("value", "some text");
        System.out.println(browser);
    }

    @Test
    @Tag("test6")
    void someTest6() {
        String browser = System.getProperty("browser", "firefox");
        String version = System.getProperty("version", "101");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        System.out.println(browser); // firefox
        System.out.println(version); // 101
        System.out.println(browserSize); // 1920x1080

        /*
        launch test in terminal: gradle clean properties_test6
         */
    }

    @Test
    @Tag("hello")
    void someTest7() {
        System.out.println("Hello " + System.getProperty("anyText"));

        // launch test in terminal: gradle clean hello_test -DanyText=world!
    }
}
