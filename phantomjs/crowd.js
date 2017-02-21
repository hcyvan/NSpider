// http://index.baidu.com/?tpl=crowd&word=%d2%f5%d1%f4%ca%a6%0d%0a
phantom.injectJs("head.js");

var URL="http://index.baidu.com/?tpl=crowd&word="+keyWord;
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
		    return $('#grp_social_l svg rect').length;
		})){
		    page.evaluate(function(){
			var result='';
			//-------- age ---------
			result += '<age>';
			result += '<graph>';
			$('#grp_social_l svg rect').each(function(){
				result+=$(this).attr('height')+';';
			})
			result += '</graph>';
			result += '<label>'
			$('#grp_social_l svg text tspan').each(function(){
			    result += $(this).text() + ';';
			})
			result += '</label>';
			result += '</age>';
			//-------- sex ---------
			result += '<sex>';
			$('#grp_social_r svg rect').each(function(){
				result+=$(this).attr('height')+';';
			})
			result += '</sex>';
			//-------- reign ---------
			var getRank=function(){
			    $('.tang-scrollpanel-content').children('div[style="display: block; padding: 10px;"]').each(function(){
				result += $(this).find('.scName').text()+':';
				var bar=$(this).find('.zbar:first-child').attr('style');
				var width=bar.split(";")[1].split(":")[1].trim();
				result+=width+';';
			    });
			}

			result += '<regin>';
			result += '<prov>';
			getRank();
			result += '</prov>';

			$('[tabval="area"]').click();
			result += '<area>';
			getRank();
			result += '</area>';

			$('[tabval="city"]').click();
			result += '<city>';
			getRank();
			result += '</city>';
			result += '</regin>';

			console.log(result);
		    })
		    phantom.exit();
		}
	    }, interval)
	})
    }
});


