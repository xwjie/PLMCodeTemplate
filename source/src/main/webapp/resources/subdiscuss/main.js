/**
 * 
 */
define([ 'jquery', 'tpl', 'text!tplDir/comment.js', 'layer' ], function($, tpl,
		commentTpl, layer) {
	console.log('jq', $);
	console.log(tpl);
	console.log(commentTpl);

	function renderConfigs(divid, configs) {
		var html = tpl.template(commentTpl, {
			list : configs
		});

		document.getElementById(divid).innerHTML = html;
	}

	return {
		getAll : function(divid, param) {
			$.getJSON('config/all', param, function(result) {
				renderConfigs(divid, result.data);
			});
		},

		open : function() {
			// 页面层-佟丽娅
			/*layer.open({
				type : 1,
				title : false,
				closeBtn : 0,
				area : '516px',
				skin : 'layui-layer-nobg', // 没有背景色
				shadeClose : true,
				content : $('#configs2')
			});*/
			layer.msg('不开心。。', {icon: 5});
		}
	}
});