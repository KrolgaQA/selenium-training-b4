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

public class AdminSectionChecker {
    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> list = new ArrayList<>();
    private List<WebElement> subList = new ArrayList<>();
    private WebElement el;
    public ArrayList<String> locators = new ArrayList<>();
    public ArrayList<String> links = new ArrayList<>();
    public String locator;
    public String link;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);
    }

    @Test
    public void adminSectionCheck() {
        driver.get("http://localhost/litecart/public_html/admin/");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.tagName("button")).click();
        list = driver.findElements(By.cssSelector("ul#box-apps-menu li"));
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                locator = list.get(i).getAttribute("id");
                locators.add(locator);
            }
        }

        for (int i = 0; i < locators.size(); i++) {
            locator = locators.get(i);
            el = driver.findElement(By.id(locator));
            el.click();
            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
//            System.out.println(driver.findElement(By.tagName("h1")).getText());
            subList = driver.findElements(By.cssSelector("#" + locator + " li"));
            if (subList.size() > 0) {
                links.clear();
                for (int j = 0; j < subList.size(); j++) {
                    link = subList.get(j).findElement(By.tagName("a")).getAttribute("href");
                    links.add(link);
                }
            }
            for (int k = 0; k < links.size(); k++) {
                driver.get(links.get(k));
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
