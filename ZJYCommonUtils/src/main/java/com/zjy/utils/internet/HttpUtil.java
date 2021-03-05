package com.zjy.utils.internet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Http工具类
 *
 * @author SuperCarryZJY 
 * @ClassName: HttpUtil
 * @Description: Http工具类
 * @date 2021/3/3 15:26
 */
public class HttpUtil
{
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static String doRequest(String queryUrl, Map<String, String> params, String method) throws IOException {
        HttpURLConnection conn = null;
        String result = null;

        try {
            String[] queryInfos = parseQueryParams(queryUrl, params);
            URL url = new URL(queryInfos[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestMethod(method);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(queryInfos[1]);
            out.flush();
            out.close();
            if (conn.getResponseCode() != 200) {
                logger.warn("respCode:" + conn.getResponseCode() + ", queryUrl:" + queryInfos[0] + ", queryParams:"
                        + queryInfos[1]);
                InputStream is = conn.getErrorStream();
                byte[] bs = new byte[is.available()];
                is.read(bs);
                throw new RuntimeException("Internal Error: " + new String(bs));
            }

            InputStream is = conn.getInputStream();
            int length = conn.getContentLength();
            byte[] buff;
            if ("gzip".equals(conn.getHeaderField("Content-Encoding"))) {
                if (length < 0) {
                    is = new GZIPInputStream((InputStream) is);
                }
                else {
                    buff = new byte[length];
                    (new DataInputStream((InputStream) is)).readFully(buff);
                    is = new GZIPInputStream(new ByteArrayInputStream(buff));
                    length = -1;
                }
            }

            if (length < 0) {
                Reader reader = new InputStreamReader((InputStream) is, "UTF-8");
                char[] cbuf = new char[1024];
                StringBuffer sb = new StringBuffer(1024);

                while (true) {
                    int count = reader.read(cbuf, 0, 1024);
                    if (count == -1) {
                        result = sb.toString();
                        break;
                    }

                    sb.append(cbuf, 0, count);
                }
            }
            else {
                buff = new byte[length];
                (new DataInputStream((InputStream) is)).readFully(buff);
                result = new String(buff, "UTF-8");
            }
        }
        finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                }
                catch (Exception var19) {
                }
                conn = null;
            }

        }

        return result;
    }

    private static String[] parseQueryParams(String queryUrl, Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuffer buff = new StringBuffer();
        int paramIndex = queryUrl.indexOf("?");
        if (paramIndex > 0) {
            String paramsStr = queryUrl.substring(paramIndex + 1, queryUrl.length());
            queryUrl = queryUrl.substring(0, paramIndex);
            String[] paramArray = paramsStr.split("&");

            for (int i = 0; i < paramArray.length; ++i) {
                String[] pair = paramArray[i].split("=", 2);
                if (pair.length == 2 && !"".equals(pair[0].trim())) {
                    buff.append(pair[0]);
                    buff.append("=");
                    buff.append(URLEncoder.encode(pair[1], "UTF-8"));
                    buff.append("&");
                }
            }
        }

        if (params != null && !params.isEmpty()) {
            Iterator i$ = params.keySet().iterator();

            while (i$.hasNext()) {
                String key = (String) i$.next();
                buff.append(key);
                buff.append("=");
                buff.append(URLEncoder.encode((String) params.get(key), "UTF-8"));
                buff.append("&");
            }
        }

        if (buff.length() > 0) {
            buff = buff.deleteCharAt(buff.length() - 1);
        }

        return new String[] { queryUrl, buff.toString() };
    }

    /**
     * get 方式发起http请求
     * @param queryUrl 请求url
     * @param params 参数
     * @return String
     * @throws IOException
     */
    public static String doGet(String queryUrl, Map<String, String> params) throws IOException {
        return doRequest(queryUrl, params, HttpMethod.GET);
    }

    /**
     * post 方式发起http请求
     * @param queryUrl
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String queryUrl, Map<String, String> params) throws IOException {
        return doRequest(queryUrl, params, HttpMethod.POST);
    }

}
