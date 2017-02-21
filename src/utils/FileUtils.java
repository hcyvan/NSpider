package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FileUtils {
	static Date begin = null;
	
    /**
     * 将line写入spider.log 文件
     * @param line
     */
    public static void log(String line){
    	log(line, false);
    }
    /**
     * 将line写入spider.log 
     * @param line
     * @param console  true:打印到控制台
     */
    public static void log(String line, boolean console){
		String result = "";
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	result += df.format(new Date()) + " :: ";// new Date()为获取当前系统时间
    	result += line + "\n";
    	if(console){
    		System.out.println(line);
    	}
    	try{
    		FileWriter fw = new FileWriter("./spider.log", true);
    		fw.write(result);
    		fw.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 写入log文件，并开始计时
     * @param line
     * @param console
     */
    public static void logBegin(String line, boolean console){
    	begin = new Date();
    	log(line+ " :: 计时"+begin.getTime()/1000, console);
    }
    
    /**
     * 写入log文件，并开始计时
     * @param line
     */
    public static void logBegin(String line){
    	logBegin(line, false);
    }
    
    /**
     * 写入log文件，并统计消耗时间，单位 秒
     * @param line
     */
    public static void logEnd(String line, boolean console){
    	Date now = new Date();
    	long diff = now.getTime() - begin.getTime();
    	log(line+ " :: 计时结束"+begin.getTime()/1000+", 共用 "+ diff/1000 + "s", console);
    }
    
    /**
     * 写入log文件，并统计消耗时间，单位 秒
     * @param line
     */
    public static void logEnd(String line){
    	logEnd(line, false);
    }
    
    /**
     * 读取文件的每行，转换成string数组
     * 读取 words.txt 中记录待检索的词条
     * @param wordFile 
     * @return
     */
    public static String[] readWords(){
    	ArrayList<String> list = new ArrayList<String>();
    	try{
    		FileReader fr = new FileReader("words.txt");
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
    /**
     * 将结果写入 result.log 文件
     * @param line
     */
    public static void record(String line){
    	try{
    		FileWriter fw = new FileWriter("./result.log", true);
    		fw.write(line);
    		fw.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
	public static void main(String[] args) {
		log("hello");
	}

}
