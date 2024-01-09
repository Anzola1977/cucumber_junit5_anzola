package com.example.pages;

import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageTwo extends BasePage{

@FindBy(xpath = "//*[text()='Payment Information:']")
public WebElement paymentInformation;

@FindBy(xpath = "//*[@class='btn_action cart_button']")
public WebElement finishButton;
    public CheckoutPageTwo(TestContext context) {
        super(context);
    }
}
