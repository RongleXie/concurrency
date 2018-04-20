package com.ronglexie.concurrency.controller;

import com.ronglexie.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ThreadLocal控制器
 *
 * @author ronglexie
 * @version 2018/4/21
 */
@Slf4j
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

	@RequestMapping("test")
	@ResponseBody
	public Long test(){
		return RequestHolder.getId();
	}
}
