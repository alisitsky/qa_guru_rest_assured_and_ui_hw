package config;

import com.codeborne.selenide.Configuration;
import config.web.WebConfig;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfigurator {

    private final WebConfig webConfig;

    public ProjectConfigurator(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void setWebConfig() {
        RestAssured.baseURI = webConfig.baseUrl();
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = "eager";
        if (webConfig.isRemote()) {
//            Configuration.remote = String.valueOf(webConfig.remoteUrl());
            Configuration.remote = "https://user1:1234@" + System.getProperty("remoteBrowserUrl", "selenoid.autotests.cloud") + "/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}


