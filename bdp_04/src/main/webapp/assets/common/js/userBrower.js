var browser = {
	versions : function() {
		var u = navigator.userAgent, app = navigator.appVersion;
		return {// 移动终端浏览器版本信息
			trident : u.indexOf("Trident") > -1, // IE内核
			presto : u.indexOf("Presto") > -1, // opera内核
			webKit : u.indexOf("AppleWebKit") > -1, // 苹果、谷歌内核
			gecko : u.indexOf("Gecko") > -1 && u.indexOf("KHTML") == -1, // 火狐内核
			mobile : !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
			ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
			android : u.indexOf("Android") > -1 || u.indexOf("Linux") > -1, // android终端或者uc浏览器
			iPhone : u.indexOf("iPhone") > -1, // 是否为iPhone或者QQHD浏览器
			iPad : u.indexOf("iPad") > -1, // 是否iPad
			webApp : u.indexOf("Safari") == -1
		// 是否web应该程序，没有头部与底部
		};
	}(),
	language : (navigator.browserLanguage || navigator.language).toLowerCase()
}
// document.writeln("语言版本: " + browser.language);
// document.writeln(" 是否为移动终端: " + browser.versions.mobile);
// document.writeln(" ios终端: " + browser.versions.ios);
// document.writeln(" android终端: " + browser.versions.android);
// document.writeln(" 是否为iPhone: " + browser.versions.iPhone);
// document.writeln(" 是否iPad: " + browser.versions.iPad);
// document.writeln(navigator.userAgent);
// 获取终端类型，PC、安卓、IOS
function getUserTerminalType() {
	if (browser.versions.mobile) {
		if (browser.versions.ios)
			return 'IOS';
		else if (browser.versions.android)
			return 'Android';
		else
			return '其他';
	} else {
		return 'PC';
	}
}
// 获取用户浏览器类型
function getExplorerInfo() {

	var userAgent = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
	var browser;
	var version;
	var ua = userAgent.toLowerCase();
	function uaMatch(ua) {
		var match = rMsie.exec(ua);
		if (match != null) {
			return {
				browser : "IE",
				version : match[2] || "0"
			};
		}
		var match = rFirefox.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rOpera.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rChrome.exec(ua);
		if (match != null) {
			return {
				browser : match[1] || "",
				version : match[2] || "0"
			};
		}
		var match = rSafari.exec(ua);
		if (match != null) {
			return {
				browser : match[2] || "",
				version : match[1] || "0"
			};
		}
		if (match != null) {
			return {
				browser : "",
				version : "0"
			};
		}
	}
	var browserMatch = uaMatch(userAgent.toLowerCase());
	if (browserMatch.browser) {
		browser = browserMatch.browser;
		version = browserMatch.version;
	}
	return browserMatch;
}
