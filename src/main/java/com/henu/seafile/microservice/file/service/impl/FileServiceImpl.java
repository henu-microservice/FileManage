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
    private String obtainAuthTokenUrl;
//    @Value("${seafile.user}")
//    private String user;
//    @Value("${seafile.password}")
//    private String password;

    public ResponseModel uploadFile(MultipartFile[] files) {
//        ResponseModel response = getToken();
//        String data = response.getData();
        return null;
    }


    public ResponseModel getToken(String json) {
        Map<String, String> accountMap = new HashMap<>();
//        accountMap.put("username", username);
//        accountMap.put("password", password);
        JSONObject accountJson = JSON.parseObject(json);
        String username = accountJson.getString("username");
        String password = accountJson.getString("password");
        accountMap.put("username",username);
        accountMap.put("password",password);
        String data = JSON.toJSONString(accountMap);
        Map<String, String> responseResult = SendRequestUtils.send(data, obtainAuthTokenUrl, HttpMethod.POST.name());
        String responseCode = responseResult.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "获取token失败");
        }
        String responseData = responseResult.get("responseData");
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        return ResponseUtils.setReturn(200, jsonObject, "获取token成功");

    }


}
