package ru.stqa.training.selenium.b4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductStickerChecker {
    private WebDriver driver;
    private WebDriverWait wait;
    public List<WebElement> list = new ArrayList<>();
    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void productStickerCheck(){
        driver.get("http://localhost/litecart/public_html/");
        list = driver.findElements(By.xpath("//div[contains(@class,'col-xs-6')]"));
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i).findElements(By.xpath(".//div[contains(@class, 'sticker')]")).size(), 1);
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
