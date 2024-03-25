package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfigurator;
import config.web.WebConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static utils.AttachUtils.*;

public class TestBase {

//    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        ProjectConfigurator projectConfigurator = new ProjectConfigurator(webConfig);
//        projectConfigurator.setWebConfig();

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.remote = "https://user1:1234@" + System.getProperty("remoteBrowserUrl", "selenoid.autotests.cloud") + "/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void attachments() {
        attachScreenshotAs("Last Step Screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
    }

    @AfterEach
    void afterEach() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}
