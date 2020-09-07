package com.iswn.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaofei.yan
 * @Create 2020-09-07 15:11
 * @Descript 图片下载工具类
 * Todo: 批量下载图片第一张总是下载失败？
 */
public class ImageDownUtils {
    /**
     * 图片存储目录
     */
    static String file = "/Users/yanxiaofei/Downloads/foodie";
    /**
     * 匹配出图片存储路径
     */
    static Pattern pattern = Pattern.compile("\\/foodie\\/?(.*)\\/(.*)");

    /**
     * 下载图片
     * @param imgUrl 图片地址
     * @param imageName 图片名称
     */
    public static Boolean downImage(String imgUrl, String path, String imageName) {
        // 下载失败的图片

        File files = new File(file  + "/" + path);
        if (!files.exists()) {
            files.mkdirs();
        }

        InputStream is;
        FileOutputStream out;

        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            is = connection.getInputStream();

            // 创建文件
            File fileOfImg = new File(file + "/" + path + "/" + imageName);
            out = new FileOutputStream(fileOfImg);

            int i = 0;
            while ((i = is.read()) != -1) {
                out.write(i);
            }
            is.close();
            out.close();

            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 匹配图片并开始下载
     * @param url 图片地址
     * @return 下载成功或失败
     */
    public static Boolean start(String url) {
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            String path = matcher.group(1);
            String imageName = matcher.group(2);
            return downImage(url, path,imageName);
        }
        return false;
    }

    /**
     * 测试代码
     * @param args
     */
    public static void main(String[] args) {
        String imageUrl = "xxxxxxx";
        Boolean result = start(imageUrl);
        System.out.println(result);
    }
}
