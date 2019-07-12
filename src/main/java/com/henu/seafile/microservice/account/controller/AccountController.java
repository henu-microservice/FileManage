package com.henu.seafile.microservice.account.controller;

import com.henu.seafile.microservice.account.service.AccountService;
//import com.henu.seafile.microservice.file.entity.AccountEntiy;
import com.henu.seafile.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @param username 传入的用户名
     * @param data     传入passwd，isStaff，isActive
     * @return
     */
    @PutMapping("/{username}")
    public ResponseModel createAccount(HttpServletRequest request, @PathVariable("username") String username, @RequestBody String data) {
        return accountService.createAccount(request.getHeader("token"), username, data);
    }

    /**
     * 删除用户(仅管理员拥有该权限)
     *
     * @param request
     * @param username
     * @return
     */
    @DeleteMapping("/{username}")
    public ResponseModel deleteAccount(HttpServletRequest request, @PathVariable("username") String username) {
        return accountService.deleteAccount(request.getHeader("token"), username);
    }

}
