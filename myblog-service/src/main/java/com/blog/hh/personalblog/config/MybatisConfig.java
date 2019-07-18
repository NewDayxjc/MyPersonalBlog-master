package com.blog.hh.personalblog.config;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*author huangh
*date 2018/12/28 17:21
*description mybatis配置
*/
@Configuration
public class MybatisConfig {
  @Bean
  public PageHelper pageHelper(){
    PageHelper pageHelper = new PageHelper();
    //添加配置，也可以指定文件路径
    Properties p = new Properties();
    p.setProperty("offsetAsPageNum", "true");
    p.setProperty("rowBoundsWithCount", "true");
    p.setProperty("reasonable", "true");
    pageHelper.setProperties(p);
    return pageHelper;
  }

  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    return new ConfigurationCustomizer() {
      @Override
      public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration.setMapUnderscoreToCamelCase(true);//设置驼峰命名规则
      }
    };
  }
}
