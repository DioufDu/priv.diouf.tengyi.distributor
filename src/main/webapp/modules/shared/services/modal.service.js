(function() {
	// Service
	angular.module('canteen.app').factory('$modal', function() {
		return {
			loading : {
				open : function() {
					$('#modal-loading').modal({
						closeViaDimmer : false,
					}).modal('open');
				},
				close : function(onClosed) {
					$('#modal-loading').modal('close').on('closed.modal.amui', function() {
						if (onClosed && typeof (onClosed) === 'function') {
							onClosed();
						}
					});
				},
			},
			alert : {
				open : function(options) {
					$('#modal-alert-title').html(options.title || 'Alert');
					$('#modal-alert-msg').html(options.msg || '');
					$('#modal-alert-ok').html(options.ok || 'OK');
					$('#modal-alert').modal({
						onConfirm : options.onConfirm || function() {
						},
						onCancel : function() {
						},
						closeViaDimmer : false,
					}).modal('open');
				},
				close : function(onClosed) {
					$('#modal-alert').modal('close').on('closed.modal.amui', function() {
						$('#modal-alert-title').html('Alert');
						$('#modal-alert-msg').html('');
						$('#modal-alert-ok').html('OK');
						if (onClosed && typeof (onClosed) === 'function') {
							onClosed();
						}
					});
				},
			},
			confirm : {
				open : function(options) {
					$('#modal-confirm-title').html(options.title || 'Confirm');
					$('#modal-confirm-msg').html(options.msg || '');
					$('#modal-confirm-ok').html(options.ok || 'OK');
					$('#modal-confirm-cancel').html(options.cancel || 'Cancel');
					$('#modal-confirm').modal({
						onConfirm : options.onConfirm || function() {
						},
						onCancel : options.onCancel || function() {
						},
						closeViaDimmer : false,
					}).modal('open');
				},
				close : function(onClosed) {
					$('#modal-confirm').modal('close').on('closed.modal.amui', function() {
						$('#modal-confirm-title').html('');
						$('#modal-confirm-msg').html('');
						$('#modal-confirm-ok').html('OK');
						$('#modal-confirm-cancel').html('Cancel');
						if (onClosed && typeof (onClosed) === 'function') {
							onClosed();
						}
					});
				},
			},
		};
	});
})();