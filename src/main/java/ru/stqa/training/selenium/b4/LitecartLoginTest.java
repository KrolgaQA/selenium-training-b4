package ru.stqa.training.selenium.b4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LitecartLoginTest {
//    WebDriver chromeDriver = new ChromeDriver();
//    WebDriver ieDriver = new InternetExplorerDriver();
    WebDriver firefoxDriver = new FirefoxDriver();
    private WebDriverWait wait;


    @Before
    public void start() {

        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(firefoxDriver, 10);
    }

    @Test
    public void letsLogin() {
        firefoxDriver.get("http://localhost/litecart/public_html/");
        firefoxDriver.findElement(By.cssSelector("#default-menu > ul.nav.navbar-nav.navbar-right > li")).click();
        firefoxDriver.findElement(By.name("email")).clear();
        firefoxDriver.findElement(By.name("email")).sendKeys("admin");
        firefoxDriver.findElement(By.name("password")).clear();
        firefoxDriver.findElement(By.name("password")).sendKeys("admin");
        firefoxDriver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        firefoxDriver.quit();
        firefoxDriver = null;
    }

}

