package com.example.steps;

import com.example.context.TestContext;
import com.example.pages.*;
import com.example.utils.ConfigurationReader;
import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleSteps {
    TestContext context;
    Scenario scenario;

    @Before
    public void beforeMethod(Scenario scenario) {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.js = (JavascriptExecutor) context.driver;
        context.driver.get(ConfigurationReader.get("base_url"));
        this.scenario = scenario;
    }

    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) context.driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @BeforeStep
    public void beforeEveryStep() {
        scenario.log("Current URL: " + context.driver.getCurrentUrl());
    }

    @Given("user enter login page")
    public void user_enter_login_page() {
        scenario.log("Entered login page");
    }

    @Given("user enter main page")
    public void user_enter_main_page() {
        scenario.log("Entered main page");
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        LoginPage lp = new LoginPage(context);
        lp.usernameInput.sendKeys(ConfigurationReader.get("standard_login"));
        lp.passwordInput.sendKeys(ConfigurationReader.get("password"));
    }

    @When("clicks login button")
    public void clicks_login_button() {
        LoginPage lp = new LoginPage(context);
        lp.loginButton.click();
    }

    @Then("main page opens")
    public void main_page_opens() {
        MainPage mp = new MainPage(context);
        assertTrue(mp.firstDescriptionContainer.isDisplayed());
    }

    @Then("it have text in footer {string}")
    public void it_have_text_in_footer(String expectedText) {
        MainPage mp = new MainPage(context);
        assertTrue(mp.footer.getText().contains(expectedText));
    }

    @When("user enters login {word} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {
        LoginPage lp = new LoginPage(context);
        lp.usernameInput.sendKeys(login);
        lp.passwordInput.sendKeys(password);
    }

    @Then("error message contains text {string}")
    public void errorMessageContainsText(String expectedErrorMessage) {
        String actualText = new LoginPage(context).loginMessageContainer.getText();
        scenario.log(String.format("expectedText: %s;\r\n actualText: %s", expectedErrorMessage, actualText));
        assertTrue(actualText.contains(expectedErrorMessage));
    }

    @When("user adds first product to the cart")
    public void user_adds_first_product_to_the_cart() {
        MainPage mp = new MainPage(context);
        mp.addToCartButtons.getFirst().click();
    }

    @Then("amount of products in the cart is {int}")
    public void amount_of_products_in_the_cart_is(Integer amount) {
        assertEquals(Integer.parseInt(new MainPage(context).shoppingCartLink.getText()), amount);
    }

    @When("user click the cart link")
    public void user_clicks_the_cart_link() {
        MainPage mp = new MainPage(context);
        mp.cartContainer.click();
    }

    @Then("cart container page opens")
    public void user_opens_the_cart() {
        assertEquals("Your Cart", new CartContainerPage(context).yourCart.getText());
    }

    @When("user adds the product by name {string}")
    public void user_adds_the_product_by_name(String partOfName) {
        MainPage mp = new MainPage(context);
        for (int i = 0; i < mp.itemsName.size(); i++) {
            if (mp.itemsName.get(i).getText().contains(partOfName)) {
                System.out.println(mp.itemsName.get(i).getText());
                mp.addToCartButtons.get(i).click();
            }
        }
    }

    @Then("cart container page contains the product with name {string}")
    public void cart_container_page_contains_the_product_with_name(String partOfName) {
        CartContainerPage containerPage = new CartContainerPage(context);
        assertTrue(containerPage.addedElement.getText().contains(partOfName));
    }

    @When("user clicks checkout button")
    public void user_clicks_the_checkout_button() {
        CartContainerPage containerPage = new CartContainerPage(context);
        containerPage.checkoutButton.click();
    }

    @Then("checkout page one opens")
    public void checkout_page_one_opens() {
        assertEquals("Checkout: Your Information", new CheckoutPageOne(context).checkoutText.getText());
    }

    @When("user enters first name")
    public void user_enters_first_name() {
        CheckoutPageOne checkoutPageOne = new CheckoutPageOne(context);
        checkoutPageOne.firstNameField.sendKeys("James");
    }

    @And("enters last name")
    public void user_enters_last_name() {
        CheckoutPageOne checkoutPageOne = new CheckoutPageOne(context);
        checkoutPageOne.lastNameField.sendKeys("Bond");
    }

    @And("enters zip code")
    public void user_enters_zip_code() {
        CheckoutPageOne checkoutPageOne = new CheckoutPageOne(context);
        checkoutPageOne.postalCodeField.sendKeys("007");
    }

    @And("clicks continue")
    public void user_clicks_continue_button() {
        CheckoutPageOne checkoutPageOne = new CheckoutPageOne(context);
        checkoutPageOne.continueButton.click();
    }

    @Then("checkout page two opens")
    public void checkout_page_two_opens() {
        assertTrue(new CheckoutPageTwo(context).paymentInformation.isDisplayed());
    }

    @When("user clicks finish button")
    public void user_clicks_finish_button() {
        CheckoutPageTwo checkoutPageTwo = new CheckoutPageTwo(context);
        checkoutPageTwo.finishButton.click();
    }

    @Then("checkout complete page opens")
    public void checkout_page_complete_opens() {
        assertEquals("Finish", new CheckoutCompletePage(context).finishText.getText());
    }

    @And("it have text in header {string}")
    public void it_have_text_in_header(String expectedText) {
        CheckoutCompletePage completePage = new CheckoutCompletePage(context);
        assertTrue(completePage.thankYouText.getText().contains(expectedText));
    }
}
