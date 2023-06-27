// 导入所需的库和模块
const express = require('express');
const puppeteer = require('puppeteer');

// 创建Express应用程序
const app = express();

// 设置静态文件目录
app.use(express.static('public'));

// 定义路由处理程序
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

app.get('/participate', async (req, res) => {
  try {
    // 启动浏览器
    const browser = await puppeteer.launch();

    // 创建一个新的页面
    const page = await browser.newPage();

    // 导航到DNF官方网站活动页面
    await page.goto('https://dnf.game.com/activities');

    // 在页面中查找并点击指定的活动
    await page.click('.activity-card');

    // 等待活动页面加载完成
    await page.waitForSelector('.activity-details');

    // 在活动页面中查找并点击领取道具按钮
    await page.click('.claim-button');

    // 等待道具领取成功的提示
    await page.waitForSelector('.success-message');

    // 输出成功领取道具的提示信息
    const successMessage = await page.$eval('.success-message', element => element.textContent);

    // 关闭浏览器
    await browser.close();

    // 返回成功领取道具的提示信息
    res.send(successMessage);
  } catch (error) {
    console.error('参与活动出错：', error);
    res.status(500).send('参与活动出错');
  }
});

// 启动服务器
app.listen(3000, () => {
  console.log('服务器已启动，访问 http://localhost:3000');
});
