package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.utils.UserThreadLocal;
import com.jt.vo.SysResult;




@Controller
@RequestMapping("/cart/")
public class CartController {
	
	
	@Reference(timeout = 3000,check = false)
	private DubboCartService dubboCartService;
	
	@RequestMapping("show")
	public String show(Model model,HttpServletRequest request) {
		User user = UserThreadLocal.get();
		Long userId = user.getId();
		List<Cart> cartList = dubboCartService.findListByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(Cart cart,HttpServletRequest request) {
		try {
			User user = UserThreadLocal.get();
			Long userId = user.getId();
			cart.setUserId(userId);
			dubboCartService.updateNum(cart);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId,HttpServletRequest request) {
		User user = UserThreadLocal.get();
		Long userId = user.getId();
		dubboCartService.deleteByItemId(itemId,userId);
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping("add/{itemId}")
	public String addCart(Cart cart,HttpServletRequest request) {
		User user = UserThreadLocal.get();
		Long userId = user.getId();
		cart.setUserId(userId);
		dubboCartService.insertCart(cart);
		return "redirect:/cart/show.html";
	}
}
