package webPage;

import utils.Configure;

public class BaiduIndex {
	private String word;
	private String wordUrl;

	public BaiduIndex(String word) {
		this.word = word;
		try {
			this.wordUrl = java.net.URLEncoder.encode(word, "GB2312");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取百度指数爬虫每个词条需要输出的最终数据
	 * @return
	 */
	public String getResult(){
		String result = "";
		result += ">>> "+this.word + " >>>\n";
		// 人群画像
		Crowd crowd = new Crowd(this.wordUrl, Configure.getBduss());
		result += crowd.getResult();
		// 需求图谱
		Demand demand= new Demand(this.wordUrl, Configure.getBduss());
		result += demand.getResult();
		//搜索趋势(周平均)
		Trend trend= new Trend(this.wordUrl, Configure.getBduss());
		result += trend.getResult();
		// 媒体指数
		Sentiment sentiment= new Sentiment(this.wordUrl, Configure.getBduss());
		result += sentiment.getResult();

		result += "<<< "+this.word + " <<<\n";
		return result;
	}

	public static void main(String[] args) {
		BaiduIndex baiduIndex = new BaiduIndex("阴阳师");
		System.out.println(baiduIndex.getResult());
	}

}
