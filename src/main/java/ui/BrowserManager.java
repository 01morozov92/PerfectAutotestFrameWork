package ui;

import com.codeborne.selenide.*;
import com.codeborne.selenide.commands.Commands;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import ui.commands.SetAlias;
import ui.commands.button.ClickButton;
import ui.commands.element.*;
import ui.commands.field.ClickField;
import ui.commands.field.FillField;

public class BrowserManager {

    private static final long ELEMENTSTIMEOUTMILLIS = 5000;
    private static final long PAGELOADTIMEOUTMILLIS = 6000;
    private static final String CHROME = Browsers.CHROME;
    private static final String DOWNLOADFOLDER = "target/downloads";

    static {
        setUpCommands();
        configSelenide(ELEMENTSTIMEOUTMILLIS);
    }

    private static void setUpCommands() {
        Commands.getInstance().add("clickElement", new ClickElement());
        Commands.getInstance().add("setAlias", new SetAlias());
        Commands.getInstance().add("checkElementAppear", new CheckElementAppear());
        Commands.getInstance().add("fillField", new FillField());
        Commands.getInstance().add("getElementText", new GetElementText());
        Commands.getInstance().add("clickField", new ClickField());
        Commands.getInstance().add("clickButton", new ClickButton());
        Commands.getInstance().add("checkElementDisappear", new CheckElementDisappear());
        Commands.getInstance().add("checkElementText", new CheckElementText());
        Commands.getInstance().add("checkElementMatchText", new CheckElementMatchText());
    }

    @Step
    public static void openUrl(String url) {
        Selenide.open(url);
    }

    private static void configSelenide(long timeout) {
        Configuration.baseUrl = "https://github.com/";
        Configuration.timeout = timeout;
        Configuration.pageLoadTimeout = PAGELOADTIMEOUTMILLIS;
        Configuration.browser = CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.downloadsFolder = DOWNLOADFOLDER;
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
