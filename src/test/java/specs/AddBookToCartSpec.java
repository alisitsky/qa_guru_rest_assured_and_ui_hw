package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class AddBookToCartSpec {

    public static RequestSpecification addBookToCartReqSpec = with()
//            .filter(withCustomTemplates())    // todo: починить (падает, если раскомментить)
            .contentType(ContentType.JSON)
            .log().uri()
            .log().headers()
            .log().method()
            .log().body();

    public static ResponseSpecification addBookToCartResSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();
}
