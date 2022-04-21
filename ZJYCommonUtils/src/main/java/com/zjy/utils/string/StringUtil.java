package com.zjy.utils.string;

/**
 * 处理字符串的工具类
 *
 * @author SuperCarryZJY 
 * @ClassName: StringUtil
 * @Description: 处理字符串的工具类
 * @date 2021/4/25 20:33
 */
public class StringUtil
{
    /**
     * 判断字符串是否为空字符串或者null
     * @param str 要判空的字符串
     * @return boolean
     */
    public static boolean isBlank(String str){
        return str == null || "".equals(str);
    }
}
