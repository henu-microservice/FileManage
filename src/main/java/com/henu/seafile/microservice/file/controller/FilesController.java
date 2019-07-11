package com.henu.seafile.microservice.file.controller;

import com.henu.seafile.microservice.file.service.FileService;
import com.henu.seafile.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 15:08
 */

@RestController
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private FileService fileService;

    /**
     * ping命令
     * 测试seafile服务是否正常
     *
     * @return
     */
    @GetMapping("/test")
//    public Map<String, String> testApi() {
//        Map<String, String> map = new HashMap<>();
//        map = SendRequestUtils.send("http://10.12.37.209:8000/api2/ping/");
//        return map;
//    }
    public ResponseModel testApi() {
        return fileService.testApi();
    }

    /**
     * 获取用户token
     *
     * @param json 用户名和密码
     * @return
     */
    @PostMapping("/token")
    public ResponseModel getToken(@RequestBody String json) {
        return fileService.getToken(json);
    }


    @GetMapping("/fileUpload")
    public ResponseModel uploadMultipleFile(@RequestParam("file") MultipartFile[] files) {

        return fileService.uploadFile(files);
    }


}
