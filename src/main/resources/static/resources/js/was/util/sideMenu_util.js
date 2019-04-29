//侧边子菜单工具类
var subMenuFold = "sub-menu-fold"; // 侧边子菜单 是否展开 cookie键
function SideMenu (content){
	this.content = content;
	this.init = function (code){
		var that = this;
		that.initSubMenu(code);
		return this;
	};
	this.initSubMenu = function (code){ //初始化子菜单
		var that= this;
		var menus = SubMenus[code];
		if (!menus){
			return ;
		}
		 commonUtil.loadTpl("frame-sidemenu", null, content.find(".ms-content-body"), function (){
				var html = commonUtil.handlebars($("#subMenuTpl").html(), menus);
			    content.find(".ms-sub-menu").html(html);
			    that.bind();
			    var menu = ".ms-sub-menu-active";
			    //从cookie获取初始化参数
			    var  contentId = that.content.data("id");	
			    var lastSubMenu = $.cookie(`sm-${that.content.data("id")}`);
			    if(lastSubMenu) {
			    	menu = `.ms-sub-menu-${lastSubMenu}`;
			    }
			    that.change(content.find(menu));
				that.menuFold.call(that, $.cookie(subMenuFold));
		 });
	};
	this.bind = function (){
		var that = this;
		content.find(".ms-sub-menu-open").on("click",  function(){
			var fold =   content.find(".ms-sub-sidemenu").hasClass("ms-sub-sidemenu-fold");
			that.menuFold(!fold);
		});
		content.find(".ms-sub-menu-item").on("click", function(){
				var code = $(this).data("code");
				var activeCode = $(".ms-sub-menu-active").data("code");
				/* if(!code || code == activeCode){ return false; } */
				that.change($(this));
		});
	};
	this.change = function (menu, params){
		if(!menu){
			return false;
		}
		if(!menu.hasClass("ms-sub-menu-active")){
			menu.addClass("ms-sub-menu-active");
			menu.siblings(".ms-sub-menu-active").removeClass("ms-sub-menu-active");
		}
    	$.cookie(`sm-${this.content.data("id")}`, menu.data("code"));
		//加载相应的 js
		var jsfile = menu.data("jsfile");
        if(jsfile){ //加载JS
        	//UrlUtil.initByUrl( "sm",$(menu).data("code"));
        	 commonUtil.loadJs2(jsfile);
        }else{
    		commonUtil.msg("功能还在开发中!");
        }
	};
	this.menuFold = function (isFold){ //菜单折叠
		var that = this;
		var left = that.content.find(".ms-sub-sidemenu");
		if(isFold){
			$.cookie(subMenuFold, true );  
			left.animate({width:"40px"}, "slow", function (){
				left.addClass("ms-sub-sidemenu-fold");
				that.content.find(".ms-sub-container").animate({width:"96%"}, "slow");
			});
		}else{
			$.cookie(subMenuFold, "" );  
			that.content.find(".ms-sub-container").animate({width:"90%"}, "100", function (){
				left.animate({width:"10%"}, "slow", function (){
					left.removeClass("ms-sub-sidemenu-fold");
				});
			});
		}
	};
}

