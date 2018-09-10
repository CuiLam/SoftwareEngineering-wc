package com.lam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    /**
     * 判断某路径是否单个文件
     *
     * @param path 路径
     * @return 如果是单个文件返回true
     */
    public static boolean isFile(String path) {
        return path != null && new File(path).isFile();
    }

    /**
     * 判断某路径是否是文件夹
     *
     * @param path 路径
     * @return 如果是文件夹返回true
     */
    public static boolean isDirectory(String path) {
        return path != null && new File(path).isDirectory();
    }

    /**
     * 读取文件行数
     *
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
//            e.printStackTrace();
            return 0;
        }
        return line;
    }

    /**
     * 计算字符串中单词和数字个数
     *
     * @param string 原字符串
     * @return 返回单词个数
     */
    public static int getStringCount(String string) {
        int count = 0;
        if (string == null)
            return 0;
        String s = string.trim();   //首先去除字符串收尾缩进
        String regex = "[a-zA-Z0-9]+\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
//            System.out.println(matcher.group(0));
            count++;
        }
        return count;
    }

    /**
     * 获取文件单词个数
     *
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
//            e.printStackTrace();
            return 0;
        }
        return count;
    }

    /**
     * 计算文件字符数
     *
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
//            e.printStackTrace();
            return 0;
        }
        return count;
    }

    /**
     * 计算文件空行行数
     *
     * @param path 文件路径
     * @return 返回空行行数
     */
    public static int getFileEmptyLine(String path) {
        int count = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //当去除首位空白之后若长度小于等于1则当做空行
                String s = line.trim();
                count += (s.length() <= 1 ? 1 : 0);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return 0;
        }
        return count;
    }

    /**
     * 判断字符串是否是注释行
     *
     * @param line 字符串
     * @return 如果是返回true
     */
    public static boolean isNoteLine(String line) {
        if (line == null)
            return false;
        String regex = "^\\W*$";
        //如果包含这些表示可能是注释行
        //包含并且注释符号前全为非字符的一定是注释行
        if (line.contains("//")) {
            return line.split("//").length == 0 || Pattern.matches(regex, line.split("//")[0]);
        } else if (line.contains("/**")) {
            return Pattern.matches(regex, line.split("//*/*")[0]);
        } else if (line.contains("*/")) {
            return Pattern.matches(regex, line.split("/*/")[0]);
        } else if (line.contains("<!--")) {
            return Pattern.matches(regex, line.split("//")[0]);
        } else if (line.contains("--!>")) {
            return Pattern.matches(regex, line.split("//")[0]);
        } else if (line.contains("*")) {
            return Pattern.matches(regex, line.split("//*")[0]);
        }
        return false;
    }

    /**
     * 计算文件注释行行数
     *
     * @param path 文件路径
     * @return 返回注释行行数
     */
    public static int getFileNoteLine(String path) {
        int count = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null)
                count += (isNoteLine(line) ? 1 : 0);
        } catch (Exception e) {
//            e.printStackTrace();
            return 0;
        }
        return count;
    }

    /**
     * 计算文件代码行行数
     *
     * @param path 文件路径
     * @return 返回代码行行数
     */
    public static int getFileCodeLine(String path) {
        int count = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //当去除首位空白之后若长度大于1则当做代码行
                String s = line.trim();
                count += (s.length() > 1 && !isNoteLine(s) ? 1 : 0);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return 0;
        }
        return count;
    }

    public static void printFileMoreDate(String path) {
        int emptyLine = 0;
        int noteLine = 0;
        int codeLine = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isNoteLine(line)) {
//                    System.out.println("--note--" + line);
                    ++noteLine;
                } else if (line.trim().length() <= 1){
//                    System.out.println("--empty-- " + line);
                    ++emptyLine;
                } else {
//                    System.out.println("--code-- " + line);
                    ++codeLine;
                }
            }
        } catch (Exception e) {
            System.out.println("文件名有误！");
            return;
//            e.printStackTrace();
        }
        if (emptyLine + noteLine + codeLine == 0) {
            System.out.println("该文件是空文件");
        } else {
            System.out.println("空行：" + emptyLine);
            System.out.println("代码行：" + codeLine);
            System.out.println("注释行：" + noteLine);
        }
    }
}
