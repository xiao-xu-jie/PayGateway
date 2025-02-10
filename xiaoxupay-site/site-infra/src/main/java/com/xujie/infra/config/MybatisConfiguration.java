package com.xujie.infra.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Xujie @Date: 2024/7/14 20:03 @Description:
 */
@Configuration
public class MybatisConfiguration {

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    // 打印sql
    mybatisPlusInterceptor.addInnerInterceptor(new MybatisPlusAllSqlLog());
    mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

    // 乐观锁插件
    mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    return mybatisPlusInterceptor;
  }
}
