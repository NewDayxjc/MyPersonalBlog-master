package com.blog.hh.personalblog.service;

import com.blog.hh.personalblog.dto.ArticleDto;
import com.blog.hh.personalblog.dto.CountDto;
import com.github.pagehelper.PageInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface ArticleService {
  void insert(ArticleDto articleDto);

  void update(@Valid ArticleDto articleDto);

  PageInfo<ArticleDto> getAll(ArticleDto articleDto);

  ArticleDto getOne(Integer id,HttpServletRequest request);

  void updateViews(List<CountDto> articleViews);

  void deleteById(Integer id);
}
