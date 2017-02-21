package utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;

public class SpiderUtils {
	public static String getResultLine(String line){
		String pattern  = "^<OK>(?!SyntaxError:).*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if(m.find()){
			return m.group();
		}else{
			return "";
		}
	}
	/**
	 * 执行cmd 命令, 并返回结果
	 * @param cmd
	 * @param dir
	 * @return
	 */
	public static String getResultByCmd(String cmd, File dir){
		String line;
		String result="";
		Runtime runtime=Runtime.getRuntime();
		try{
			Process p = runtime.exec(cmd, null, dir);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				result = getResultLine(line);
				if(result != ""){
					break;
				}
			}
			input.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(result == "" ||result.contains("DENIED")){
			throw new RuntimeException("PERMISSION DENIED");
		}
		return result;
	}
	
	/**
	 *  从line中找到与pattern匹配的字符串， pattern为正则表达式，至少包含一个括号
	 * 
	 * @return pattern 中的group(1)
	 */
	public static String getPatternInLine(String pattern, String line){
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if(m.find()){
			return m.group(1);
		}else{
			return "";
		}
	}
	/**
	 * 通过字符串获取浮点数数组
	 * @param line 字符串
	 * @param regex 分隔符
	 * @return
	 */
	public static float[] getFloatArrayFromString(String line, String regex){
		return getFloatArrayFromString(line, regex, 0, line.split(regex).length);
	}
	/**
	 * 通过字符串获取浮点数数组,返回数组从 begin 开始，到 end-1 结束
	 * @param line 字符串
	 * @param regex 分隔符
	 * @param begin
	 * @param end
	 * @return
	 */
	public static float[] getFloatArrayFromString(String line, String regex, int begin, int end){
		String[] arrayS = getStringArrayFromString(line, regex, begin, end);
		int len = arrayS.length;
		float[] arrayF = new float[len];
		for(int i=0;i<len;i++){
			arrayF[i]=Float.parseFloat(arrayS[i]);
		}
		return arrayF;
	}
	/**
	 * 以regex为界限分隔字符串，生成数组
	 * @param line 字符串
	 * @param regex 分隔符
	 * @return
	 */
	public static String[] getStringArrayFromString(String line, String regex){
		return getStringArrayFromString(line, regex, 0, line.split(regex).length);
	}
	/**
	 * 以regex为界限分隔字符串，生成数组，并且返回从 begin 开始，到 end-1 结束
	 * 的新数组
	 * @param line 字符串
	 * @param regex 分隔符
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String[] getStringArrayFromString(String line, String regex, int begin, int end){
		String[] arrayS = line.split(regex);
		int len = arrayS.length;
		if(end>len){
			end=len;
		}
		String[] result = new String[end-begin];
		int s = 0;
		for(int i = begin; i<end; i++){
			result[s++] = arrayS[i];
		}
		return result;
	}
	public static float getSumOfFloatArray(float[] array){
		float result=0;
		for(float e:array){
			result+=e;
		}
		return result;
	}
	/**
	 * 将path转换 y 坐标的数组
	 * @param line
	 * @return
	 */
	public static ArrayList<Float> getYFromPath(String line){
		String[] splitC = line.split("C");
		int lc = splitC.length;
		ArrayList<Float> result = new ArrayList<Float>();
		for(int i=0; i<lc; i++){
			String[] p = splitC[i].split(",");
			int len = p.length;
			result.add(Float.parseFloat(p[len-1]));
		}
		return result;
	}
	/**
	 * 将path转换成 y 坐标，并进行格式化
	 * 百度指数最低的点设为0
	 * @param line
	 * @return
	 */
	public static ArrayList<Float> getYFromPathFormat(String line){
		ArrayList<Float> result = new ArrayList<Float>();
		ArrayList<Float> tmp = getYFromPath(line);
		
		Float max = Collections.max(tmp); //此处为max
		for(Float y:tmp){
			result.add(max-y);
		}
		
		return result;
	}
	
	/**
	 * 将path坐标转换成 浮点数对
	 * @param line
	 * @return
	 */
	public static ArrayList<float[]> getFloatPairsFromPath(String line){
		String[] splitC = line.split("C");
		int lc = splitC.length;
		ArrayList<float[]> result = new ArrayList<float[]>();
		for(int i=0; i<lc; i++){
			String[] p = splitC[i].split(",");
			int len = p.length;
			float[] tmp = {Float.parseFloat(p[len-2]),Float.parseFloat(p[len-1])};
			result.add(tmp);
		}
		return result;
	}	

	public static void main(String[] args) {
		
//		String line = getResultByCmd("phantomjs crowd.js %D2%F5%D1%F4%CA%A6", new File("./phantomjs/"));
		String line = getResultByCmd("phantomjs crowd.js %D6%D0%CC%EF%CE%C4", new File("./phantomjs/"));

		String p1 = "age::g:(.*?)a:";
		System.out.println(line);
		String result= getPatternInLine(p1, line);
		System.out.println(result);
		float[] arrayF=getFloatArrayFromString(result,";");
		for(Float e:arrayF){
			System.out.println(e);
		}
	}

}
