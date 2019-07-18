package com.blog.hh.personalblog.service;

import com.blog.hh.personalblog.dto.CategoryDto;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CategoryService {

  void insert(@Valid CategoryDto categoryDto);

  List<CategoryDto> getAll();

  void countNum(@NotNull(message = "分类id不能为空") Integer cateId);
}
