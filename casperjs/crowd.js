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
//phantom.outputEncoding="GBK";
var URL="http://index.baidu.com/?tpl=crowd&word="+keyWord;

casper.start(URL);
casper.then(function(){
    if (this.exists('.btnbtxt[href^="/VIP/buy/?word="]')) {
        this.echo('<DENIED>').exit();
    }
})

var result = '<OK>';
// 年龄
casper.then(function() {
    this.waitForSelector('#grp_social_l svg rect', function(){	    
	result+=casper.evaluate(function(){
	    var tmp='';
	    tmp += '<age>';
	    tmp += '<graph>';
	    $('#grp_social_l svg rect').each(function(){
		tmp+=$(this).attr('height')+';';
	    })
	    tmp += '</graph>';
	    tmp += '<label>'
	    $('#grp_social_l svg text tspan').each(function(){
		tmp += $(this).text() + ';';
	    })
	    tmp += '</label>'
	    tmp += '</age>';
	    return tmp;
	});
    }, ms);
});
// 性别
casper.then(function() {
    this.waitForSelector('#grp_social_r svg rect', function(){	    
	result+=casper.evaluate(function(){
	    var tmp='';
	    tmp+='<sex>';
	    $('#grp_social_r svg rect').each(function(){
		tmp += $(this).attr('height') + ';';
	    })
	    tmp+='</sex>';
	    return tmp;
	});
    }, ms);

});
// 地区
var getRank=function(){
    var tmp='';
    $('.tang-scrollpanel-content').children('div[style="display: block; padding: 10px;"]').each(function(){
	tmp += $(this).find('.scName').text()+':';
	var bar=$(this).find('.zbar:first-child').attr('style');
	var width=bar.split(";")[1].split(":")[1].trim();
	tmp+=width+';';
    });
    return tmp;
}
casper.then(function() {
    this.waitForSelector('[tabval="area"]', function(){	    
	result+=casper.evaluate(function(){
	    var tmp='';
	    tmp+='<regin>';

	    tmp += '<prov>';
	    $('.tang-scrollpanel-content').children('div[style="display: block; padding: 10px;"]').each(function(){
		tmp += $(this).find('.scName').text()+':';
		var bar=$(this).find('.zbar:first-child').attr('style');
		var width=bar.split(";")[1].split(":")[1].trim();
		tmp+=width+';';
	    });
	    tmp += '</prov>';

	    $('[tabval="area"]').click();
	    tmp += '<area>';
	    $('.tang-scrollpanel-content').children('div[style="display: block; padding: 10px;"]').each(function(){
		tmp += $(this).find('.scName').text()+':';
		var bar=$(this).find('.zbar:first-child').attr('style');
		var width=bar.split(";")[1].split(":")[1].trim();
		tmp+=width+';';
	    });
	    tmp += '</area>';

	    $('[tabval="city"]').click();
	    tmp += '<city>';
	    $('.tang-scrollpanel-content').children('div[style="display: block; padding: 10px;"]').each(function(){
		tmp += $(this).find('.scName').text()+':';
		var bar=$(this).find('.zbar:first-child').attr('style');
		var width=bar.split(";")[1].split(":")[1].trim();
		tmp+=width+';';
	    });
	    tmp += '</city>';

	    tmp +='</regin>';
	    return tmp;
	});
    }, ms);

});

casper.then(function(){
    this.echo(result);
});

casper.run();

