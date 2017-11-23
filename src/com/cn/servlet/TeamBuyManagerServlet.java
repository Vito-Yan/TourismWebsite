package com.cn.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cn.dao.LineDao;
import com.cn.dao.LineTypeDao;
import com.cn.dao.PictureDao;
import com.cn.dao.TouristDao;
import com.cn.pojo.Line;
import com.cn.pojo.LineType;
import com.cn.pojo.Picture;
import com.cn.utils.PagingUitls;

/**
       团购管理
 */
public class TeamBuyManagerServlet extends HttpServlet {

	private static final long serialVersionUID = -6412784539578742068L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String flag=request.getParameter("flag");
		
		if("_hundredRegiments".equals(flag)){
			
			this.hundredRegiments(request, response);
		}
		
	}
	
	//百团大战(查询所有当前在团购时间内的团购线路)
	public   void hundredRegiments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询所有团购路线
		LineDao lineDao=new LineDao();
		String currentPage=request.getParameter("currentPage");
		int totalRecords=lineDao.queryTeamCount(1);
		PagingUitls pagingUitls=new PagingUitls();
		pagingUitls.executePaging(currentPage, totalRecords, 9);
		//查询所有团购路线
		List<Line> allTeamLineList=lineDao.queryTeam(pagingUitls.beginSize(),9,1);	
		//查询该线路有多少人出游
		TouristDao touristDao=new TouristDao();
		List<Integer> countList=new ArrayList<Integer>();
		for(Line line:allTeamLineList){
			
			countList.add(touristDao.findTouristCount(line.getLineID()));
		}
		List<Picture> picList=new ArrayList<Picture>();
		    PictureDao picDao=new PictureDao();
		    for(Line line:allTeamLineList){
		    	picList.add(picDao.findPictrue(line.getLineID()).get(0));
		    }
		request.setAttribute("picList", picList);
		//查询页面导航显示的线路类型
		LineTypeDao lineTypeDao=new LineTypeDao();
        List<LineType> listLineType=lineTypeDao.findTopLine(4);
 	    request.setAttribute("ltl", listLineType);
		request.setAttribute("countList", countList);
	    request.setAttribute("currentPage",pagingUitls.getCurrentPage());
	    request.setAttribute("atlList",    allTeamLineList);
	    request.setAttribute("bottomPage", pagingUitls.getTotalPages());
	    request.getRequestDispatcher("./groupbuy.jsp").forward(request, response);
	}
	
}
