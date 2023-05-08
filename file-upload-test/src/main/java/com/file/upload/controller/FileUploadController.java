package com.file.upload.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试文件上传相关
 * @Author 211145187
 * @Date 2022/9/20 18:27
 **/
@RequestMapping("/fileUpload")
@RestController
public class FileUploadController {

    //测试方法1,测试把上传文件转换成base64Encoder编码方式输出
    @RequestMapping(value = "uploadLogo1")
    public void uploadLogo(@RequestParam("file") MultipartFile fileImage) {
        String base64EncoderImg = "";
        try {
            base64EncoderImg = Base64.encodeBase64String(fileImage.getBytes());
            base64EncoderImg = "data:image/png;base64," + base64EncoderImg;
            System.out.println(base64EncoderImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //测试方法2,测试把上传文件转换成base64Encoder编码方式输出
    @RequestMapping(value = "uploadLogo2")
    public void uploadLogo2(@RequestParam("file") MultipartFile fileImage) {
        String base64EncoderImg = "";
        byte[] buffer = null;
        try {
            InputStream inputStream = fileImage.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = inputStream.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            inputStream.close();
            bos.close();
            buffer = bos.toByteArray();
            base64EncoderImg = Base64.encodeBase64String(buffer);
            base64EncoderImg = "data:image/png;base64," + base64EncoderImg;
            System.out.println(base64EncoderImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
