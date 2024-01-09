package com.example.pages;
import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {
    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(xpath = "//*[@class='inventory_item_name']")
    public List<WebElement> itemsName;

    @FindBy(css = "#shopping_cart_container")
    public WebElement cartContainer;

    @FindBy(css = ".inventory_item_desc")
    public WebElement firstDescriptionContainer;

    @FindBy(css = ".footer_copy")
    public WebElement footer;

    @FindBy(css = ".shopping_cart_link")
    public WebElement shoppingCartLink;

    public MainPage(TestContext context) {
        super(context);
    }
}
