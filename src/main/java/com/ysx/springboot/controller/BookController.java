package com.ysx.springboot.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysx.springboot.dao.AccountDao;
import com.ysx.springboot.model.Account;

@Controller
public class BookController {
  @Autowired
	AccountDao accountDao;
  
  @RequestMapping("listAccount")
  public String listAccount(Model m) {
	 List<Account> accountLists= accountDao.findAll();
	 BigDecimal budgets =  new BigDecimal(0);
	 BigDecimal balances =  new BigDecimal(0);
	 for (Account a : accountLists) {
		 budgets = budgets.add(a.getBudget()); //预算合计
		 balances =  balances.add(a.getBalance());//余额合计
	 }
	 
	 Account total = new Account();
	 total.setId(0);
	 total.setName("合计");
	 total.setBudget(budgets);
	 total.setBalance(balances);
	 accountLists.add(total);
	 System.out.println(accountLists.size());
	 m.addAttribute("page",accountLists);
	
	  return "listAccount";
  }
  
  @RequestMapping("updateAccount")
  public String updateAccount(Model  m ,int id ) {
	 
	  Account a= accountDao.getOne(id);
	   m.addAttribute("c",a);
	  return "reduceBalance";
  }
  
  @RequestMapping("reduceBalance")
  public String reduceBalance(HttpServletRequest req) {
	 String aMount = req.getParameter("aMount");
	 int id = Integer.valueOf(req.getParameter("id"));
	 Account a = 	 accountDao.getOne(id);
	  a.setBalance(a.getBalance().subtract(new BigDecimal(aMount)));
//	  System.out.println(a);
	  accountDao.save(a);
	  
	  return "redirect:listAccount";
  }
  
  
}
