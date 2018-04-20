package com.ronglexie.concurrency.filter;

import com.ronglexie.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 线程封闭
 * Ad-hoc线程封闭：程序控制实现，最糟糕，忽略
 * 堆栈封闭：局部变量，无并发问题
 * ThreadLocal线程封闭：特别好的线程封闭方法
 *
 * http过滤器
 * ThreadLocal
 *
 * @author ronglexie
 * @version 2018/4/19
 */
@Slf4j
public class HttpFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		log.info("httpFilter do filter : {}, {}",Thread.currentThread().getId(),request.getRequestURL());
		RequestHolder.add(Thread.currentThread().getId());
		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void destroy() {

	}
}
