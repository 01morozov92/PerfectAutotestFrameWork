package config;

import com.codeborne.selenide.*;
import com.codeborne.selenide.commands.Commands;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import ui.commands.*;
import ui.commands.button.*;
import ui.commands.checkbox._GetCheckBoxValue;
import ui.commands.element.*;
import ui.commands.field.*;

public class BrowserManager {

    private static final long ELEMENTS_TIMEOUT_MILLIS = 5000;
    private static final long PAGE_LOAD_TIMEOUT_MILLIS = 6000;
    private static final String CHROME = Browsers.CHROME;
    private static final String DOWNLOAD_FOLDER = "target/downloads";

    static {
        setUpCommands();
        configSelenide(ELEMENTS_TIMEOUT_MILLIS);
    }

    private static void setUpCommands() {
        Commands.getInstance().add("_click", new _ClickElement());
        Commands.getInstance().add("_setAlias", new _SetAlias());
        Commands.getInstance().add("_checkElementAppear", new _CheckElementAppear());
        Commands.getInstance().add("_fillField", new _FillField());
        Commands.getInstance().add("_getElementText", new _GetElementText());
        Commands.getInstance().add("_getElementOwnText", new _GetElementOwnText());
        Commands.getInstance().add("_getElementValue", new _GetElementValue());
        Commands.getInstance().add("_getElementAttributeValue", new _GetElementAttributeValue());
        Commands.getInstance().add("_checkElementAttributeValue", new _CheckElementAttributeValue());
        Commands.getInstance().add("_checkElementCssValue", new _CheckElementCssValue());
        Commands.getInstance().add("_checkElementAttributeMatching", new _CheckElementAttributeMatching());
        Commands.getInstance().add("_checkElementAttributeNotMatching", new _CheckElementAttributeNotMatching());
        Commands.getInstance().add("_checkFieldEnabled", new _CheckFieldEnabled());
        Commands.getInstance().add("_checkFieldDisabled", new _CheckFieldDisabled());
        Commands.getInstance().add("_checkFieldReadOnly", new _CheckFieldReadOnly());
        Commands.getInstance().add("_checkFieldAppear", new _CheckFieldAppear());
        Commands.getInstance().add("_clickField", new _ClickField());
        Commands.getInstance().add("_clickButton", new _ClickButton());
        Commands.getInstance().add("_checkButtonEnabled", new _CheckButtonEnabled());
        Commands.getInstance().add("_checkButtonDisabled", new _CheckButtonDisabled());
        Commands.getInstance().add("_checkButtonAppear", new _CheckButtonAppear());
        Commands.getInstance().add("_checkButtonDisappear", new _CheckButtonDisappear());
        Commands.getInstance().add("_checkElementDisappear", new _CheckElementDisappear());
        Commands.getInstance().add("_checkElementText", new _CheckElementText());
        Commands.getInstance().add("_checkElementValue", new _CheckElementValue());
        Commands.getInstance().add("_checkElementMatchText", new _CheckElementMatchText());
        Commands.getInstance().add("_checkElementHref", new _CheckElementHref());
        Commands.getInstance().add("_clickElementLink", new _ClickElementLink());
        Commands.getInstance().add("_getCheckBoxValue", new _GetCheckBoxValue());
        Commands.getInstance().add("_hoverElement", new _HoverElement());
        Commands.getInstance().add("_uploadFile", new _UploadFile());
        Commands.getInstance().add("_checkElementSelect", new _CheckElementSelect());
    }

    @Step
    public static void openUrl(String url) {
        Selenide.open(url);
    }

    private static void configSelenide(long timeout) {
        Configuration.baseUrl = "https://github.com/";
        Configuration.timeout = timeout;
        Configuration.pageLoadTimeout = PAGE_LOAD_TIMEOUT_MILLIS;
        Configuration.browser = CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.downloadsFolder = DOWNLOAD_FOLDER;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
    }

    public static void scrollPageToTop() {
        executeJS("window.scrollTo(0, 0);");
    }

    public static void scrollPageToBottom() {
        executeJS("window.scrollTo(0, document.body.clientHeight);");
    }

    public static void scrollUp(int pixels) {
        executeJS(String.format("window.scrollBy(0, -%d);", pixels));
    }

    public static void scrollDown(int pixels) {
        executeJS(String.format("window.scrollBy(0, %d);", pixels));
    }

    @Step
    public static void executeJS(String js) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(js);
    }
}
