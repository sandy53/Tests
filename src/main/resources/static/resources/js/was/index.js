$(document).ready(function() {
	index.init(); // 首页初始化
	//modal 中使用select2时，搜索框没法使用解决
    $.fn.modal.Constructor.prototype.enforceFocus = function () {};
});



var index = {
  init: function() {
    var that = this;
  //  commonUtil.initBind(); // 绑定初始化事件
  //  HandlebarsUtil.registerHelper(); // 模板引擎自定义工具注册
    that.initBind();
    Tests.init();
  },
  initBind : function(){
	  var that = this;
	  $("#j-method").bind("click", function(){
		  var a = $(this).find("a");
		  a.text(a.text() == "GET" ? "POST" : "GET");
	  });
	  $("#j-btn-go").bind("click", function(){
		  if($("#run-group-plus .plus-item").length > 0){
			  Tests.doRun();
		  }else{
			  Tests.doGo();
		  }
	  });
	  $("#j-btn-reverse").bind("click", function(){
		  $("#run-group-plus").html("");
		  $("#j-path").val("");
		  API_INDEX = 0;
		  $("#j-method a").text("GET");
	  });
	  $(".api-param-add").bind("click", function(){
		  var tbody = $(this).parents(".tab-params").find("tbody");
		  that.doAddParam(tbody);
		  
	  });

  },
  doAddParam :function (tbody, key, value, description){
	  key = key ? key : "";
	  value = value ? value : "";
	  description = description ? description : "";
	  var tr = $(`
			  <tr class="api-param-item">
				<td> <input type="checkbox" class="form-control param-check" checked="checked"></td>
				<td><input type="text" class="form-control  param-key"  value="${key}" placeholder="参数名"></td>
				<td><input type="text" class="form-control  param-value"   value="${value}"  placeholder="参数值"></td>
				<td><input type="text" class="form-control  param-description"   value="${description}"   placeholder="描述"></td>
			  </tr>
	  `);
	  tr.appendTo(tbody);
	  var td = $(`<td><a href="javascript:void(0);" class="api-param-remove mplus-item"><span  >x</span> </a></td>`);
	  td.bind("click", function(){
		  tr.remove();
	  }).appendTo(tr);
  },
  
}


