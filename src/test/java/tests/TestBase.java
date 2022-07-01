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
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = format("https://%s:%s@%s", login, password, System.getProperty("selenoidStand"));

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

