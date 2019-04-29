/**  API 定义        */

var apiBase = "";

//接口配置
var ApiConfig = {
		
		apiInfo : apiBase +  "/psi/tests/common/ApiInfo/search",
		apiParams : apiBase +  "/psi/tests/common/ApiParams/search",
		apiLogs : apiBase +  "/psi/tests/common/ReqLogs/search",
		apiLogParams : apiBase +  "/psi/tests/common/ReqParams/search",
		
		group : apiBase + "/psi/tests/common/ApiVersion/search",
		groups : apiBase + "/psi/tests/common/ApiVersions/search",
		doGroup : apiBase + "/psi/tests/group/save",
		
		groupApis : apiBase + "/psi/tests/group/apis",

};
