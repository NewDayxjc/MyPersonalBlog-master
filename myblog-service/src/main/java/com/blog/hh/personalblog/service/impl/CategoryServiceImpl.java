package com.blog.hh.personalblog.service.impl;

import com.blog.hh.personalblog.dto.CategoryDto;
import com.blog.hh.personalblog.enums.BlogEnum;
import com.blog.hh.personalblog.exception.BlogException;
import com.blog.hh.personalblog.mapper.CategoryMapper;
import com.blog.hh.personalblog.service.CategoryService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl  implements CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Override
  public void insert(@Valid CategoryDto categoryDto) {
    categoryDto.setUploadTime(new Date());
    categoryDto.setStatus(1);
    categoryDto.setNumber(0);
    String byName = categoryMapper.findByName(categoryDto.getName());
    if (byName!=null){
      throw new BlogException(BlogEnum.CATEGORY_EXIST);
    }
    categoryMapper.insert(categoryDto);
  }

  @Override
  public List<CategoryDto> getAll() {
    return categoryMapper.getAll();
  }

  @Override
  public void countNum(@NotNull(message = "分类id不能为空") Integer cateId) {

  }
}
