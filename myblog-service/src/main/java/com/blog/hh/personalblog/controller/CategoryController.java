package com.blog.hh.personalblog.controller;

import com.blog.hh.personalblog.dto.CategoryDto;
import com.blog.hh.personalblog.result.Result;
import com.blog.hh.personalblog.result.ResultVo;
import com.blog.hh.personalblog.service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*date 2018/12/29 18:14
*description 分类模块
*/
@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /*添加分类*/
  @PostMapping("/add")
  public Result add(@RequestBody @Valid CategoryDto categoryDto,BindingResult result){
    if (result.hasErrors()){
      return ResultVo.error(result.getFieldError().getDefaultMessage());
    }
    categoryService.insert(categoryDto);
    return ResultVo.success();
  }
  /*查询所有分类*/
  @GetMapping("/find")
  public Result findAll(){
  List<CategoryDto> list = categoryService.getAll();
  return ResultVo.success(list);
  }

}
