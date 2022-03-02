package guru.qa.tests.ui;

import guru.qa.tests.TestBase;
import guru.qa.tests.ui.pages.ProductPage;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;

@Tag("ui")
public class ProductPageTests extends TestBase {
    private ProductPage productPage = new ProductPage();

    @BeforeEach
    public void beforeEach() {
        productPage.openProductPage();
    }

    @Test
    @DisplayName("В заголовке страницы содержится название товара")
    void titleTest() {
        productPage.shouldBeProductNameInTitle();
    }

    @Test
    @DisplayName("На странице присутствует название товара")
    void productNameTest() {
        productPage.shouldBeProductNameOnPage();
    }

    @Test
    @DisplayName("Кнопка добавления товара в корзину меняет текст на 'Оформить заказ' после добавления товара в корзину")
    void addToCartButtonTextTest() {
        productPage.addToBag();
        productPage.shouldBeAnotherTextOnAddToBagButton();
    }
}

