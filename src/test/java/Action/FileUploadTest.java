package Action;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUploadTest extends BaseTest{

    @Test
    public void uploadDirectSendKeysTest() throws InterruptedException {
        System.out.println("System.setProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        System.out.println("System.getProperty(\"os.name\") = " + System.getProperty("os.name"));

        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement chooseFile = driver.findElement(By.cssSelector("#file-upload"));
        String projectPath = System.getProperty("user.dir");
        String filePath ="src/main/resources/logback.xml";
        String fullPath = projectPath+"/"+filePath;

        System.out.println(fullPath);

        chooseFile.sendKeys(fullPath);
        //driver.findElement(By.id("file-submit")).click();
        chooseFile.submit();
        String actualText = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals("logback.xml", actualText);
        Thread.sleep(5000);
    }
/*---это костыль под Unix системы - нет возможности проверить----*/
    @Test
    public void dialogUploadTestUnix() throws InterruptedException, AWTException {
        System.out.println("System.setProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        System.out.println("System.getProperty(\"os.name\") = " + System.getProperty("os.name"));

        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement chooseFile = driver.findElement(By.cssSelector("#drag-drop-upload"));
        String projectPath = System.getProperty("user.dir");
        String filePath ="src/main/resources/logback.xml";
        String fullPath = projectPath+"/"+filePath;
        System.out.println(fullPath);
        Thread.sleep(1500);
        chooseFile.click();
        //actions.moveToElement(chooseFile).click().perform();
        Thread.sleep(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fullPath), null);
        Robot robot = new Robot();
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(1500);
        String actualText = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals("logback.xml", actualText);
        Thread.sleep(5000);
    }
/*--- это костыль под Windows---*/
    @Test
    public void dialogUploadTestWindows() throws InterruptedException, AWTException {
        System.out.println("System.setProperty(\"user.dir\") = " + System.getProperty("user.dir"));
        System.out.println("System.getProperty(\"os.name\") = " + System.getProperty("os.name"));

        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement chooseFile = driver.findElement(By.cssSelector("#drag-drop-upload"));
        String projectPath = System.getProperty("user.dir");
        String filePath ="src\\main\\resources\\logback.xml";
        String fullPath = projectPath+"\\"+filePath;
        System.out.println(fullPath);
        Thread.sleep(1500);
        actions.moveToElement(chooseFile).click().perform();
        Thread.sleep(1500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fullPath), null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(1500);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1500);
        String actualText = driver.findElement(By.xpath("(//*[@class='dz-details'])[1]")).getText();
        System.out.println(actualText);
        assertEquals("logback.xml", actualText);
        Thread.sleep(1500);
        driver.findElement(By.id("file-submit")).click();
        /*--эта часть не работает так как сайт выдает ошибку "Internal Server Error"--*/
        //String actualText1 = driver.findElement(By.id("uploaded-files")).getText();
        //System.out.println(actualText1);
        //assertEquals("logback.xml", actualText);

    }
}
