package com.cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ×Ö·û±àÂë¹ıÂËÆ÷
 *
 */
public class EncodingFilter  implements  Filter{
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//ÉèÖÃÇëÇó±àÂëÎªutf-8
		req.setCharacterEncoding("GBK");
		chain.doFilter(req, resp);
		
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
	

}
