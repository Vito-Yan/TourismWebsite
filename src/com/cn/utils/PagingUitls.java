package com.cn.utils;



public class PagingUitls {
	
	//查询记录数
	 private int totalRecords;
	
	//第几页
	 private int currentPage;
	
	//每页多少条记录
	 private int pageSize;
	
	//总页数
	 public int getTotalPages(){
	    
		 return totalRecords%pageSize==0?totalRecords/pageSize:(totalRecords/pageSize+1);
	    	
	   
	 }
	
	 //起始条数
	 public int  beginSize(){
		 int a=(this.currentPage-1)*pageSize;
		  return a;
	 }

	 public   void  executePaging(String currentPage,int totalRecords,int pageSize){
		   this.currentPage=currentPage==null?1:Integer.parseInt(currentPage);
		   this.totalRecords=totalRecords;
		   this.pageSize=pageSize;
	 }

	
	
	
	 public int getTotalRecords() {
	    return totalRecords;
     }
	 
	 public int getCurrentPage() {
	   return currentPage;
	 }
	
	 public int getPageSize() {
	   return pageSize;
	 }
	
	

}
