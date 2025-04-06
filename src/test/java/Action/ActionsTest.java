package Action;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionsTest extends BaseTest {
    @Test
    public void hoverTest() {

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement img2 = driver.findElement(By.xpath("(//img)[2]"));
        //TODO: find a better way for wait
        actions.moveToElement(img2).pause(1000).perform();

        WebElement view_profile1 = driver.findElement(By.linkText("View profile"));

        System.out.println("view_profile1.getText() = " + view_profile1.getText());

        assertTrue(view_profile1.isDisplayed(), "verify that element is DISPLAYED");
    }

    @Test
    public void doubleClickTest() {
        driver.get("https://demoqa.com/buttons");

        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickButton).click().perform();

        assertEquals("You have done a double click", driver.findElement(By.id("doubleClickMessage")).getText());
    }

    @Test
    public void contextClickTest() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement contextClickArea = driver.findElement(By.id("hot-spot"));
        actions.moveToElement(contextClickArea).contextClick().perform();
        //actions.contextClick(contextClickArea).perform();
        assertEquals("You selected a context menu", driver.switchTo().alert().getText());
    }

    @Test
    public void dragAndDropTest() {

        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.cssSelector("#draggable"));
        WebElement target = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));

        actions.dragAndDrop(source, target).perform();

        WebElement verifyMessage = driver.findElement(By.xpath("//p[text()='Dropped!']"));
        System.out.println("verifyMessage.getText() = " + verifyMessage.getText());

        assertTrue(verifyMessage.isDisplayed());
        assertEquals("Dropped!", verifyMessage.getText(), "verify that element has dropped");

    }

    @Test
    public void dragAndDropByTest() {

        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.cssSelector("#draggable"));

        actions.dragAndDropBy(source, 355, 50).perform();

        WebElement verifyMessage = driver.findElement(By.xpath("//p[text()='Dropped!']"));
        System.out.println("verifyMessage.getText() = " + verifyMessage.getText());

        assertTrue(verifyMessage.isDisplayed());
        assertEquals("Dropped!", verifyMessage.getText(), "verify that element has dropped");

    }

    @Test
    public void dragAndDropAnotherTest() {

        driver.get("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.cssSelector("#draggable"));
        WebElement target = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));

        actions.moveToElement(source).clickAndHold().moveToElement(target).pause(100).release().perform();


        WebElement verifyMessage = driver.findElement(By.xpath("//p[.='Dropped!']"));
        System.out.println("verifyMessage.getText() = " + verifyMessage.getText());

        assertTrue(verifyMessage.isDisplayed());
    }

    @Test
    public void testScrollingToElement() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/large");
        WebElement tableHeader = driver.findElement(By.xpath("//table/../h4"));

        wait.until(ExpectedConditions.visibilityOf(tableHeader));

        actions.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(tableHeader), 0, 1000).build().perform();
        //actions.scrollByAmount(1000, 50).perform();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        Thread.sleep(3000);
        assertTrue(tableHeader.isDisplayed());
    }

    @Test
    public void testSendKeysKeyDown() throws InterruptedException {
        driver.get("https://demo.aspnetawesome.com/TextBoxDemo");
        WebElement textBox = driver.findElement(By.id("Name-awed"));

        actions.keyDown(Keys.SHIFT).sendKeys(textBox, "hello world").keyUp(Keys.SHIFT).perform();

        Thread.sleep(3000);
    }
}
