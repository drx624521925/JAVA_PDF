package com.pdf.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTools {

	/**
	 * 
	 *���ߣ�ZhangHuanMing
	 *ʱ�䣺2017��3��23������2:22:03
	 *��������getDateBetween
	 *���ܣ�������������֮�������
	 *������
	 *����ֵ��int
	 * @throws ParseException 
	 */
	public static int getDateBetween(String date1,String date2) throws ParseException{
		
		long to = 0;
		long from = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		if( date1.compareTo(date2) > 0 ){               //date1����date2
			
			to = df.parse(date1).getTime();
		    from = df.parse(date2).getTime();
		}else if(date1.compareTo(date2) < 0){
			
			to = df.parse(date2).getTime();
		    from = df.parse(date1).getTime();
		}else{                                      //���
			return 0;
		}
	    return (int) ((to - from) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 *     
	 *���ߣ�ZhangHuanMing
	 *ʱ�䣺2017��3��23������3:13:14
	 *��������getDatesBetween2Date
	 *���ܣ������������֮�����е�����
	 *������
	 *����ֵ��List<String>
	 */
    public static List<String> getDatesBetween2Date(String start_date, String end_date) {
    
    	List<String> result = new ArrayList<String>();
    
    	try {
    
    		start_date = start_date.trim();
    		end_date = end_date.trim();
    
		    if ( start_date.isEmpty() || end_date.isEmpty() ) {
		    
		    	return result;
		    }
		    
		    String tempDate = null;
		    
		    if( start_date.compareTo(end_date)>=0){
		    	
		    	tempDate = end_date;
		    	result.add(end_date);
		    }else {
		    	
		    	tempDate = start_date;
		    	result.add(start_date);
		    }
		    
		    for (int i = 0; i < getDateBetween(start_date,end_date); i++) {
				
		    	result.add(turnDate(tempDate,1+i));
			}
		 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	  return result;
    
    }
	    
    /**
     * 
     *���ߣ�ZhangHuanMing
     *ʱ�䣺2017��3��23������2:49:44
     *��������turnDate
     *���ܣ������ַ�������ָ��������
     *������
     *����ֵ��String
     */
    public static String turnDate(String showDate, int interDay) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Date date = null; 
    	Date newDate = null;
	    try {
	    	date = sdf.parse(showDate); 
	    	newDate = addDate(date,interDay);
	    }catch (ParseException e) { 
	    	e.printStackTrace(); 
	    }
	    
	    return sdf.format(newDate);
	    
    }
	    
    /**
     * 
     *���ߣ�ZhangHuanMing
     *ʱ�䣺2017��3��23������2:58:03
     *��������addDate
     *���ܣ������ڼ�������
     *������
     *����ֵ��Date
     */
	public static Date addDate(Date date,int day){
		
		long time = date.getTime(); // �õ�ָ�����ڵĺ�����
		day = day*24*60*60*1000; // Ҫ���ϵ�����ת���ɺ�����
		time+=day; // ��ӵõ��µĺ�����
		return new Date(time); // ��������ת��������
	}    
	

	/**
	 * 
	 *���ߣ�ZhangHuanMing
	 *ʱ�䣺2017��4��10������8:55:18
	 *��������dateFormat
	 *���ܣ����ڸ�ʽת��
	 *������
	 *����ֵ��String
	 */
	public static String dateFormat(String oldDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try{
			
			date = sdf.parse(oldDate);
		}catch(ParseException e){
			
			e.printStackTrace();
		}
		
		return sdf.format(date);
	}
	
	
	
	public static void main(String[] args) throws ParseException {
		
		String date1 = "2017-12-28";
		String date2 = "2018-01-03";
		System.out.println(getDateBetween(date1,date2));
		for (int i = 0; i < getDatesBetween2Date(date2,date1).size(); i++) {
			
			System.out.println( getDatesBetween2Date(date2,date1).get(i));
		}
		
		System.out.println(dateFormat("2017-4-3"));
	}
}
