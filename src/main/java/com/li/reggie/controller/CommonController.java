package com.li.reggie.controller;

import com.li.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Author LI
 * @create 2022/6/26 19:04
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        File file1 = new File(basePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        String fileName = UUID.randomUUID() + suffix;
        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            log.error("找不到文件目录");
            return R.error("上传失败");
        }
        return R.success(fileName);
    }
    @GetMapping ("/download")
    public void download(String name, HttpServletResponse response){

        try {
            FileInputStream fileInputStream = new FileInputStream(basePath + name);
            ServletOutputStream outputStream=response.getOutputStream();
            byte[] bytes = new byte[1024];

            int len=0;
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            fileInputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
