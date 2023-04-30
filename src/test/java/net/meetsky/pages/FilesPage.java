package net.meetsky.pages;

import net.meetsky.utilities.ConfigurationReader;
import net.meetsky.utilities.Driver;
import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class FilesPage extends BasePage implements ElementDisplayed {

    @FindBy(linkText = "Favorites")
    public WebElement favouritesPageButton;

    @FindBy(linkText = "Recent")
    public WebElement recentPageButton;

    @FindBy(linkText = "Shares")
    public WebElement sharesPageButton;

    @FindBy(linkText = "Tags")
    public WebElement tagsPageButton;

    @FindBy(id = "fileList")
    public WebElement fileList;

    @FindBy(xpath = "//span[@class='icon icon-starred']")
    public WebElement favouriteIcon;

    @FindBy(xpath = "//tbody[@id='fileList']//tr")
    public List<WebElement> allFilesAndFoldersRowsElements;

    @FindBy(xpath = "//tbody[@id='fileList']//td[@class='filename']//span[@class='innernametext']")
    public List<WebElement> allFilesAndFolders_WEforNames_InFavorites;

    @FindBy(xpath = "//span[.='Add to favorites']")
    public WebElement addToFavoritesButton;

    @FindBy(xpath = "//span[.='Remove from favorites']")
    public WebElement removeFromFavoritesButton;

    public String getFileNameOf(int fileNumber) {
        return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr[" + fileNumber + "]//span[@class='innernametext']")).getText();
    }

    public WebElement get3dotsMenuOf(int fileNumber) {
        return Driver.getDriver().findElement(By.xpath("//tbody[@id='fileList']//tr[" + fileNumber + "]//a[@data-action='menu']"));
    }

    @FindBy(xpath = "//td//div[contains(@style, 'folder')]")
    public WebElement firstFolder;

    @FindBy(xpath = "//a[contains(@class,'button new')]")
    public WebElement addIcon;

    @FindBy(xpath = "//label[@for='file_upload_start']")
    public WebElement uploadFileButton;

    @FindBy(xpath = "//label[@for='checkbox-allnewfiles']")
    public WebElement newFilesCheckbox;

    @FindBy(xpath = "//button[.='Continue']")
    public WebElement continueButton;

    String OperatingSystem = System.getProperty("os.name").toLowerCase();

    public void uploadFile(String filePath) {

        // Get file path as a string -> copy it to the System memory
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        if (OperatingSystem.contains("mac")) {
            // Creating instance of Robot class
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
            }
            // to switch the window focus
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_META); // press cmd
            robot.keyPress(KeyEvent.VK_TAB); // press tab
            robot.keyRelease(KeyEvent.VK_META); // release cmd
            robot.keyRelease(KeyEvent.VK_TAB); // release tab
            // to open GOTO window on MAC
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_META); // press cmd
            robot.keyPress(KeyEvent.VK_SHIFT); // press shift
            robot.keyPress(KeyEvent.VK_G); // press enter
            robot.keyRelease(KeyEvent.VK_META); // release cmd
            robot.keyRelease(KeyEvent.VK_SHIFT); // release shift
            robot.keyRelease(KeyEvent.VK_G); // release enter
            // to paste file
            robot.keyPress(KeyEvent.VK_META); // press cmd
            robot.keyPress(KeyEvent.VK_V); // press V
            robot.keyRelease(KeyEvent.VK_META); // release cmd
            robot.keyRelease(KeyEvent.VK_V); // release V
            robot.keyPress(KeyEvent.VK_ENTER); // press enter
            robot.keyRelease(KeyEvent.VK_ENTER); // release enter
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER); // press enter
            robot.keyRelease(KeyEvent.VK_ENTER); // release enter

            // to handle file duplicate html pop-up
            try {
                newFilesCheckbox.click();
                continueButton.click();
            } catch (RuntimeException e) {
            }
        } else if (OperatingSystem.contains("win")) {
            // Create a robot class object
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
            }

            robot.delay(1000);
            //CTRL+V action then ENTER
            robot.keyPress(KeyEvent.VK_CONTROL); //press CTRL
            robot.keyPress(KeyEvent.VK_V); //press V
            robot.keyRelease(KeyEvent.VK_CONTROL); // release CTRL
            robot.keyRelease(KeyEvent.VK_V); // release V
            robot.keyPress(KeyEvent.VK_ENTER); //Press ENTER
            robot.keyRelease(KeyEvent.VK_ENTER); //Release ENTER
            clickToApproveNewFilePopup();
        }
    } // by using Robot class

    public void clickToApproveNewFilePopup() {
        // to handle file duplicate html pop-up
        try {
            newFilesCheckbox.click();
            continueButton.click();
        } catch (RuntimeException e) {
        }
    }

    /**
     * This method will take a file path as argument and check if it is displayed on the web page.
     * --> takes String parameter
     * <-- returns boolean if displayed or not
     */
    @Override
    public boolean elementIsDisplayed(String text) {
        String textToLocate = "";
        if (OperatingSystem.contains("mac")) {
            // to read the text from file path an locate web element
            textToLocate = text.substring(text.lastIndexOf('/') + 1, text.lastIndexOf('.'));
        } else if (OperatingSystem.contains("win")) {
            textToLocate = text.substring(text.lastIndexOf('\\') + 1, text.lastIndexOf('.'));
        }
        return Driver.getDriver().findElement(By.xpath("//*[.='" + textToLocate + "']")).isDisplayed();
    }

    @FindBy(xpath = "//a[@id='comments']")
    public WebElement commentBtn;

    @FindBy(xpath = "//div[contains(@placeholder,'Write message')]")
    public WebElement commentInputBox;

    @FindBy(xpath = "//input[@class='comment__submit icon-confirm has-tooltip']")
    public WebElement submitArrow;

    @FindBy(xpath = "//span[@class='icon icon-add']")
    public WebElement plusIcon;

    @FindBy(xpath = "//input[@type ='file']")
    public WebElement uploadFile;

    public void addedFileIsDisplayed() {
        String expectedName = ConfigurationReader.getProperty("filePath");
        if (OperatingSystem.contains("mac")) {
            expectedName = expectedName.substring(expectedName.lastIndexOf('/') + 1, expectedName.lastIndexOf('.'));
        }else if (OperatingSystem.contains("win")) {
            expectedName = expectedName.substring(expectedName.lastIndexOf('\\') + 1, expectedName.lastIndexOf('.'));
        }

        java.util.List<WebElement> addedFiles = Driver.getDriver().findElements(By.xpath("//a[@class='name']"));
        List<String> filesTexts = new ArrayList<>();
        for (WebElement eachFile : addedFiles) {
            filesTexts.add(eachFile.getText());
        }

        for (String eachFileName : filesTexts) {
            if (eachFileName.startsWith(expectedName)){
                return;
            }
        }
           Assert.fail("File is not displayed");


       // Assert.assertTrue(filesTexts.contains(expectedName));
    }

    @FindBy(xpath = "//span[contains(@class, 'extra-data')]")
    public List<WebElement> allDeletedFilesFoldersList;

    @FindBy(xpath = "//a[@class='action action-menu permanent']")
    public WebElement threeDots;

    @FindBy(xpath = "//span[contains(@class, 'extra-data')]")
    public WebElement firstFile;

    public void clickActionIcons(String action) {
        String locator = "//li[@class=' action-" + action.toLowerCase() + "-container']/a";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }

    public void clickOnSubModules(String subModule) {
        String locator = "//ul[@class ='with-icon']//a[.='" + subModule + "']";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }

    @FindBy(xpath = "//span[.='New folder']")
    public WebElement NewFolder;

    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement inputNewFolder;

    @FindBy(xpath = "(//input[@type='submit'])[2]")
    public WebElement submit;

    @FindBy(xpath = "//label[@for='select_all_files']/..")
    public WebElement SelectFiles;

    public void checkCommentIsDisplayed(String theComment) {
        WebElement commentWE = Driver.getDriver().findElement(By.xpath("//div[normalize-space()='" + theComment + "']"));
        Assert.assertTrue(commentWE.isDisplayed());
    }

    @FindBy(xpath = "//button[@class='settings-button']")
    public WebElement settingsButton;

    @FindBy(xpath = "//label[@for='showRichWorkspacesToggle']")
    public WebElement firstCheckBox;

    @FindBy(xpath = "//label[@for='recommendationsEnabledToggle']")
    public WebElement secondCheckBox;

    @FindBy(xpath = "//label[@for='showhiddenfilesToggle']")
    public WebElement thirdCheckBox;

    @FindBy(xpath = "//label[@for='cropimagepreviewsToggle']")
    public WebElement fourthCheckBox;

    public boolean checkBoxesIsEnable() {
        return firstCheckBox.isEnabled() && secondCheckBox.isEnabled() && thirdCheckBox.isEnabled() && fourthCheckBox.isEnabled();
    }

    @FindBy(xpath = "//div[@id='app-navigation-vue']//a")
    public List<WebElement> navigations;
}

