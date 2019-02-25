package com.jack.crawl.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static Map<Object, Object> sendGet(String url) {
        Map<Object, Object> result = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String contentType = null;
        BufferedReader in = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            //url中中文字符的处理
            String fa = url.substring(0,url.lastIndexOf("/"));
            String da = url.substring(url.lastIndexOf("/")+1);
            da = URLEncoder.encode(da,"UTF-8");
            url = fa+"//"+da;
            System.out.println(url);
            URL readUrl = new URL(url);
            URLConnection conn = readUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置连接时间
            conn.setConnectTimeout(5000);
            //建立实际连接
            conn.connect();
            //读取连接后的响应
            contentType = conn.getContentType();
            is = conn.getInputStream();
            //字节流到字符流的装换
            isr = new InputStreamReader(is);
            //得到连接后的字符流
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("content", sb.toString());
        result.put("contentType", contentType);
        return result;
    }

    public static Map<Object, Object> sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        Map<Object, Object> result = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String contentType = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置连接时间
            conn.setConnectTimeout(5000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            //读取连接后的响应
            contentType = conn.getContentType();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        result.put("content", sb.toString());
        result.put("contentType", contentType);
        return result;
    }
}
