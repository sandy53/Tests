var commonUtil = {
	common : this,
	POST : "POST",
	GET : "GET",
	version : function() { // 上线后可更换为 版本号
		//return Math.random();
		return 1.0;
	},
	is : function (){ //是否 a d min
		return $(".ms-menu").data("msi");
	},
	showIs : function (){ //显示管理可见
		var that = this;
		if(that.is()){
			$(".ms-hide").removeClass("ms-hide");
		}
	},
	initBind : function(){
		var that = this;
	    that.bindLogin(); //绑定登陆事件
	},
	
	btnEvent : function (targetBtn){  //绑定按钮事件
		var btn = 	$(targetBtn);
		btn.prop("disabled", true);
		var title =btn.val();
		var times = 2;
		var interval = setInterval(function(){
				btn.val(times + "S");
				if(times <= 0){
					btn.val(title);
					btn.prop("disabled", false);
					clearInterval(interval);
				}
				times -= 1;
		}, 1000);
	},
	// -------------------------------  modal  ------------------------------------------------------------------------------
	modal: function (isShow, title, content){
		var modal = $("#ms-modal");
		if(isShow){
			modal.modal("show");
			modal.find(".modal-dialog").removeClass("modal-pay");
			modal.find(".ms-modal-btn").empty();
			modal.find("#msModalLabel").html(title);
			content && $("#ms-modal .ms-modal-body").html(content);
		}else{
			modal.modal("hide");
		}
	},
	formParams : function (reqParam, target){
		var that = this;
		var formControl = target || that.find(".j_search .form-control");
		if(!formControl){
			return false;
		}
		var validate = true;
		reqParam = reqParam || {};
		formControl.each(function(index, form) {
			var name = $(form).attr("name");
			var required = $(form).prop("required");
			var val = $(form).val();
			var title = $(form).prop("title");
			if (( required) && (!val || val == "")) {
				commonUtil.validFail($(form), title + " 不能为空");
				validate = false;
				return false;
			}
			if(val && val != ""){
				reqParam[name] = val;
				var regex =  $(form).prop("pattern");
				//regex = regex || WasConfig.default_pattern;
				if(regex && !(new RegExp(regex,"i").test(val))){
					console.log(regex)
					console.log(name)
					commonUtil.validFail($(form))
					validate = false;
					return false;
				}
			}
		});
		if (!validate) {
			return false;
		}
		return reqParam;
	},
	
	http : function(url, param, callback) { // ajax 统一方法
		var that = this;
		if(!param){
			param = {};
		}
		param.limitNum = 20;
		commonUtil.loading(true);
		url &&
		$.ajax({
			type :  "POST",
			url : url,
			data : param,
			beforeSend: function(request) {
				//token && request.setRequestHeader("Authorization", token);
            },
			success : function(data) {
				commonUtil.loading(false);
				//console.log(data);
				callback(data);
			},
			error : function(e){
				commonUtil.loading(false);
				console.log(e);
				//callback(e);
			}
		});
		
		
	},
	
	
	doAjax : function(token, param) { // ajax 统一方法
		var that = this;
		if(!token || !param){
			//return;
		}
		param.url &&
		$.ajax({
			type :  param.type || "GET",
			url : param.url,
			data : param.reqParam,
			beforeSend: function(request) {
				token && request.setRequestHeader("Authorization", token);
            },
			success : function(data) {
				commonUtil.loading(false);
				if (!data.resultCode) {// 未知格式
					param.callback && param.callback.call(that, data, param.callbackParam); // 回调
					return;
				}
				if (data.resultCode != 100000) {
					commonUtil.msg(data.reason, true);
					param.eCallback && param.eCallback.call(that);
					return;
				}
				param.callback && param.callback.call(that, data.data, param.callbackParam); // 回调
			},
			error : function(e){
				commonUtil.loading(false);
				param.eCallback && param.eCallback.call(that);
				if(e.status == 401){
					commonUtil.initLogin();
					commonUtil.msg("验证失败，请重新登陆！");
					return;
				}
				if(e.status == 503){
					commonUtil.msg("服务升级中，请稍候再试！");
					return;
				}
				console.log("do ajax error");
				console.log(e);
			}
		});
		
		
	},
	
	ajax : function(url, type, reqParam, callback, callbackParam, eCallback) { // ajax 统一方法
		var that = this;
		commonUtil.loading(true);
		var ajaxParam = {
				url : url, 
				type : type, 
				reqParam:reqParam, 
				callback:callback, 
				callbackParam:callbackParam, 
				eCallback:eCallback
		}
		var token = commonUtil.fetchToken();
		commonUtil.doAjax.call(that, token,ajaxParam);
		//console.log(ajaxParam)
		return;
		
		if(token && token != "null"){
			commonUtil.doAjax.call(that, token,ajaxParam);
			return;
		}
		var refreshToken = $.cookie('msrefreshtoken');
		if(refreshToken){
			commonUtil.doLogin.call(that, {"rpassword": refreshToken}, commonUtil.doAjax, ajaxParam);
			return false;
		}
		 //刷新token不存在则初始化登陆界面
		commonUtil.initLogin();		
	},
	msg : function(msg, isWarn) { // 消息框
		var that = this;
		var warn = isWarn ? 'msg-warn' : "";
		var id = Math.random() +"";
		id = "msg-item-"+id.substring(2);
		$('<span class="msg-box-itme '+id+'  '+warn+'">'+msg+'</span>').prependTo(".msg-box");
		setTimeout(function() {
			$("."+id).remove();
		}, 5000);
	},
	paging : function(paging, searchCallback, renderCallback) { // 分页渲染
		var that = this;
		$('.pagination').empty();
		if(!paging || paging.pages <=1){
			return;
		}
		PageUtil.pagingRender.call(that, paging); //分页渲染
		PageUtil.pagingEvent.call(that, searchCallback,  renderCallback);//单击事件绑定
	},
	load : function(html, target, callback) {                 // 加载html文件
		if(!html || $(".ms-"+html).length > 0){
			callback && callback();
			return false;
		}
		commonUtil.loading(true);
		var url  = html + ".html";
		$.ajax({
			url : url,
			async : true,
			success : function(html) {
				commonUtil.loading(false);
				target = target ? target : ".ms-sub-content";
				$(target).html(html);
				callback && callback();
			},
			error : function (e) {
				commonUtil.loading(false);
				if(e.status == 404){
					commonUtil.msg("功能还在开发中", true);
				}
			}
		});
	},
	loadTpl : function(fileName, data, target, callback, callbackParam, isAppend) { // 加载 tpl模板文件
		var that = this;
		if($("#tmp-"+fileName).length > 0){
			commonUtil.loadTplAfter.call( that, fileName, data, target, callback, callbackParam, isAppend);
			return;
		}		
		$.ajax({
			url : "/templates/" + fileName + ".tpl?t=" + commonUtil.version(),
			success : function(html) {
				$("body").append(html);
				commonUtil.loadTplAfter.call(that, fileName, data, target, callback, callbackParam, isAppend);
			}
		});
	},
	loadTplAfter : function (fileName, data, target, callback, callbackParam, isAppend){
		var that = this;
		if(target){
			var html  = commonUtil.handlebars($("#tmp-"+fileName).html(), data);
			var content = null;
			if(typeof target == "string"){
				content = $(target);
			}else{
				content = target;
			}
			if( isAppend){
				$(target).append(html);
			}else{
				$(target).html(html);
			}
		}
		callback && callback.call(that,callbackParam); // 回调
	},
	loadJs : function(js) { // 加载JS
		var html = `<script id="mmng" src="./resources/js/was/${js}.js?t=${commonUtil.version()}"></script> `;
		$("head").find("#mmng").remove();
		$("head").append(html);
	},
	loadJs2 : function(js, dir, param, func) { // 加载JS
		if(param){//缓存参数
			jsParamCache[js] = param;
		}
		if(func){
			jsFuncCache[js] = func;
		}
		if($(`#${js}`).length > 0){ //已经存在
			jsCache[js].init();
			return;
		}
		dir =  dir ||  "";
		js = dir + js;
		var html = `<script id="${js}" src="./resources/js/was/${js}.js?t=${commonUtil.version()}"></script> `;
		$("head").append(html);
	},
	bindCheckbox : function (target){
		if(target){
			$(target).find(".cbox-all").unbind().on("change", function(){
				$(target).find(".cbox-item").prop("checked", $(this).is(':checked'));
			});
		}else{
			$(".cbox-all").unbind().on("change", function(){
				$(".cbox-item").prop("checked", $(this).is(':checked'));
			});
		}
	},
	// -------------------------------------------------------- 日志 -----------------------------------------------------------------------------------------------------------------
	logList : function (recordType, recordCode){
		var that = this;
		commonUtil.ajax(ApiConfig.logList, commonUtil.GET, {"recordType":recordType, "recordCode":recordCode}, that.logListAfter);
	},
	logListAfter : function (data){
		var that = this;
		commonUtil.loadTpl("log-list" , data.results , "#modal-log #log_body");	
		$("#modal-log").modal("show");
	},
	//  验证码
	initVcode : function (target, key){
		var that = this;
		$.ajax({
			url : ApiConfig.imglogin,
			success : function(data) {
				if (data.resultCode != 100000) {
					that.msg(data.reason, true);
					return;
				}
				$("#"+target).prop("src", "data:image/jpeg;base64,"+ data.data.value);
				$("#"+key).val( data.data.key);
			}
		});
	},
	// login token
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	fetchToken : function (){
		return $.cookie('mstoken');
	},
	initLogin : function (){
		var that = this;
		$("#modal-login").modal("show");		
		//密码登陆暂留
		$("#vcode")[0].focus();
		that.initVcode('img-login', 'vcodekey');
		//渲染二维码
		that.loginQrcodeRender();
	},
	loginQrcodeRender :function(){
		var that = this;
		$("#qrcode-refresh").unbind().bind("click", function(){
			$(".qrcode-timeout").addClass("fn-hide");
			that.loginQrcodeRender();
		});
		//扫码登陆
		$.ajax({
			type : commonUtil.GET,
			url : ApiConfig.loginQrcode,
			success : function(data) {
				commonUtil.loading(false);
				if(data.data){
					 $("#login_qrcode").empty().qrcode({
				    		width: 256, 
				    		height: 256, 
				    		render : "canvas",    
				    		text: data.data, 
				    		background : "#ffffff",  
				    });
					 that.loginQrcodePoll();
				}else{
					commonUtil.msg("登陆服务暂停！");
				}
			},
			error : function(e){
				commonUtil.loading(false);
				console.log("loginKey error: "+ e);
				commonUtil.msg("登陆服务升级中，请稍候再使用！");
			}
		});
	},
	//轮询  二维码
	loginQrcodePoll : function(){
		var that = this;
		var count = 0;
		var interval = setInterval(function(){
			//请求后台
			$.ajax({
				type : commonUtil.GET,
				url : ApiConfig.loginQrcodePoll,
				success : function(data) {
					console.log(data);
					if(data.data){
						// 存储到cookie
						var token = data.data;
						var date = new Date();
						var tokenstr = token.token_type+token.access_token;
						date.setTime(date.getTime()+token.expires_in * 1000);
						$.cookie('mstoken', tokenstr , { expires: date });  
						$.cookie('msrefreshtoken', token.refresh_token);  
						$("#modal-login").modal("hide");
						window.clearInterval(interval);
						commonUtil.msg("登陆成功，可刷新页面确认！")
					}
				}
			});
			count ++;
			if(count >= 20){// 3秒执行一次， 20次，一分钟
				window.clearInterval(interval);
				// 设置超时
				$(".qrcode-timeout").removeClass("fn-hide");
			}
		},3000);
		
	},
	bindLogin : function (){// 登陆事件
		var that = this;
		$("#modal-login").on("keyup", function(event){
			 if(event.keyCode ==13){  //回车
				that.loginForPag();
			 }
		});
		$("#btn-login").unbind().on("click", function(){
			that.loginForPag();
		});
	},
	loginForPag : function (){
		var that = this;
		var reqParam = commonUtil.formParams({}, $(".j_login .form-control"));
		commonUtil.login(reqParam, function (){
			window.location.reload();
		});
	},
	login : function(reqParam, callback, callbackParam){
		var that = this;
		commonUtil.loading(true);
		reqParam &&
		$.ajax({
			type : commonUtil.GET,
			url : ApiConfig.loginKey,
			success : function(data) {
				commonUtil.loading(false);
				if(data.resultCode == 100000 && data.data){
					setMaxDigits(130);
				  	var key = new RSAKeyPair("10001","",data.data); 
				  	var username = encryptedString(key, encodeURIComponent(reqParam["username"]));
					var pass = encryptedString(key, encodeURIComponent(reqParam["password"]));
					reqParam["username"] = username;
					reqParam["password"] = pass;
					commonUtil.doLogin.call(that, reqParam, callback, callbackParam);
				}else{
					console.log(data)
					commonUtil.loading(false);
					commonUtil.msg("服务升级中，请稍候再使用！");
				}
			},
			error : function(e){
				commonUtil.loading(false);
				console.log("loginKey error: "+ e);
				commonUtil.msg("服务升级中，请稍候再使用！");
			}
		});
	},
	doLogin : function(reqParam, callback, callbackParam){
		var that =this;
		commonUtil.loading(true);
		reqParam && 
		$.ajax({
			type : commonUtil.POST,
			url : ApiConfig.login,
			data : reqParam,
			success : function(data) {
				commonUtil.loading(false);
				console.log(data);
				if(data.resultCode == 100013){
					commonUtil.msg("登陆服务升级中，请稍候再试或联系我们", true);
					$("#modal-login #img-login").click();
					$("#modal-login #vcode").val("");
					return null;
				}
				if(data.resultCode != 100000){
					commonUtil.msg(data.reason, true);
					$("#modal-login").modal("show");
					$("#modal-login #img-login").click();
					$("#modal-login #vcode").val("");
					return null;
				}
			
				// 存储到cookie
				var token = data.data;
				var date = new Date();
				var tokenstr = token.token_type+token.access_token;
				date.setTime(date.getTime()+token.expires_in * 1000);
				$.cookie('mstoken', tokenstr , { expires: date });  
				$.cookie('msrefreshtoken', token.refresh_token);  
				$("#modal-login").modal("hide");
				callback && callback.call(that, tokenstr, callbackParam);
			},
			error : function(e){
				commonUtil.loading(false);
				console.log("login error: ")	
				console.log(e)	
				var msg = null;
				if(e.status == 400){
					msg = "用户名，密码或验证码错误！";
					$("#modal-login").modal("show");
					$("#modal-login #img-login").click();
					$("#modal-login #vcode").val("");
				}else if(e.status == 401){
					msg = "暂不支持登陆！请稍候再试";
				}
				if(msg != null){
					commonUtil.msg(msg, true);
					return;
				}
				if(e.responseJSON && e.responseJSON.reason){
					commonUtil.msg( e.responseJSON.reason, true);
				}
			}
		});
	},
	
	afterLogout : function(data){
		console.log(data);
	},
	//loading
	loading : function (show){
		if(show){
			$(".ms-loading").addClass("ms-loading-show");
		}else{
			$(".ms-loading").removeClass("ms-loading-show");
		}
	},

	// -----------------------------------------------------------------------	 handlebars  -------------------------------------------------------------------------------------------------------
	handlebars : function(htmlTpl, data) { // handlebars 模板渲染
		var PL = Handlebars.compile(htmlTpl);
		var content = PL ? PL(data) : "";
		return content;
	},
	//分转元
	money : function (money){
		if ( typeof money !== "number" || isNaN( money ) ) {return null;}
	    return ( money / 100 ).toFixed( 2 );
	},
/*	money: function (money){  //金额格式化
		if(money == 0){
			return "0.00";
		}

		money = money.toString().replace(/\$|\,/g,'');  
	    if(isNaN(money))  
	    	money = "0";  
	    sign = (money == (money = Math.abs(money)));  
	    money = Math.floor(money*100+0.50000000001);  
	    cents = money%100;  
	    money = Math.floor(money/100).toString();  
	    if(cents<10)  
	    cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((money.length-(1+i))/3); i++)  
	    	money = money.substring(0,money.length-(4*i+3))+','+  
	    	money.substring(money.length-(4*i+3));  
	    return (((sign)?'':'-') + money + '.' + cents);  
	},*/
	confirm : function (msg, callback, param){
		if(confirm(msg)){
			callback && callback(param);
		}
	},
	validFail : function (target, msg){
		var forValid = target.data("forvalid");
		var ele = forValid ? $(forValid) : target;
		var top=  ele.offset().top;
		 var left =  ele.offset().left;
		 var  height = ele.outerHeight();
		 var validate =$(".ms-form-validate");
		 validate.find(".text").text(msg || target.data("warn") || WasConfig.default_warn);
		 validate.css({left: left + 1, top : top+height + 10}).removeClass("fn-hide");
		 setTimeout(function() {
			 validate.addClass("fn-hide")
		}, 1000);
		 
	},
	pay : function (orderSn ){
		var that = this;
		orderSn && 
		commonUtil.ajax.call(that, ApiConfig.paySumpay + orderSn, commonUtil.GET, null, function(data){
			if(!data){
				commonUtil.msg("生成支付参数异常！");
				return;
			}
			if(data.resp_code != "000000"){
				commonUtil.msg(data.resp_msg);
				return;
			}
			commonUtil.modal(true, "订单支付", " ");
			$("#ms-modal .modal-dialog").addClass("modal-pay");
			$(`<a  class="opt-pay-item" href="${data.redirect_url}" target="_blank">支付单生成成功，点击跳转前往支付</a>`).appendTo($("#ms-modal .ms-modal-body")).on("click", function(){
				commonUtil.modal();
			});
		});
	}

};