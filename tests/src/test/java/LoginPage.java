import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseTest{
    private WebDriver driver;

    private final By emailField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[.//span[contains(text(),'Belépés')]]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.mezpro.hu/customer/login");
    }

    public void login(String email, String password) {
        waitAndFind(emailField).clear();
        waitAndFind(emailField).sendKeys(email);

        waitAndFind(passwordField).clear();
        waitAndFind(passwordField).sendKeys(password);

        waitAndFind(loginButton).click();
    }

    public boolean isLoginFormVisible() {
        return waitAndFind(emailField).isDisplayed()
            && waitAndFind(passwordField).isDisplayed()
            && waitAndFind(loginButton).isDisplayed();
    }
}
