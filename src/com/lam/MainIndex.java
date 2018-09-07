package com.lam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.lam.util.FileUtil.getFileCharsCount;
import static com.lam.util.FileUtil.getFileLine;
import static com.lam.util.FileUtil.getFileStringCount;
import static com.lam.util.FileUtil.isDirectory;
import static com.lam.util.FileUtil.isFile;
import static com.lam.util.FileUtil.printFileMoreDate;

public class MainIndex {

    public static void main(String[] args) {
        MainIndex main = new MainIndex();
        //处理参数
        List<String> params = new ArrayList<>();
        for (String s : args) {
            if (s.startsWith("-")) {
                params.add(s);
            } else if (isFile(s)) {
                for (String param : params)
                    main.operSingleFile(s, param);
            } else if (isDirectory(s)) {
                for (String param : params)
                    main.operFiles(s, param, null);
            } else {
                //s可能是通配符
                String currentPath = main.getCurrentPath();
                for (String param : params)
                    main.operFiles(currentPath, param, getRegex(s));
//                String[] paths = new File(currentPath).list();
//                if (paths == null) {
//                    System.out.println("当前目录文件夹为空！");
//                    return;
//                }
//                for (String p : paths) {
//
//                }
//                System.out.println("参数有误！");
                return;
            }
        }
    }

    private static String getRegex(String string) {
        String[] strings1 = string.split("\\*");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings1.length; ++i) {
            if (i != 0 && !strings1[i].equals(""))
                stringBuilder.append("\\S*");
            stringBuilder.append(strings1[i]);
//            System.out.println("--" + strings1[i] + "--");
        }
//        System.out.println(stringBuilder);
        String[] strings2 = stringBuilder.toString().split("\\?");
        stringBuilder = new StringBuilder();
        for (int i = 0; i < strings2.length; ++i) {
            if (i != 0)
                stringBuilder.append("\\S");
            stringBuilder.append(strings2[i]);
        }
//        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private String getCurrentPath() {
        String line = null;
        try {
            Process ps = Runtime.getRuntime().exec("pwd");
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private void operFiles(String path, String param, String regex) {
        String[] files = new File(path).list();
        if (files == null) {
            System.out.println("文件夹为空");
            return;
        }
        for (String p : files) {
            String truePath = path + "/" + p;
            if (isDirectory(truePath)) {
                operFiles(truePath, param, regex);
            } else if (isFile(truePath)) {
                if (regex != null && !Pattern.matches(regex, p))
                    continue;
                operSingleFile(truePath, param);
            } else {
                System.out.println("路径有误!");
            }
        }
    }

    private void operSingleFile(String path, String param) {
        int count;
        System.out.println(path);
        switch (param) {
            case "-l":  //行数
                count = getFileLine(path);
                if (count == 0)
                    System.out.println("该文件是空文件");
                else
                    System.out.println("行数：" + count);
                break;
            case "-w":  //单词数
                count = getFileStringCount(path);
                if (count == 0)
                    System.out.println("该文件是空文件");
                else
                    System.out.println("单词个数：" + count);
                break;
            case "-c":  //字符数
                count = getFileCharsCount(path);
                if (count == 0)
                    System.out.println("该文件是空文件");
                else
                    System.out.println("字符数：" + count);
                break;
            case "-a":  //更复杂的数据（代码行 / 空行 / 注释行）
                printFileMoreDate(path);
                break;
        }
    }
}
