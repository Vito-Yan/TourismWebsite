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
import com.cn.utils.TypeEnum;

public class LineManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	         String  flag=request.getParameter("flag");
	         System.out.println(flag);
	          if("_showLine".equals(flag)){
	        	 
	        	 this.showLine(request, response);
	         }else if("_partLine".equals(flag)){
	        	 
	        	 this.showPartLine(request, response);
	        	 
	         }else if("_specific".equals(flag)){
	        	 this.showSpecificLine(request, response);
	        	 
	         }else if("_hot".equals(flag)){
	        	 
	        	 this.showAllHotLine(request, response);
	         }else if("_allLine".equals(flag)){
	        	 
	        	 this.showLineTypeOfLine(request, response);
	         }else if("_team".equals(flag)){
	        	 
	        	 this.showLineTypeOfTeamBuyLine(request, response);
	         }
	}
	//显示主页面线路类型及类型及类型中的线路
	public void  showLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            //查询总出游人数
		    int  touristCount=new TouristDao().findAllTouristCount();
		    LineDao lineDao=new LineDao();
		    LineTypeDao lineTypeDao=new LineTypeDao();
		    List<LineType> listLineType=lineTypeDao.findTopLine(5);;
		    //显示主页旅游产品分类的前5条优先类型对应的线路
		   // Map<LineType,List<Line>> lineListMap=new HashMap<LineType,List<Line>>();
		    List<List<Line>> listLineList=new ArrayList<List<Line>>();
		    for(LineType lineType:listLineType){
		    	
		    	List<Line> lineList=lineDao.findLine(lineType.getLineTypeID(),6);
		        listLineList.add(lineList);
		    }
		    request.setAttribute("listType", listLineType);
		    request.setAttribute("llList",listLineList);
		    request.setAttribute("ltl", listLineType);
		    //查询显示页面前四条的团购
		   // List<Line> teamBuylineList=lineDao.findLine(1, 1, 4);
		   // request.setAttribute("tbList", teamBuylineList);
		    //一日游
		    List<Line>  oneDaysLineList=lineDao.findLineByDays(1, 9);
		    //两日游
		    List<Line>  twoDasyLineList=lineDao.findLineByDays(2, 9);
		    //三日游
		    List<Line>  threeDasyLineList=lineDao.findLineByDays(3, 9);
		    request.setAttribute("odll", oneDaysLineList);
		    request.setAttribute("tdll", twoDasyLineList);
		    request.setAttribute("thdll",threeDasyLineList);
			//最热门的前3条线路
		    List<Line> hostLineList=lineDao.findAllHotLine(3);
		    PictureDao pictureDao=new PictureDao();
            List<Picture> hotLinePicList=new ArrayList<Picture>();
	        //获取最热门的三条线路的图片
	        for(Line line:hostLineList){
	        	hotLinePicList.add(pictureDao.findPictrue(line.getLineID()).get(0));
	        }
	        request.setAttribute("hpicList", hotLinePicList);
	        request.setAttribute("hostLineList",hostLineList);
	        //查询团购订单次数前4名的团购线路
	        List<Line> top4TeamLineList=lineDao.queryTeamBuyLineList(0,4,1);	
	        List<Picture> picList=new ArrayList<Picture>();
	        
	        for(Line line:top4TeamLineList){
	        	   picList.add(pictureDao.findPictrue(line.getLineID()).get(0));
	        }
	        request.setAttribute("picList", picList);
	        request.setAttribute("t4tll", top4TeamLineList);
	        request.setAttribute("count", touristCount);
		    request.getRequestDispatcher("./main.jsp").forward(request, response);
	
	}
	//显示首页部分旅游类型显示信息
	public void  showPartLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   LineTypeDao lineTypeDao=new LineTypeDao();
		   List<LineType> listLineType=lineTypeDao.findTopLine(4);
		   //获取线路类型ID
	       String  lineTypeID=request.getParameter("lineTypeID");
		   LineDao lineDao=new LineDao();
		   List<Line> partLineList=lineDao.findLine(lineTypeID, 6);
		   List<Integer> countList=new ArrayList<Integer>();
		   TouristDao  touristDao=new TouristDao();
		   for(Line line:partLineList){
			   //将查询出的每条线路的出行总人数添加countList元素中
			   countList.add(touristDao.findTouristCount(line.getLineID()));
		   }
		    List<Picture> picList=new ArrayList<Picture>();
	        PictureDao pictureDao=new PictureDao();
	        for(Line line:partLineList){
	        	   picList.add(pictureDao.findPictrue(line.getLineID()).get(0));
	        }
	        request.setAttribute("picList", picList);
		   request.setAttribute("countList", countList);
		   request.setAttribute("pll", partLineList);
		   request.setAttribute("llt",listLineType); 
		   request.getRequestDispatcher("./linetype.jsp").forward(request, response);
    }
	//显示具体线路详情
	public void  showSpecificLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LineDao lineDao=new LineDao();
	    LineTypeDao lineTypeDao=new LineTypeDao();
	    List<LineType> listLineType=lineTypeDao.findTopLine(5);
	    //显示主页旅游产品分类的前5条优先类型对应的线路
	      request.setAttribute("ltl", listLineType);
		  String   lineId=request.getParameter("lineId");
          Line line=lineDao.findLineById(lineId);
          request.setAttribute("line", line);
          PictureDao pictureDao=new PictureDao();
          List<Picture> pictureList=pictureDao.findPictrue(lineId);
          request.setAttribute("picList",pictureList);
          TouristDao  touristDao=new TouristDao();
          //查询该条线路出游总人数
          int count=touristDao.findTouristCount(lineId);
          request.setAttribute("count", count);
          request.getRequestDispatcher("./checkline.jsp").forward(request, response);
	}
	//显示所有线路类型最热门前5条路线和当前查看线路类型最热线路前10条
	public  void  showAllHotLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    String    lineTypeID=request.getParameter("lineTypeId");
		    LineDao lineDao=new LineDao();
		    String typeName=request.getParameter("typeName");
		    List<Line> hostLineList=lineDao.findAllHotLine(5);
	        request.setAttribute("hostLineList",hostLineList);
	        request.setAttribute("lineTypeId", lineTypeID);
	        request.setAttribute("typeName", typeName);
			//查询某一线路类型中的热门线路的前10条数据
		    List<Line> hotLineOfTypeList=lineDao.findHotLnie(lineTypeID, 10);
		    //获取当前旅游线路前10条线路的四张图片的第一张存放到picList中
		    List<Picture> picList=new ArrayList<Picture>();
		    PictureDao picDao=new PictureDao();
		    for(Line line:hotLineOfTypeList){
		    	picList.add(picDao.findPictrue(line.getLineID()).get(0));
		    	
		    }
		    this.showLineTypeOfTeamBuyLine(request, response);
		    LineTypeDao lineTypeDao=new LineTypeDao();
            List<LineType> listLineType=lineTypeDao.findTopLine(4);
    	    request.setAttribute("ltl", listLineType);
		    request.setAttribute("picList",picList);
		    request.setAttribute("htpLineList", hotLineOfTypeList);
	        request.getRequestDispatcher("./lineclassify.jsp").forward(request, response);
	}
	//显示某一线路类型全部线路
	public  void  showLineTypeOfLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  String   lineTypeID=request.getParameter("lineTypeId");
		  LineDao lineDao=new LineDao();
		  String currentPage=request.getParameter("currentPage");

		   int totalRecords=lineDao.findTypeLineCount(lineTypeID);
		   PagingUitls pagingUitls=new PagingUitls();
		   pagingUitls.executePaging(currentPage, totalRecords, 8);
		    //当前线路类型所有线路
		   List<Line> lineList=lineDao.findLineByLineTypeID(lineTypeID,pagingUitls.beginSize(), 8);
		   List<Picture> picList=new ArrayList<Picture>();
		   PictureDao picDao=new PictureDao();
		    for(Line line:lineList){
		    	picList.add(picDao.findPictrue(line.getLineID()).get(0));
		    	
		    }
		   request.setAttribute("picList",picList);
		   request.setAttribute("currentPage",pagingUitls.getCurrentPage());
	       request.setAttribute("lineList",   lineList);
	       request.setAttribute("bottomPage", pagingUitls.getTotalPages());
	       request.setAttribute("lineTypeId", lineTypeID);
	       request.getRequestDispatcher("./linetypeinner.jsp").forward(request, response);
	}
	
	
	//显示某一线路类型团购线路
		public  void  showLineTypeOfTeamBuyLine(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			  String   lineTypeID=request.getParameter("lineTypeId");
			  LineDao lineDao=new LineDao();
			  String currentPage=request.getParameter("currentPage");
			   int totalRecords=lineDao.findTeamTypeLineCount(lineTypeID, 1);
			   PagingUitls pagingUitls=new PagingUitls();
			   pagingUitls.executePaging(currentPage, totalRecords, 3);
			    //当前线路类型所有团购线路
			   List<Line> teamLineList=lineDao.findLine(lineTypeID, 1, pagingUitls.beginSize(), 3);	
			   System.out.println(teamLineList);
			   List<Picture> picList=new ArrayList<Picture>();
			   PictureDao picDao=new PictureDao();
			   for(Line line:teamLineList){
			    	picList.add(picDao.findPictrue(line.getLineID()).get(0));
			    	
			   }
			   request.setAttribute("pList",picList);
		       request.setAttribute("currentPage",pagingUitls.getCurrentPage());
		       request.setAttribute("tlList",   teamLineList);
		       request.setAttribute("bottomPage", pagingUitls.getTotalPages());
		       request.setAttribute("lineTypeId", lineTypeID);
		     
		}
		
	
		
				

}
