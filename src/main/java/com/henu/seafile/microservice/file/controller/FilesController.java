package com.henu.seafile.microservice.file.controller;

import com.henu.seafile.microservice.file.service.FileService;
import com.henu.seafile.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 15:08
 */

@RestController
@RequestMapping("/api/my-libs/")
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

    /**
     * 上传文件
     *
     * @param request
     * @param repoId  在指定的某个资料库下
     * @return
     */
    @PostMapping("/upload-api/{repo-id}")
    public ResponseModel uploadMultipleFile(HttpServletRequest request,
                                            @PathVariable("repo-id") String repoId,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("parent_dir") String parentDir) {
        String originalFilename = file.getOriginalFilename();
        String path = "D:/" + originalFilename;
        File file1 = new File(path);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileService.uploadFile(request.getHeader("token"), repoId, parentDir, file1);
    }


}
