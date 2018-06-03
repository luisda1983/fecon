app.directive('scrolly', function() {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var raw = element[0];
			
			element.bind('scroll', function() {
                sh = raw.scrollHeight;
                ps = raw.scrollTop + raw.offsetHeight;
                console.log(sh + ' ' + ps);
                if (sh <= ps) {
                	scope.$apply(attrs.scrolly);
                }
			});
		}
	};
});