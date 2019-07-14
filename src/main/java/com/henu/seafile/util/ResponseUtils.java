package com.henu.seafile.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/15 9:03
 */
public class ResponseUtils {
    public static ResponseModel setReponse(Map<String,String> map) {

        ResponseModel model = new ResponseModel();
        String data = map.get("responseData");
        int code = Integer.valueOf(map.get("responseCode"));
        JSONObject responseDataJsonObj = JSON.parseObject(data);

        //判断响应状态码
        if (201==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("执行成功");
            return model;
        }
        if (200==code) {
            model.setCode(code);
            model.setData(data);
            model.setMessage("执行成功");
            return model;
        }
        if (202==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("已接受请求，未处理");
            return model;
        }
        if (400==code) {
            model.setCode(code);
            model.setData(responseDataJsonObj);
            model.setMessage("无效的json");
            return model;
        }
        if (401==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("未经授权，或启用了验证，您没有提供足够的信息来验证该请求");
            return model;
        }
        if (403==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("无权限操作");
            return model;
        }
        if (404==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("资源不存在");
            return model;
        }
        if (409==code) {
            model.setCode(code);
            model.setData(null);
            model.setMessage("已存在此任务");
            return model;
        }
        if (422==code) {
            model.setCode(code);
            model.setData(responseDataJsonObj);
            model.setMessage("执行失败");
            return model;
        }

        model.setCode(code);
        model.setData(responseDataJsonObj);

        return model;
    }

    /**
     * controller 统一通过此处返回数据
     * @param code
     * @param message
     * @return
     */
    public static ResponseModel setReturn(int code, Object data, String message) {
        ResponseModel responseModel = new ResponseModel(code,data, message);
        return responseModel;
    }
}
