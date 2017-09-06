package com.jbit.jboa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.jstl.sql.Result;

public class Util {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	private static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat dfCN = new SimpleDateFormat("yyyy年MM月dd日");
	/**
	 * @param s
	 * @return
	 */
	public static boolean isNullOrBlank(final String s) {
		if (s == null) {
			return true;
		}
		if (s.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为空
	 * @param s
	 * @return
	 */
	public static boolean isNullOrBlank(final Short s) {
		if (s == null || s.intValue() == -1) {
			return true;
		}
		return false;
	}
	/**
	 * 把字符串转换为java.util.Date
	 *2008-11-2下午06:41:32
	 *testepet
	 *Util.java
	 *@param date
	 *@return
	 *
	 */
	public static Date parseDate(String date)
	{
		Date ret = null;		
		try {
			ret = df.parse(date);
		} catch (ParseException e) {
			ret = null;
		}
		return ret;
	}
	/**
	 * 把字符串类型转换为java.sql.Date类型
	 *2008-11-2下午06:41:03
	 *testepet
	 *Util.java
	 *@param date
	 *@return
	 *
	 */
	public static java.sql.Date parseSqlDate(String date)
	{
		Date ret = null;
		java.sql.Date args=null;
		try {
			ret =  df.parse(date);
			args= new java.sql.Date(ret.getTime());
		} catch (ParseException e) {
			ret = null;
		}
		return args;
	}
	/**
	 * 把Date类型转换为字符串
	 *2008-11-2下午06:42:41
	 *testepet
	 *Util.java
	 *@param date
	 *@return
	 *
	 */
	public static String formatDate(Date date)
	{
		String ret = "";
		try {
			ret = df.format(date);
		}
		catch(Exception e)
		{
			ret = "";
		}
		return ret;
	}
	public static String formatDateCN(Date date)
	{
		String ret = "";
		try {
			ret = dfCN.format(date);
		}
		catch(Exception e)
		{
			ret = "";
		}
		return ret;
	}
	
	public static String formatTime(Date date)
	{
		String ret = "";
		try {
			ret = df2.format(date);
		}
		catch(Exception e)
		{
			ret = "";
		}
		return ret;
	}

	public static String getWeekNameCN(Date date)
	{
		String ret = "";
		try {
			ret = "星期";
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int w = cal.get(Calendar.DAY_OF_WEEK);
			String ws = "日一二三四五六";
			ret += ws.charAt(w-1);
		}
		catch(Exception e)
		{
			ret = "";
		}
		return ret;
	}
	
	public static boolean isNotNullOrEmpty(String str)
	{
		if (null==str){
			return false;
		}
		if (str.trim().equals("")){
			return false;
		}
		return true;
	}
	public static boolean isNotNullOrEmpty(Integer i)
	{
		if (null==i){
			return false;
		}
		if (i.intValue()==-1){
			return false;
		}
		return true;
	}
	/**
	 * 判断查询结果是否为空
	 */
	public static boolean isNotNullOrEmpty(Result rs){
		boolean type=false;
		if(null!=rs && rs.getRowCount()>0){
			type=true;
		}
		return type;
		
	}
	/**
	 * 判断自定义类型的对象是否为空
	 */
	public static boolean isNotNullOrEmpty(Object obj){
		boolean type=false;
		if(null!=obj ){
			type=true;
		}
		return type;
		
	}
	public static String now(){
		return df.format(new Date());
	}
}
