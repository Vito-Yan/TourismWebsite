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

import com.cn.dao.CarDao;
import com.cn.dao.LineDao;
import com.cn.dao.LineTypeDao;
import com.cn.dao.PictureDao;
import com.cn.pojo.Car;
import com.cn.pojo.Line;
import com.cn.pojo.LineType;
import com.cn.pojo.Picture;
import com.cn.utils.GeneralUtils;
import com.cn.utils.PagingUitls;

/**
 * 购物车管理
 *
 */
public class ShoppingCarManagerServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	        String  flag=request.getParameter("flag");
	        System.out.println(flag);
	        if("_seeCar".equals(flag)){
	        	
	        	this.seeShoppingCar(request, response);
	        }else if("_addCar".equals(flag)){
	        	
	        	this.AddtoShoppingCar(request, response);
	        }else if("_remove".equals(flag)){
	        	
	        	this.removeLineFromCart(request, response);
	        }
	}
	
	//查看购物车
	public  void  seeShoppingCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //获取当前页
	      HttpSession  session=request.getSession();
          int customerID=(Integer)session.getAttribute("customerID");
		  CarDao  carDao=new CarDao();
		  //获取当前用户点击的是第几页
		  String currentPage=request.getParameter("currentPage");
		  //当前登录用户购物车中线路的总数量
		  int totalRecords=carDao.count(customerID);
          PagingUitls pagingUitls=new PagingUitls();
          //执行分页操作
          pagingUitls.executePaging(currentPage, totalRecords, 5);
          LineDao lineDao=new LineDao();
          List<Car> carList=carDao.findCar(customerID, pagingUitls.beginSize(),5);
          List<Line> carLineList=new ArrayList<Line>();
          PictureDao pictureDao=new PictureDao();
          List<Picture> pictureList=new ArrayList<Picture>();
          for(Car car:carList){
        	  carLineList.add(lineDao.findLineById(car.getLineID()));
        	  pictureList.add(pictureDao.findPictrue(car.getLineID()).get(0));
         }
         LineTypeDao lineTypeDao=new LineTypeDao();
         List<LineType> listLineType=lineTypeDao.findTopLine(4);
         request.setAttribute("llt",listLineType); 
         request.setAttribute("picList",pictureList);
         request.setAttribute("carList",carList);
         request.setAttribute("currentPage",pagingUitls.getCurrentPage());
         request.setAttribute("carLineList", carLineList);
         request.setAttribute("bottomPage", pagingUitls.getTotalPages());
         request.getRequestDispatcher("./shoppingcart.jsp").forward(request, response);
         
	}
	
	//加入购物车
	public  void  AddtoShoppingCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //获取用户编号
          int customerID=((Integer)request.getSession().getAttribute("customerID"));
          String  lineID=request.getParameter("lineId");
          //获取出发日期
          CarDao carDao=new CarDao();
          Line line=carDao.findCardLine(customerID, lineID);
          if(line!=null){//说明购物中已经有了该线路不需要重复条件
        	  request.setAttribute("isHave","该线路已经在购物车中");
        	  request.getRequestDispatcher("LineManagerServlet?flag=_specific").forward(request, response);
          
          }else{//购物车中没有此线路，添加到购物车中
        	  Car car=new Car();
              car.setCustomerID(customerID);
              car.setLineID(lineID);
              car.setTime(GeneralUtils.dateConvertString(new Date(), GeneralUtils.DATEFORMAT_YMDHMS));
              int flag=carDao.addCar(car);
              if(flag>0){//添加成功
            	  //查询当前用户购物车的线路的总数量
            	  HttpSession session =request.getSession();
            	  //获取当前用户购物车中线路总数
            	  int CarLineCount=carDao.count(customerID);
            	  //将session中当前用户的购物的线路的数量修改为最新的数量
    			  session.setAttribute("clc", CarLineCount);
    			  request.setAttribute("msg","添加成功");
    			  request.getRequestDispatcher("LineManagerServlet?flag=_specific").forward(request, response);
              }else{//添加失败
            	  request.setAttribute("msg","添加失败，重新添加");
            	  request.getRequestDispatcher("LineManagerServlet?flag=_specific").forward(request, response);
              }
          }
	}
	
	//将旅游路线从购物车中删除
	public  void  removeLineFromCart(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		//获取当前页
		int  currentPage=Integer.parseInt(request.getParameter("currentPage"));
		int  carID=Integer.parseInt(request.getParameter("carID"));
		Car car=new Car();
		car.setCarID(carID);
		CarDao carDao=new CarDao();
		carDao.deleteCar(car);
		HttpSession session =request.getSession();
		int customerID=((Integer)request.getSession().getAttribute("customerID"));
	   	//获取当前用户购物车中线路总数
	    int CarLineCount=carDao.count(customerID);
	   	//将当前用户购物车限量的数量重新修改
	     session.setAttribute("clc", CarLineCount);
	     response.sendRedirect("ShoppingCarManagerServlet?flag=_seeCar&currentPage="+currentPage);
	  
	}
	

}
