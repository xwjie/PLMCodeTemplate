/**
 * 
 */
define([ 'jquery', 'tpl', 'layer', 'text!tplDir/comment.js',
		'text!tplDir/openreplay.html' ], function($, tpl, layer, commentTpl,
		openreplayTpl) {

	console.log('jq', $);
	console.log(tpl);
	console.log(commentTpl);

	return {
		getAll : function(divid, param) {

			function renderConfigs(divid, configs) {
				var html = tpl.template(commentTpl, {
					list : configs
				});

				document.getElementById(divid).innerHTML = html;

				$(".cls-del").on("click", function() {
					// layer.msg('不开心。。', {
					// icon : 5
					// });

					layer.open({
						type : 1,
						title : false,
						closeBtn : 0,
						area : '516px',
						skin : 'layui-layer-demo', // 样式类名
						closeBtn : 0, // 不显示关闭按钮
						anim : 2,
						shadeClose : true, // 开启遮罩关闭
						content : openreplayTpl,
						end : function() {
							refresh();
						}
					});
				});
			}

			function refresh(){
				$.getJSON('config/all', param, function(result) {
					renderConfigs(divid, result.data);
				});
			}
			
			refresh();
		},
	}
});