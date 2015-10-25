(function() {
	// Controller
	angular.module('canteen.app').controller('canteen.statistics.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams', '$amui',
	// Main
	function($scope, $http, $routeParams, $amui) {
		$http.get(window.location.apiRoot + 'api/dish').success(function(statistics) {
			$scope.offers = statistics.map(function(offer) {
				return {
					offerDate : new Date(offer.offerDate),
					formattedOfferDate : moment(offer.offerDate).format('YYYY-MM-DD'),
					isToday : new Date(offer.offerDate).getDate() == new Date().getDate(),
					recommendationCount : offer.recommendationCount,
					dishCount : offer.dishCount,
				};
			});
			$scope.closeMenu = function() {
				$('#canteen-menu').offCanvas('close');
			}
		});
	} ])
})();