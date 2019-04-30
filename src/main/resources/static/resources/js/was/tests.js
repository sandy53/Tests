var API_INDEX = 0;
var Tests = {
  init: function() {
    var that = this;
  //  commonUtil.initBind(); // 绑定初始化事件
  //  HandlebarsUtil.registerHelper(); // 模板引擎自定义工具注册
    that.initBind();
    that.loadApi();
    //that.doGroup();
  },
  initBind : function(){
	  var that = this;
	  $("#j-group-show").bind("click",function(){
		  var has = $("#j-group-content").hasClass("fn-hide");
		  if(has){
			  that.loadGroup();
			  $("#j-group-content").removeClass("fn-hide");
			  $("#params-content").addClass("fn-hide");
		  }else{
			  $("#j-group-content").addClass("fn-hide");
		  }
	  });
	  $("#j-menu-all").bind("click",function(){
		  that.loadApi();
	  });
	 
  },
  doRun: function (){
	  var that = this;
	  $(`#run-group-plus .plus-item:eq(${API_INDEX})`).addClass("green-text");
	  var apiItem = $( `.item-api:eq(${API_INDEX++})`);
	  apiItem.addClass("item-api-runed")
	  var code =  apiItem.data("code");
	  var method =  apiItem.data("method");
      $("#j-method a").text(method);
      that.loadLogs(code, true);
  },
  doGroup : function (param){
	  var that = this;
/*	  var param = {
			  groupTitle:"测试一个组",
			  apiCode: '20e87ecb-44dc-4d19-a27e-992bfd461638',
			  apiPath: 'http://192.168.40.215:8080/api/order/dict/status',
	  }*/
	  param &&
	  commonUtil.http(ApiConfig.doGroup, param, function(data){
		  console.log(data);
		   if(!data.success){
			   console.log(data);
			   return;
		   }
	  });
  },
  /** 查询分组 */
  loadGroup : function (){
	  var that = this;
	  commonUtil.http(ApiConfig.group, {}, function(data){
		  $(".group-list").html(""); 
		  if(!data.success){
			   console.log(data);
			   return;
		   }
		   data = data.data;
		  $(data.results).each(function(index, group){
			  var item = $(`<li class="li-item item-group"></li>`);
			  //查询组关联的信息，  放到下方可编辑
			  var groupItem = $(`<a href="javascript:void(0);" data-index="${index}">${group.title}</a></li>`);
			  groupItem.bind("click",function(){
				  var groupEle =   $(`.item-group:not(:eq(${index}))`);
				  var showApis = groupEle.hasClass("fn-hide");
				  groupEle.toggleClass("fn-hide");
				  if(showApis){
					  $(".groups-list").html("");
					  return false;
				  }
				  $("#j-group-code").val(group.code);
				  //查询分组关联信息
				  group.code && 
				  commonUtil.http(ApiConfig.groups , {versionCode : group.code }, function(data){
					   console.log(data)
					   if(data.success){
						   that.renderGroups(data.data);
					   }
				  });
			  }).appendTo(item);
			  //查询组关联的API信息  放到左侧待执行
			  $(`<a class="group-do" href="javascript:void(0);"><-</a></li>`).bind("click",function(){
				  $("#j-group-code").val(group.code);
				  API_INDEX = 0;
				  group.code && 
				  commonUtil.http(ApiConfig.groupApis , {versionCode : group.code }, function(data){
					   console.log(data)
					   if(data.success){
						   that.renderApi(data.data);
						   that.renderRun(data.data);
					   }
				  });
				  groupItem.click();
			  }).prependTo(item);
			  
			  item.appendTo($(".group-list"));
		  });
	  });
  },
  /**  渲染 run执行按钮里的  +++ */
  renderRun : function(data) {
	 var that = this; 
	 var len = 0;
	 var plus = $("#run-group-plus");
	 plus.html("");
	 if(!data || !data.results || (len = data.results.length) == 0){
		 return false;
	 }
	 for (var i = 0; i < len; i++) {
		 plus.append(`<span class="plus-item">+</span>`)
	}
  },
  renderGroups :function(data){
	  var that = this;
	  $(".groups-list").html("");
	  $(data.results).each(function(index, group){
		  var format =  that.apiFormat(group.apiPath);
		  $(`<li class="li-item"><a href="javascript:void(0);">${format.path}</a></li>`).bind("click",function(){
			  //点击事件
		  }).appendTo($(".groups-list"));
	  });
  },
  /** 查询api请求列表 */
  loadApi : function(){
	  var that = this;
	  commonUtil.http(ApiConfig.apiInfo , {}, function(data){
		   console.log(data)
		   if(data.success){
			   that.renderApi(data.data);
		   }
	  });
  },
  renderApi: function (data){
	  var that = this;
	  $(".api-list").html("");
	  if(!data){
		  return;
	  }
	  $(data.results).each(function(index, api){
		 var obj =  that.apiFormat(api.path);
		//  console.log(path)
		  api.pathInfo = obj;
		  api.info = JSON.stringify(api);
		  var li = $(`<li class="li-item item-api"  data-code="${api.code}" data-path="${api.path}" data-method="${api.method}"></li>`);
		  //API列表事件
		  $(`<a href="javascript:void(0);"> ${api.title}:${api.pathInfo.path}</a>`).bind("click", function(){
			  	$("#j-method a").text(api.method);
			  	//隐藏分组数据
			  	$("#params-content").removeClass("fn-hide");
			  	$("#j-group-content").addClass("fn-hide");
			    //加载参数
		    	that.loadLogs(api.code);
		    }).appendTo(li);
		  //API加入组事件
		  $(`<a class="group-join" title="加入指定分组" href="javascript:void(0);">-></a>`).bind("click", function(){
			  var groupCode = $("#j-group-code").val();
			  var groupTitle = $("#j-group-title").val();
			  if(!groupCode &&  !groupTitle){
				  commonUtil.msg("请先选择分组或填写新组名", true);
				  return false;
			  }
				that.doGroup({groupCode: groupCode,  groupTitle: groupTitle, apiCode : api.code, apiPath : api.path});
		    }).appendTo(li);
		  
		   li.appendTo($(".api-list"));
	
	  });
	  
  },
  /** 加载api记录 */
  loadLogs:function (apiCode, isRun) {
	  var that = this;
	  commonUtil.http(ApiConfig.apiLogs , {apiCode: apiCode}, function(data){
		   console.log(data)
		   var list = $("#params-list");
		   list.html("");
		   if(data && !data.success){
			   return;
		   }
		   var first = null;
		   var firstLog = null;
		   $(data.data.results).each(function(index, log){
				  var li = $(`<li class="li-item"></li>`);
				  //API列表事件
				  var logItem = $(`<a href="javascript:void(0);">${log.path}</a>`)
				  logItem.bind("click", function(){
					  //清除上一次的值
					  $(".tab-params .form-control").val("");
					  that.loadLogParams(log, isRun); //加载参数
				  }).appendTo(li);
				  li.appendTo(list);
				  if(index == 0){
					  first = logItem; 
					  firstLog = log;
				  }
			});
		   
		   firstLog && $("#j-path").val(firstLog.path).data("original",firstLog.path);
		   console.log(first)
		   //默认第一个执行
		   first && first.click();
	  });
  },
  /** 加载api请求记录参数 */
  loadLogParams:function (log, isRun) {
	  var that = this;
	  commonUtil.http(ApiConfig.apiLogParams , {code: log.code}, function(data){
		   console.log(data)
		   if(data && !data.success){
			   return;
		   }
	    	//console.log(api)
	    	$("#j-path").val(log.path).data("code", log.code).data("original",log.path);
		   var params = {};
		   var headerIndex = 0;
		   var paramIndex = 0;
		
		   $(data.data.results).each(function(index, param){
			   var isParam =  param.paramType == "PARAM";
			   var paramTr = isParam ? $(`#api-params .api-param-item:eq(${paramIndex++})`) 
					   : $(`#api-headers .api-param-item:eq(${headerIndex++})`);
			   console.log(paramIndex + " : " + headerIndex)
			   if(paramTr.length == 1){
				   console.log(param)
				   paramTr.find(".param-key").val(param.param);
				   paramTr.find(".param-value").val(param.value);
				   paramTr.find(".param-description").val(param.remarks);
			   }else{
				   var tbody = isParam ?  $(`#api-params tbody`)  : $(`#api-headers tbody`);
				   var   value = param.value ? param.value : "";
				   var   description =param.remarks ? param.remarks : "";
				   var tr = $(`
							  <tr class="api-param-item">
								<td> <input type="checkbox" class="form-control param-check" checked="checked"></td>
								<td><input type="text" class="form-control  param-key"  value="${param.param}" placeholder="参数名"></td>
								<td><input type="text" class="form-control  param-value"   value="${value}"  placeholder="参数值"></td>
								<td><input type="text" class="form-control  param-description"   value="${description}"   placeholder="描述"></td>
							  </tr>
					  `);
					  tr.appendTo(tbody);
					  var td = $(`<td><a href="javascript:void(0);" class="api-param-remove mplus-item"><span  >x</span> </a></td>`);
					  td.bind("click", function(){
						  tr.remove();
					  }).appendTo(tr);
				   
			   }
			  // console.log(paramTr.length)
			});
		   isRun && that.doGo();
	  });
  },
  //api路径请求格式化
  apiFormat : function(path){
	  if(!path){
		  return;
	  }
	  var obj = {}
	  //协议
	  if(path.indexOf("http://") == 0){
		  obj.protocol = 'http';
		  path = path.replace("http://", "");
	  }else if(path.indexOf("https://") == 0){
		  obj.protocol = 'https';
		  path = path.replace("https://", "");
	  }
	  //截取端口前的数据
	  path = path.substring(path.indexOf("/"));
	  obj.path=path;
	  return obj;
  },
  //进行请求
  doGo : function() {
	  var that = this;
	 //收集请求参数
	  var path = $("#j-path").val();
	  if(!path){
		  alert("路径都不填，是要上天吗?");
		  return false;
	  }
      var original = $("#j-path").data("original");
      if(!original && path != original ){
    	  apiCode = "";
      }
	  var method = $("#j-method a").text();
	  var param = {
			  method : method,
			  path : path
	  }
	  var params = that.loadParams("#api-params");
	  var headers = that.loadParams("#api-headers");
	  param.params = params;
	  param.headers = headers;
	  console.log(param)
	  commonUtil.http("/psi/tests/request", param, function(data){
		  Resp.render(data);
		   //console.log(html);
		   //$(".tst-resp").html(html);
	  });
	  //console.log(param);
  },
  /** 加载 参数 */
  loadParams : function(parent){
	  var that = this;
	  var array = [];
	  parent && 
	  $(parent).find(".api-param-item").each(function(){
		  var check = $(this).find(".param-check");
		  if(!check.is(':checked')){
			  return;
		  }
		  var key = $(this).find(".param-key");
		  var value = $(this).find(".param-value");
		  var description = $(this).find(".param-description");
		  if(key.val()){
			  array.push({
				  'param': key.val() ,
				  'value' : value.val(),
				  'remarks' : description.val()
			  });
		  }
	  });
	  if(array.length > 0){
		  return JSON.stringify(array);
	  }
	  return null;
  }

  
}


