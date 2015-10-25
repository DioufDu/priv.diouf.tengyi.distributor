(function() {
	// Controller
	angular.module('canteen.app').controller('dish.editor.controller', [
	// Dependencies
	'$scope', '$http', '$routeParams', '$amui',
	// Main
	function($scope, $http, $routeParams, $amui) {
		// Update - Load dish
		if ($routeParams.id && $.isNumeric($routeParams.id) && $routeParams.id > 0) {
			$http.get(window.location.apiRoot + 'api/dish/' + $routeParams.id).success(function(dish) {
				syncModel(dish);
			});
		}
		// Create - Initial dish
		else {
			syncModel({
				id : 0,
				chineseName : '',
				englishName : '',
				line : '',
				description : '',
				offerDate : new Date() - 0,
				isRecommended : false,
				recommendedReason : '',
				photo : {},
				rate : {
					overall : 0,
					taste : 0,
					style : 0,
					service : 0,
				},
			});
		}
		function syncModel(dish) {
			// Load and initial dish model
			dish.formattedOfferDate = moment(dish.offerDate).format('MMM-DD');
			dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.thumbnailPhotoId;
			dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.standardPhotoId;
			dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + dish.photo.fullScreenPhotoId;
			$scope.dish = dish;
			// Initial raty to display
			setTimeout(function() {
				$('.raty-control').each(function() {
					var ratyControl = $(this);
					ratyControl.html(parseFloat(ratyControl.html()).toFixed(2));
					ratyControl.raty({
						score : ratyControl.text(),
						starType : 'i',
						readOnly : true,
					});
				});
			}, 150);
			// Events
			$('#ipt-dish-offer-date').datepicker().on('changeDate.datepicker.amui', function(event) {
				$scope.dish.offerDate = moment(event.date).format('YYYY-MM-DD');
			});
			$amui.load();
		}
		// Photo
		$scope.photo = {
			// Change
			change : function() {
				$('#btn-upload-photo').click();
			},
			// Rotate
			rotate : function(angle) {
				$scope.dish.photo.angle = (parseInt($scope.dish.photo.angle) + angle) % 360;
			},
			// Upload
			upload : function(uploadPhotoForm) {
				$scope.$apply();
				$('#' + uploadPhotoForm.id).ajaxSubmit({
					url : window.location.apiRoot + 'api/photo',
					type : 'POST',
					success : function(photoIds) {
						$scope.dish.photo.angle = 0;
						$scope.dish.photo.originalPhotoId = photoIds.originalPhotoId;
						$scope.dish.photo.thumbnailPhotoId = photoIds.thumbnailPhotoId;
						$scope.dish.photo.standardPhotoId = photoIds.standardPhotoId;
						$scope.dish.photo.fullScreenPhotoId = photoIds.fullScreenPhotoId;
						$scope.dish.photo.thumbnailPhotoUrl = window.location.apiRoot + 'api/photo/' + photoIds.thumbnailPhotoId;
						$scope.dish.photo.standardPhotoUrl = window.location.apiRoot + 'api/photo/' + photoIds.standardPhotoId;
						$scope.dish.photo.fullScreenPhotoUrl = window.location.apiRoot + 'api/photo/' + photoIds.fullScreenPhotoId;
						$scope.dish.isPhotoRenewRequired = true;
						$scope.$apply();
					}
				});
			}
		};

		// Confirm Save
		$scope.save = function(formDish) {
			$('#modal-confirm-save').modal({
				onConfirm : function() {
					// Update
					if ($routeParams.id && $.isNumeric($routeParams.id) && $routeParams.id > 0) {
						$http.put(window.location.apiRoot + 'api/dish/' + $routeParams.id, $scope.dish)
						// Save Success
						.success(function(savedDish) {
							syncModel(savedDish);
							$('#modal-alert-save-success').modal();
						})
						// Save Fail
						.error(function(error) {
							$('#modal-alert-save-fail').modal();
						});

					}
					// Create
					else {
						$http.post(window.location.apiRoot + 'api/dish', $scope.dish)
						// Save Success
						.success(function(savedDish) {
							$('#modal-alert-save-success').modal({
								onConfirm : function() {
									setTimeout(function() {
										window.location.href = '#/dish/' + savedDish.id;
									}, 500);
								},
								closeViaDimmer : false,
							});
						})
						// Save Fail
						.error(function(error) {
							$('#modal-alert-save-fail').modal();
						});
					}

				},
				closeViaDimmer : false,
			}).modal('open');
		};

		// Confirm Delete
		$scope['delete'] = function(formDish) {
			$('#modal-confirm-delete').modal({
				onConfirm : function() {
					// Delete
					if ($routeParams.id && $.isNumeric($routeParams.id) && $routeParams.id > 0) {
						$http['delete'](window.location.apiRoot + 'api/dish/' + $routeParams.id, $scope.dish)
						// Delete Success
						.success(function() {
							$('#modal-alert-delete-success').modal({
								onConfirm : function() {
									setTimeout(function() {
										window.history.go(-1);
									}, 500);
								}
							});
						})
						// Delete Fail
						.error(function(error) {
							$('#modal-alert-delete-fail').modal();
						});

					}
					// Create
					else {
						$http.post(window.location.apiRoot + 'api/dish', $scope.dish)
						// Delete Success
						.success(function(deletedDish) {
							$('#modal-alert-delete-success').modal({
								onConfirm : function() {
									setTimeout(function() {
										window.location.href = '#/dish/' + deletedDish.id;
									}, 500);
								},
								closeViaDimmer : false,
							});
						})
						// Delete Fail
						.error(function(error) {
							$('#modal-alert-delete-fail').modal();
						});
					}

				},
				closeViaDimmer : false,
			}).modal('open');
		};

		// Confirm Exit
		$scope.exit = function() {
			$('#modal-confirm-exit').modal({
				onConfirm : function() {
					setTimeout(function() {
						window.location.href = '#/dish/search';
					}, 500);
				},
				closeViaDimmer : false,
			}).modal('open');
		};
	} ]);
})()
