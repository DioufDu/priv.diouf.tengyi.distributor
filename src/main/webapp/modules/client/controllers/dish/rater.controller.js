(function() {
	// Controller
	angular.module('canteen.app').controller('dish.rater.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams',
	// Main
	function($scope, $http, $routeParams) {
		// Refresh
		$scope.refresh = function() {
			setTimeout(function() {
				$('.raty-control').each(function() {
					var ratyControl = $(this);
					ratyControl.raty({
						starType : 'i',
						click : function(score, args) {
							$scope.check(this);
						},
					});
				});
			}, 100);
		}
		$scope.refresh();
		// Check
		$scope.allRateFieldFilled = false;
		$scope.check = function(trigger) {
			$scope.allRateFieldFilled = true;
			$('.raty-control').each(function() {
				var ratyControl = $(this);
				if (!ratyControl.raty('score')) {
					if ($(trigger).attr('id') !== ratyControl.attr('id')) {
						$scope.allRateFieldFilled = false;
						return false;
					}
				}
			});
			$scope.$apply();
		};
		// Submit
		$scope.submit = function() {
			$('#modal-confirm-rate').modal({
				onConfirm : function() {
					$http.post(
					// URL
					window.location.apiRoot + 'api/dish/' + $scope.dish.id + '/rate',
					// Request
					{
						taste : $('#ipt-rate-taste').raty('score'),
						style : $('#ipt-rate-style').raty('score'),
						service : $('#ipt-rate-service').raty('score'),
					})
					// Rate Success
					.success(function(dishResponse) {
						$('#modal-alert-rate-success').modal({
							onConfirm : function() {
								// window.location.reload();
								$scope.allRateFieldFilled = false;
								$scope.$apply();
								$('.raty-control').each(function() {
									var ratyControl = $(this);
									ratyControl.raty({
										score : 0,
										starType : 'i',
										click : function(score, args) {
											$scope.check(this);
										},
									});
								});
							}
						});
					})
					// Rate Fail
					.error(function(error) {
						$('#modal-alert-rate-fail').modal();
					});

				},
				closeViaDimmer : false,
			}).modal('open');
		};
	} ]);
})()