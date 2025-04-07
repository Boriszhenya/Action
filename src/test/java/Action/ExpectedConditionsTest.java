package Action;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpectedConditionsTest extends BaseTest {
    @Test
    public void waitForElementHiddenTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement buttonUno = driver.findElement(By.cssSelector("#start > button"));
        buttonUno.click();

        WebElement textboxUno = driver.findElement(By.cssSelector("#finish > h4"));
        System.out.println(textboxUno.getText() + "; " + textboxUno.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(textboxUno));
        System.out.println(textboxUno.getText() + "; " + textboxUno.isDisplayed());
        assertEquals("Hello World!", textboxUno.getText());
    }

    @Test
    public void waitForElementHiddenTextTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement buttonUno = driver.findElement(By.cssSelector("#start > button"));
        buttonUno.click();

        WebElement textboxUno = driver.findElement(By.cssSelector("#finish > h4"));
        wait.until(ExpectedConditions.textToBePresentInElement(textboxUno, "Hello World!"));
        System.out.println(textboxUno.getText() + "; " + textboxUno.isDisplayed());
        assertEquals("Hello World!", textboxUno.getText());
    }

    @Test
    public void waitForElementAbsentTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement buttonUno = driver.findElement(By.cssSelector("#start > button"));
        buttonUno.click();

        WebElement textboxUno = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish > h4")));
        System.out.println(textboxUno.getText() + "; " + textboxUno.isDisplayed());
        assertEquals("Hello World!", textboxUno.getText());
    }

    @Test
    public void waitForElementToBeClickableTest() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement button = driver.findElement(By.xpath("//*[.='Add Element']"));
        button.click();

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[.='Delete']")));
        assertTrue(deleteButton.isEnabled());
    }

    @Test
    public void waitForAllElements() {
        driver.get("https://demo.aspnetawesome.com/");
        List<WebElement> tablesList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//table"), 2));

        assertTrue(tablesList.size() >= 3, String.format("Actual tables count: %s", tablesList.size()));
        //не проходит из-за масштаба странички, табличка не попадает в первую видимую страничку
    }
}
