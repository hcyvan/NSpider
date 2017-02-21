package webPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.Collections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Test {
	
    public static String[] readWords(){
    	ArrayList<String> list = new ArrayList<String>();
    	try{
    		FileReader fr = new FileReader("tmp");
    		BufferedReader br = new BufferedReader(fr);
    	    String line = null;  
    	    while((line = br.readLine()) != null){
    	    	list.add(line.trim());
    	    }  
    		fr.close();
    		br.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	return (String[])list.toArray(new String[0]);
    }
	public static void main(String[] args) {
		String line = readWords()[0];
		System.out.println(line);
		ArrayList<Float> axisY = utils.SpiderUtils.getYFromPathFormat(line);
		System.out.println(axisY.size());
		System.out.println(axisY);
//		for(Float y:axisY){
//			System.out.println(y);
//		}
		
		
		String t ="2011-01-01 至 2017-02-11";
		String t1="2011-01-01";
		String t2="2017-02-12 23:59:59";
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");         
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
		Date date1 = null;
		Date date2 = null;                   
		// String转Date    
		try {    
		     date1 = format1.parse(t1);   
		     date2 = format2.parse(t2);   
		} catch (Exception e) {    
		           e.printStackTrace();    
		}
		long diff = date2.getTime() - date1.getTime();
		System.out.println(diff);
		System.out.println(diff/(long)(3600000*24*7));
		
	    Calendar cal = Calendar.getInstance();
	    try{
	    	cal.setTime(format1.parse(t1));
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    for(int i=0; i<319; i++){
	    	cal.add(java.util.Calendar.DATE, 7); // 向前一周；如果需要向后一周，用正数即可
//	    	System.out.println(format1.format(cal.getTime()));
	    }
//	    int[] numbers = { 8, 2, 7, 10, 4, 9, 5};
        Float min = Collections.min(axisY);
        Float max = Collections.max(axisY);

	    System.out.println(max);
	    System.out.println(min);
		
	}
}
