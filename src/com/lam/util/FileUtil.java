package com.lam.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
}
