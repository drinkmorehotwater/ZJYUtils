package com.zjy.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.*;

/**
 * 文件工具类
 *
 * @author SuperCarryZJY 
 * @ClassName: FileUtil
 * @Description: 文件工具类
 * @date 2021/3/3 17:27
 */
public class FileUtil
{
    private static final Logger logger= LoggerFactory.getLogger(FileUtil.class);

    /**
     * 保存文件到指定路径
     * @param bytes 文件字节流
     * @param filePath 保存路径
     * @throws IOException IO异常
     */
    public static void saveFile(byte[] bytes,String filePath) throws IOException {
        Path path=Paths.get(filePath);
        Files.write(path,bytes);
    }

    /**
     * 从指定路径加载文件
     * @param filePath 指定的文件路径
     * @return byte[] 字节流
     * @throws IOException IO异常
     */
    public static byte[] loadFile(String filePath) throws IOException {
        Path path=Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    /**
     * 从指定位置复制文件到另外的指定位置
     * @param sourceFilePathStr 原文件位置
     * @param destFilePathStr 目标文件位置
     * @param copyOption 复制选项
     * @throws IOException IO异常
     */
    public static void copyFile(String sourceFilePathStr,String  destFilePathStr,CopyOption copyOption)
            throws IOException {
        Path sourceFilePath=Paths.get(sourceFilePathStr);
        Path destFilePath=Paths.get(destFilePathStr);
        Files.copy(sourceFilePath,destFilePath, copyOption);
    }

    /**
     * 从指定位置复制文件到另外的指定位置
     * @param sourceFilePathStr 原文件位置
     * @param destFilePathStr 目标文件位置
     * @throws IOException IO异常
     */
    public static void copyFile(String sourceFilePathStr,String  destFilePathStr)
            throws IOException {
        Path sourceFilePath=Paths.get(sourceFilePathStr);
        Path destFilePath=Paths.get(destFilePathStr);
        Files.copy(sourceFilePath,destFilePath, StandardCopyOption.REPLACE_EXISTING);
        copyFile(sourceFilePathStr,destFilePathStr,StandardCopyOption.REPLACE_EXISTING);
    }

    public static void main(String[] args) throws IOException {
//        byte[] bytes=loadFile("C:\\Users\\SuperCarryZJY\\Desktop\\picture\\20190829006周俊扬.jpg");
//        System.out.println("length"+bytes.length);
//        saveFile(bytes,"C:\\Users\\SuperCarryZJY\\Desktop\\picture\\20190829006周俊扬2.jpg");
        copyFile("C:\\Users\\SuperCarryZJY\\Desktop\\picture\\20190829006周俊扬.jpg","C:\\Users\\SuperCarryZJY\\Desktop\\picture\\20190829006周俊扬1.jpg",StandardCopyOption.REPLACE_EXISTING);
//        System.out.println("getName"+file.getName());
//        System.out.println("getParentFile"+file.getParentFile());
//        System.out.println("getAbsolutePath"+file.getAbsolutePath());
//        System.out.println("getPath"+file.getPath());
//        System.out.println("getTotalSpace"+file.getTotalSpace());
//        System.out.println("getUsableSpace"+file.getUsableSpace());
    }
}
