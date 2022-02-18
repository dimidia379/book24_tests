package guru.qa.tests.ui.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static guru.qa.tests.TestData.*;
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

    @Step("Проверяем количество экземпляров товара")
    public void shouldBeTwoItemsInCart(int number) {
        $(".product-cart__counter-total").text().startsWith(number + " x");
    }

    @Step("Нажимаем на '-', чтобы сократить на 1 число экземпляров товара в корзине")
    public void removeOneProductItem() {
        $$(".counter__button").get(0).click();
    }
}
