(function() {
	// Controller
	angular.module('canteen.app').controller('dish.detail.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams', '$modal', '$amui',
	// Main
	function($scope, $http, $routeParams, $modal, $amui) {
		if ($routeParams.id) {
			$http.get(window.location.apiRoot + 'api/dish/' + $routeParams.id).success(function(dish) {
				dish.formattedOfferDate = moment(dish.offerDate).format('MMM-DD');
				dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
				dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
				dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
				$scope.dish = dish;
				$amui.load();
			});
		} else {
			$amui.load();
		}
	} ]);
})()