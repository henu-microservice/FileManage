package com.henu.seafile.microservice.file.controller;

import com.henu.seafile.util.SendRequestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 15:08
 */

@RestController
@RequestMapping("/api")
public class FilesController {

    /**
     * ping命令
     * 测试seafile服务是否正常
     * @return
     */
    @GetMapping("/test")
    public Map<String, String> testApi(){
        Map<String,String> map = new HashMap<>();
        map = SendRequestUtils.send("http://10.12.36.165:8000/api2/ping/");
        return map;
    }

//    @PostMapping("/account/token")
//    public ResponseEntity getToken(@RequestBody ResponseEntity account){
//        System.out.println("account: " + account.toString());
//        return account;
//    }
}
