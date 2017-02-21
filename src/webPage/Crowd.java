package webPage;

import utils.SpiderUtils;
import utils.Configure;

public class Crowd extends WebBase{
	public Crowd(String... args){
		super(Configure.getExe()+" crowd.js", args);
	}
	
	/**
	 * 获取年龄分布
	 * @return
	 */
	public String r_getAgeDist(){
		String result="";
		
		result += "年龄分布: ";
		String r1= this.getPattern("<graph>(.*?)</graph>");
		String r2 = this.getPattern("<label>(.*?)</label>");
		if(r1!=""&& r2!=""){
			float[] arrayF = SpiderUtils.getFloatArrayFromString(r1,";");
			String[] arrayS = SpiderUtils.getStringArrayFromString(r2, ";", 1, 6);
			
			float sum = SpiderUtils.getSumOfFloatArray(arrayF);
			int len = arrayF.length;
			for(int i=0; i<len; i++){
				result+=arrayS[i]+": "+arrayF[i]/sum+";";
			}
		}
		return result;
	}
	/**
	 * 获取性别分布
	 * @return
	 */
	public String r_getSexDist(){
		String result = "";
		
		result += "性别分布: ";
		String r1= this.getPattern("<sex>(.*)</sex>");
		if(r1!=""){
			float[] arrayF = SpiderUtils.getFloatArrayFromString(r1,";", 0, 2);
			float man = arrayF[1]/arrayF[0];
			result +="男:"+ man +";"+"女:"+(1-man);
		}
		return result;
	}
	/**
	 * 词条省份搜索排名
	 * @return
	 */
	public String r_getProv(){
		String result = "";
		
		result += "省份排名: ";
		String r1= this.getPattern("<prov>(.*)</prov>");
		if(r1!=""){
			String[] arrayS = SpiderUtils.getStringArrayFromString(r1, ";");
			for(String e: arrayS){
				String[] item = e.substring(0, e.indexOf("px")).split(":");
				result += item[0] + ":";
				result += Float.parseFloat(item[1]) + ";";
			}
		}

		return result;
	}
	/**
	 * 词条区域搜索排名
	 * @return
	 */
	public String r_getArea(){
		String result = "";
		
		result += "区域排名: ";
		String r1= this.getPattern("<area>(.*)</area>");
		if(r1!=""){
			String[] arrayS = SpiderUtils.getStringArrayFromString(r1, ";");
			for(String e: arrayS){
				String[] item = e.substring(0, e.indexOf("px")).split(":");
				result += item[0] + ":";
				result += Float.parseFloat(item[1]) + ";";
			}
		}
		return result;
	}
	/**
	 * 词条区域搜索排名
	 * @return
	 */
	public String r_getCity(){
		String result = "";
		
		result += "城市排名: ";
		String r1= this.getPattern("<city>(.*)</city>");
		if(r1!=""){
			String[] arrayS = SpiderUtils.getStringArrayFromString(r1, ";");
			for(String e: arrayS){
				String[] item = e.substring(0, e.indexOf("px")).split(":");
				result += item[0] + ":";
				result += Float.parseFloat(item[1]) + ";";
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// %D6%D0%CC%EF%CE%C4 没有权限
		// %D2%F5%D1%F4%CA%A6 权限
//		Crowd crowd = new Crowd("%D6%D0%CC%EF%CE%C4");
		Crowd crowd = new Crowd("%D2%F5%D1%F4%CA%A6");
//		System.out.println(crowd.getLine());
//		System.out.println(crowd.getAgeDist());
//		System.out.println(crowd.getSexDist());
		System.out.println(crowd.getResult());

	}

}
