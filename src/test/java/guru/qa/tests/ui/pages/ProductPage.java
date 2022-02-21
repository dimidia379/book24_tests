package guru.qa.tests.ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.tests.TestData.PRODUCT_NAME;
import static guru.qa.tests.TestData.PRODUCT_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage {

    @Step("Открываем страницу товара")
    public void openProductPage() {
        open(PRODUCT_URL);
    }

    @Step("Проверяем название товара в заголовке страницы")
    public void shouldBeProductNameInTitle() {
        assertThat(title()).startsWith(PRODUCT_NAME);
    }

    @Step("Проверяем название товара на странице")
    public void shouldBeProductNameOnPage() {
        $(byClassName("product-detail-page__title")).shouldHave(Condition.text(PRODUCT_NAME));
    }

    @Step("Нажимаем на кнопку добавления в корзину")
    public void addToBag() {
        $(byClassName("product-main-button")).click();
    }

    @Step("Проверяем, что текст на кнопке добавления в корзину стал 'Оформить заказ'")
    public void shouldBeAnotherTextOnAddToBagButton() {
        $(".product-main-button .b24-btn__content").shouldHave(Condition.text("Оформить заказ"), Duration.ofSeconds(5));
    }
}
