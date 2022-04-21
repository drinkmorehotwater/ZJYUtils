package com.zjy.utils.other;

import com.zjy.utils.file.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Objects;

/**
 * @author SuperCarryZJY 
 * @ClassName:
 * @Description:
 * @date 2022/4/21 15:42
 */
public class BiliBiliCacheFileUtil
{
    /**
     * 从源地址读取文件并删除前三个字节，保存到目的地址 b站的缓存视频文件的限制机制就是在视频文件的前面加了三个FF
     * @param originFilePath 源地址
     * @param destFilePath 目的地址
     */
    public static void editBiliBiliCacheMp4File(String originFilePath,String destFilePath) throws IOException {
        //加载文件并文件转化为字节数组
        byte[] bytes= FileUtil.loadFile(originFilePath);
        byte[] destBytes=new byte[bytes.length-3];
        //删除开头三个字节
        System.arraycopy(bytes,3,destBytes,0,destBytes.length);
        //将文件命名并写到指定位置
        FileUtil.saveFile(destBytes,destFilePath);
    }

    /**
     * 获取指定文件夹的全部子文件夹
     * @param parentFolderPath 指定文件夹
     * @return File[]
     */
    public static File[] getSubFolders(String parentFolderPath){
        File file=new File(parentFolderPath);
        //文件过滤器，过滤出文件夹
        FileFilter fileFilter= File::isDirectory;
        //返回文件夹名列表
        return file.listFiles(fileFilter);
    }

    /**
     * 获取指定文件夹下面的一个mp4文件，如果有多个，请勿使用此方法
     * @param parentFolderPath 指定文件夹
     * @return File
     */
    public static File getOneSubFileMP4(String parentFolderPath){
        File file=new File(parentFolderPath);
        //文件名过滤器
        FilenameFilter filenameFilter= (dir, name) -> {
            //这里过滤掉文件而保留文件夹
            //因为文件名中包含"."，所以按照下面的方法可以过滤
            return name.contains(".mp4");
        };
        //返回文件夹名列表
        return Objects.requireNonNull(file.listFiles(filenameFilter))[0];
    }

    /**
     * 修改指定路径下的B站缓存视频文件并保存到目的文件夹
     * @param originFolderPath 指定路径
     * @param destFolderPath 目的地址
     */
    public static void editFile(String originFolderPath,String destFolderPath) throws IOException {
        String fileNameSuffix=".mp4";
        // 读取到所有的子文件夹到数组中
        File[] folders= getSubFolders(originFolderPath);
        // 遍历容器获取里面的mp4文件
        for(File folder:folders){
            // 依次修改这些文件并将修改后的文件写到指定地址
            File file=getOneSubFileMP4(folder.getPath());
            editBiliBiliCacheMp4File(file.getAbsolutePath(),destFolderPath+folder.getName()+fileNameSuffix);
        }
    }
}
