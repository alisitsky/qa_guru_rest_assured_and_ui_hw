package config.web;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:${environment}.properties"
})
public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteBrowserUrl")
    @DefaultValue("selenoid.autotests.cloud")
    String remoteBrowserUrl();
}