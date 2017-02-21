package webPage;

import java.util.ArrayList;

import utils.Configure;

public class Trend extends WebBase {

	public Trend(String... args) {
		super(Configure.getExe()+" trend.js", args);
	}
	/**
	 * 词条历史趋势
	 * @return
	 */
	public String r_getTrend(){
		String result = "";
//		System.out.println(this.getLine());
//		System.out.println(this.getCmd());

		result +="趋势(周平均): ";
		String r1= this.getPattern("<time>(.*)</time>");
		result += r1 + ":";
		
		String r2= this.getPattern("<path>M(.*)</path>");
		ArrayList<Float> points = utils.SpiderUtils.getYFromPathFormat(r2);
		for(Float y:points){
			result += y + ";";
		}
		return result;
	}
	
	public static void main(String[] args) {
		Trend trend = new Trend("%D2%F5%D1%F4%CA%A6");
		String result = trend.getLine();
		System.out.print(result+"\n");
		System.out.println(trend.getResult());
	}
}
