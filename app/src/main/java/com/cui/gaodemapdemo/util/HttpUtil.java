package com.cui.gaodemapdemo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
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
 * HTTP 相关工具类，模拟 GET 和 POST 请求
 *
 * @author cui7dr by 2019/07/21
 */

public class HttpUtil {

    public class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // 直接返回 True
            return true;
        }
    }

    /**
     * POST 请求
     *
     * @param headerMap 请求头
     * @param paramsMap 请求体参数
     * @return
     */
    public String methodPost(Map<String, String> headerMap, Map<String, String> paramsMap) {
        StringBuffer sbf = new StringBuffer();
        // 如果是 http 协议
        if (paramsMap.get("requestURL").startsWith("https")) {
            return this.methodPost(headerMap, paramsMap);
        }
        // 连续多次请求
        for (int i = 0; i < 1; i++) {
            sbf.delete(0, sbf.length());
            HttpsURLConnection connect = null;
            try {
                URL url = new URL(paramsMap.get("requestURL"));
                connect = (HttpsURLConnection) url.openConnection();
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
                bfw.write(paramsMap.toString());
                bfw.flush();
                BufferedReader bfr = null;
                // 响应码成功
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
            // 重试机制
            if (!"true".equalsIgnoreCase(headerMap.get("retry"))) {
                break;
            }
        }
        // 没有查询到数据，调用 https
        if (sbf.length() <= 0) {
            return this.methodHttpsPost(headerMap, paramsMap);
        }
        return sbf.toString();
    }

    /**
     * HttpsPost 请求
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
                TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                            }

                            @Override
                            public X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }
                        }
                };
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustAllCerts, new SecureRandom());
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
                        sbf_params.append(entry.getKey() + "" + "=" + entry.getValue() + "&");
                    }
                }
                if (sbf_params.toString().endsWith("&")) {
                    sbf_params.delete(sbf_params.length() - 1, sbf_params.length());
                }
                for (Iterator iterator = headerMap.entrySet().iterator(); iterator
                        .hasNext(); ) {
                    Entry me = (Entry) iterator.next();
                    if (!"requestURL".equalsIgnoreCase(headerMap.get("requestURL"))) {
                        connect.setRequestProperty(me.getKey() + "", me.getValue()
                                + "");
                    }
                }
                connect.setRequestProperty("Cookie", "Hm_lvt_2d57a0f88eed9744a82604dcfa102e49=1386575661; CNZZDATA5342694=cnzz_eid%3D1753424715-1386575827-http%253A%252F%252Fwww.btctrade.com%26ntime%3D1386750475%26cnzz_a%3D5%26ltime%3D1386750483033%26rtime%3D1; pgv_pvi=8171956224; __utma=252052442.1822116731.1386640814.1386741966.1386750468.3; __utmz=252052442.1386640814.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); wafenterurl=/; wafcookie=51a45a2229469ee92bbd8cc281e98e91; __utmb=252052442.1.10.1386750468; __utmc=252052442; wafverify=afe13eda6d99c7f141d7dd3966b59d9e; USER_PW=ab3f61ee826a95e51734cf7174100382; PHPSESSID=9f2c19d6ffd0ef808ba8bac0b74ab0f3; IESESSION=alive; pgv_si=s1618283520");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                bfw.write(sbf_params.toString());
                bfw.flush();
                BufferedReader bfr = null;
                // 响应码成功
                if (connect.getResponseCode() == 200) {
                    bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                } else {
                    if (connect.getErrorStream() != null) {
                        bfr = new BufferedReader(new InputStreamReader(connect.getErrorStream(), "UTF-8"));
                    } else {
                        bfr = new BufferedReader(new InputStreamReader(connect.getInputStream(), "Utf-8"));
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
            // 重试机制
            if (!"true".equalsIgnoreCase(headerMap.get("retry"))) {
                break;
            }
        }
        return sbf.toString();
    }
}
