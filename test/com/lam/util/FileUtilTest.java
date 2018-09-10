package com.lam.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileUtilTest {

    @Test
    public void isFile() {
        assertEquals(true, FileUtil.isFile("/home/lam/gdut/SoftwareEngineering/DemoOne/src/com/lam/MainIndex.java"));
        assertEquals(false, FileUtil.isFile("/home/lam/gdut/SoftwareEngineering/DemoOne"));
        assertEquals(false, FileUtil.isFile(""));
        assertEquals(false, FileUtil.isFile(null));
    }

    @Test
    public void isDirectory() {
        assertEquals(false, FileUtil.isDirectory("/home/lam/gdut/SoftwareEngineering/DemoOne/src/com/lam/MainIndex.java"));
        assertEquals(true, FileUtil.isDirectory("/home/lam/gdut/SoftwareEngineering/DemoOne"));
        assertEquals(false, FileUtil.isDirectory(""));
        assertEquals(false, FileUtil.isDirectory(null));
    }

    @Test
    public void getFileLine() {
        assertEquals(0, FileUtil.getFileLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(12, FileUtil.getFileLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileLine(""));
        assertEquals(0, FileUtil.getFileLine(null));
    }

    @Test
    public void getStringCount() {
        assertEquals(0, FileUtil.getStringCount(""));
        assertEquals(0, FileUtil.getStringCount(null));
        assertEquals(4, FileUtil.getStringCount("assertEquals(0, FileUtil.getStringCount());"));
        assertEquals(3, FileUtil.getStringCount("int a = 5;"));
        assertEquals(6, FileUtil.getStringCount("public static void main(String[] args)"));
    }

    @Test
    public void getFileStringCount() {
        assertEquals(0, FileUtil.getFileStringCount("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(15, FileUtil.getFileStringCount("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileStringCount(""));
        assertEquals(0, FileUtil.getFileStringCount(null));
    }

    @Test
    public void getFileCharsCount() {
        assertEquals(0, FileUtil.getFileCharsCount("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(163, FileUtil.getFileCharsCount("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileCharsCount(""));
        assertEquals(0, FileUtil.getFileCharsCount(null));
    }

    @Test
    public void getFileEmptyLine() {
        assertEquals(0, FileUtil.getFileEmptyLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(3, FileUtil.getFileEmptyLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileEmptyLine(""));
        assertEquals(0, FileUtil.getFileEmptyLine(null));
    }

    @Test
    public void isNoteLine() {
        assertEquals(false, FileUtil.isNoteLine(""));
        assertEquals(false, FileUtil.isNoteLine(null));
        assertEquals(true, FileUtil.isNoteLine("/**"));
        assertEquals(false, FileUtil.isNoteLine("System.out.println(\"//\");"));
        assertEquals(false, FileUtil.isNoteLine("        int a = 0;  //这是代码行\n"));
        assertEquals(true, FileUtil.isNoteLine("        }  //这是注释行\n"));
    }

    @Test
    public void getFileNoteLine() {
        assertEquals(0, FileUtil.getFileNoteLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(5, FileUtil.getFileNoteLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileNoteLine(""));
        assertEquals(0, FileUtil.getFileNoteLine(null));
    }

    @Test
    public void getFileCodeLine() {
        assertEquals(0, FileUtil.getFileCodeLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java"));
        assertEquals(4, FileUtil.getFileCodeLine("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java"));
        assertEquals(0, FileUtil.getFileCodeLine(""));
        assertEquals(0, FileUtil.getFileCodeLine(null));
    }

    @Test
    public void printFileMoreDate() {
        FileUtil.printFileMoreDate("/home/lam/gdut/SoftwareEngineering/DemoOne/test/BlankTest.java");
        FileUtil.printFileMoreDate("/home/lam/gdut/SoftwareEngineering/DemoOne/test/NoteTest.java");
        FileUtil.printFileMoreDate("/home/lam/gdut/SoftwareEngineering/DemoOne/test");
        FileUtil.printFileMoreDate("");
        FileUtil.printFileMoreDate(null);
    }

}