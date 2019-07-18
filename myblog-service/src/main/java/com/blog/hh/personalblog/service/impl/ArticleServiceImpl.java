package com.blog.hh.personalblog.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.blog.hh.personalblog.dto.ArticleDto;
import com.blog.hh.personalblog.dto.CategoryDto;
import com.blog.hh.personalblog.dto.CountDto;
import com.blog.hh.personalblog.mapper.ArticleMapper;
import com.blog.hh.personalblog.mapper.CategoryMapper;
import com.blog.hh.personalblog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class ArticleServiceImpl  implements ArticleService {

  private static final Integer ZERO = 0;

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private  ArticleMapper articleMapper;

  @Autowired
  private CategoryMapper categoryMapper;


  @Override
  @CacheEvict(value = "articleAll" ,allEntries=true)
  public void insert(ArticleDto articleDto) {
    articleDto.setUserId(1);
    articleDto.setUploadTime(new Date());
    @NotNull(message = "markdown格式的内容不能为空") String markdownContent = articleDto
        .getMarkdownContent();
    String content = markdownContent.replaceAll("#", "")
    .replaceAll("`", "").replaceAll("\\*", "");
    articleDto.setContent(content);
    CategoryDto categoryDto = categoryMapper.selectById(articleDto.getCateId());
    categoryDto.setNumber(categoryDto.getNumber()+1);
    categoryMapper.updateById(categoryDto);
    articleMapper.insert(articleDto);
  }

  @Override
  @CacheEvict(value = "articleAll" ,allEntries=true)
  public void update(@Valid ArticleDto articleDto) {
    articleDto.setUploadTime(new Date());
    articleMapper.updateById(articleDto);
  }

  @SuppressWarnings("unchecked")
  public PageInfo<ArticleDto> getAll(ArticleDto articleDto) {
//    //获取所有内容
    List<CountDto> articleViews = redisTemplate.opsForList().range("articleViews", articleDto.getPageNum(),articleDto.getPageSize());
    String orderBy = "upload_time  desc ";//按照排序字段 倒序 排序

    if (articleDto.getIsOffset()!= ZERO){
      PageHelper.offsetPage(articleDto.getIsOffset(),articleDto.getPageNum(),orderBy);
    }else{
      PageHelper.startPage(articleDto.getPageNum(),articleDto.getPageSize(), orderBy);
    }

    List<ArticleDto> list = Collections.EMPTY_LIST;
    if (!StringUtils.isEmpty(articleDto.getSearchKey())){
      list = articleMapper.selectList(new EntityWrapper().eq("status", 1).like("title", articleDto.getSearchKey()));
    }else {
      list = articleMapper.selectList(new EntityWrapper().eq("status", 1));
    }
    PageInfo<ArticleDto> pageInfo = new PageInfo<>(list);
    List<ArticleDto> select = pageInfo.getList();
    // 用category id查询名称 设置缓存后的点击率
    for (ArticleDto article : select) {
      // TODO 需要优化sql
      String name = categoryMapper.selectByIdReturnName(article.getCateId());
      if (name==null){
        log.error("查询的分类名为空,可能是该分类名不存在,分类id={}" ,articleDto.getCateId());
      }
      article.setCategoryName(name);
      for (CountDto articleView : articleViews) {
        if (article.getId().equals(articleView.getArticleId())){
          article.setView(articleView.getCount());
        }
      }
      articleDto.setCategoryName(name);
    }
    return pageInfo;
  }

  @Override
  public ArticleDto getOne(Integer id,HttpServletRequest request) {
    return articleMapper.selectById(id);
  }

  public ArticleDto findOne(Integer id){
    return articleMapper.selectById(id);
  }

  @Override
  public void updateViews(List<CountDto> articleViews) {
    articleViews.forEach(e->{
      ArticleDto one = findOne(e.getArticleId());
      one.setView(e.getCount());
      update(one);
    });
  }

  @Override
  public void deleteById(Integer id) {
    articleMapper.deleteById(id);
  }

  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
      //多次反向代理后会有多个ip值，第一个ip才是真实ip
      int index = ip.indexOf(",");
      if(index != -1){
        return ip.substring(0,index);
      }else{
        return ip;
      }
    }
    ip = request.getHeader("X-Real-IP");
    if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
      return ip;
    }
    return request.getRemoteAddr();

  }
}
