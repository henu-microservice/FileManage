package com.henu.seafile.microservice.account.service;

import com.henu.seafile.util.ResponseModel;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/7/11 11:34
 */
public interface AccountService {
    ResponseModel creatAccount(String account, String data);
}
