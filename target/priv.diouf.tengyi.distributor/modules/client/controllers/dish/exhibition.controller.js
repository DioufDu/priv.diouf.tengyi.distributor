(function() {
	// Controller
	angular.module('canteen.app').controller('dish.exhibition.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams', '$modal', '$amui',
	// Main
	function($scope, $http, $routeParams, $modal, $amui) {
		if ($routeParams.date) {
			var requestDate = parseInt($routeParams.date);
			$scope.offerDate = requestDate;
			$scope.formattedOfferDate = moment(requestDate).format('YYYY-MM-DD');
			$scope.formattedOfferCalendar = moment(requestDate).format('MMMM Do dddd');
			$http.get(window.location.apiRoot + 'api/dish/group/date/' + $routeParams.date).success(function(dishGroups) {
				// Check if show notification or not
				if (dishGroups.length) {
					$scope.isEmpty = false;
				} else {
					$scope.isEmpty = true;
					$amui.load();
					return;
				}
				// Transfer the image URL
				dishGroups.forEach(function(dishGroup) {
					dishGroup.items.forEach(function(dish) {
						dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
						dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
						dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
					});
				});
				// Load the dish group image one by one (performance optimization)
				$scope.dishGroups = [];
				// Load the first group of dish
				$scope.dishGroups.push(dishGroups[0]);
				$amui.load();
				// Load the rest groups of dish
				setTimeout(function() {
					dishGroups.forEach(function(dishGroup, idx) {
						if (idx !== 0) {
							$scope.dishGroups.push(dishGroups[idx]);
						}
					})
					$scope.$apply();
					$amui.load();
				}, 100);
			});
			$('#ipt-offer-date-selector').datepicker().on('changeDate.datepicker.amui', function(event) {
				$(this).datepicker('close');
				window.location.href = '#/dish/exhibition/date/' + (new Date(event.date) - 0);
			});
		}
	} ]);
})()