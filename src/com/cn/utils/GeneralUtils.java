package com.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class GeneralUtils {
	public  static  final String  DATEFORMAT_YMDHMS="yyyy-MM-dd HH:mm:ss";
	public  static  final String  DATEFORMAT_YMD="yyyy-MM-dd";
	//日期转成字符串
	public  static   String  dateConvertString(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	//字符串转日期
	public  static   Date  StringConvertDate(String  source,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
	    Date  date=null;
		try {
			date=sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	    
	}
	public  static String     getUUID(){
		
		return UUID.randomUUID().toString();
	}
	
	public  static   String  getString(String resource,  String pattern ,String regex,String replace, int index){
		
		String str= resource.split(pattern)[index];
		if(regex!=null && replace!=null){
			str=str.replaceAll(regex, replace);
		}
		return  str;
	}

	
}
