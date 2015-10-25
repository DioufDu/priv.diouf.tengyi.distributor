(function() {
	// Controller
	angular.module('canteen.app').controller('dish.search.controller', [
	// Dependencies
	'$scope', '$http', '$amui',
	// Main
	function($scope, $http, $amui) {
		// Events
		$('#ipt-criteria-start-date').datepicker().on('changeDate.datepicker.amui', function(event) {
			$scope.criteria.startDate = moment(event.date).format('YYYY-MM-DD');
		});
		$('#ipt-criteria-end-date').datepicker().on('changeDate.datepicker.amui', function(event) {
			$scope.criteria.endDate = moment(event.date).format('YYYY-MM-DD');
		});
		// Search
		$scope.reset = function() {
			$scope.criteria = {
				name : '',
				line : '',
				startDate : moment().subtract(1, 'days').format('YYYY-MM-DD'),
				endDate : moment().add(1, 'days').format('YYYY-MM-DD'),
				description : '',
				isRecommended : false,
			};
			$scope.dishes = [];
		};
		$scope.search = function() {
			$scope.dishes = [];
			$http.post(window.location.apiRoot + 'api/dish/search/advanced', {
				criteria : $scope.criteria,
			}).success(function(dishPage) {
				if (dishPage) {
					$scope.count = dishPage.numberOfElements
					$scope.total = dishPage.totalElements;
					if (dishPage.content) {
						dishPage.content.forEach(function(dish) {
							dish.formattedOfferDate = moment(dish.offerDate).format('YYYY-MM-DD');
							dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
							dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
							dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
							$scope.dishes.push(dish);
						});
					}
				}
				$('#accordion-search-criteria').collapse('close');
				$('#accordion-search-result').collapse('open');
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
		};
		// Navigation
		$scope.navToDetail = function(dishId) {
			store.set('cached_search_scope', {
				criteria : $scope.criteria,
				dishes : $scope.dishes,
				scrollPosition : window.scrollY,
			});
			window.location.href = '#/dish/' + dishId;
		};
		// Initial
		var store = $.AMUI.store;
		var cachedSearchScope = store.get('cached_search_scope');
		if (cachedSearchScope) {
			$scope.criteria = cachedSearchScope.criteria;
			$scope.dishes = cachedSearchScope.dishes;
			$('#accordion-search-criteria').collapse('close');
			$('#accordion-search-result').collapse('open');
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
				position : cachedSearchScope.scrollPosition
			});
			store.set('cached_search_scope', null);
		} else {
			$scope.reset();
		}
		$amui.load();
	} ]);
})()