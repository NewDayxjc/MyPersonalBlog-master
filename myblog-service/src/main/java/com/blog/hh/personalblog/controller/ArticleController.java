package com.blog.hh.personalblog.controller;

import com.blog.hh.personalblog.dto.ArticleDto;
import com.blog.hh.personalblog.enums.BlogEnum;
import com.blog.hh.personalblog.exception.BlogException;
import com.blog.hh.personalblog.result.Result;
import com.blog.hh.personalblog.result.ResultVo;
import com.blog.hh.personalblog.service.ArticleService;
import com.blog.hh.personalblog.service.CategoryService;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*author huangh
*date 2019/1/9 18:16
*description 文章模块
*/
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
  @Autowired
  private ArticleService articleService;


  @Autowired
  private RedisTemplate redisTemplate;

  @RequestMapping("/find")
  public Result getAll( ArticleDto articleDto){
    PageInfo<ArticleDto> info = articleService.getAll(articleDto);
    return ResultVo.success(info);
  }

  @RequestMapping("/add")
  public Result add(@RequestBody @Valid ArticleDto articleDto,BindingResult result, HttpServletRequest request){
    if (result.hasErrors()){
      return ResultVo.error(result.getFieldError().getDefaultMessage());
    }
    articleService.insert(articleDto);
    log.info("文章插入成功");
    return ResultVo.success(articleDto.getId());
  }

  @RequestMapping("/update")
  public Result update(@RequestBody @Valid ArticleDto articleDto,BindingResult result){
    if (result.hasErrors()){
      return ResultVo.error(result.getFieldError().getDefaultMessage());
    }
    articleService.update(articleDto);
    return ResultVo.success();
  }

  @RequestMapping("/get")
  public Result findOne( Integer id, HttpServletRequest request) {
    if (StringUtils.isEmpty(id)) {
      throw new BlogException(BlogEnum.PARAM_EMPTY);
    }
    ArticleDto articleDto = articleService.getOne(id, request);
    return ResultVo.success(articleDto);
  }

  @RequestMapping("/delete")
  public Result delete(@RequestBody ArticleDto articleDto){
    if(StringUtils.isEmpty(articleDto.getId())){
      return  ResultVo.error(BlogEnum.PARAM_EMPTY);
    }
    articleService.deleteById(articleDto.getId());
    return ResultVo.success();
  }
}
