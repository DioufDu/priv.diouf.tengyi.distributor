(function() {
	// Service
	angular.module('canteen.app').factory('$amui', [ '$http', function($http) {
		var store = $.AMUI.store;
		if (!store.enabled) {
		  alert('Local storage is not supported by your browser. Please disable "Private Mode", or upgrade to a modern browser.');
		  return;
		}
		return {
			load : function() {
				if (store.enabled) {
					var amuiJs = store.get('cached_amui_js');
					if (amuiJs) {
						setTimeout(function() {
							eval(amuiJs);
						}, 300);
					} else {
						$http.get('libs/amaze-ui/js/amazeui.min.js').success(function(amuiJs) {
							store.set('cached_amui_js', amuiJs);
							setTimeout(function() {
								eval(amuiJs);
							}, 300);
						});
					}
				} else {
					setTimeout(function() {
						$(document.body).append('<script src="libs/amaze-ui/js/amazeui.min.js" async="async"></script>');
					}, 300);
				}
			},
		};
	} ]);
})();