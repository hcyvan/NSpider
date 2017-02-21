package webPage;

import java.io.File;
import java.lang.reflect.Method;

import utils.Configure;
import utils.SpiderUtils;


class WebBase {
	private File dir = new File(Configure.getDir()); // casperjs文件的目录
	protected String line = "";
	protected String cmd="";
	public WebBase(String cmd, String... args){
		for(String e:args){
			cmd += " "+ e;
		}
		this.cmd=cmd;
//		System.out.println(cmd);
		this.line = SpiderUtils.getResultByCmd(cmd, this.dir);
	}
	/**
	 * 依次执行类中以 "r_" 开头的方法
	 * @return
	 */
	public String getResult(){
//		System.out.println(this.getLine());
//		System.out.println(this.getCmd());
		String result = "";
		Method[] ms =this.getClass().getDeclaredMethods();
		for(Method me: ms){
			if(me.getName().indexOf("r_")==0){;
				try{
					result += me.invoke(this)+"\n";
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取与pattern匹配的字符串
	 * @param pattern
	 * @return
	 */
	public String getPattern(String pattern){
		return SpiderUtils.getPatternInLine(pattern, this.line);
	}
	
	public String getLine(){
		return this.line;
	}
	public String getCmd(){
		return this.cmd;
	}
}
