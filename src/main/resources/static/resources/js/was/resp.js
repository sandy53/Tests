
var Resp = {
  render : function (data){
	  var that = this;
	  $(".tst-resp").html("");
	  var json = null;
	  try {
		  json = JSON.parse(data);
	  } catch (e) {
		  console.log(e);
		  $(".tst-resp").html(data);
		  return false;
	  }
	
	 // console.log(json)
	
	that.renderObj(json, $(".tst-resp"), 1);
	
	// 补齐  [  ]  { } ,等
	$(".ul-obj").prepend("{").append("}");
	$(".resp-ul .ul-obj").not(":last").append(",");
	$(".ul-array").prepend("[").append("]");
	$(".resp-ul  .resp-li").not(":last").append(",");
  },
  
  renderObj : function(data, parent, layer){
	  var that = this;
	  if(!data || typeof(data) != 'object'){
		  return false;
	  }
	  var sub = $(`<ul class="resp-ul ul-obj"></ul>`);
	  sub.appendTo(parent);
	  //console.log('obj....' + JSON.stringify(data)); //
	  //console.log(Object.getOwnPropertyNames(data).length)
	  for ( var key in data) {
			var value = data[key];
			if(Array.isArray(value)){
				var arrSub = $(`<li class="resp-li resp-li-${layer}">
					 		<span class="resp-key">"${key}"</span>:
						</li>
				`);
				arrSub.appendTo(sub);
				var arrSubVal = $(`<ul class="resp-ul ul-array"></ul>`);
				arrSubVal.appendTo(arrSub);
				that.renderArray(value, arrSubVal, ++layer);
			}else if(typeof(value) == "object" && Object.prototype.toString.call(value).toLowerCase() == "[object object]" && !value.length){
				that.renderObj(value, sub, layer);
			}else{
				//console.log(`${key} : ${value}`)
				$(`
						<li class="resp-li resp-li-${layer}">
					 		<span class="resp-key">"${key}"</span>:<span>${value}</span>
					 	</li>
				`).appendTo(sub);
			}
	 }
  },
  renderArray : function (array, parent, layer){
	  var that = this;
	  //console.log('array....' + JSON.stringify(array))
	  if(!array || !Array.isArray(array)){
		  return false;
	  }
	  for ( var index in array) {
		  that.renderObj(array[index], parent, ++layer);
	 }
  }
  
}


