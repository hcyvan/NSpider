package webPage;

import utils.Configure;
import utils.SpiderUtils;

public class Demand extends WebBase {

	public Demand(String... args){
		super(Configure.getExe()+" demand.js", args);
//		super("phantomjs demand_phantom.js", args);
	}
	/**
	 * 获取关联词
	 * @return
	 */
	public String r_getRelatedWords(){
		String result = "";
//		System.out.println(this.getLine());
//		System.out.println(this.getCmd());
		result += "关联词: ";
		String r1= this.getPattern("<demand>(.*);3");
		String[] arrayS = SpiderUtils.getStringArrayFromString(r1, ";");
		for(String e:arrayS){
			if(!e.equals("")){
				result += e + ";";
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		Demand demand= new Demand("%D2%F5%D1%F4%CA%A6");
//		System.out.println(demand.getLine());
		System.out.println(demand.r_getRelatedWords());
	}
}
