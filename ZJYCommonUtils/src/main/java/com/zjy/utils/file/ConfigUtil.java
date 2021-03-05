package com.zjy.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * 配置工具类
 *
 * @author SuperCarryZJY 
 * @ClassName:
 * @Description: 配置工具类
 * @date 2021/3/5 14:09
 */
public class ConfigUtil
{
    /**
     * 从指定路径的properties配置文件中获取指定key的值
     * @param configFilePath 配置文件绝对路径
     * @param propertyName 属性名称
     * @return String 属性值
     * @throws IOException IO异常
     */
    public static String getValue(String configFilePath,String propertyName) throws IOException {
        //先看propertyMap里是否能查询到
        Properties properties=new Properties();
        InputStream is=FileUtil.loadFile2InputStream(configFilePath);
        properties.load(is);
        String propertyVal=properties.getProperty(propertyName);
        is.close();
        return propertyVal;
    }
}
