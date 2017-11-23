package com.cn.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileUploadUtils {
	private  Request req;
    private  SmartUpload smartUpload;
	private  String path;
	private  HttpServletRequest request;
	private  List<String> fileNames=new ArrayList<String>();

    public   Request getRequest(){
    	return this.req;
    }
    public void init(HttpServlet servlet,HttpServletRequest request,HttpServletResponse response,String dir) throws ServletException{
    	path=this.getRootPath()+"/"+servlet.getServletConfig().getInitParameter("SAVEPATH")+"/"+dir+"/";
        this.request=request;
		/*实例化JspSmartUpload组件中的SmartUpload对象
		   该类对象的作用是实现上传*/
	    smartUpload = new SmartUpload();
		//smartUpload对象进行初始化
		smartUpload.initialize(servlet.getServletConfig(), request, response);
		
		//设置所有上传文件总的大小
		smartUpload.setTotalMaxFileSize(1024*1024*10*4);
		//设置单个上传文件的大小为
		smartUpload.setMaxFileSize(1024*1024*10);
    }
	public  boolean  upload() throws IOException, ServletException {
		
		try {

			//调用upload方法实现文件
			smartUpload.upload();
			this.req=smartUpload.getRequest();
			//获取容器中表单提交的上传所有文件的容器
			Files files=smartUpload.getFiles();
			for(int j=0;j<files.getCount();j++){
			//返回JspSmartUpload组件中File类每一个file对象代表一条form表单的文件域
			  File file=files.getFile(j);
			  //获取上传文件的文件名
			  String fileName=file.getFileName();
			  if(fileName==null || "".equals(fileName)){
				  
				  continue;
			  }
			  
			  this.fileNames.add(fileName);
			  //将上传的文件进行保存到指定位置下
			  createFolder(path);
			  file.saveAs(path+fileName);
			  
			  request.setAttribute("msg", "文件上传成功");
			
			}
			return true;
		}catch (SmartUploadException e) {
			e.printStackTrace();
		}catch(SecurityException e){
			/*无论是文件扩展名不符还是文件大小不
			   符都会都会出现SecurityException
			  但是二者异常信息最后的状态码不同所以
			  我们要获取异常信息后得到状态码进行判断*/
		   String code=	returnException(e);
		   String Msg=null;
		   //扩展名不符合
		   if("1015".equals(code)){
			   Msg="上传文件扩展名不符";
			  
		   }//文件大小不符
		   else if("1105".equals(code)){
			   Msg="上传文件大小过大";
		   }
		   request.setAttribute("msg", Msg);
		
		}
		
		return false;
	  
	}
	//创建存放图片的文件夹
	private   void  createFolder(String  folderName){
		
		  java.io.File  file=new  java.io.File(folderName);
		  if(!file.exists()){//判断文件夹是否存在
			    //如果不存在创建目录
			   file.mkdirs();
		  }
	}
	//获取服务器下当前项目的根目录
	private  String  getRootPath(){
		
		String path=this.getClass().getResource("/").toString();
		return GeneralUtils.getString(GeneralUtils.getString(path, "file:/", "%20", " ", 1),"WEB-INF/classes/", null, null, 0); 

	}
	/*判断状态码
	 *状态码1015为扩展名不符合要求
	 *状态码1105为文件大小不符合要求
	 **/
	private    String    returnException(Exception e){
		
		 //获取异常信息内容  
		 String exceptionMsg=e.getMessage();
		 //获取第一"("位置
		 int bIndx=exceptionMsg.indexOf("(");
		 //获取最后一个")"位置
		 int eIndex=exceptionMsg.indexOf(")");
		 //获取状态码
		 String code=exceptionMsg.substring(bIndx+1, eIndex);
		 return code;
		 
	}
//	//删除文件操作
//	private void  removeFile(java.io.File  fileList){
//		  java.io.File[] files= fileList.listFiles();
//		  if(files.length>0){
//			  for(java.io.File file:files){
//				  
//				   file.delete();
//			  }
//			  
//		  }
//
//	}
	public  List<String> getFileNames(){
		
		return this.fileNames;
	}
}
