(function() {
	// Controller
	angular.module('canteen.app').controller('canteen.home.controller', [
	// Dependencies
	'$scope', '$http', '$amui',
	// Main
	function($scope, $http, $amui) {
		$http.get(window.location.apiRoot + 'api/dish/recommended').success(function(dishPage) {
			dishPage.content.forEach(function(dish) {
				dish.formattedOfferDate = moment(dish.offerDate).format('MMM-DD');
				dish.isToday = dish.formattedOfferDate === moment().format('MMM-DD')
				dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
				dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
				dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
				return dish;
			});
			$scope.dishes = dishPage.content;
			setTimeout(function() {
				$('.raty-control:not(:has("i"))').each(function() {
					var ratyControl = $(this);
					var score = ratyControl.text();
					ratyControl.raty({
						readOnly : true,
						starType : 'i',
						score : score,
						hints : [ null ],
					});
				});
			});
			$amui.load();
		});
	} ]);
})();