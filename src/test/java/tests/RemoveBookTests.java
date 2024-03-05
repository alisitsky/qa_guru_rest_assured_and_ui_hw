package tests;

import io.restassured.http.ContentType;
import models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static specs.LoginSpec.*;
import static specs.AddBookToCartSpec.*;


public class RemoveBookTests extends TestBase {

//    Разработать автотест на удаление товара из списка https://demoqa.com/profile
//      - добавить степы
//      - модели
//      - спецификации
//  * Реализовать авторизацию с @WithLogin

    @Test
    public void RemoveItemTest(){

        //  Авторизоваться
        LoginReqBodyModel loginReqBM = new LoginReqBodyModel();
        loginReqBM.setUserName("username42");
        loginReqBM.setPassword("Username42!");

        LoginResBodyModel loginResBM = step("Login", () ->
            given(loginReqSpec)
                    .body(loginReqBM)
            .when()
                    .post("/Account/v1/Login")
            .then()
                    .spec(loginResSpec)
                    .extract().as(LoginResBodyModel.class));

        // получить список книг и сложить в объект модели
        BookItemsListResBodyModel bookListResBM =
                step("Get list of books", () ->
                // todo: положить в given(reqSpec)
                with()
                .contentType(ContentType.JSON)
                .log().uri()
                .log().headers()
                .log().method()
                .log().body()

                        .when()
                        .get("BookStore/v1/Books")
                        .then()
                        .spec(loginResSpec) //  todo: заменить на свою спеку
                        .extract().as(BookItemsListResBodyModel.class));


        // todo: убрать (блок проверки)
        Random random = new Random();
        int randomIndex = random.nextInt(bookListResBM.getBooks().size());
        Books randomBook = bookListResBM.getBooks().get(randomIndex);
        System.out.println("randomBook: " + randomBook);



        // Создать и заполнить объект IsbnModel - в него положить рандомный isbn из bookListResBM
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookListResBM.getBooks().get(randomIndex).getIsbn()); // получить рандомный isbn из bookListResBM

        // создать и заполнить объект List<IsbnModel>
        String isbn = bookListResBM.getBooks().get(randomIndex).getIsbn();
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbnModel);

        // Создать и заполнить объект addBookReqBM (чтобы потом сделать POST на добавление книги в корзину)
        AddBookReqBodyModel addBookReqBM = new AddBookReqBodyModel();
        addBookReqBM.setUserId(loginResBM.getUserId());
        addBookReqBM.setCollectionOfIsbns(isbnList);    // сюда передать объект List<IsbnModel> (где IsbnModel - это класс с полем "String isbn;")

        System.out.println("addBookReqBM: " + addBookReqBM);


        // Добавить книгу в корзину
        step("Add a book to cart", () ->
                given(addBookToCartReqSpec)
                        .header("Authorization", "Bearer " + loginResBM.getToken())
                        .body(addBookReqBM)
                .when()
                        .post("BookStore/v1/Books")
                .then()
                        .spec(addBookToCartResSpec)
        );

        // Удалить книгу из корзины в UI

    }
}
