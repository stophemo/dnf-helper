package com.stophemo.api;

import com.stophemo.utils.HttpUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class LoginController {
    /**
     * 应用ID
     */
    private final static String APP_ID = "21000127";
    private final static String DAID = "8";

    private static String qrsig = "";

    public InputStream getQrCode() {
        String t = Double.toString(Math.random());
        String url = "https://ssl.ptlogin2.qq.com/ptqrshow?appid=" + APP_ID + "&e=2&l=M&s=3&d=72&v=4&t=" + t + "&daid=" + DAID + "&pt_3rd_aid=0";
        byte[] data = HttpUtil.doDownload(url, null, null, null);
        if (data != null && data.length > 0) {
            Map<String, String> cookies = HttpUtil.getCookies();
            if (cookies != null) {
                qrsig = cookies.get("qrsig");
                return new ByteArrayInputStream(data);
            }
        }
        return null;
    }
}
