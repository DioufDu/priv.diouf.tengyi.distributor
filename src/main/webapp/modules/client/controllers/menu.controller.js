(function() {
	// Controller
	angular.module('canteen.app').controller('canteen.menu.controller', [
	// Dependencies
	'$scope', '$amui',
	// Main
	function($scope, $amui) {
		$scope.closeMenu = function() {
			$('#canteen-menu').offCanvas('close');
		}
		$amui.load();
	} ]);
})()
