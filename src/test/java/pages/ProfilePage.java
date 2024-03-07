package pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static data.TestData.faviconPath;
import static data.TestData.profilePagePath;

public class ProfilePage {

    public ProfilePage openAndAuth(String userId, String token, String expires) {
        open(faviconPath);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", expires));
        open(profilePagePath);
        return this;
    }

    public ProfilePage removeFirstItem() {
        $$("span#delete-record-undefined").first().shouldBe(visible).click();
        $("button#closeSmallModal-ok").shouldBe(visible).click();
        return this;
    }

    public ProfilePage itemIsAbsent() {
        $("div.rt-tr.-odd[role=row]:not(.-padRow)").should(disappear);
        return this;
    }

    public ProfilePage confirmModal(){
        confirm();
        return this;
    }
}