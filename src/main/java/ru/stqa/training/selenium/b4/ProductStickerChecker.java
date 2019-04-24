package ru.stqa.training.selenium.b4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductStickerChecker {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement el;
    public List<WebElement> list;
    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void productStickerCheck(){
        driver.get("http://localhost/litecart/public_html/");
        int size = 1;
        int size1 = 1;
        for (int i = 0; i <size ; i++) {
            el = driver.findElement(By.cssSelector("#content > ul"));
            list = el.findElements(By.cssSelector("li"));
            size = list.size();
            list.get(i).click();
//            System.out.println(list.get(i).getText());
            for (int j = 0; j <size1 ; j++) {
                list = driver.findElements(By.cssSelector("#box-campaign-products > div > div"));
                el = list.get(j);
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
