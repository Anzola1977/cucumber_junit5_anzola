package com.example.pages;

import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartContainerPage extends BasePage{

@FindBy(xpath = "//*[@class ='subheader']")
public WebElement yourCart;

@FindBy(xpath = "//*[@class ='btn_action checkout_button']")
public WebElement checkoutButton;

@FindBy(xpath = "//*[@class='cart_item_label']")
public WebElement addedElement;

    public CartContainerPage(TestContext context) {
        super(context);
    }
}
