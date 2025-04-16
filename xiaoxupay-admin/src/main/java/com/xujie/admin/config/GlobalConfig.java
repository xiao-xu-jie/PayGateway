package com.xujie.admin.config;

/**
 * @Author: Xujie
 * @Date: 2024/7/14 20:08
 * @Description:
 **/

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xujie.admin.auth.AuthInterceptor;
import com.xujie.admin.common.exception.AuthException;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Objects;

/**
 * mvc的全局处理
 *
 * @author: ChickenWing
 * @date: 2023/10/7
 */
@Configuration
@EnableScheduling
@EnableFormValidator
public class GlobalConfig extends WebMvcConfigurationSupport {

    @Resource
    Environment env;

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(mappingJackson2HttpMessageConverter());
    }


    /**
     * 添加自定义转换器
     *
     * @param registry
     */
    @Override
    protected void addFormatters(FormatterRegistry registry) {
    }

// 设置前缀
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**");
//    }

    /**
     * 自定义mappingJackson2HttpMessageConverter
     * 目前实现：空值忽略，空字段可返回
     */
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        //date格式化
        objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册sa-token的拦截器，打开注解式鉴权功能
        registry.addInterceptor(new AuthInterceptor(handle -> {
                    System.out.println("--------- 请求进入了拦截器，访问的 path 是：" + SaHolder.getRequest().getRequestPath());
                    try {
                        StpUtil.checkLogin();  // 登录校验，只有会话登录后才能通过这句代码
                    } catch (Exception e) {
                        throw new AuthException("请先登录", 401);
                    }
                }))
                .addPathPatterns("/**")
                .excludePathPatterns(Objects.requireNonNull(env.getProperty("sa-token.exclude-paths")).split(","));
    }

//    @Bean
//    public WxAppConfig wxAppConfig(Environment environment) {
//        return new WxAppConfig(environment);
//    }

    @Bean(name = "logExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("log-");
        executor.setKeepAliveSeconds(60);
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
