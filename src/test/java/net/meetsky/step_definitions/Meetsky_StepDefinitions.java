package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.LoginPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;

public class Meetsky_StepDefinitions {

    LoginPage loginPage = new LoginPage();

    @Given("user on the login page")
    public void user_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user use username {string} and passcode {string}")
    public void user_use_username_and_passcode(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user click the login button")
    public void user_click_the_login_button() {
        loginPage.loginButton.click();
    }
    @When("user enter invalid {string} and {string}")
    public void user_enter_invalid_and(String username, String password) {
        loginPage.userNameInputBox.sendKeys(username);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @Then("verify {string} message should be displayed")
    public void verify_message_should_be_displayed(String message) {
        Assert.assertEquals(message,loginPage.wrongUsernameMessage.getText());
    }

    @Then("verify the user should be at the {string} page")
    public void verify_the_user_should_be_at_the_dashboard_page(String title) {
        BrowserUtils.verifyTitleContains(title);
    }

}