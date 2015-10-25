(function() {
	// Router
	angular.module('canteen.app').config([ '$routeProvider', function($routeProvider) {
		$routeProvider
		/** ****************************************************** */
		/** ************************* App *********************** */
		/** ****************************************************** */
		// Welcome
		.when('/welcome', {
			templateUrl : 'modules/client/views/welcome.html',
			controller : 'canteen.welcome.controller'
		})
		// Home
		.when('/home', {
			templateUrl : 'modules/client/views/home.html',
			controller : 'canteen.home.controller'
		})
		// About
		.when('/about', {
			templateUrl : 'modules/client/views/about.html',
			controller : 'canteen.about.controller'
		})
		// Contact
		.when('/contact', {
			templateUrl : 'modules/client/views/contact.html',
			controller : 'canteen.contact.controller'
		})
		/** ****************************************************** */
		/** ************************* Dish *********************** */
		/** ****************************************************** */
		// Popular
		.when('/dish/popular', {
			templateUrl : 'modules/client/views/dish/popular.html',
			controller : 'dish.popular.controller'
		})
		// Dish Exhibition - Group by Date and Line
		// Today and Tomorrow
		.when('/dish/today', {
			redirectTo : '/dish/exhibition/date/' + (new Date() - 0),
		}).when('/dish/tomorrow', {
			redirectTo : '/dish/exhibition/date/' + (new Date() - 0 + 24 * 60 * 60 * 1000),
		})
		// Default as Today
		.when('/dish/exhibition/date/', {
			redirectTo : '/dish/exhibition/date/' + (new Date() - 0),
		})
		// Specific Date
		.when('/dish/exhibition/date/:date', {
			templateUrl : 'modules/client/views/dish/exhibition.html',
			controller : 'dish.exhibition.controller'
		})
		// Dish List - Specials
		.when('/dish/specials', {
			templateUrl : 'modules/client/views/dish/specials.html',
			controller : 'dish.specials.controller'
		})
		// Dish List - Popular
		.when('/dish/popular', {
			templateUrl : 'modules/client/views/dish/popular.html',
			controller : 'dish.popular.controller'
		})
		// Dish Detail
		.when('/dish/:id', {
			templateUrl : 'modules/client/views/dish/detail.html',
			controller : 'dish.detail.controller'
		})
		// Default
		.otherwise({
			redirectTo : '/welcome'
		});
	} ]);
})()