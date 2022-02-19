package guru.qa.tests.ui;

import guru.qa.tests.TestBase;
import guru.qa.tests.ui.pages.CartPage;
import guru.qa.tests.ui.pages.ProductPage;
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
        cartPage.shouldBeTwoItems();
    }

    @Test
    @DisplayName("Уменьшение количества товара в корзине")
    void decreaseItemsOfProductTest() {
        productPage.openProductPage();
        productPage.addToBag();
        cartPage.openCart();
        cartPage.addOneMoreProductItem();
        cartPage.addOneMoreProductItem();
        cartPage.removeOneProductItem();
        cartPage.shouldBeTwoItems();
    }

    @Test
    @DisplayName("Отображение наименования товара в корзине")
    void productNameTest() {
        productPage.openProductPage();
        productPage.addToBag();
        cartPage.openCart();
        cartPage.shouldBeProductName();
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    void deletingProductTest() {
        productPage.openProductPage();
        productPage.addToBag();
        cartPage.openCart();
        cartPage.deleteProduct();
        cartPage.shouldBeDeletingInfo();
    }
}
