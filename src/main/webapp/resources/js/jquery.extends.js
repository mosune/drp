(function(jQuery) {
	jQuery.fn.extend({
		bindSelector: function(settings) {
			$(this).click(function(){
				var _modal = $('#'+settings.target+'_modal');
				if (_modal.size() > 0) {
					_modal.modal('show');
					return;
				}
				var data = [
				                	{name: 'target', value: settings.target},
				                	{name: 'isCredit', value: settings.isCredit},
				                	{name: 'custCast', value: settings.custCast},
				                	{name: 'callback', value: settings.callback},
				                	{name: 'code', value: settings.code},
				                	{name: 'isFactor', value: settings.isFactor}
				                	/*{name: 'orgType', value: settings.orgType}*/
				                ];
				data = data.concat(settings.options);
				jQuery.ajax({
					url: settings.url,
					type: 'post',
					data: data,
					success: function(htm) {
						$('body').append(htm);
						
						$('#'+settings.target+'_modal').modal('show');
					}
				});
			});			
		},
		dateFormat: function(date, format) {
			if (!date) return;
		    if (!format) format = "yyyy-MM-dd";   
		    switch(typeof date) {
		        case "string":   
		            date = new Date(date);
		            break;   
		        case "number":   
		            date = new Date(date);   
		            break;   
		    }
		    if (!date instanceof Date) return;
		    var o = {
		    	    "M+" : date.getMonth()+1, //月份           
		    	    "d+" : date.getDate(), //日           
		    	    "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时           
		    	    "H+" : date.getHours(), //小时           
		    	    "m+" : date.getMinutes(), //分           
		    	    "s+" : date.getSeconds(), //秒           
		    	    "q+" : Math.floor((date.getMonth()+3)/3), //季度           
		    	    "S" : date.getMilliseconds() //毫秒           
		    	    }; 
		    if(/(y+)/.test(format)){           
		    	format=format.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));           
		    }             
		    for(var k in o){           
		        if(new RegExp("("+ k +")").test(format)){           
		        	format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
		        }           
		    }           
		    return format;
		}
	});
	
})(jQuery);