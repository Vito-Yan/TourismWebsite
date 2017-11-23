package com.cn.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cn.dao.CustomerDao;
import com.cn.dao.LineDao;
import com.cn.dao.LineTypeDao;
import com.cn.dao.PictureDao;
import com.cn.pojo.Customer;
import com.cn.pojo.Line;
import com.cn.pojo.LineType;
import com.cn.pojo.Picture;
import com.cn.utils.FileUploadUtils;
import com.cn.utils.GeneralUtils;
import com.cn.utils.PagingUitls;
import com.jspsmart.upload.Request;


/**
 * 管理员管理
 *
 */
public class AdminManagerServlet extends HttpServlet {

	private static final long serialVersionUID = -8574004126323498963L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String  flag = request.getParameter("flag");
	    System.out.println(flag);
	    if("adminLogin".equals(flag)){
	    	
	    	this.adminLogin(request, response);	    	
	    }else if("_addLine".equals(flag)){
	    	
	    	this.addLine(request, response);
	    }else if("_showType".equals(flag)){
	    	
	    	this.seeLineType(request, response);
	    }else if("_showall".equals(flag)){
	    	
	    	this.queryAllLine(request, response);
	    }else if("_showone".equals(flag)){
	    	
	    	this.querySpecifyLine(request, response);
	    }else if("_update".equals(flag)){
	    	
	    	this.updateSpecifyLine(request, response);
	    }else if("_addType".equals(flag)){
	    	
	    	this.addLineType(request, response);
	    }else if("_seeType".equals(flag)){
	    	
	    	this.queryAllLine(request, response);
	    }else if("_seeOnetype".equals(flag)){
	    	
	    	this.querySpecifyLineLineType(request, response);
	    }else if("_updateType".equals(flag)){
	    	
	    	this.updateSpecifyLineType(request, response);
	    }else if("_seteam".equals(flag)){
	    	
	    	this.setTeamBuyInfo(request, response);
	    }else if("_seeTeam".equals(flag)){
	    	
	    	this.queryTeamBuy(request, response);
	    }else if("_logout".equals(flag)){
	    	
	    	this.adminLogout(request, response);
	    }
	}
	//管理员登录
	public void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      String account = request.getParameter("username");
	      String password = request.getParameter("passwrod");
	      String verifycode = request.getParameter("verifycode");
	      HttpSession  session = request.getSession();
	      //判断验证码是否正确
	      if(!(session.getAttribute("safeCode").equals(verifycode))){
	    	    request.setAttribute("msg","验证码不正确");
	    	    request.getRequestDispatcher("./admin/adminLogin.jsp").forward(request, response);
	      }else{
	    	  CustomerDao customerDao = new CustomerDao();
	    	  Customer customer = customerDao.findCustomer(account, password);
	    	  if(customer!=null){
	    		  //账号密码正确
	    		  if(customer.getType()==1){
		    		  //管理员登录
	    			  session.setAttribute("account", account);
	    			  request.setAttribute("msg", "恭喜你登录成功");
		    		  response.sendRedirect("./admin/index.jsp");
		    	  }else {
		    		  //非管理无法登录后台系统
		    		  request.setAttribute("msg", "您非管理员无法登录");
		    		  request.getRequestDispatcher("./admin/adminLogin.jsp").forward(request, response);
		    	  }
	    		  
	    		  
	    	  }else{
	    		  
	    		  request.setAttribute("msg", "账号或密码不正确");
	    		  request.getRequestDispatcher("./admin/adminLogin.jsp").forward(request, response);
	    	  }
	    	 
	    	  
	      }
	}
	//查询所有线路类型
	public void  seeLineType (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LineTypeDao  lineTypeDao = new LineTypeDao();
		List<LineType> lineTypeList = lineTypeDao.findTopLine(0);
		request.setAttribute("lineTypeList" ,lineTypeList);
		String  target = request.getParameter("target");
		if("_up".equals(target)){
			
			request.getRequestDispatcher("./admin/updateLine.jsp").forward(request, response);
		}else if("_team".equals(target)){
			

			request.getRequestDispatcher("./admin/setTeambuyInfo.jsp").forward(request, response);
		}
		else{
			
			request.getRequestDispatcher("./admin/addLine.jsp").forward(request, response);
		}
	
	  }
		
	
	
   //管理员添加旅游路线
	public void addLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = GeneralUtils.getUUID();
		FileUploadUtils fileUploadUtils = new FileUploadUtils();
		fileUploadUtils.init(this, request, response,no);
		boolean flag = fileUploadUtils.upload();
		if(flag){
			Request req = fileUploadUtils.getRequest();
			String lineName = req.getParameter("lineName");
			String   lineTypeId = req.getParameter("lineType");
			int  days = Integer.parseInt(req.getParameter("days"));
			double price = Double.parseDouble(req.getParameter("price"));
			String vehicle = req.getParameter("vehicle");
			String reason = req.getParameter("reason");
			String arrange = req.getParameter("arrange");
			String introduction = req.getParameter("introduction");
			String onTime = GeneralUtils.dateConvertString(new Date(), GeneralUtils.DATEFORMAT_YMDHMS);
			Line line = new Line();
			line.setOnTime(onTime);
			line.setArrange(arrange);
			line.setLineID(no);
			line.setPrice(price);
			line.setIntroduction(introduction);
			line.setVehicle(vehicle);
			line.setLineName(lineName);
			line.setReason(reason);
			line.setDays(days);
			line.setIntroduction(introduction);
			line.setLineTypeID(lineTypeId);
			LineDao lineDao = new LineDao();
	
			//添加线路线
			lineDao.addLine(line);
			String[] picIntorductions = req.getParameterValues("picIntroduction");
            List<Picture> pictureList = new ArrayList<Picture>();
			for(int i=0;i<picIntorductions.length;i++){
				Picture picture = new Picture();
				picture.setIntroduction(picIntorductions[i]);
				picture.setName(fileUploadUtils.getFileNames().get(i));
				picture.setNo(no);
				PictureDao pictureDao=new PictureDao();
				//添加线路对应的图片信息
				pictureList.add(picture);
				pictureDao.addPicture(picture);
			}
			request.setAttribute("msg","线路添加成功");
			request.setAttribute("line", line);
			request.setAttribute("pList", pictureList);

		}
		request.getRequestDispatcher("AdminManagerServlet?flag=_showType").forward(request, response);
		
	}
	
	//管理员查看所有路线
	public void  queryAllLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   LineDao  lineDao = new LineDao();
		   String currentPage = request.getParameter("currentPage");
		   int totalRecords = lineDao.queryAllLineCount();
		   PagingUitls pagingUitls = new PagingUitls();
		   pagingUitls.executePaging(currentPage, totalRecords, 5);
	       List<Line> lineList = lineDao.queryAllLine(pagingUitls.beginSize(), 5);
	       request.setAttribute("currentPage",pagingUitls.getCurrentPage());
	       request.setAttribute("lineList",   lineList);
	       request.setAttribute("bottomPage", pagingUitls.getTotalPages());
	       request.getRequestDispatcher("./admin/inner.jsp").forward(request, response);
	}
	//管理修改路线前先查看指定路线
	public  void  querySpecifyLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String op = request.getParameter("op");
		 String  lineId = request.getParameter("no");
		 LineDao lineDao = new LineDao();
		 Line  line = lineDao.findLineById(lineId); 
		 PictureDao  pictureDao = new PictureDao();
		 //获取本路线对应的图片
		 List<Picture>  pictureList = pictureDao.findPictrue(lineId);
		 line.setVehicle(line.getVehicle().trim());
		 if(line.getBeginTime()!=null){
			 line.setBeginTime(GeneralUtils.dateConvertString(GeneralUtils.StringConvertDate(line.getBeginTime(), GeneralUtils.DATEFORMAT_YMD), GeneralUtils.DATEFORMAT_YMD));
		 }
		 if(line.getEndTime()!=null){
			 line.setEndTime(GeneralUtils.dateConvertString(GeneralUtils.StringConvertDate(line.getEndTime(), GeneralUtils.DATEFORMAT_YMD), GeneralUtils.DATEFORMAT_YMD)); 
			 
		 }
	

		 request.setAttribute("line", line);
		 request.setAttribute("picList",pictureList);
		 LineTypeDao  lineTypeDao = new LineTypeDao();
	     List<LineType> lineTypeList = lineTypeDao.findTopLine(0);
	     request.setAttribute("lineTypeList" ,lineTypeList);
		 if("team".equals(op)){//如果no值为team为设置团购跳转至设置团购页面
			 
			 request.getRequestDispatcher("./admin/setTeambuyInfo.jsp").forward(request, response);
				
			 
		 }else{//否则跳转至修改线路页面
			 request.getRequestDispatcher("./admin/updateLine.jsp").forward(request, response);
				
			 
		 }
		 
		
	}
	//管理员修改线路信息
	public  void  updateSpecifyLine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  lineId = request.getParameter("lineID");
		FileUploadUtils fileUploadUtils = new FileUploadUtils();
		fileUploadUtils.init(this, request, response,lineId);
		boolean flag = fileUploadUtils.upload();
		if(flag){
			Request req = fileUploadUtils.getRequest();
			String lineName = req.getParameter("lineName");
			String  lineTypeId = req.getParameter("lineType");
			int  days=Integer.parseInt(req.getParameter("days"));
			double price=Double.parseDouble(req.getParameter("price"));
			String vehicle = req.getParameter("vehicle");
			String reason = req.getParameter("reason");
			String arrange = req.getParameter("arrange");
			String introduction = req.getParameter("introduction");
			Line line = new Line();
			line.setArrange(arrange);
			line.setLineID(lineId);
			line.setPrice(price);
			line.setIntroduction(introduction);
			line.setVehicle(vehicle);
			line.setLineName(lineName);
			line.setReason(reason);
			line.setDays(days);
			line.setIntroduction(introduction);
			line.setLineTypeID(lineTypeId);
			LineDao lineDao = new LineDao();
            lineDao.updateLine(line);
            //获取参数名名为hid的数组
            String[] picIdAndNames = req.getParameterValues("hid");
			PictureDao  pictureDao = new PictureDao();
            try {
    			for(String idName:picIdAndNames){
    				 String[]  str = idName.split("-");
    				 if(str.length>1){
    					 int  id = Integer.parseInt(str[0]);//得到图片 pictureID
    					 String name = str[1];//图片名称
    					 Picture  picture = new Picture();
    					 picture.setPictureID(id);
    					 picture.setName(name);
    				     //修改线路信息
    					 pictureDao.updatePicture(picture); 
    				 }
    			}
    			
    			//修改简介
    			String[] picIntroductions=req.getParameterValues("picIntroduction");
    			for(int i=0;i<picIntroductions.length;i++){
    				int id=Integer.parseInt(picIdAndNames[i].split("-")[0]);//获取图片编号
    				Picture picture=new Picture();
    				picture.setPictureID(id);
    				picture.setIntroduction(picIntroductions[i]);
    				pictureDao.updatePicture(picture);
    			}
    			List<Picture> picList=new PictureDao().findPictrue(lineId);
    			request.setAttribute("picList", picList);
    			request.setAttribute("line", line);
    			
    			
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				request.getRequestDispatcher("AdminManagerServlet?flag=_showType&target=_up").forward(request, response);
			}
		}			
	}
	//管理员添加线路类型
    public  void  addLineType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String lineTypeId="";
	     for(int i=0;i<5;i++){
				
				lineTypeId+=GeneralUtils.getUUID().split("-")[i];
	     }
		 FileUploadUtils fileUploadUtils=new FileUploadUtils();

	     fileUploadUtils.init(this, request, response,lineTypeId);
		 boolean flag=fileUploadUtils.upload();
		 if(flag){
				Request req=fileUploadUtils.getRequest();
				String typeName=req.getParameter("typeName");
				String ontime=GeneralUtils.dateConvertString(new Date(), GeneralUtils.DATEFORMAT_YMDHMS);
				LineType lineType=new LineType();
				lineType.setTypeName(typeName);
				lineType.setOntime(ontime);
			    lineType.setIcon(fileUploadUtils.getFileNames().get(0));
				lineType.setLineTypeID(lineTypeId);
				LineTypeDao lineTypeDao=new LineTypeDao();
				lineTypeDao.addLineType(lineType);
				request.setAttribute("lineType", lineType);
				request.setAttribute("msg", "添加成功");
					
		 }
		 request.getRequestDispatcher("./admin/addLineType.jsp").forward(request, response);
	}
   //管理员查看所有线路类型
    public void  queryAllLineType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   LineTypeDao  lineTypeDao=new LineTypeDao();
		   String currentPage=request.getParameter("currentPage");
		   int totalRecords=lineTypeDao.queryLineTypeCount();
		   PagingUitls pagingUitls=new PagingUitls();
		   pagingUitls.executePaging(currentPage, totalRecords, 5);
	       List<LineType> lineTypeList=lineTypeDao.queryAllLineType(pagingUitls.beginSize(), 5);
	       request.setAttribute("currentPage",pagingUitls.getCurrentPage());
	       request.setAttribute("lineTypeList",   lineTypeList);
	       request.setAttribute("bottomPage", pagingUitls.getTotalPages());
	       request.getRequestDispatcher("./admin/innerType.jsp").forward(request, response);
	}
    
    //管理查看指定线路类型
    public void  querySpecifyLineLineType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	 String  lineTypeId=request.getParameter("id");
		 LineTypeDao lineTypeDao=new LineTypeDao();
		 LineType  lineType=lineTypeDao.queryLineType(lineTypeId);
		 System.out.println(lineType);
		 request.setAttribute("lineType", lineType);
		 request.getRequestDispatcher("./admin/updateLineType.jsp").forward(request, response);
    }
    //管理修改线路类型信息
  	public  void  updateSpecifyLineType(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  		String  typeId=request.getParameter("typeId");
  		FileUploadUtils fileUploadUtils=new FileUploadUtils();
  		fileUploadUtils.init(this, request, response,typeId);
  		boolean flag=fileUploadUtils.upload();
  		if(flag){
  			Request req=fileUploadUtils.getRequest();
  			String typeName=req.getParameter("typeName");
  			String idIcon=req.getParameter("hid");
  			String yes=req.getParameter("yes");
            String[]  idIcons=idIcon.split("-");
            LineType lineType=new LineType();
            lineType.setTypeName(typeName);
            lineType.setLineTypeID(typeId);
            if("yes".equals(yes)){
            	//如果勾线中了复选框说明将LineType表中的time时间修改为当前时间
            	lineType.setOntime(GeneralUtils.dateConvertString(new Date(), GeneralUtils.DATEFORMAT_YMDHMS));
            }
            if(idIcons.length>1){
            	String icon=idIcons[1];
            	lineType.setIcon(icon);
            }
  	        LineTypeDao lineTypeDao=new LineTypeDao();
  	        lineTypeDao.updateLineType(lineType);
  	        LineType type=lineTypeDao.queryLineType(typeId);
  	        request.setAttribute("msg", "修改成功");
  	        request.setAttribute("lineType", type);
  		}
  	  request.getRequestDispatcher("./admin/updateLineType.jsp").forward(request, response);
  	
  	}
  	//设置团购信息
	public  void  setTeamBuyInfo(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
		
		String  lineId=request.getParameter("lineID");
		FileUploadUtils fileUploadUtils=new FileUploadUtils();
		fileUploadUtils.init(this, request, response,lineId);
		boolean flag=fileUploadUtils.upload();
		if(flag){
			Request req=fileUploadUtils.getRequest();
			String lineName=req.getParameter("lineName");
			String  lineTypeId=req.getParameter("lineType");
			int  days=Integer.parseInt(req.getParameter("days"));
			double price=Double.parseDouble(req.getParameter("price"));
			//获取团购价格
			double teamPrice=Double.parseDouble(req.getParameter("teamprice"));
			//获取开团时间
			String beginTime=req.getParameter("beginTime");
			//获取闭团时间
			String endTime=req.getParameter("endTime");
			String vehicle=req.getParameter("vehicle");
			String reason=req.getParameter("reason");
			String arrange=req.getParameter("arrange");
			String introduction=req.getParameter("introduction");
			Line line= new Line();
			line.setTeamBuy(1);//1为团购
			line.setTeamBuyPrice(teamPrice);
			line.setBeginTime(beginTime);
			line.setEndTime(endTime);
			line.setArrange(arrange);
			line.setLineID(lineId);
			line.setPrice(price);
			line.setIntroduction(introduction);
			line.setVehicle(vehicle);
			line.setLineName(lineName);
			line.setReason(reason);
			line.setDays(days);
			line.setIntroduction(introduction);
			line.setLineTypeID(lineTypeId);
			LineDao lineDao=new LineDao();
            lineDao.updateTeamLine(line);
			String[] picIdAndNames=req.getParameterValues("hid");
			PictureDao  pictureDao=new PictureDao();
			if(picIdAndNames!=null){
				
				for(String idName:picIdAndNames){
					 String[]  str=idName.split("-");
					 if(str.length>1){
						 int  id=Integer.parseInt(str[0]);//得到图片 pictureID
						 String name=str[1];//图片名称
						 Picture  picture=new Picture();
						 picture.setPictureID(id);
						 picture.setName(name);
					
						 pictureDao.updatePicture(picture); 
					 }
				}
			}
			
			//修改简介
			String[] picIntroductions=req.getParameterValues("picIntroduction");
			if(picIntroductions!=null){
			for(int i=0;i<picIntroductions.length;i++){
				int id=Integer.parseInt(picIdAndNames[i].split("-")[0]);//获取图片编号
				Picture picture=new Picture();
				picture.setPictureID(id);
				picture.setIntroduction(picIntroductions[i]);
				pictureDao.updatePicture(picture);
			}}
			List<Picture> picList=new PictureDao().findPictrue(lineId);
			request.setAttribute("picList", picList);
			request.setAttribute("line", line);
			
			
		
		}
		request.getRequestDispatcher("AdminManagerServlet?flag=_showType&target=_team").forward(request, response);
		
	}
  	
	//管理员查看所有团购线路
	public  void  queryTeamBuy(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {
		   LineDao  lineDao = new LineDao();
		   String currentPage = request.getParameter("currentPage");
		   //查询所有团购总数
		   int totalRecords = lineDao.queryTeamBuyCount(1);
		   PagingUitls pagingUitls = new PagingUitls();
		   pagingUitls.executePaging(currentPage, totalRecords, 5);
		   //查询所有团购的线路
	       List<Line> lineList = lineDao.findTeamBuyLine(pagingUitls.beginSize(), 5, 1);
	       request.setAttribute("currentPage",pagingUitls.getCurrentPage());
	       request.setAttribute("lineList",   lineList);
	       request.setAttribute("bottomPage", pagingUitls.getTotalPages());
	       request.getRequestDispatcher("./admin/innerTeam.jsp").forward(request, response);
	}
     //管理员退出操作
	public void  adminLogout(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		HttpSession session=request.getSession();
		session.removeAttribute("account");//移除会话中的account属性
		session.invalidate();//将当前会话session失效
		response.setCharacterEncoding("GBK");
		response.getWriter().write("<script>alert('您已安全退出后台管理系统！');window.top.location.href='" + getServletContext().getContextPath() + "/admin/adminLogin.jsp';</script>");
	} 
	
	
}
