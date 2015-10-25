(function() {
	// Controller
	angular.module('canteen.app').controller('dish.history.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams', '$amui',
	// Main
	function($scope, $http, $routeParams, $amui) {
		// Initially load 12 items or specific items
		var pageSize = 12;
		var pageIndex = 0;
		if ($routeParams.count && $.isNumeric($routeParams.count)) {
			pageSize = $routeParams.count;
		}
		pageSize = pageSize < 3 ? 3 : pageSize > 60 ? 60 : pageSize;
		// Load page by page
		$scope.dishes = [];
		$scope.last = false;
		$scope.loadPage = function() {
			$http.get(window.location.apiRoot + 'api/dish/latest/' + pageSize + '/' + pageIndex++).success(function(dishPage) {
				dishPage.content.forEach(function(dish) {
					dish.formattedOfferDate = moment(dish.offerDate).format('MMM-DD');
					dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
					dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
					dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
					$scope.dishes.push(dish);
				});
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
				$scope.last = dishPage.last;
				if (!dishPage.first) {
					setTimeout(function() {
						var $w = $(window);
						$w.smoothScroll({
							position : document.body.scrollTop + $w.height() - 250,
						});
					}, 150);
				}
			});
		}
		// Navigation
		$scope.navToDetail = function(dishId) {
			store.set('cached_history_scope', {
				criteria : $scope.criteria,
				dishes : $scope.dishes,
				scrollPosition : window.scrollY,
			});
			window.location.href = '#/dish/' + dishId;
		};
		// Initial
		var store = $.AMUI.store;
		var cachedHistoryScope = store.get('cached_history_scope');
		if (cachedHistoryScope) {
			$scope.criteria = cachedHistoryScope.criteria;
			$scope.dishes = cachedHistoryScope.dishes;
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
			$(window).smoothScroll({
				position : cachedHistoryScope.scrollPosition
			});
			store.set('cached_history_scope', null);
		} else {
			$scope.loadPage();
		}
		$amui.load();
	} ]);
})()