package com.ysx.springboot.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysx.springboot.dao.AccountDao;
import com.ysx.springboot.dao.BConsumeDetailDao;
import com.ysx.springboot.dao.BEntryAccountDetailDao;
import com.ysx.springboot.model.Account;
import com.ysx.springboot.model.BConsumeDetail;
import com.ysx.springboot.model.BEntryAccountDetail;

@Controller
public class BookController {
  @Autowired
	AccountDao accountDao;
  @Autowired
   BConsumeDetailDao bConsumeDetailDao;
  @Autowired 
  BEntryAccountDetailDao bEntryAccountDetailDao;
  
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
	 System.out.println();
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
	 String remark = req.getParameter("remark");//备注
	int compare =  new BigDecimal(aMount).compareTo(new BigDecimal(0));
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
	
	 if(remark !=null &&!"".equals(remark)&&compare>0) {
		 BConsumeDetail consumeDetail = new BConsumeDetail();
		 consumeDetail.setTotal(new BigDecimal(aMount).abs());
		 consumeDetail.setRemark(remark);
		 consumeDetail.setCreatetime(sdf.format(new Date()));;
		 bConsumeDetailDao.save(consumeDetail);
		 System.out.println("保存消费信息结束");
	 }else if(remark !=null &&!"".equals(remark)&&compare<0) {
		 BEntryAccountDetail  entryAccountDetail = new BEntryAccountDetail();
		 entryAccountDetail.setTotal(new BigDecimal(aMount).abs());
		 entryAccountDetail.setRemark(remark);
		 entryAccountDetail.setCreatetime(sdf.format(new Date()));
		 bEntryAccountDetailDao.save(entryAccountDetail);
		 System.out.println("保存入账信息结束");
	 }
	 
	 int id = Integer.valueOf(req.getParameter("id"));
	 Account a = 	 accountDao.getOne(id);
	  a.setBalance(a.getBalance().subtract(new BigDecimal(aMount)));
	  accountDao.save(a);
	  
	  return "redirect:listAccount";
  }
  
  
}
