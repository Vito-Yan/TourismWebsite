package com.cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.dao.CarDao;
import com.cn.dao.CustomerDao;
import com.cn.pojo.Customer;

public class AutoLoginFilter  implements  Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

	       HttpServletRequest  request=(HttpServletRequest)req;
	       HttpServletResponse response=(HttpServletResponse)resp;
	       Cookie[] cookies=request.getCookies();
	       String account=null;
	       String password=null;
	       for(int i=0;cookies!=null && i<cookies.length;i++){
	    	   if(cookies[i].getName().equals("account")){  
	                account = cookies[i].getValue();  
	            }  
	            if(cookies[i].getName().equals("password")){  
	                password = cookies[i].getValue();  
	            }   
	    	   
	       }
	       CustomerDao  customerDao = new CustomerDao();
		   Customer customer = customerDao.findCustomer(account, password);
		   if(customer!=null){
			  HttpSession session=request.getSession();
			  session.setAttribute("account",account);
			  session.setAttribute("customerID",customer.getCustomerID());
			  int carLineCount=new CarDao().count(customer.getCustomerID());
			  session.setAttribute("clc", carLineCount);
			 
		   }
		    chain.doFilter(request, response);
		 
	      
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
