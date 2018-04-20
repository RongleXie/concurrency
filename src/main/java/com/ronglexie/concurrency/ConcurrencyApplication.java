package com.ronglexie.concurrency;

import com.ronglexie.concurrency.filter.HttpFilter;
import com.ronglexie.concurrency.interceptor.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	/**
	 * 注册Filter过滤器
	 *
	 * @param
	 * @return org.springframework.boot.web.servlet.FilterRegistrationBean
	 * @author wxt.xqr
	 * @version 2018/4/20
	 */
	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HttpFilter());
		filterRegistrationBean.addUrlPatterns("/threadLocal/*");
		return filterRegistrationBean;
	}

	/**
	 * 注册Interceptor拦截器
	 *
	 * @param registry
	 * @return void
	 * @author wxt.xqr
	 * @version 2018/4/20
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
