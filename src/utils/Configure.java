package utils;

public class Configure {
	private static String bduss = " add your cookies here";
//	private static String exe = "C:\\casperjs\\bin\\casperjs.exe";
	
	
	/**
	 * 注释以下部分，启用 phantomjs
	 */
//	private static String exe = "casperjs";
//	private static String dir = "./casperjs";

	/**
	 * 注释以下部分，启用 casperjs
	 */
	private static String exe = "phantomjs";
	private static String dir = "./phantomjs";
	
	public static String getBduss(){
		return bduss;
	}
	
	public static String getExe(){
		return exe;
	}
	
	public static String getDir(){
		return dir;
	}
	
	public static void main(String[] args) {
		System.out.println(Configure.getBduss());
	}
}
