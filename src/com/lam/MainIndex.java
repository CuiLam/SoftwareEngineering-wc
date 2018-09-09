package com.lam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import static com.lam.util.FileUtil.getFileCharsCount;
import static com.lam.util.FileUtil.getFileLine;
import static com.lam.util.FileUtil.getFileStringCount;
import static com.lam.util.FileUtil.isDirectory;
import static com.lam.util.FileUtil.isFile;
import static com.lam.util.FileUtil.printFileMoreDate;

public class MainIndex {

    public static final List<String> ALL_PARAMS = new ArrayList<>();

    {
        ALL_PARAMS.add("-l");
        ALL_PARAMS.add("-w");
        ALL_PARAMS.add("-c");
        ALL_PARAMS.add("-a");
    }

    public static void main(String[] args) {
        MainIndex main = new MainIndex();
        //处理参数
        List<String> params = new ArrayList<>();
        for (String s : args) {
            if (s.equals("-x")) {
              main.showGui();
            } else if(s.startsWith("-")) {
                params.add(s);
            } else if (isFile(s)) {
                System.out.println(s);
                for (String param : params)
                    main.operSingleFile(s, param);
            } else if (isDirectory(s)) {
                    main.operFiles(s, params, null);
            } else {
                //s可能是通配符
                String currentPath = main.getCurrentPath();
                    main.operFiles(currentPath, params, getRegex(s));
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
        }
        String[] strings2 = stringBuilder.toString().split("\\?");
        stringBuilder = new StringBuilder();
        for (int i = 0; i < strings2.length; ++i) {
            if (i != 0)
                stringBuilder.append("\\S");
            stringBuilder.append(strings2[i]);
        }
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

    private void operFiles(String path, List<String> params, String regex) {
        String[] files = new File(path).list();
        if (files == null) {
            System.out.println("文件夹为空");
            return;
        }
        for (String p : files) {
            String truePath = path + "/" + p;
            if (isDirectory(truePath)) {
                operFiles(truePath, params, regex);
            } else if (isFile(truePath)) {
                if (regex != null && !Pattern.matches(regex, p))
                    continue;
                System.out.println(truePath);
                for (String param : params)
                    operSingleFile(truePath, param);
            } else {
                System.out.println("路径有误!");
            }
        }
    }

    private void operSingleFile(String path, String param) {
        int count;
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

    private void showGui() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.showDialog(new JLabel(), "选择");
        File file = jFileChooser.getSelectedFile();
        if (file == null) {
            System.out.println("没有选择文件或文件夹");
            return;
        }
        if (file.isDirectory()) {
                operFiles(file.getAbsolutePath(), ALL_PARAMS, null);
        } else if (file.isFile()) {
            System.out.println(file.getAbsolutePath());
            for (String p : ALL_PARAMS)
                operSingleFile(file.getAbsolutePath(), p);
        }
    }
}
