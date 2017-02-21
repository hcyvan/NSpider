phantom.injectJs("head.js");

var URL="http://index.baidu.com/?tpl=demand&word="+keyWord;
page.open(URL, function (s) {
    if(s == 'success'){
        //检测是否有查看词条的权限
        phantom.injectJs("permission.js");

	page.includeJs('jquery-3.1.1.js', function(){
	    window.setInterval(function(){
                timeOut -= interval;
                if (timeOut <= 0) {
                    phantom.exit();
                }
		if(page.evaluate(function(){
		    // 页面渲染完成标志
		    return $('.mainSplit svg text tspan').length;
		})){
		    page.evaluate(function(){
			var result='';
			result += '<demand>';
			//-------- demand ---------
			$('.mainSplit svg text tspan').each(function(){
			    result+=$(this).text()+';';
			})
			result += '</demand>';
			console.log(result);
		    })
		    phantom.exit();
		}
	    }, interval)
	})
    }
});

