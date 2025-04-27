package com.zzy.controller;

import com.aliyuncs.exceptions.ClientException;
import com.zzy.pojo.Result;
import com.zzy.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

//    private static final String UPLOAD_DIR = "/Users/zhouziyan/Downloads/";

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException, ClientException {
        log.info("upload info: {}, {}, {}",name,age,file);

        if (!file.isEmpty()){

//            name might be the same which will cause file overwrite
//            file.transferTo(new File("/Users/zhouziyan/Downloads/"+file.getOriginalFilename()));

//            1.generate unique file name
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            originalFilename = UUID.randomUUID().toString().replace("-","") + extension;
/*
//            2.complete file path
            File targetFile = new File(UPLOAD_DIR + originalFilename);
//            3.if dir not exists, create one
            if (!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdir();
            }

//            4. save file
            file.transferTo(targetFile);*/

            String url = aliyunOSSOperator.upload(file.getBytes(), originalFilename);
            return Result.success(url);
        }

        return Result.error("upload failed.");
    }
}
