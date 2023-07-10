package com.stophemo.onestepjava;

import com.stophemo.api.LoginController;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@SpringBootTest
class testQrcode {

	public static void main(String[] args) {
		LoginController loginController = new LoginController();
		InputStream qrCode = loginController.getQrCode();
		// 将byte数组转换为BufferedImage对象
		BufferedImage qrCodeImage = null;
		try {
			qrCodeImage = ImageIO.read(qrCode);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// 将BufferedImage对象保存为本地文件
		String fileName = UUID.randomUUID().toString() + ".png";
		File outputFile = new File(fileName);
		try {
			ImageIO.write(qrCodeImage, "png", outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("二维码已保存到本地文件：" + fileName);
		System.out.println("当前工作目录：" + System.getProperty("user.dir"));
	}
}
