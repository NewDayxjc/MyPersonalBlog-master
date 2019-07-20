package com.blog.hh.personalblog.result;

import com.blog.hh.personalblog.enums.BlogEnum;
import java.util.Collections;
import java.util.stream.Collectors;

/**
*description 返回对象
*/
public class ResultVo {
  /*返回对象不含数据*/
  public static Result success(){
    Result<Object> result = new Result<>();
    result.setCode(200);
    result.setMessage("成功！");
    result.setData(Collections.emptyList());
    return result;
  }
  /*返回数据包含data*/
  public static Result success(Object data){
    Result<Object> result = new Result<>();
    result.setCode(200);
    result.setMessage("成功！");
    result.setData(data);
    return result;
  }
  /*返回数据包含data*/
  public static Result success(Object data, BlogEnum blogEnum){
    Result<Object> result = new Result<>();
    result.setCode(blogEnum.getCode());
    result.setMessage(blogEnum.getMessage());
    result.setData(data);
    return result;
  }

  /*返回错误对象*/
  public static Result error(String message){
    Result<Object> result = new Result<>();
    result.setCode(500);
    result.setMessage(message);
    return result;
  }
  /*返回错误对象*/
  public static Result error(BlogEnum blogEnum){
    Result<Object> result = new Result<>();
    result.setCode(blogEnum.getCode());
    result.setMessage(blogEnum.getMessage());
    return result;
  }
}
