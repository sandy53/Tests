/** Handlebars 工具类*/
var  HandlebarsUtil = {
		isIgnoreList : "系统订单号", //是否忽略的元素，如果非管理员，将隐藏这些元素， 多个用,分隔
		registerHelper : function() { // handlebars 插件注册
			var that = this;
			Handlebars.registerHelper("eachPage", function(start, end) {
				var arr = new Array();
				for (var i = start; i < end; i++) {
					arr.push(i);
				}
				return arr;
			});
			Handlebars.registerHelper("formatTime", function(timestamp) {
				if (!timestamp)
					return "";
				var date = new Date(timestamp);
				return date ? date.Format("yyyy-MM-dd hh:mm:ss") : "";
			});
			Handlebars.registerHelper("formatDate", function(timestamp) {
				if (!timestamp)
					return "";
				var date = new Date(timestamp);
				return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
						+ date.getDate();
			});
			Handlebars.registerHelper("indexInit", function(index) {
				return index + 1;
			});
			Handlebars.registerHelper('prettifyPositive', function(number) {
				var format = '0.00', zero = true;
				number = parseInt(number) / 10000;
				if (arguments.length > 2) {
					format = arguments[1];
				}
				if (arguments.length > 3) {
					zero = arguments[2];
				}
				return (zero ? number != null && number > 0 : !!number) ? numeral(
						number).format(format) : '0';
			});
			Handlebars.registerHelper('equals', function(v1, v2, opts) {
				if (v1 == v2)
					return opts.fn(this);
				else
					return opts.inverse(this);
			});
			Handlebars.registerHelper('unEquals', function(v1, v2, opts) {
				if (v1 != v2)
					return opts.fn(this);
				else
					return opts.inverse(this);
			});
			Handlebars.registerHelper('isDeclare', function(state, opts) {
				if (state >=20 && state < 50)
					return opts.fn(this);
				else
					return opts.inverse(this);
			});
			Handlebars.registerHelper('hbIcon', function(code, opts) {
				return IconConfig[code];
			});
			Handlebars.registerHelper('hbStatus', function(type, status, opts) {
				if(type == 1){ // 订单导入状态
					switch(status){
						case 0:
							return "初始化中";
						case 1:
							return "初始化成功";
						default:
							return "初始化失败";
					}
				}else if(type == 2){ // 发货单导入状态
					return State[status];
				}else if(type == 3){ //地址实名认证状态
					switch(status){
					case 1:
						return "认证成功";
					case 2:
						return "认证失败";
					default:
						return "未认证";
					}
				}
				return "";
			});
			Handlebars.registerHelper('hbText', function(text, length, opts) {
				if(!text || text.length <= length){
					return text;
				}
				return text.substring(0, length) + "...";
			});
			Handlebars.registerHelper('money', function(money, opts) {
				return commonUtil.money(money);
			});
			
			Handlebars.registerHelper('is', function(opts) {
				if (commonUtil.is())
					return opts.fn(this);
				else
					return opts.inverse(this);
			});
			Handlebars.registerHelper('ishow', function(sign, opts) {
				if (sign && that.isIgnoreList.indexOf(sign) >=0 && !commonUtil.is()){
					return opts.inverse(this);
				}else{
					return opts.fn(this);
				}
			});
			Handlebars.registerHelper('hbTax', function(type, opts) {
				if (type === 1){
					return "行邮";
				}else if(type === 2){
					return "HS";
				}
				return "未知类型";
			});
			Handlebars.registerHelper('hbJson', function(data, opts) {
				if (data){
					return JSON.stringify(data);
				}
				return '';
			});
		}
		
		
}