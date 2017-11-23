package com.cn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.dao.CarDao;
import com.cn.dao.CustomerDao;
import com.cn.pojo.Customer;

public class CustomerManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String  flag=request.getParameter("flag");
	    System.out.println(flag);
	    if("_login".equals(flag)){	    
	    	this.login(request, response);
	    }else if("_register".equals(flag)){
	    	this.register(request, response);
	    }
	}
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   String account=request.getParameter("account");
		   String password=request.getParameter("password");
		   String timeout=request.getParameter("timeout");
		   CustomerDao  customerDao=new CustomerDao();
		   Customer customer=customerDao.findCustomer(account, password);
		   //账号密码正确
		   if(customer!=null){
			     HttpSession session=request.getSession();
			     session.setAttribute("account",account);
			     session.setAttribute("customerID",customer.getCustomerID());
				 //String timeout=request.getParameter("timeout");
			     if("on".equals(timeout)){
			    	 //创建安Cookie对象
				     Cookie cookie1=new Cookie("account",account);
				     Cookie cookie2=new Cookie("password",password);
				     //设置可以获取cookie的路径
				     cookie1.setPath("/");
				     cookie2.setPath("/");
				     cookie1.setMaxAge(60*60*24*30);
				     cookie2.setMaxAge(60*60*24*30);
				     response.addCookie(cookie1);
				     response.addCookie(cookie2);
			     }
			   CarDao carDao=new CarDao();
			   //获取当前用户购物车中旅游线路的总数量
			   int carLineCount=carDao.count(customer.getCustomerID());
			   session.setAttribute("clc", carLineCount);
			   request.getRequestDispatcher("LineManagerServlet?flag=_showLine").forward(request, response);
		   }else{
			   
			   request.setAttribute("msg","对不起账号或密码有误");
			   request.getRequestDispatcher("./login.jsp").forward(request, response);
		   }
	}
	//注册
	public  void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  account=request.getParameter("account");
		CustomerDao customerDao=new CustomerDao();
		Customer customer=customerDao.findCustomer(account, null);
		//该账号不存在
		if(customer==null){//
			String  password=request.getParameter("password");
			String  realName=request.getParameter("realname");
			String  IDCard=request.getParameter("IdCard");
			//获取表单提交的性别
			String  gender=request.getParameter("gender");
			String tel=request.getParameter("tel");
			//获取表单提的验证码
			String  code=request.getParameter("code");
			//获取Session中保存的验证码
			String sessionCode=(String)request.getSession().getAttribute("safeCode");
			//判断用户输入的验证码是否正确
			if(sessionCode.equals(code)){
				 customer=new Customer();
				 customer.setAccount(account);
				 customer.setPassword(password);
				 customer.setGender(gender);
				 customer.setIdentityID(IDCard);
				 customer.setTel(tel);
				 customer.setName(realName);
				 customerDao.addCustomer(customer);
				 request.setAttribute("msg","注册成功");
				
			}else{
				request.setAttribute("msg","验证码不正确");
			}
			
		}else{//该账号已经不注册
			request.setAttribute("msg","该账号已存在");
		}
		request.getRequestDispatcher("./register.jsp").forward(request, response);
		
	}
	
	

}
