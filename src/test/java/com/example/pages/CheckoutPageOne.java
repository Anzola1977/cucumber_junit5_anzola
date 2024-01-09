package com.example.pages;

import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageOne extends BasePage{

    @FindBy(xpath = "//*[@class ='subheader']")
    public WebElement checkoutText;

    @FindBy(css = "#first-name")
    public WebElement firstNameField;

    @FindBy(css = "#last-name")
    public WebElement lastNameField;

    @FindBy(css = "#postal-code")
    public WebElement postalCodeField;

    @FindBy(xpath = "//*[@class='btn_primary cart_button']")
    public WebElement continueButton;

    public CheckoutPageOne(TestContext context) {
        super(context);
    }
}
