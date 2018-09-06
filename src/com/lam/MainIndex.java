package com.lam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainIndex {

    public static final int ARGS_PARAM = 0;
    public static final int ARGS_PATH = 1;

    public static void main(String[] args) {
        String param = args[ARGS_PARAM];
        String path = args[ARGS_PATH];
        int line = 0;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine() != null) {
                line++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("行数：" + line);
    }

}
