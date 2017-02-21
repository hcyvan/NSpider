casper = require('casper').create();
var ms = 5000;

if(casper.cli.args.length !=2){
    casper.echo("Usage: script <word> <cookies>");
    casper.exit();
}
//var keyWord='%d2%f5%d1%f4%ca%a6%0d%0a';
//var keyWord='%D6%D0%CC%EF%CE%C4';//没权限
//var keyWord='%D6%C1%D7%F0%C6%FA%C9%D9';//记录不全
var keyWord = casper.cli.get(0);
var bduss = casper.cli.get(1);

// 设置cookies
phantom.addCookie({
  domain: 'index.baidu.com',
  name: 'BDUSS',
  value: bduss,
});
var URL="http://index.baidu.com/?tpl=demand&word="+keyWord;

casper.start(URL);
casper.then(function(){
    if (this.exists('.btnbtxt[href^="/VIP/buy/?word="]')) {
        this.echo('<DENIED>').exit();
    }
})

var result = '<OK>';

casper.then(function() {
    this.waitForSelector('.mainSplit svg text tspan', function(){	    
	result+=casper.evaluate(function(){
	    var tmp = "";
	    tmp +='<demand>';
	    $('.mainSplit svg text tspan').each(function(){
			tmp+=$(this).text()+';';
	    })
	    tmp +='</demand>';
	    return tmp;
	});
    }, ms);
});

casper.then(function(){
    this.echo(result);
});

casper.run();

