if(page.evaluate(function(){
    if($('.btnbtxt[href^="/VIP/buy/?word="]').length){
	console.log("DENIED");
	return true;
    }else{
    return false;
    }
})){
    phantom.exit();
}

