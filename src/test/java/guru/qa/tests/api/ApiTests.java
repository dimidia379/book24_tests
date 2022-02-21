package guru.qa.tests.api;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static guru.qa.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @Tag("api")
    @Test
    @DisplayName("Фильтр книг по издательству выдает книги только этого издательства")
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

    @Tag("api")
    @Test
    @DisplayName("Фильтр книг по печати по требованию выдает только книги, печатаемые по требованию")
    void filterBooksByPrintOnDemand() {
        List<Boolean> isDemandPrint = given()
                .filter(new AllureRestAssured())
                .header("contentType", "application/json")
                .header("x-token", X_TOKEN)
                .queryParam("filter", "demand_print=1")
                //.queryParam("include", "author,badge,publishingHouse")
                .when()
                .get("https://book24.ru/api/v1/internal/catalog/product/")
                .then()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("data.data.flatten().attributes.isDemandPrint");
        assertThat(isDemandPrint.stream().allMatch(x ->x==true)).isTrue();
    }

    @Tag("api")
    @Test
    @DisplayName("В списке предложений, выдаваемых при вводе поискового запроса, " +
            "присутствует соответствующий запросу товар")
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

    @Tag("api")
    @Test
    @DisplayName("Товар добавляется в корзину")
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
}

