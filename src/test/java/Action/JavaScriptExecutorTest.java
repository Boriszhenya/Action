package Action;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaScriptExecutorTest extends BaseTest{

    @Test
    public void changeInputValueTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/docs/api/java/index.html");

        WebElement searchInput = driver.findElement(By.id("search-input"));
        changeInputValue(searchInput, "Actions");
        sendEnterKey(searchInput);
        searchInput.sendKeys(Keys.ENTER);

        Thread.sleep(5000);

        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Class Actions", header.getText());
    }

    private void changeInputValue(WebElement inputElement, String newValue) throws InterruptedException {
        js.executeScript("arguments[0].value = arguments[1];", inputElement, newValue);
        Thread.sleep(2000);
    }

    private void sendEnterKey(WebElement element) throws InterruptedException {
        js.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keydown', { 'key': 'Enter' }));", element);
        Thread.sleep(2000);
    }

    @Test
    public void changeBackgroundColorTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/docs/api/java/index.html");

        WebElement chromeLink = driver.findElement(By.linkText("org.openqa.selenium.chrome"));
        changeElementColor(chromeLink, "yellow");
        Thread.sleep(2000);
        hideElement(chromeLink);

        assertEquals(Color.fromString("yellow"), Color.fromString(chromeLink.getCssValue("background-color")));
    }

    private void changeElementColor(WebElement element, String color) throws InterruptedException {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
        Thread.sleep(2000);
    }

    private void hideElement(WebElement element) throws InterruptedException {
        js.executeScript("arguments[0].style.display = 'none';", element);
        Thread.sleep(2000);
    }

    @Test
    public void getInternalVariableValue() {
        driver.get("https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html");
        System.out.println(getScriptVariableValue("oddRowColor"));
    }

    private String getScriptVariableValue(String variableName) {
        return (String) js.executeScript("return " + variableName + ";");
    }
}
