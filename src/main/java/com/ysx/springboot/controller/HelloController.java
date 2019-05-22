package com.ysx.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysx.springboot.model.Product;
/**
 * 把本来的@RestController 改为@Controller。
 * 这时返回"hello"就不再是字符串，
 * 而是根据application.properties 中的视图重定向，到/WEB-INF/jsp目录下去寻找hello.jsp文件
 * 但教程没介绍application.properties  是怎样被加载的
 * @author admin
 *
 */
@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String  hello(Model m)  {
		m.addAttribute("name","ysx");
		System.out.println("hello springboot!"); 
		return "hello";
	} 
	
	 @RequestMapping("/test")
	    public String test(Model m) {
	        String htmlContent = "<p style='color:red'> 红色文字</p>";
	        Product currentProduct =new Product(5,"product e", 200);
	         Boolean testBoolean = true;
	        m.addAttribute("htmlContent", htmlContent);
	        m.addAttribute("currentProduct", currentProduct);
	        m.addAttribute("testBoolean", testBoolean);
	        
	        List<Product> ps = new ArrayList<>();
	        ps.add(new Product(1,"product a", 50));
	        ps.add(new Product(2,"product b", 100));
	        ps.add(new Product(3,"product c", 150));
	        ps.add(new Product(4,"product d", 200));
	        ps.add(currentProduct);
	        ps.add(new Product(6,"product f", 200));
	        ps.add(new Product(7,"product g", 200));       
	         
	        m.addAttribute("ps", ps);
	        
	        Date now = new Date();
	        m.addAttribute("now", now);
	        
	        return "test";
	    }	
	
}
