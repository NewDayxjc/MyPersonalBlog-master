package com.blog.hh.personalblog.utils;

import com.blog.hh.personalblog.result.Result;
import com.blog.hh.personalblog.result.ResultVo;
import org.springframework.validation.BindingResult;
/**
*author huangh
*date 2018/12/29 18:22
    *description 验证请求参数
    */
public class ValidUtil {

  public static Result verify(BindingResult result){
    if (result.hasErrors()){
      return ResultVo.error(result.getFieldError().getDefaultMessage());
    }
    return null;
  }
}
