package com.blog.hh.personalblog.controller;

import com.blog.hh.personalblog.Wrapper.FastDFSClientWrapper;
import com.blog.hh.personalblog.enums.BlogEnum;
import com.blog.hh.personalblog.result.Result;
import com.blog.hh.personalblog.result.ResultVo;
import com.blog.hh.personalblog.utils.base64ToMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
*author huangh
*date 2019/1/6 10:12
*description 上传文件
*/
@RestController
@RequestMapping("/upload")
public class UploadController {
  @Autowired
  private FastDFSClientWrapper fastDFSClientWrapper;

  @RequestMapping("file")
  public String upload(@RequestParam("file") MultipartFile file) throws Exception {
    String imgUrl = fastDFSClientWrapper.uploadFile(file);
    return imgUrl;
  }

  @RequestMapping("fileBase64")
  public Result uploadByBase64(@RequestBody  String base64) throws Exception {
    MultipartFile multipartFile = base64ToMultipart.base64ToMultipart(base64);
    String imgUrl = fastDFSClientWrapper.uploadFile(multipartFile);
    return ResultVo.success(imgUrl,BlogEnum.UPLOAD_SUCCESS);
  }

}
