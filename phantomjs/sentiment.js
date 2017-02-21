phantom.injectJs("head.js");

var URL="http://index.baidu.com/?tpl=sentiment&word="+keyWord;
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
                    return $("svg path[stroke='#3ec7f5']").attr('d');
		})){
		    page.evaluate(function(){
			var result='';
			result += "<trend>";
			//-------- trend ---------
                        var data=$("svg path[stroke='#3ec7f5']").attr('d');
                        result += "<path>"+data+"</path>";
                        result += "<time>"+$("#sentimNews .compInfo").eq(1).text()+"</time>";
			result += "</trend>";
			console.log(result);
		    })
                    phantom.exit();
                }
            }, interval)
        })
    }
});

