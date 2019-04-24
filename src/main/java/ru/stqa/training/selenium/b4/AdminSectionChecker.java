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
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class AdminSectionChecker {
        private WebDriver driver;
        private WebDriverWait wait;
        private List<WebElement> list;
        private List<WebElement> listSmall;
//        private ListIterator<WebElement> li;


        @Before
        public void start() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
        }

        @Test
        public void adminSectionCheck() {
            driver.get("http://localhost/litecart/public_html/admin/");
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).clear();
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.tagName("button")).click();
//            list = driver.findElements(By.cssSelector("ul#box-apps-menu li"));
//            li = list.listIterator();
//
//            while (li.hasNext()){
//                li.next().click();
//                name = driver.findElement(By.tagName("h1")).getText();
//                System.out.println(name);
//                driver.get("http://localhost/litecart/public_html/admin/");
//                list = driver.findElements(By.cssSelector("ul#box-apps-menu li"));
//            }
            int size = 1;
            int sizeSmall = 1;

            for (int i = 0 ; i < size ; ++i) {
                list = driver.findElements(By.cssSelector("ul#box-apps-menu li"));
                size = list.size();
                String s = list.get(i).getAttribute("id");
                list.get(i).click();
                System.out.println(driver.findElement(By.tagName("h1")).getText());
                listSmall = driver.findElements(By.cssSelector("li#" + s + "ul.docs li"));
                sizeSmall = listSmall.size();
                if(sizeSmall>0){
                for (int j = 0; j < sizeSmall; ++j) {
                    if (!listSmall.get(j).isSelected()) {
                        listSmall.get(j).click();
                        System.out.println(driver.findElement(By.tagName("h1")).getText());
                        driver.navigate().back();
                    }
                }
                }
            }
            driver.navigate().back();

        }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
