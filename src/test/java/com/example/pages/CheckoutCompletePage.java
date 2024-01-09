package com.example.pages;

import com.example.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{

    @FindBy(css = "#checkout_complete_container")
    public WebElement thankYouText;

    @FindBy(xpath = "//*[@class ='subheader']")
    public WebElement finishText;

    public CheckoutCompletePage(TestContext context) {
        super(context);
    }
}
