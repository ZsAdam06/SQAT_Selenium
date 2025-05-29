import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BaseTest {
    private WebDriver driver;

    private final By header = By.xpath("//h1[normalize-space()='Fiókom']");
    private final By firstnameField = By.xpath("//input[@name='lastname']");
    private final By continueButton = By.xpath("//button[@type='submit' and normalize-space()='Tovább']");
    private final By successMessage = By.cssSelector(".alert-success");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHeaderVisible() {
        return waitAndFind(header).isDisplayed();
    }

    public void openEditPage() {
        driver.get("https://www.mezpro.hu/index.php?route=account/edit");
    }

    public void changeFirstName(String name) {
        WebElement field = waitAndFind(firstnameField);
        field.clear();
        field.sendKeys(name);
    }

    public void submitChanges() {
        waitAndFind(continueButton).click();
    }

    public boolean isSuccessMessageVisible() {
        return waitAndFind(successMessage).isDisplayed();
    }
}
