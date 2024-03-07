package utils;

import com.codeborne.selenide.WebDriverRunner;
import models.AddBookReqBodyModel;
import models.BookItemsListResBodyModel;
import models.IsbnModel;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class RandomUtils {

    public static String getRandomBookIsbn(BookItemsListResBodyModel bookListResBM) {
        Random random = new Random();
        int randomIndex = random.nextInt(bookListResBM.getBooks().size());
        String randomIsbn =  bookListResBM.getBooks().get(randomIndex).getIsbn();
        return randomIsbn;
    }

    public static AddBookReqBodyModel getModelForBookAdding (String userId, String isbn) {
        IsbnModel isbnModel = new IsbnModel();
        List<IsbnModel> isbnList = new ArrayList<>();
        AddBookReqBodyModel addBookReqBM = new AddBookReqBodyModel();

        isbnModel.setIsbn(isbn);
        isbnList.add(isbnModel);
        addBookReqBM.setUserId(userId);
        addBookReqBM.setCollectionOfIsbns(isbnList);

        return addBookReqBM;
    }

    public static void auth(String urlPath, String userId, String token, String expires) {
        open("https://demoqa.com/favicon.ico");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", userId));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", expires));


    }
}
