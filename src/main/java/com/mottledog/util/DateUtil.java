package com.mottledog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	  /**
     * 
     * @param dateTime 格式为"yyyy-mm-dd"的日期格式
     * @return string类型的下一天的日期
     * @throws ParseException
     */
    public static String getNextDate(String dateTime) throws ParseException{
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        long dif = df.parse(dateTime).getTime()+86400*1000;
        Date date=new Date();
        date.setTime(dif);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            
        return sdf.format(date);
      }

    /**
     * 
     * @param dateTime 格式为"yyyy-mm-dd"的日期格式
     * @return string类型的前一天的日期
     * @throws ParseException
     */
    public static String getFormerDate(String dateTime) throws ParseException{
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
        long dif = df.parse(dateTime).getTime()-86400*1000;
        Date date=new Date(); 
        date.setTime(dif);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            
        return sdf.format(date);
     }
    
}
