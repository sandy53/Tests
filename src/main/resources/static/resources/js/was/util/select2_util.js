  var Select2 = {
        bindAjax : function(url, selector,param, defaultVal,multiple) {
        	var that = this;
        	var callbackParam = {selector : selector, multiple : multiple, defaultVal:defaultVal}
        	commonUtil.ajax(url, "GET", param, that.bindAjaxAfter, callbackParam);
        },
        bindAjaxAfter : function (data, callbackParam){
        	if(callbackParam.multiple){
        		Select2.bindForTags(callbackParam.selector, data, callbackParam.defaultVal);
        		return;
        	}
        	Select2.bindLocalForData(callbackParam.selector, data, callbackParam.defaultVal);
        },
        bindAjaxSearch: function(url, selector,multiple,width) {
        	if(typeof(multiple)=='undefined'){
        		multiple=false;
        	}
        	var token = commonUtil.fetchToken();
    		if(!token){
    			commonUtil.msg("登陆超时，请先登陆！")
    			return false;
    		}
            $(selector).select2({
                ajax: {
                	url: url,
                    dataType: 'json',
                    delay: 250,
                    beforeSend: function(request) {
    	                request.setRequestHeader("Authorization", token);
    	            },
                    data: function (params) {
                        return {
                        	text: params.term
                        };
                    },
                    processResults: function (data, page) {
                        var display_data = data.data;
                     	console.log(data);
                     	return {
                     		results: display_data
                     	};
                    }
                },
                initSelection : function (element, callback) {
                	/*   var elementText = $(element).attr('data-initvalue');
                    callback(elementText);*/
                },
             //   width:width,
                multiple:multiple,
                placeholder: "请输入开始搜索",
                minimumInputLength: 1,
                allowClear: true
            });
        },
        format : function(param){
        	var data = [];
        	$(param).each(function(index, p){
        		data.push({id : p, text: p});
        	});
        	return data;
        },
        
        /**
         *  数据格式
         *  localData : [{id:'', text:''}]
         * 
         */
        //数据从指定数组而来    值只能从数组来
        bindLocalForData : function (selector, localData, defaultVal,width , placeholder){
          $(selector).empty();
          $(selector).select2({
     		  	initSelection : function (element, callback) {   // 初始化时设置默认值
     	        	callback({});
     	    	},
   	    		data: localData,
   	    		placeholder:  placeholder ? placeholder : "请点击查询",
   	    		width:width,
   	    		allowClear: true
           }).select2('val', defaultVal ? JSON.parse(defaultVal) : '');
           
        },
        bindLocalForDataMultiple : function (selector, localData,defaultVal){
            $(selector).empty();
            $(selector).select2({
       		  	initSelection : function (element, callback) {   // 初始化时设置默认值
       	        	callback({});
       	    	},
     	    		data: localData,
     	    		multiple:true,
     	    		placeholder: "可多选",
     	    		allowClear: true
             }).select2('val', defaultVal);
          },
        //数据从指定数组而来    从数组中搜索不到值时，可把输入值录入
        bindLocalForTags : function (selector, localData,placeholder,defaultVal , width){
        	 width = width ? width : 200;
        	 $(selector).empty();
        	 $(selector).select2({
     		  	initSelection : function (element, callback) {   // 初始化时设置默认值
     	        	callback({});
     	    	},
   	    		tags: localData,
   	    		placeholder: placeholder ? placeholder : "请点击选择或者输入",
   	    		width:width,
   	   // 		minimumInputLength: 1,
   	    		allowClear: true
           }).select2('val', defaultVal ? JSON.parse(defaultVal) : '');
        },
        //数据从指定数组而来    从数组中搜索不到值时，可把输入值录入
        bindForTags : function (selector, localData,placeholder,defaultVal , width){
        	 //width = width ? width : 200;
        	 $(selector).empty();
        	 $(selector).select2({
     		  	initSelection : function (element, callback) {   // 初始化时设置默认值
     	        	callback({});
     	    	},
   	    		tags: localData,
   	    		placeholder: placeholder ? placeholder : "请点击选择或者输入",
   	    		width: width,
   	    		multiple : true,
   	   // 		minimumInputLength: 1,
   	    		allowClear: true
           }).select2('val', defaultVal ? JSON.parse(defaultVal) : '');
        }
    };
    
    $.fn.select2.defaults.set("language", {
        errorLoading: function () {
            return "无法载入结果。"
        }, inputTooLong: function (e) {
            var t = e.input.length - e.maximum, n = "请删除" + t + "个字符";
            return n
        }, inputTooShort: function (e) {
            var t = e.minimum - e.input.length, n = "请再输入至少" + t + "个字符";
            return n
        }, loadingMore: function () {
            return "载入更多结果…"
        }, maximumSelected: function (e) {
            var t = "最多只能选择" + e.maximum + "个项目";
            return t
        }, noResults: function () {
            return "未找到结果"
        }, searching: function () {
            return "搜索中…"
        }
    });
