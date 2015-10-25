(function() {
	// Controller
	angular.module('canteen.app').controller('canteen.welcome.controller', [
	// Dependencies
	'$scope',
	// Main
	function($scope) {
		document.body.style['position'] = 'initial';
		$('#component-header').hide();
		$('#component-menu').hide();
		$('#component-navbar').hide();
		setTimeout(function() {
			document.body.removeAttribute('style')
			window.location.href = '#/home';
			$('#component-header').show();
			$('#component-menu').show();
			$('#component-navbar').show();
		}, 3000);
	} ]);
})();