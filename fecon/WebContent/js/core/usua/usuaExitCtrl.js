app.controller('usuaExitCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	var srv1 = comc.request('usua/exit', null);
	$q.all([srv.stResp(srv1)]).then(function() {
		srv.logOut();
	})
});