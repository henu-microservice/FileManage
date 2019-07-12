package com.henu.seafile.microservice.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.henu.seafile.microservice.account.service.AccountService;
import com.henu.seafile.util.ResponseModel;
import com.henu.seafile.util.ResponseUtils;
import com.henu.seafile.util.SendRequestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/7/11 11:32
 */

@Service
public class AccountServiceImpl implements AccountService {
    @Value("${seafile.api.obtainAuthToken}")
    private String getAuthTokenUrl;
    @Value("${seafile.api.account}")
    private String getAccountApi;

    /**
     * @param token
     * @param username 用户名
     * @param data     json结构体：包括passwd,is_staff,is_active
     * @return 响应体
     */
    public ResponseModel createAccount(String token, String username, String data) {
        username = username + "@123.com";
        Map<String, String> accountMap = new HashMap<>();
        JSONObject accountJson = JSON.parseObject(data);
        String password = accountJson.getString("password");
        String isStaff = accountJson.getString("is_staff");
        String isActive = accountJson.getString("is_active");
        accountMap.put("password", password);
        accountMap.put("is_staff", isStaff);
        accountMap.put("is_active", isActive);
        String accountData = JSON.toJSONString(accountMap);
        Map<String, String> responseResult = SendRequestUtils.send(accountData, getAccountApi + username + "/", token, HttpMethod.PUT.name());
        //String responseCode = responseResult.get("responseCode");
//        if ("401".equals(responseCode)) {
//            return ResponseUtils.setReturn(401, null, "Token无效");
//        }
//        if ("200".equals(responseCode)) {
//            return ResponseUtils.setReturn(201, null, "已存在该用户");
//        }
//        if (!"200".equals(responseCode) && !"201".equals(responseCode)) {
//            return ResponseUtils.setReturn(409, null, "创建失败");
//        }
        //String responseData = responseResult.get("responseData");
        //JSONObject jsonObject = JSONObject.parseObject(responseData);
        //return ResponseUtils.setReturn(200, jsonObject, "创建用户成功");
        return ResponseUtils.setReponse(responseResult);
    }

    public ResponseModel deleteAccount(String token, String username) {
        username = username + "@123.com";
        Map<String, String> responseResult = SendRequestUtils.send(username, getAccountApi + username + "/", token, HttpMethod.DELETE.name());
//        String responseCode = responseResult.get("responseCode");
//        if ("401".equals(responseCode)) {
//            return ResponseUtils.setReturn(401, null, "Token无效");
//        }
//        if ("202".equals(responseCode)) {
//            return ResponseUtils.setReturn(202, null, "帐户不存在或已被删除");
//
//        }
//        if (!"200".equals(responseCode) && !"201".equals(responseCode)) {
//            return ResponseUtils.setReturn(409, null, "删除失败");
//        }
        //String responseData = responseResult.get("responseData");
        //JSONObject jsonObject = JSONObject.parseObject(responseData);
        //return ResponseUtils.setReturn(200, jsonObject, "删除成功");
        return ResponseUtils.setReponse(responseResult);
    }
}
