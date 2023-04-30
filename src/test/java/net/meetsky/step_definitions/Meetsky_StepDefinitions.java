package net.meetsky.step_definitions;

import io.cucumber.java.en.*;
import net.meetsky.pages.FilesPage;
import net.meetsky.pages.DashboardPage;
import net.meetsky.pages.LoginPage;
import net.meetsky.pages.SearchPage;
import net.meetsky.utilities.BrowserUtils;
import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Meetsky_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    FilesPage filesPage = new FilesPage();

    String lastFileName;
    String removedFileName;

    LocalDateTime dt = LocalDateTime.now();

    @Given("user on the login page")
    public void user_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user use username {string} and passcode {string}")
    public void user_use_username_and_passcode(String username, String password) {
        loginPage.login(username, password);

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
        Assert.assertEquals(message, loginPage.wrongUsernameMessage.getText());
    }

    @Then("verify the user should be at the {string} page")
    public void verify_the_user_should_be_at_the_dashboard_page(String title) {
        BrowserUtils.verifyTitleContains(title);
    }

    @And("user logins with valid credentials")
    public void the_users_log_in_with_valid_credentials() {
        loginPage.login();
    }

    DashboardPage dashboardPage = new DashboardPage();

    @Then("Verify the user see the following modules:")
    public void verify_the_user_see_the_following_modules(List<String> expectedModules) {
        List<String> actualModules = new ArrayList<>();
        for (WebElement each : dashboardPage.topModules) {
            actualModules.add(each.getAttribute("aria-label"));
        }
        Assert.assertEquals(expectedModules, actualModules);
    }

    @Given("user is on the {string} page")
    public void user_is_on_the_files_page(String pageName) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
        filesPage.clickDashboardModules(pageName);
    }

    @When("user clicks on the three dots icon next to the file with the favorite icon")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_with_the_favorite_icon() {
        if (filesPage.favouriteIcon.isDisplayed()) {
            filesPage.get3dotsMenuOf(1).click();
        }
    }

    @When("user clicks {string} option from file 3dotsMenu")
    public void user_clicks_option(String string) {
        if (string.equals("Download")) {
            Driver.getDriver().findElement(By.xpath("(//span[.='" + string + "'])[2]")).click();
        } else {
            Driver.getDriver().findElement(By.xpath("//span[.='" + string + "']")).click();
        }
    }

    @Then("user shouldn't see the removed file or folder among favorites")
    public void user_shouldn_t_see_the_removed_file_folder_among_favorites() {
        List<String> allFilesAndFoldersInFavourites = new ArrayList<>();
        for (WebElement each : filesPage.allFilesAndFolders_WEforNames_InFavorites) {
            allFilesAndFoldersInFavourites.add(each.getText());
        }
        Assert.assertTrue(!allFilesAndFoldersInFavourites.contains(removedFileName));
    }

    @When("user clicks on the three dots icon next to any file or folder.")
    public void user_clicks_on_the_three_dots_icon_next_to_the_file_or_folder() {
        List<WebElement> allFilesAndFoldersRowsElements = filesPage.allFilesAndFoldersRowsElements;
        int numberOfFiles = allFilesAndFoldersRowsElements.size();
        lastFileName = filesPage.getFileNameOf(numberOfFiles);
        WebElement lastFile3dotsMenu = filesPage.get3dotsMenuOf(numberOfFiles);
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(lastFile3dotsMenu).click().perform();

    }

    @When("user clicks on the Favorites option from the top left menu")
    public void user_clicks_on_the_favorites_option_from_the_top_left_menu() {
        filesPage.favouritesPageButton.click();
    }

    @Then("user should see the added file in the favourites list")
    public void user_should_see_the_added_file_in_the_favourites_list() {
        List<String> allFilesAndFoldersInFavourites = new ArrayList<>();
        for (WebElement each : filesPage.allFilesAndFolders_WEforNames_InFavorites) {
            allFilesAndFoldersInFavourites.add(each.getText());
        }
        Assert.assertTrue(allFilesAndFoldersInFavourites.contains(lastFileName));
    }

    @Given("users on the dashboard page")
    public void user_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the user clicks the {string} module")
    public void the_user_clicks_the_module(String module) {
        dashboardPage.clickDashboardModules(module);
    }

    @When("user choose a folder from the page")
    public void user_choose_a_folder_from_the_page() {
        filesPage.firstFolder.click();
    }

    @When("user clicks the add icon on the top")
    public void user_clicks_the_add_icon_on_the_top() {
        filesPage.plusIcon.click();
    }

    @When("the user uploads a file with the upload file option")
    public void the_user_uploads_a_file_with_the_upload_file_option() {
        // Using JS to click
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", filesPage.uploadFileButton);
        filesPage.uploadFile(""+System.getProperty("user.dir")+"\\src\\test\\resources\\435.jpg");
    }

    @Then("Verify the file is displayed on the page")
    public void verify_the_file_is_displayed_on_the_page() {
        Assert.assertTrue(filesPage.elementIsDisplayed(ConfigurationReader.getProperty("filePath")));
    }

    @Then("verify the page title is {string}")
    public void verify_the_page_title_is(String contacts) {
        BrowserUtils.verifyTitleContains(contacts);
    }

    @Given("User is on the home page")
    public void userIsOnTheHomePage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("user clicks {string} option from top app menu")
    public void userClicksOptionFromTopAppMenu(String moduleName) {
        dashboardPage.clickDashboardModules(moduleName);
    }

    @And("user clicks Comments option")
    public void userClicksCommentsOption() {
        filesPage.commentBtn.click();
    }

    @And("user writs {string} inside the comment input box")
    public void userWritsInsideTheCommentInputBox(String theComment) {
        filesPage.commentInputBox.sendKeys(theComment);
    }

    @And("user clicks submit button to post it")
    public void userClicksSubmitButtonToPostIt() {
        filesPage.submitArrow.click();
    }

    @Then("user should see {string} displayed in the comment section")
    public void userShouldSeeDisplayedInTheCommentSection(String theComment) {
        filesPage.checkCommentIsDisplayed(theComment);
    }

    @Given("user on the dashboard page")
    public void users_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the users click the {string} module")
    public void the_users_click_the_module(String file) {
        filesPage.clickDashboardModules(file);
    }

    @When("the users click the add icon on the top")
    public void the_users_click_the_add_icon_on_the_top() {
        filesPage.plusIcon.click();
    }

    @When("users uploads file with the Upload file option")
    public void users_uploads_file_with_the_option() {
        filesPage.uploadFile.sendKeys(ConfigurationReader.getProperty("filePath"));
        BrowserUtils.sleep(3);

    }

    @Then("verify file is displayed on the page")
    public void verify_file_is_displayed_on_the_page() {
        filesPage.addedFileIsDisplayed();
    }


    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("user clicks the {string} module")
    public void user_clicks_the_module(String fileModule) {
        dashboardPage.clickDashboardModules(fileModule);
    }

    @When("user clicks action-icon from any file on the page")
    public void user_clicks_action_icon_from_any_file_on_the_page() {
        filesPage.threeDots.click();
    }

    @When("user chooses the {string} file or folder option")
    public void user_chooses_the_option(String action) {
        filesPage.clickActionIcons(action);
    }

    @When("user clicks the {string} sub-module on the left side")
    public void user_clicks_the_sub_module_on_the_left_side(String subModule) {
        filesPage.clickOnSubModules(subModule);
    }

    @Then("Verify the deleted file is displayed on the Deleted Files page")
    public void verify_the_deleted_file_is_displayed_on_the_deleted_files_page() {
        String deletedFileName = filesPage.firstFile.getAttribute("title");
        List<String> listOfDeletedFiles = BrowserUtils.listOfWE_to_ListOfString(filesPage.allDeletedFilesFoldersList, "title");
        Assert.assertTrue(listOfDeletedFiles.contains(deletedFileName));
    }

    @Then("user can see following modules")
    public void user_can_see_following_modules(List<String> expectedModules) {
        List<String> allModules = new ArrayList<>();
        for (WebElement eachModules : filesPage.navigations) {
            allModules.add(eachModules.getText());
        }
        Assert.assertEquals(expectedModules, allModules);
    }

    SearchPage searchPage = new SearchPage();

    @Given("user logged in to the app")
    public void user_logged_in_to_the_app() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the user clicks the magnifier icon on the right top")
    public void the_user_clicks_the_magnifier_icon_on_the_right_top() {
        searchPage.magnifierIcon.click();
    }

    @When("users search any existing file or folder or user name")
    public void users_search_any_existing_file_or_folder_or_user_name() {
        searchPage.searchInputBox.sendKeys(ConfigurationReader.getProperty("searchValue"));
    }

    @Then("verify the app displays the expected result option")
    public void verify_the_app_displays_the_expected_result_option() {
        Assert.assertEquals(ConfigurationReader.getProperty("searchValue"), searchPage.displayedResult.getText());
    }

    @Given("the user on the dashboard page")
    public void the_user_on_the_dashboard_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.login();
    }

    @When("the users click {string} module")
    public void the_users_click_module(String fileModule) {
        filesPage.clickDashboardModules(fileModule);
    }

    @When("user clicks Settings on the left bottom corner")
    public void user_clicks_settings_on_the_left_bottom_corner() {
        filesPage.settingsButton.click();
    }

    @Then("the user should be able to click any checkbox")
    public void the_user_should_be_able_to_click_any_checkbox() {
        Assert.assertTrue(filesPage.checkBoxesIsEnable());
    }


    @When("user click new folder")
    public void user_click_new_folder() {
        filesPage.NewFolder.click();
    }

    @When("user write a folder name")
    public void user_write_a_folder_name() {
        filesPage.inputNewFolder.sendKeys("Saja Folder "+ dt);
        BrowserUtils.sleep(2);
    }

    @When("the user click submit icon")
    public void the_user_click_submit_icon() {
        filesPage.submit.click();
        BrowserUtils.sleep(2);
    }

    @Then("Verify the folder is displayed on the page")
    public void verify_the_folder_is_displayed_on_the_page() {
        filesPage.SelectFiles.isDisplayed();
        String expected = "Saja Folder";
        Assert.assertTrue(expected, filesPage.SelectFiles.isDisplayed());
    }

    @Then("verify the page title is as given {string}")
    public void verify_the_page_title_is_as_given(String expectedTitleFilePage) {
        BrowserUtils.verifyTitle(expectedTitleFilePage);
    }

    @When("user click the top-left checkbox of the table")
    public void user_click_the_top_left_checkbox_of_the_table() {
        filesPage.selectAllFilesBox.click();

    }
    @Then("verify all the files are selected")
    public void verify_all_the_files_are_selected() {

        List<String> selectedFiles = new ArrayList<>();

        for (WebElement each : filesPage.allFilesAndFoldersRowsElements){
           selectedFiles.add(each.getAttribute("class"));
        }

        Assert.assertTrue(selectedFiles.contains("selected"));

    }

}
