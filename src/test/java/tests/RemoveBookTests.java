package tests;

import models.AddBookReqBodyModel;
import models.BookItemsListResBodyModel;
import models.LoginReqBodyModel;
import models.LoginResBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static data.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.AddBookToCartSpec.addBookToCartReqSpec;
import static specs.AddBookToCartSpec.addBookToCartResSpec;
import static specs.LoginSpec.loginReqSpec;
import static specs.LoginSpec.loginResSpec;
import static utils.RandomUtils.getModelForBookAdding;
import static utils.RandomUtils.getRandomBookIsbn;

public class RemoveBookTests extends TestBase {


    @Test
    @DisplayName("Remove item from cart")
    public void removeItemTest() {

        LoginReqBodyModel loginReqBM = new LoginReqBodyModel(userName, password);

        LoginResBodyModel loginResBM = step("Login", () ->
                given(loginReqSpec)
                        .body(loginReqBM)
                        .when()
                        .post(loginAPIPath)
                        .then()
                        .spec(loginResSpec)
                        .extract().as(LoginResBodyModel.class));

        BookItemsListResBodyModel bookListResBM = step("Get list of books", () ->
                given(addBookToCartReqSpec)
                        .when()
                        .get(booksAPIPath)
                        .then()
                        .spec(loginResSpec)
                        .extract().as(BookItemsListResBodyModel.class));

        step("Add a book to cart", () -> {
            AddBookReqBodyModel addBookReqBM = getModelForBookAdding(loginResBM.getUserId(), getRandomBookIsbn(bookListResBM));

            given(addBookToCartReqSpec)
                    .header("Authorization", "Bearer " + loginResBM.getToken())
                    .body(addBookReqBM)
                    .when()
                    .post(booksAPIPath)
                    .then()
                    .spec(addBookToCartResSpec);
        });

        step("Remove book from cart", () -> {
            ProfilePage profilePage = new ProfilePage();

            profilePage.openAndAuth(loginResBM.getUserId(),
                                    loginResBM.getToken(),
                                    loginResBM.getExpires())
                        .removeFirstItem()
                        .confirmModal()
                        .itemIsAbsent();
        });
    }
}
