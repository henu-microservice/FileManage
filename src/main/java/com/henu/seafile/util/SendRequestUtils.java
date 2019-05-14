package com.henu.seafile.util;

import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 14:35
 */
public class SendRequestUtils {
    public static final String CODE = "responseCode";
    public static final String DATA = "responseData";
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType PATCH_JSON
            = MediaType.parse("application/strategic-merge-patch+json; charset=utf-8");

    public static final MediaType FILE_TYPE
            = MediaType.parse("multipart/form-data; charset=utf-8");

    private SendRequestUtils() {
    }

    /**
     * 发送请求
     *
     * @param data   调用接口时，客户端传入的json请求数据
     * @param url    调用的接口
     * @param method 请求方法
     * @return 所调用的接口的返回的数据：map集合
     * responseCode 响应码
     * responseData 响应数据
     */
    public static Map<String, String> send(String data, String url, String method) {
        RequestBody body = null;
        HashMap<String, String> map = new HashMap<>();

        if (StringUtils.isEmpty(data) && !"GET".equals(method)) {
            body = RequestBody.create(JSON, "{}");
        } else if ("PATCH".equals(method)) {
            body = RequestBody.create(PATCH_JSON, data);
        } else if (!StringUtils.isEmpty(data) && !"GET".equals(method)) {
            body = RequestBody.create(JSON, data);
        }
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .build();
        return getData(client, request);
    }

    /**
     * GET 请求
     *
     * @param url .
     * @return .
     */
    public static Map<String, String> send(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return getData(client, request);
    }

    /**
     * 发送请求后，获取相应的数据
     *
     * @param okHttpClient
     * @param request
     * @return
     */
    public static Map<String, String> getData(OkHttpClient okHttpClient, Request request) {
        Map<String, String> map = new HashMap<>();
        Response response = null;
        ResponseBody body = null;
        try {
            response = okHttpClient.newCall(request).execute();
            map.put(CODE, String.valueOf(response.code()));
            body = response.body();
            map.put(DATA, body.string());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (body != null) {
                body.close();
            }
            if (response != null) {
                response.close();
            }
        }
        return map;

    }

//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map = SendRequestUtils.send("http://10.12.36.165:8000/api2/ping/");
//        System.out.println(map);
//
//
//    }
}
