package tests;

import models.LoginReqBodyModel;
import models.LoginResBodyModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static specs.LoginSpec.loginReqSpec;
import static specs.LoginSpec.loginResSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class RemoveItemTests extends TestBase {

//    Разработать автотест на удаление товара из списка https://demoqa.com/profile
//    Добавить степы, модели и спецификации
//  * Реализовать авторизацию с @WithLogin

    @Test
    public void RemoveItemTest(){
//        +Авторизоваться
        LoginReqBodyModel loginReqBM = new LoginReqBodyModel();
        loginReqBM.setUserName("username42");
        loginReqBM.setPassword("Username42!");

        LoginResBodyModel LoginResBM = step("Make request", () ->
        given(loginReqSpec)
                .body(loginReqBM)
        .when()
                .post("/Account/v1/Login")
        .then()
                .spec(loginResSpec)
                .extract().as(LoginResBodyModel.class));



    }
}
