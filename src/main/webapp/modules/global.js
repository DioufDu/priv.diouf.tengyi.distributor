(function() {
	// Get Root Path
	window.location.apiRoot = '';

	Number.prototype.toFixed = Number.prototype.toFixed || function(digit) {
		var val = this.toString();
		var digitIndex = val.indexOf('.');
		if (digitIndex !== -1) {
			val = val.substring(0, digitIndex + digit + 1);
		}
		return parseFloat(val);
	};
})();