package guru.qa.tests.api;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import java.util.List;

import static guru.qa.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void filterBooksByPublisher() {
        List<String> ids = given()
                .filter(new AllureRestAssured())
                .header("contentType", "application/json")
                .header("x-token", X_TOKEN)
                .queryParam("filter", "publi_id=" + PUBLISHER_ID)
                .queryParam("include", "author,badge,publishingHouse")
                .when()
                .get("https://book24.ru/api/v1/internal/catalog/product/")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("data.data.flatten().relationships.publishingHouse.data.id");
        assertThat(ids.stream().allMatch(PUBLISHER_ID::equals)).isTrue();
    }

    @Test
    void addToCart() {
        given()
                .filter(new AllureRestAssured())
                .header("contentType", "application/x-www-form-urlencoded")
                .header("x-token", X_TOKEN)
                .formParam("QUANTITY", 1)
                .formParam("PRODUCT_ID", PRODUCT_ID)
                .when()
                .post("https://book24.ru/api/v1/sale/order/basket/")
                .then()
                .log().all()
                .statusCode(200)
                .body("STATUS", is("OK"))
                .body("DATA.BASKET_ITEMS.flatten().PRODUCT_ID", hasItem(PRODUCT_ID));;
    }

    @Test
    void searchSuggestions() {
        given()
                .filter(new AllureRestAssured())
                .header("contentType", "application/json")
                .header("x-token", X_TOKEN)
                .queryParam("q", SEARCH_QUERY)
                .queryParam("limit_suggest", 5)
                .queryParam("limit_product", 5)
                .when()
                .get("https://book24.ru/api/v1/catalog/search/suggests/")
                .then()
                .log().body()
                .statusCode(200)
                .body("STATUS", is("OK"))
                .body("DATA.flatten().title", hasItem(PRODUCT_NAME));
    }
}

