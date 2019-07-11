package com.henu.seafile.microservice.file.service;

import com.henu.seafile.util.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/13 17:09
 */
public interface FileService {

    ResponseModel uploadFile(MultipartFile[] files);
    ResponseModel getToken(String json);
    ResponseModel testApi();


}
