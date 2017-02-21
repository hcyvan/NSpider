var system = require('system');
if (system.args.length === 1) {
    console.log('Usage: '+ system.args[0] +' <word> <cookies>');
    phantom.exit();
}
var keyWord= system.args[1]; // gb2312编码
var bduss = system.args[2]
var timeOut=15000;
var interval=100;
var page = require('webpage').create();
// 登录cookies
var cookiesLogin = {
    'name':'BDUSS',
    'value': bduss,
    'domain':'index.baidu.com',
    'path':'/',
};
phantom.addCookie(cookiesLogin);

page.onConsoleMessage = function(msg) {
  console.log('<OK>' + msg);
};

