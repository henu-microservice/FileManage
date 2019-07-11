package com.henu.seafile.microservice.account.controller;

import com.henu.seafile.microservice.account.service.AccountService;
//import com.henu.seafile.microservice.file.entity.AccountEntiy;
import com.henu.seafile.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/7/11 12:50
 */

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 创建一个新用户（仅管理员拥有该权限）
     *
     * @param account 传入的用户名
     * @param data    传入passwd，isStaff，isActive
     * @return
     */
    @PutMapping("/{username}")
    public ResponseModel creatAccount(@PathVariable("username") String username, @RequestBody String data) {
        return accountService.creatAccount(username, data);
    }


}
