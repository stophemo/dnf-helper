package com.stophemo.onestepjava.driver;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

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
        // 创建 ChromeOptions 对象
        ChromeOptions options = new ChromeOptions();
        // 设置浏览器静音
        options.addArguments("--mute-audio");
        // 创建 ChromeDriver 实例并传入配置选项
        driver = new ChromeDriver(options);
        driver.get("https://dnf.qq.com/cp/a20230615index/");
        // 定位搜索框并输入关键字
        WebElement loginBtn = driver.findElement(By.id("user-login"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", loginBtn);
        // 等待快捷登录弹窗出现
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // 设置最大等待时间为5秒
        WebElement quickLoginPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("qqLoginFrame")));

        driver.switchTo().frame(quickLoginPopup);
        // 定位用户头像并点击
        WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("face")));
        executor.executeScript("arguments[0].click();", userProfile);

        // 等待登录成功
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chang-area")));
        driver.switchTo().defaultContent();
        // 领取道具
        WebElement dTask0 = driver.findElement(By.id("d-task-0"));
        executor.executeScript("arguments[0].click();", dTask0);
        acceptAlert(driver);
        WebElement dTask1 = driver.findElement(By.id("d-task-1"));
        executor.executeScript("arguments[0].click();", dTask1);
        acceptAlert(driver);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.PAGE_DOWN).perform();
        WebElement cjBtnNum = driver.findElement(By.id("c-j-btn-num"));
        String textContent = cjBtnNum.getAttribute("textContent");
        int cjNum = Integer.parseInt(StrUtil.subAfter(textContent, "：", false).trim());
        for (int i = -1; i < cjNum; i++) {
            WebElement cjBtn = driver.findElement(By.id("c-j-btn"));
            executor.executeScript("arguments[0].click();", cjBtn);
            acceptAlert(driver);
            log.info("第{}次抽奖", i + 11);
        }
        log.info("---------------------------End!!!-----------------------------------");
    }

    private void acceptAlert(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        Alert  alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert != null) {
            alert.accept();
        }
    }
}