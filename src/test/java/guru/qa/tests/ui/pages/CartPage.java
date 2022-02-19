package guru.qa.tests.ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static guru.qa.tests.TestData.CART_URL;
import static guru.qa.tests.TestData.PRODUCT_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage {
    @Step("Открываем страницу избранного")
    public void openCart() {
        open(CART_URL);
    }

    @Step("Проверяем наименование заголовка страницы")
    public void shouldBeCartInTitle() {
        assertThat(title()).isEqualTo("Корзина");
    }

    @Step("Нажимаем на '+', чтобы прибавить еще один экземпляр товара")
    public void addOneMoreProductItem() {
        $$(".counter__button").get(1).click();
    }

    @Step("Нажимаем на '-', чтобы сократить на 1 число экземпляров товара в корзине")
    public void removeOneProductItem() {
        $$(".counter__button").get(0).click();
    }

    @Step("Проверяем, что в корзине 2 экземпляра товара")
    public void shouldBeTwoItems() {
        $(".product-cart__counter-total").text().startsWith("2 x");
    }

    @Step("Проверяем название товара в корзине")
    public void shouldBeProductName() {
        $(".product-cart__name-link").shouldHave(Condition.text(PRODUCT_NAME));
    }

    @Step("Удаляем товар из корзины")
    public void deleteProduct() {
        $$(".delete-button__caption").get(2).click();
    }

    @Step("Проверяем, на месте удаленного товара отображается сообщени об удалении")
    public void shouldBeDeletingInfo() {
        $(".cart-page__product").text().startsWith("Вы удалили из корзины");
    }


}
