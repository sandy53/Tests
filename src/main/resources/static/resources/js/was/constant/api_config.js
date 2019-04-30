/**  API 定义        */

var apiBase = "";

//接口配置
var ApiConfig = {
		
		remove  : apiBase +  "/api/tests/common/remove",
		
		apiInfo : apiBase +  "/api/tests/common/ApiInfo/search",
		apiParams : apiBase +  "/api/tests/common/ApiParams/search",
		apiLogs : apiBase +  "/api/tests/common/ReqLogs/search",
		apiLogParams : apiBase +  "/api/tests/common/ReqParams/search",
		
		group : apiBase + "/api/tests/common/ApiVersion/search",
		groups : apiBase + "/api/tests/common/ApiVersions/search",
		doGroup : apiBase + "/api/tests/group/save",
		
		request : apiBase +  "/api/tests/request",
		groupApis : apiBase + "/api/tests/group/apis",

};
