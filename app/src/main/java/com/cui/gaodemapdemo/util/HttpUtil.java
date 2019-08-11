package com.cui.gaodemapdemo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * HTTP的相关工具类,模拟POST和GET请求
 *
 * @author cui7dr by 2019/07/21
 */

public class HttpUtil {

    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // 直接返回true
            return true;
        }
    }

    /**
     * Post请求
     *
     * @param headerMap 请求头
     * @param paramsMap 请求体参数
     * @return
     */
    public String methodPost(Map<String, String> headerMap, Map<String, String> paramsMap) {
        StringBuffer sbf = new StringBuffer();
        //如果是https协议
        if (paramsMap.get("requestURL").startsWith("https")) {
            return this.methodHttpsPost(headerMap, paramsMap);
        }
        // 连续请求多次
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            HttpURLConnection connect = null;
            try {
                URL url = new URL(paramsMap.get("requestURL"));
                connect = (HttpURLConnection) url.openConnection();
                connect.setConnectTimeout(60000);
                connect.setReadTimeout(60000);
                connect.setDoInput(true);
                connect.setDoOutput(true);
                StringBuffer sbf_params = new StringBuffer();
                for (Iterator iterator = paramsMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(entry.getKey() + "") && !"retry".equalsIgnoreCase(entry.getKey() + "")) {
                        sbf_params.append(entry.getKey() + "=" + entry.getValue() + "&");
                    }
                }
                if (sbf_params.toString().endsWith("&")) {
                    sbf_params.delete(sbf_params.length() - 1, sbf_params.length());
                }
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    connect.setRequestProperty(entry.getKey() + "", entry.getValue() + "");
                }
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                bfw.write(sbf_params.toString());
                bfw.flush();
                BufferedReader bfr = null;
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                    }
                }
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    sbf.append(line.trim());
                }
                bfr.close();
                bfw.close();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //重试机制
            if (!"true".equalsIgnoreCase(headerMap.get("retry"))) {
                break;
            }
        }
        //没有查询到数据,调用https
        if (sbf.length() <= 0) {
            return this.methodHttpsPost(headerMap, paramsMap);
        }
        return sbf.toString();
    }

    /**
     * HttpsPost请求
     *
     * @param headerMap 请求头参数
     * @param paramsMap 请求体参数
     * @return
     */
    public String methodHttpsPost(Map<String, String> headerMap, Map<String, String> paramsMap) {
        StringBuffer sbf = new StringBuffer();
        // 连续请求多次
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            HttpsURLConnection connect = null;
            try {
                TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }};
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
                URL url = new URL(paramsMap.get("requestURL"));
                connect = (HttpsURLConnection) url.openConnection();
                connect.setHostnameVerifier(new TrustAnyHostnameVerifier());
                connect.setConnectTimeout(60000);
                connect.setReadTimeout(60000);
                connect.setDoInput(true);
                connect.setDoOutput(true);
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                StringBuffer sbf_params = new StringBuffer();
                for (Iterator iterator = paramsMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(entry.getKey() + "")) {
                        sbf_params.append(entry.getKey() + "=" + entry.getValue() + "&");
                    }
                }
                if (sbf_params.toString().endsWith("&")) {
                    sbf_params.delete(sbf_params.length() - 1, sbf_params.length());
                }
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(headerMap.get("requestURL"))) {
                        connect.setRequestProperty(entry.getKey() + "", entry.getValue() + "");
                    }
                }
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                bfw.write(sbf_params.toString());
                bfw.flush();
                BufferedReader bfr = null;
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                    }
                }
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    sbf.append(line.trim());
                }
                bfr.close();
                bfw.close();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //重试机制
            if (!"true".equalsIgnoreCase(headerMap.get("retry"))) {
                break;
            }
        }
        return sbf.toString();
    }

    /**
     * Post请求 请求url放到headerMap中
     *
     * @param postStr
     * @return
     */
    public String methodPost(Map<String, String> headerMap, String postStr) {
        StringBuffer sbf = new StringBuffer();
        //如果是https协议
        if (headerMap.get("requestURL").startsWith("https")) {
            return this.methodHttpsPost(headerMap, postStr);
        }
        // 连续请求多次
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            HttpURLConnection connect = null;
            try {
                URL url = new URL(headerMap.get("requestURL"));
                connect = (HttpURLConnection) url.openConnection();
                connect.setConnectTimeout(60000);
                connect.setReadTimeout(60000);
                connect.setDoInput(true);
                connect.setDoOutput(true);
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(headerMap.get("requestURL"))) {
                        connect.setRequestProperty(entry.getKey() + "", entry.getValue() + "");
                    }
                }
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(headerMap.get("requestURL"))) {
                        connect.setRequestProperty(entry.getKey() + "", entry.getValue() + "");
                    }
                }
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                bfw.write(postStr);
                bfw.flush();
                BufferedReader bfr = null;
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                    }
                }
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    sbf.append(line.trim());
                }
                bfr.close();
                bfw.close();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return this.methodHttpsPost(headerMap, postStr);
            }
        }
        //没有查询到数据,调用https
        if (sbf.length() <= 0) {
            return this.methodHttpsPost(headerMap, postStr);
        }
        return sbf.toString();
    }

    /**
     * HttpsPost请求 请求url放到headerMap中
     *
     * @param headerMap 请求头参数
     * @param postStr
     * @return
     */
    public String methodHttpsPost(Map<String, String> headerMap, String postStr) {
        StringBuffer sbf = new StringBuffer();
        // 连续请求多次
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            HttpsURLConnection connect = null;
            try {
                TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }};
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
                URL url = new URL(headerMap.get("requestURL"));
                connect = (HttpsURLConnection) url.openConnection();
                connect.setHostnameVerifier(new TrustAnyHostnameVerifier());
                connect.setConnectTimeout(60000);
                connect.setReadTimeout(60000);
                connect.setDoInput(true);
                connect.setDoOutput(true);
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(headerMap.get("requestURL"))) {
                        connect.setRequestProperty(entry.getKey() + "", entry.getValue() + "");
                    }
                }
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                bfw.write(postStr);
                bfw.flush();
                BufferedReader bfr = null;
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                    }
                }
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    sbf.append(line.trim());
                }
                bfr.close();
                bfw.close();
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //重试机制
            if (!"true".equalsIgnoreCase(headerMap.get("retry"))) {
                break;
            }
        }
        return sbf.toString();
    }

    /**
     * Get请求
     *
     * @param paramsMap
     * @return
     */
    public String methodGet(Map<String, String> paramsMap) {
        StringBuffer sbf = new StringBuffer();
        // 连续请求多次
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            StringBuffer sbf_params = new StringBuffer();
            HttpURLConnection connect = null;
            try {
                sbf_params.append(paramsMap.get("requestURL"));
                if (!sbf_params.toString().endsWith("?") && paramsMap.size() > 1) {
                    sbf_params.append("?");
                }
                for (Iterator iterator = paramsMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Entry entry = (Entry) iterator.next();
                    if (!entry.getKey().toString().equalsIgnoreCase("requestURL")) {
                        sbf_params.append(entry.getKey() + "=" + entry.getValue() + "&");
                    }
                }
                if (sbf_params.toString().endsWith("&")) {
                    sbf_params.delete(sbf_params.length() - 1, sbf_params.length());
                }
                URL url = new URL(sbf_params.toString());
                connect = (HttpURLConnection) url.openConnection();
                connect.setConnectTimeout(60000);
                connect.setReadTimeout(60000);
                connect.setAllowUserInteraction(true);
                connect.setDoInput(true);
                connect.setDoOutput(false);
                connect.setRequestProperty("Cookie", "Hm_lvt_4982d57ea12df95a2b24715fb6440726=1398138317; mstuid=1398138316637_9919; userId=10931889; XM_Hd_Start=1; Hm_lvt_7080c6a6aba51276281d5d595b080def=1398143044");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
                BufferedReader bfr = null;
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                    }
                }
                String line = "";
                while ((line = bfr.readLine()) != null) {
                    sbf.append(line.trim());
                }
                bfr.close();
                //响应码成功
                if (connect.getResponseCode() == 200) {
                    break;
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //重试机制
            if (!"true".equalsIgnoreCase(paramsMap.get("retry"))) {
                break;
            }
        }
        return sbf.toString();
    }

    /**
     * 上传文件
     *
     * @param headerMap
     * @param requestMap 上传文件的信息
     * @return
     */
    public String methodUploadFile(Map<String, String> headerMap, Map<String, String> requestMap) {
        String urlStr = headerMap.get("requestURL");
        if (!urlStr.endsWith("?")) {
            urlStr = urlStr + "?";
        }
        for (Iterator iterator = requestMap.entrySet().iterator(); iterator.hasNext(); ) {
            Entry entry = (Entry) iterator.next();
            String key = entry.getKey() + "";
            String value = entry.getValue() + "";
            urlStr = urlStr + "&" + key + "=" + value;
        }
        /* 返回值 */
        StringBuffer sbf = new StringBuffer();
        String filePath = requestMap.get("filePath");
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL(urlStr);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            // 设置为POST请求
            connect.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            connect.setDoOutput(true);
            connect.setDoInput(true);
            connect.setUseCaches(false);
            // 设置请求头参数
            connect.setRequestProperty("connection", "Keep-Alive");
            connect.setRequestProperty("Charsert", "UTF-8");
            connect.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(connect.getOutputStream());
            // 上传文件
            File file = new File(filePath);
            StringBuilder sbd = new StringBuilder();
            sbd.append(boundaryPrefix);
            sbd.append(BOUNDARY);
            sbd.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sbd.append("Content-Disposition: form-data;name=\"file\";filename=\"" + filePath + "\"" + newLine);
            sbd.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sbd.append(newLine);
            sbd.append(newLine);
            // 将参数头的数据写入到输出流中
            out.write(sbd.toString().getBytes());
            // 数据输入流,用于读取文件数据
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(newLine.getBytes());
            in.close();
            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sbf.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

}

