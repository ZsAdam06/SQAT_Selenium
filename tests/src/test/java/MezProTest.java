import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class MezProTest extends BaseTest {

    @Test
    public void testLoginFormElementsExist() {
        driver.get("https://www.mezpro.hu/customer/login");

        WebElement emailInput = waitAndFind(By.name("email"));
        WebElement passwordInput = waitAndFind(By.name("password"));
        WebElement loginButton = waitAndFind(By.xpath("//button[.//span[contains(text(),'Belépés')]]"));

        assertTrue(emailInput.isDisplayed());
        assertTrue(passwordInput.isDisplayed());
        assertTrue(loginButton.isDisplayed());
    }

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://www.mezpro.hu/customer/login");

        waitAndFind(By.name("email")).sendKeys("zsoriadam10@gmail.com");
        waitAndFind(By.name("password")).sendKeys("MezPro1234");
        waitAndFind(By.xpath("//button[.//span[contains(text(),'Belépés')]]")).click();

        WebElement accountHeader = waitAndFind(By.xpath("//h1[normalize-space()='Fiókom']"));
        assertTrue(accountHeader.isDisplayed());
    }

    @Test
    public void testEditFirstNameAndSave() {
        // Belépés
        testSuccessfulLogin();

        // Szerkesztés
        driver.get("https://www.mezpro.hu/index.php?route=account/edit");
        WebElement nameField = waitAndFind(By.xpath("//input[@name='lastname']"));
        nameField.clear();
        nameField.sendKeys("Ádám");

        waitAndFind(By.xpath("//button[@type='submit' and normalize-space()='Tovább']")).click();

        WebElement successMessage = waitAndFind(By.cssSelector(".alert-success"));
        assertTrue(successMessage.isDisplayed());
    }

    @Test
    public void testHomePageTitle() {
        driver.get("https://www.mezpro.hu/");
        String expectedTitle = "MezPro - Legújabb focimezek és melegítők elképesztően alacsony árakon";
        assertEquals(expectedTitle, driver.getTitle());
    }

    @Test
    public void testWelcomeTextIsPresent() {
        driver.get("https://www.mezpro.hu/");
        WebElement welcomeText = waitAndFind(By.xpath("//span/strong[contains(text(),'Üdvözlünk')]"));
        assertEquals("Üdvözlünk a MezPro weboldalán!", welcomeText.getText().trim());
    }

    @Test
    public void testHoverRevealsLiverpoolText() {
        driver.get("https://www.mezpro.hu/20182019-es-mezek-160/liverpool-175");

        WebElement image = waitAndFind(By.xpath("//img[contains(@src, 'pool654654.jpg.webp')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(image).perform();

        WebElement hoverText = waitAndFind(By.xpath("//*[contains(text(),'Liverpool hazai 2024-2025 férfi mez')]"));
        assertTrue(hoverText.isDisplayed());
    }
}
