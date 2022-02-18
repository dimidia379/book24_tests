package guru.qa.tests.ui;

import guru.qa.tests.TestBase;
import guru.qa.tests.ui.pages.ProductPage;
import guru.qa.tests.ui.pages.CartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartPageTests extends TestBase {

    private CartPage cartPage  = new CartPage();
    private ProductPage productPage = new ProductPage();

    @BeforeEach
    public void beforeEach() {
        cartPage.openCart();
    }

    @Test
    @DisplayName("В заголовке страницы должно содержаться 'Корзина'")
    void titleTest() {
        cartPage.shouldBeCartInTitle();
    }

    @Test
    @DisplayName("Увеличение количества товара в корзине")
    void increaseItemsOfProductTest() {
        productPage.openProductPage();
        productPage.addToBag();
        cartPage.openCart();
        cartPage.addOneMoreProductItem();
        cartPage.shouldBeTwoItemsInCart(2);
    }

    @Test
    @DisplayName("Уменьшение количества товара в корзине")
    void decreaseItemsOfProductTest() {
        productPage.openProductPage();
        productPage.addToBag();
        cartPage.openCart();
        cartPage.addOneMoreProductItem();
        cartPage.removeOneProductItem();
        cartPage.shouldBeTwoItemsInCart(1);
    }
}
