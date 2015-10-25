(function() {
	// Router
	angular.module('canteen.app').config([ '$routeProvider', function($routeProvider) {
		$routeProvider
		/** ****************************************************** */
		/** ************************* App *********************** */
		/** ****************************************************** */
		// About
		.when('/about', {
			templateUrl : 'modules/admin/views/about.html',
			controller : 'canteen.about.controller'
		})
		/** ****************************************************** */
		/** ************************* Dish *********************** */
		/** ****************************************************** */
		// Dish - Search
		.when('/dish/search', {
			templateUrl : 'modules/admin/views/dish/search.html',
			controller : 'dish.search.controller'
		})
		// Dish - History
		.when('/dish/history', {
			templateUrl : 'modules/admin/views/dish/history.html',
			controller : 'dish.history.controller'
		})
		// Dish - New
		.when('/dish/new', {
			templateUrl : 'modules/admin/views/dish/editor.html',
			controller : 'dish.editor.controller'
		})
		// Dish - Editor
		.when('/dish/:id', {
			templateUrl : 'modules/admin/views/dish/editor.html',
			controller : 'dish.editor.controller'
		})
		// Default
		.otherwise({
			redirectTo : '/dish/search'
		});
	} ]);
})()