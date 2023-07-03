package com.stophemo.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Chrome implements CommandLineRunner {
    private WebDriver driver;
    @Override
    public void run(String... args) throws Exception {
        log.info("---------------------------Start!!!-----------------------------------");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        // 设置Chrome浏览器的路径
        // System.setProperty("webdriver.chrome.driver", "D:\\Jack\\newpage\\one-step\\one-step-java\\src\\main\\resources\\static\\driver\\chromedriver.exe");
        // System.setProperty("webdriver.chrome.bin", "C:\\Users\\windows\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "one-step-java/src/main/resources/static/driver/chromedriver.exe");
        try {
            driver = new ChromeDriver();
            // 打开Google网站
            driver.get("https://www.baidu.com");
            // 定位搜索框并输入关键字
            WebElement searchBox = driver.findElement(By.name("wd"));
            searchBox.sendKeys("Selenium WebDriver");
            // 提交搜索请求
            searchBox.submit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}