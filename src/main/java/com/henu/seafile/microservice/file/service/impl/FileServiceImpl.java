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

import java.io.File;
import java.io.IOException;
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
    private String authTokenUrl;

    @Value("${seafile.api.ping}")
    private String apiPing;
    @Value("${seafile.baseUrl}")
    private String seafileIp;

    /**
     * 测试seahub服务是否正常,测试ping接口
     *
     * @return
     */
    public ResponseModel testApi() {
        Map<String, String> map = new HashMap<>();
        map = SendRequestUtils.send(apiPing);
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
     * @param
     * @return
     */
    public ResponseModel uploadFile(String token, String repoId, String parentDir, File... files) {

        String uploadURL = getUploadLink(token, repoId);
        Map<String, String> responseResult = SendRequestUtils.send(token, uploadURL, parentDir, files);
        String responseCode = responseResult.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "上传失败");
        }
        String responseData = "{\"fileId\":" + "\"" + responseResult.get("responseData") + "\"" + "}";
        System.out.println(responseData);
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        System.out.println(jsonObject);
        return ResponseUtils.setReturn(200, jsonObject, "上传成功");
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
        Map<String, String> responseResult = SendRequestUtils.send(data, authTokenUrl, HttpMethod.POST.name());
        String responseCode = responseResult.get("responseCode");
        if (!"200".equals(responseCode)) {
            return ResponseUtils.setReturn(409, null, "获取token失败");
        }
        String responseData = responseResult.get("responseData");
        JSONObject jsonObject = JSONObject.parseObject(responseData);
        return ResponseUtils.setReturn(200, jsonObject, "获取token成功");
    }

    /**
     * 获取上传文件的URL
     *
     * @param token
     * @param repoId 存储库Id
     * @return
     */

    public String getUploadLink(String token, String repoId) {
        String getupLoadUrl = "http://10.12.37.209:8000/api2/repos/" + repoId + "/upload-link/"; //获取上传链接
        Map<String, String> responseResult = SendRequestUtils.send(getupLoadUrl, token);
        String responseData = responseResult.get("responseData").replaceAll("\"", "");//获取的数据中，即上传链接
        System.out.println(responseData);
        return responseData;
    }
}
