package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/")
public class IndexController {
	@RequestMapping("{pageName}")
	public String page(@PathVariable String pageName) {
		return pageName;
	}
}
