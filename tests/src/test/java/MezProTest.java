import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class MezProTest extends BaseTest {

    @Test
    public void testLoginFormElementsExist() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        assertTrue(loginPage.isLoginFormVisible());
    }

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.open();

        loginPage.login("zsoriadam10@gmail.com", "MezPro1234");

        WebElement accountHeader = waitAndFind(By.xpath("//h1[normalize-space()='Fiókom']"));
        assertTrue(accountHeader.isDisplayed());
    }

    @Test
    public void testLogout() {
        // Először jelentkezzünk be
        testSuccessfulLogin();

        // Kijelentkezés URL
        String logoutUrl = "https://www.mezpro.hu/index.php?route=account/logout";

        // Kijelentkezés URL meghívása
        driver.get(logoutUrl);

        // Bejelentkezési mező lokátor
        WebElement emailInput = waitAndFind(By.name("email"));

        // Ellenőrizzük, hogy ténylegesen megjelent
        assertTrue(emailInput.isDisplayed());
    }

    @Test
    public void testEditFirstNameAndSave() {
        // Belépés
        testSuccessfulLogin();

        // Példányosítjuk az AccountPage objektumot
        AccountPage accountPage = new AccountPage();

        // Megnyitjuk a szerkesztési oldalt
        accountPage.openEditPage();

        // Módosítjuk a nevet
        accountPage.changeFirstName("Ádám");

        // Beküldjük a változásokat
        accountPage.submitChanges();

        // Ellenőrizzük a sikeres mentés üzenetet
        assertTrue(accountPage.isSuccessMessageVisible());
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
