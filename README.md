百度指数爬虫
===============================================
## 文件介绍
1. src/下的文件是用java编写的爬虫框架部分
2. phantomjs/ javascript脚本编写的爬虫核心文件
3. result.log 最终的结果输出文件   ---------------
4. spider.log 爬虫log文件
5. words.txt  待检索的词条，每个词条一行

## 使用说名：
1. 安装phantomjs.(网上有说明)
2. 将项目导入eclipse, 执行 src/baiduIndex/Enter.java

百度指数爬虫v2
===============================================
1. 在上一版本基础上，可以选择利用 casperjs 替代phantomjs 爬取数据的方案，
   在linux系统中进行测试，casperjs模式比phantomjs模式速度快一倍左右，
   casperjs模式会产生乱码
2. 修复因为没有匹配内容产生的程序卡死。
## 文件介绍
1. casperjs/ javascript脚本编写的爬虫核心文件(casperjs模式)
2. 
## 使用说名：
1. 安装phantomjs(必须) 和 casperjs(如果选择casperjs模式)
2. 打开 utils/Configure.java 文件，通过注释和去注释一些代码，选择想要的模式
   详细见utils/Configure.java 文件
3. 将项目导入eclipse, 执行 src/baiduIndex/Enter.java