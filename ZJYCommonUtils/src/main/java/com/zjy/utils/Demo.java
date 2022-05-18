package com.zjy.utils;

import com.zjy.utils.internet.HttpUtil;
import com.zjy.utils.other.BiliBiliCacheFileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperCarryZJY 
 * @ClassName:
 * @Description:
 * @date 2021/3/5 13:54
 */
public class Demo
{
    public static void main1(String[] args) {
//        Map<String, String> params=new HashMap<>();
//        for(int i=0;i<99999;i++){
//            try {
//                System.out.println(ConfigUtil.getValue("D:\\WorkSpaces\\MySpace\\ZJYUtils\\ZJYCommonUtils\\src\\main\\resources\\property\\url.properties","weatherUrl"));
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        try {
//            String rtnStr= HttpUtil.get("http://www.weather.com.cn/data/cityinfo/101190403.html");
//            Map<String,String> bodyMap=new HashMap<>();
//            bodyMap.put("params","{\"client_id\":\"60dcb643-657b-49a8-b820-2c75b236edba\",\"client_secret\":\"752d37c6-33ea-4a27-b856-05a9c67c4572\",\"grant_type\":\"client_credentials\"}\n");
//            String rtnStr= HttpUtil.postForm("http://192.168.219.109:8086/IOTWebService_sup/rest/oauth2/token",bodyMap);
//            System.out.println(rtnStr);
//            Map<String,String>
                        Map<String,String> bodyMap=new HashMap<>();
                        Map<String,String> headerMap=new HashMap<>();
            headerMap.put("Authorization","Bearer 7d99800638c3baa3cf74d13b799694ea");
                        bodyMap.put("params","{\n"
                                + "    \"unifiedProjectCode\":\"c9e35c17-09f4-424b-89c9-b3aa667bb750\",\n"
                                + "    \"dataTimeStamp\":\"1592904863842\",\n" + "    \"deviceSN\":\"cssjk01\",\n"
                                + "    \"deviceFactory\":\"随便\",\n" + "    \"protocolVer\":\"V1.0\",\n"
                                + "    \"realTimeData\":[\n" + "        {\n"
                                + "            \"uuid\":\"a8ce7854-e36d-11ea-9aea-fatf3ec9ia87\",\n"
                                + "            \"time\":\"202103021135314\",\n" + "            \"interval\":\"1\",\n"
                                + "            \"metadata\":{\n" + "                \"gps\":{\n"
                                + "                    \"lng\":\"42.2\",\n" + "                    \"lat\":\"12.4\"\n"
                                + "                },\n" + "                \"deviceType\":\"01\",\n"
                                + "                \"terminalName\":\"随便\",\n"
                                + "                \"terminalNumber\":\"暂无\",\n"
                                + "                \"projectNumber\":\"nope\",\n"
                                + "                \"monitorTypeCode\":\"froundLev\",\n"
                                + "                \"monitorTypeName\":\"suibian\"\n" + "            },\n"
                                + "            \"current\":{\n" + "                \"calcValue\":[\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":29.242,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":19.726,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":42.341,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":28.958,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":37.663,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":9.021,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":24.308,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":-3.576,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":28.776,\n"
                                + "                        \"variation\":0\n" + "                    },\n"
                                + "                    {\n" + "                        \"rateChange\":0,\n"
                                + "                        \"value\":-19.116,\n"
                                + "                        \"variation\":0\n" + "                    }\n"
                                + "                ],\n" + "                \"pointAlarmStatus\":{\n"
                                + "                    \"occurTime\":\"20210205110800\",\n"
                                + "                    \"alarmStatus\":\"1\"\n" + "                },\n"
                                + "                \"battery\":\"76.88\"\n" + "            }\n" + "        }\n"
                                + "    ]\n" + "}");

            String rtnStr=HttpUtil.postForm("http://192.168.219.109:8086/IOTWebService_sup/rest/DeepFoundPitDataService/PushSJKRealTimeInfo",bodyMap,headerMap);
            System.out.println(rtnStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // b站缓存视频文件夹下面的指定缓存视频文件夹
        String folderPath="E:\\b站缓存\\838132893";
        String destFolderPath="E:\\计算机教程\\Docker";
        BiliBiliCacheFileUtil.editFile(folderPath,destFolderPath);
    }
}
