package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static data.TestData.faviconPath;
import static data.TestData.profilePagePath;

public class ProfilePage {

    SelenideElement removeItemButton = $$("span#delete-record-undefined").first(),
                    confirmModalButton = $("button#closeSmallModal-ok"),
                    itemInCart = $("div.rt-tr.-odd[role=row]:not(.-padRow)");

    public ProfilePage openAndAuth(String userId, String token, String expires) {
        open(faviconPath);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", expires));
        open(profilePagePath);
        sleep(1000);
        return this;
    }

    public ProfilePage removeFirstItem() {
        removeItemButton.shouldBe(visible).click();
        confirmModalButton.shouldBe(visible).click();
        return this;
    }

    public ProfilePage itemIsAbsent() {
        itemInCart.should(disappear);
        return this;
    }

    public ProfilePage confirmModal(){
        confirm();
        sleep(1000);
        return this;
    }
}