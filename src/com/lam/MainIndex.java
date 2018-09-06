package com.lam;

import static com.lam.util.FileUtil.*;

public class MainIndex {

    public static final int ARGS_PARAM = 0;
    public static final int ARGS_PATH = 1;

    public static void main(String[] args) {
        String param = args[ARGS_PARAM];
        String path = args[ARGS_PATH];
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
        }
    }
}
