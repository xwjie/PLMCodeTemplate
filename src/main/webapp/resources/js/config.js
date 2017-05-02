/**
 * 
 */

		function showError(s) {
			alert(s);
		}

		function handlerResult(result, fn) {
			// 成功执行操作，失败提示原因
			if (result.code == 0) {
				fn(result.data);
			} else {
				showError(result.msg);
			}
		}
		
		function loginDone(data){
			$("#userinfo").html("login user:" + data);
		}

		function deleteConfig(id) {
			$.post('config/delete', {
				id : id
			}, function(result) {
				console.log('delete result', result);
				handlerResult(result, fetchAllConfigs);
			});
		}

		function fetchAllConfigs() {
			$.getJSON('config/all', function(result) {
				handlerResult(result, renderConfigs);
			});
		}

		function renderConfigs(configs) {
			var html = baidu.template('configTemplate', {
				list : configs
			});
			document.getElementById('configs').innerHTML = html;
		}