package baiduIndex;

import utils.FileUtils;
import webPage.BaiduIndex;

public class Enter {
	public static void main(String[] args) {
        String[] words = FileUtils.readWords();
        for(String e: words){
        	String result = "";
    		FileUtils.logBegin("开始检索: " + e + "...",true);
    		
    		try{
    			BaiduIndex index = new BaiduIndex(e);
    			result = index.getResult();
    		}catch(RuntimeException ex){
    			String l = ex +" 不具有访问 "+ e +" 的权限";
    			result += l+'\n';
    			FileUtils.log(l, true);
    		}
    		FileUtils.record(result);
    		FileUtils.logEnd(e+"检索结束", true);
        }
	}
}
