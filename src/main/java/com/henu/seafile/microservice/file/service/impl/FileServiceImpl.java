package com.henu.seafile.microservice.file.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.henu.seafile.microservice.file.service.FileService;
import com.henu.seafile.util.ResponseModel;
import com.henu.seafile.util.ResponseUtils;
import com.henu.seafile.util.SendRequestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/13 17:06
 */

@Service
public class FileServiceImpl implements FileService {


    @Value("${seafile.api.obtainAuthToken}")
    private String getAuthTokenUrl;

    @Value("${seafile.api.getUploadLink}")
    private String getUploadLink;

    @Value("${seafile.api.ping}")
    private String getApiPing;

    @Value("${seafile.token}")
    private String token;

    @Value("${seafile.library.repoId}")
    private String repoId;

    /**
     * 测试seahub服务是否正常,测试ping接口
     * @return
     */
    public ResponseModel testApi() {
        Map<String, String> map = new HashMap<>();
        map = SendRequestUtils.send(getApiPing);
        String responseCode = map.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "测试失败");
        }
        String jsonObject = map.get("responseData").replaceAll("\"", "");
        return ResponseUtils.setReturn(200, jsonObject, "测试成功");
    }


    /**
     * 上传文件的接口实现方法，目前没有使用
     *
     * @param files
     * @return
     */
    public ResponseModel uploadFile(MultipartFile[] files) {

        Map<String, String> responseResult = SendRequestUtils.send(getUploadLink, token);
        String responseCode = responseResult.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "获取上传链接失败!");
        }
        String responseData = responseResult.get("responseData").replaceAll("\"", "");
//        System.out.println(responseData);
//        JSONObject jsonObject = JSONObject.parseObject(responseData);
//        return ResponseUtils.setReturn(200, jsonObject, "获取上传链接成功！");
        return null;
    }


    /**
     * 获取token的接口的实现方法
     *
     * @param json
     * @return
     */
    public ResponseModel getToken(String json) {
        Map<String, String> accountMap = new HashMap<>();
        JSONObject accountJson = JSON.parseObject(json);
        String username = accountJson.getString("username");
        String password = accountJson.getString("password");
        accountMap.put("username", username);
        accountMap.put("password", password);
        String data = JSON.toJSONString(accountMap);
        Map<String, String> responseResult = SendRequestUtils.send(data, getAuthTokenUrl, HttpMethod.POST.name());
        String responseCode = responseResult.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "获取token失败");
        }
        String responseData = responseResult.get("responseData");
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        return ResponseUtils.setReturn(200, jsonObject, "获取token成功");

    }


}
