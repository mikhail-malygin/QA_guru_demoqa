package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsDemoQaConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.StudentRegistrationFormPage;

import static java.lang.String.format;

public class TestBase {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();
    static CredentialsDemoQaConfig config = ConfigFactory.create(CredentialsDemoQaConfig.class);

    @BeforeAll
    static void beforeAll() {
        String login = config.login();
        String password = config.password();

        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true); // включение потовокого вещание видео
        capabilities.setCapability("enableVideo", true); // запись видео теста, записывается на сервер селеноида

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        Configuration.browserSize = System.getProperty("resolution", "1920х1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = format("https://%s:%s@%s", login, password, System.getProperty("selenoidStand"));

        System.out.println("browser = " + System.getProperty("browser", "chrome"));
        System.out.println("browserVersion = " + System.getProperty("version", "100"));
        System.out.println("browserSize = " + System.getProperty("resolution", "1920х1080"));
        System.out.println("remote = " + format("https://%s:%s@%s", login, password, System.getProperty("selenoidStand")));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

