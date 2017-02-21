phantom.injectJs("head.js");

var URL="http://index.baidu.com/?tpl=trend&word="+keyWord;
page.open(URL, function (s) {
    if(s == 'success'){
	page.includeJs('jquery-3.1.1.js', function(){
	    //检测是否有查看词条的权限
	    phantom.injectJs("permission.js");

	    var t1=setInterval(function(){
		timeOut -= interval;
                if (timeOut <= 0) {
                    phantom.exit();
                }
		if(page.evaluate(function(){
		    var data=$("svg path[stroke='#3ec7f5']").attr('d');
		    if(data){
			return true;
		    }else{
			return false;
		    }
		})){
		    clearInterval(t1);
		    page.evaluate(function(){
			$('[rel="all"]')[0].click();
		    });
		    
		    setTimeout(function(){ 
			page.evaluate(function(){
			    var result = ""
			    result += "<trend>"
			    var data=$("svg path[stroke='#3ec7f5']").attr('d');
			    result += "<path>"+data+"</path>";
			    result += "<time>"+$("#trend-wrap .compInfo").eq(1).text()+"</time>";
			    result += "</trend>"

			    console.log(result);
			});
			phantom.exit();
			
		    }, 3000)
		}
	    }, interval);
	})
    }
});

