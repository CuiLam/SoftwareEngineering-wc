package com.lam.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    /**
     * 读取文件行数
     * @param path 文件路径
     * @return 返回文件行数
     */
    public static int getFileLine(String path) {
        int line = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine() != null)
                line++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * 计算字符串中单词个数
     * @param string 原字符串
     * @return 返回单词个数
     */
    public static int getStringCount(String string) {
        int count = 0;
        String s = string.trim();   //首先去除字符串收尾缩进
        String pattern = "[a-zA-Z0-9]+\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(s);
        while (matcher.find()) {
//            System.out.println(matcher.group(0));
            count++;
        }
        return count;
    }

    /**
     * 获取文件单词个数
     * @param path 文件路径
     * @return 返回文件单词个数
     */
    public static int getFileStringCount(String path) {
        int count = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                count += getStringCount(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 计算文件字符数
     * @param path 文件路径
     * @return 返回文件字符数
     */
    public static int getFileCharsCount(String path) {
        int count = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                count += line.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
