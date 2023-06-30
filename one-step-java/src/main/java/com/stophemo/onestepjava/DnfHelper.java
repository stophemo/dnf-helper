package top.stophemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * dnf-helper
 *
 * @author 霍杰
 * @date 2023/6/27 17:44
 */

public class DnfHelper {
    private WebDriver driver;

    public DnfHelper() {
        // 设置 ChromeDriver 的路径
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // 创建 ChromeDriver 实例
        driver = new ChromeDriver();
    }

    public void login(String username, String password) {
        // 导航到登录页面
        driver.get("https://mail.qq.com/");

        // 输入用户名和密码
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        // 提交登录表单
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    public void participateInActivity(String activityUrl) {
        // 导航到活动页面
        driver.get(activityUrl);

        // 点击参加活动按钮
        WebElement participateButton = driver.findElement(By.cssSelector(".participate-button"));
        participateButton.click();

        // 等待参加成功消息出现
        WebElement successMessage = driver.findElement(By.cssSelector(".success-message"));
        String message = successMessage.getText();

        System.out.println("参加活动成功：" + message);
    }

    public void claimItem(String itemUrl) {
        // 导航到道具页面
        driver.get(itemUrl);

        // 点击领取按钮
        WebElement claimButton = driver.findElement(By.cssSelector(".claim-button"));
        claimButton.click();

        // 等待领取成功消息出现
        WebElement successMessage = driver.findElement(By.cssSelector(".success-message"));
        String message = successMessage.getText();

        System.out.println("领取道具成功：" + message);
    }

    public void quit() {
        // 关闭浏览器
        driver.quit();
    }
}
