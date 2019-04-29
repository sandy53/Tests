/** 页面工具类 */
var PageUtil = {
		pagingRender : function (paging){
			var that = this;
			var currentPage = paging.currentPage;
			var start = currentPage - 3;
			start = start > 0 ? start : 1;
			var end = start + 6;
			if (end > paging.pages) {
				end = paging.pages;
				start = end - 6;
			}
			start = start > 0 ? start : 1;
			var previous = start - 1;
			var previousCanuse = previous > 0 ? "" : "disabled";
			var next = end + 1;
			var nextCanuse = next <= paging.pages ? "" : "disabled";
			paging["previous"] = previous;
			paging["previousCanuse"] = previousCanuse;
			paging["next"] = next;
			paging["nextCanuse"] = nextCanuse;
			paging["start"] = start;
			paging["end"] = end;
			var pageItem = new Array();
			var active = "";
			for (var i = start; i <= end; i++) {
				active = i == currentPage ? "active" : "";
				pageItem.push({
					page : i,
					active : active
				});
			}
			paging["pageItem"] = pageItem;
			var html = commonUtil.handlebars($("#pageTpl").html(), paging);
			that.find('.pagination').html(html);
		},
		pagingEvent : function(searchCallback,  renderCallback) { // 分页事件
			var that = this;
			$(".j_page").unbind().on("click", function() {
				var clickPage = $(this).data("page");
				var pages = $(".j_page_total").data("page");
				if (clickPage <= 0 || clickPage > pages) {
					return;
				}
				$(".pagination li").removeClass("active");
				$(this).parent().addClass("active");
				UrlUtil.initByUrl("page", clickPage, "d");
				searchCallback && searchCallback.call(that, {"currentPage": clickPage}, renderCallback);
			});
		},
		
}