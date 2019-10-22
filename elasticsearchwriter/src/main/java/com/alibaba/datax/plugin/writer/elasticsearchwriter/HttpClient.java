/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alibaba.datax.plugin.writer.elasticsearchwriter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Admin
 */
public class HttpClient {

    private static final Logger logger = Logger.getLogger(HttpClient.class.getName());
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();

    private HttpClient() {
    }

    /**
     * 向服务器发送get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        String result = "";
        //实例化一个HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        //设置请求响应配置
        httpGet.setConfig(requestConfig);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            //如果返回值为200，则请求成功，可以通过TestNG做判断 HttpStatus.SC_OK
            //获取到请求的内容
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            httpGet.releaseConnection();//关闭响应
            //httpClient.close();
        }
        return result;
    }
}
