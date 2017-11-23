package com.cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionTimeoutFilter  implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	     HttpServletRequest req=(HttpServletRequest)request;    
	     String  flag=req.getParameter("flag");
	     if(((HttpServletRequest)request).getRequestURI().equals("/TLX/admin/adminLogin.jsp")||!((HttpServletRequest)request).getRequestURI().endsWith(".jsp")){
	    	 chain.doFilter(request, response);
	     }else{
	    	 if(!"adminLogin".equals(flag)){
		    	 
		    	 HttpServletRequest httpServletRequest = (HttpServletRequest)request;
					HttpSession httpSession = httpServletRequest.getSession();
				    String account=(String)httpSession.getAttribute("account");
				    response.setCharacterEncoding("GBK");
				
					if(account == null){
						response.setCharacterEncoding("GBK");
						((HttpServletResponse)response).getWriter().write("<script>alert('您尚未登录！请先登录，系统将自动跳转至登录页！');window.top.location.href='" + ((HttpServletRequest)request).getContextPath() + "/admin/adminLogin.jsp';</script>");
					}else{
						chain.doFilter(request, response);
					}
		     }else{
		    		chain.doFilter(request, response);
		    	 
		     }
	     }
	     
	
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
