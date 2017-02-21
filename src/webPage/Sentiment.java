package webPage;

import java.util.ArrayList;

import utils.Configure;

public class Sentiment extends WebBase {

	public Sentiment(String... args) {
		super(Configure.getExe()+" sentiment.js", args);
	}
	
	/**
	 * 新闻监测/舆情洞察
	 * @return
	 */
	public String r_getSentiment(){
		String result = "";
		
		result +="媒体指数: ";
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
		Sentiment sentiment = new Sentiment("%D2%F5%D1%F4%CA%A6");
		System.out.println(sentiment.getLine());
		System.out.println(sentiment.getResult());
	}

}
