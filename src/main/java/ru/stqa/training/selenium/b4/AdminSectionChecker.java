package ru.stqa.training.selenium.b4;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class AdminSectionChecker {
        private WebDriver driver;
        private WebDriverWait wait;
        private List<WebElement> list;
        private ListIterator<WebElement> li = list.listIterator();
        private String name;


        @Before
        public void start() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
        }

        public void adminSectionCheck() {
            driver.get("http://localhost/litecart/admin");
            try {
                list = driver.findElements(By.className(""));
            }
            catch (Exception e){
                System.out.println("Can't find element");
            }
            while (li.hasNext()){
                li.next().click();
                name = driver.findElement(By.tagName("h1")).getText();
                System.out.println(name);
            }
        }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
