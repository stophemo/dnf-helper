package top.stophemo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * description
 *
 * @author 霍杰
 * @date 2023/6/27 17:38
 */
public class App {
    public static void main(String[] args) {

        try {
            // 请求接口地址
            String url = "https://ssl.ptlogin2.qq.com/ptqrshow?appid=716027609&e=2&l=M&s=3&d=72&v=4&t=0.7687923577756941&daid=383&pt_3rd_aid=102013353&u1=https%3A%2F%2Fgraph.qq.com%2Foauth2.0%2Flogin_jump";

            // 创建URL对象
            URL qrCodeUrl = new URL(url);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) qrCodeUrl.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 发送请求并获取响应状态码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 获取响应输入流
                BufferedImage qrCodeImage = ImageIO.read(connection.getInputStream());

                // 将二维码转换为特殊字符
                String qrCodeAscii = convertToAscii(qrCodeImage);

                // 打印特殊字符表示的二维码
                System.out.println(qrCodeAscii);
            } else {
                System.out.println("请求失败，响应状态码：" + responseCode);
            }

            // 关闭连接
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

//        DnfHelper dnfHelper = new DnfHelper();
//
//        // 登录
//        dnfHelper.login("your_username", "your_password");
//
//        // 参加活动
//        dnfHelper.participateInActivity("https://dnf.game.com/activity1");
//        dnfHelper.participateInActivity("https://dnf.game.com/activity2");
//
//        // 领取道具
//        dnfHelper.claimItem("https://dnf.game.com/item1");
//        dnfHelper.claimItem("https://dnf.game.com/item2");
//
//        // 退出
//        dnfHelper.quit();
    }


    private static String convertToAscii(BufferedImage qrCodeImage) {
        StringBuilder asciiBuilder = new StringBuilder();

        // 定义特殊字符的映射表，按照灰度值从高到低排序
        char[] asciiChars = {'@', '#', '8', '&', 'o', ':', '*', '.', ' '};

        // 获取二维码图像的宽度和高度
        int width = qrCodeImage.getWidth();
        int height = qrCodeImage.getHeight();

        // 遍历二维码图像的每个像素点
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x++) {
                // 获取当前像素点的RGB值
                int pixel = qrCodeImage.getRGB(x, y);

                // 将RGB值转换为灰度值
                int gray = (int) (0.2989 * ((pixel >> 16) & 0xFF) + 0.5870 * ((pixel >> 8) & 0xFF) + 0.1140 * (pixel & 0xFF));

                // 根据灰度值选择对应的特殊字符
                int index = (gray * (asciiChars.length - 1)) / 255;
                asciiBuilder.append(asciiChars[index]);
            }

            // 换行
            asciiBuilder.append("\n");
        }

        return asciiBuilder.toString();
    }
}



