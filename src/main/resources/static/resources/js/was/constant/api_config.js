/**  API 定义        */

var apiBase = "";

//接口配置
var ApiConfig = {
		
		remove  : { url:  apiBase +  "/api/tests/common/remove", method : "POST"},
		update  : { url:  apiBase +  "/api/tests/common/update", method : "POST"},
		
		apiInfo : { url: apiBase +  "/api/tests/common/ApiInfo/search"},
		apiParams : { url: apiBase +  "/api/tests/common/ApiParams/search"},
		apiLogs : { url: apiBase +  "/api/tests/common/ReqLogs/search"},
		apiLogParams : { url: apiBase +  "/api/tests/common/ReqParams/search"},
		
		group : { url: apiBase + "/api/tests/common/ApiVersion/search"},
		groups : { url: apiBase + "/api/tests/common/ApiVersions/search"},
		doGroup : { url: apiBase + "/api/tests/group/save"},
		
		request : { url: apiBase +  "/api/tests/request"},
		groupApis : { url: apiBase + "/api/tests/group/apis"},

};
