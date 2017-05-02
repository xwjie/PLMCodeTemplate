/**
 * 
 */

require.config({
	// appDir : 'resources',
	// By default load any module IDs from scripts/app
	baseUrl : 'resources/js',
	// except, if the module ID starts with "lib"
	paths : {
		jquery : '../lib/jquery-1.11.3.min',
		cookie : '../lib/jquery.cookie',
		tpl : '../lib/baiduTemplate',
		tplDir : '../templates',
		form : '../lib/jquery.form',
		text : '../lib/text',
		layer : '../lib/layer/layer'
	},

	// load backbone as a shim
	shim : {
		'tpl' : {
			// The underscore script dependency should be loaded before loading
			// backbone.js
			// deps: ['underscore'],
			// use the global 'Backbone' as the module name.
			exports : 'baidu'
		},
		'cookie' : {
			deps : [ 'jquery' ],
			exports : 'jQuery.cookie'
		},
		'cookie' : {
			deps : [ 'jquery' ],
			exports : 'jQuery.cookie'
		},
		layer : {
			deps : [ 'jquery' ],
			exports : 'layer'
		},
		'discuss' : {
			exports : 'discuss'
		}

	}
});

require([ 'layer' ], function(layer) {
	layer.config({
		path : 'resources/lib/layer/' // layer.js所在的目录，可以是绝对目录，也可以是相对目录
	});
});
