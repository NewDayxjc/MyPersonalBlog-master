package com.blog.hh.personalblog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.hh.personalblog.dto.CategoryDto;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper  extends BaseMapper<CategoryDto> {

//  @Insert("insert into category(name,upload_time,status)"
//      + " values(#{name},#{uploadTime},#{status})")
//  void insert(CategoryDto categoryDto);

  @Select("select name from category where name = #{name}")
  String findByName(@NotNull(message = "名称不能为空") String name);

  @Select("select * from category where status = 1")
  List<CategoryDto> getAll();

  @Select("select name from category where id =#{cateId} ")
  String selectByIdReturnName(Integer cateId);
}
