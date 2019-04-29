package ru.stqa.training.selenium.b4;

import javafx.collections.transformation.SortedList;
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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CountriesSortingChecker {
    private WebDriver driver;
    private WebDriverWait wait;
    private By countryForm = By.xpath("//form[@name = 'countries_form']//tbody//tr");
    //todo: Написать нормальный селектор для поиска на новой странице
    private By subcountryForm = By.xpath("//form[@name = 'countries_form']//tbody//tr");
    private List<WebElement> elList;
    private String cName;
    private List <String> orList = new ArrayList<>();
    private List <String> sortOrList = new ArrayList<>();
    private List <String> links = new ArrayList<>();
    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
    }

    @Test
    public void countriesSortingCheck(){
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.tagName("button")).click();
        elList = driver.findElements(countryForm);
        System.out.println(elList.size());
        for (int i = 0; i < elList.size(); i++) {
            cName = elList.get(i).findElement(By.xpath(".//td[5]")).getText();
            orList.add(cName);
            if(Integer.parseInt(elList.get(i).findElement(By.xpath(".//td[6]")).getText())>0){
                links.add(elList.get(i).findElement(By.xpath(".//td[5]")).getAttribute("href"));
                System.out.println(elList.get(i).findElement(By.xpath(".//td[5]//a")).getAttribute("href"));
            }
            sortOrList.add(cName);
        }
        Collections.sort(sortOrList);
        Assert.assertEquals(orList, sortOrList);
        //todo: Дописать реализацию проверки сортировки для ссылок
        //todo: Вынести сортировку в отдельный метод
//        for (int i = 0; i < links.size(); i++) {
//            driver.get(links.get(i));
//            elList = driver.findElements(subcountryForm);
//            for (int j = 0; j < elList.size(); j++) {
//                cName = elList.get(j).findElement(By.xpath(".//td[5]")).getText();
//                orList.add(cName);
//                sortOrList.add(cName);
//            }
//        }
//        Assert.assertEquals(orList, sortOrList);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
