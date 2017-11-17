app.controller('usuaExitCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	var srv1 = srvUsuaExit();	
	$q.all([srv1]).then(function() { /* srv.clearMsg(); */ });
	
	//Llamada al servicio de logout.
	function srvUsuaExit() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};
		
		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/usua/exit/', dataObject);
		output.then(function() {
			var data = srv.getData();
			srv.logOut();
			d.resolve(data);
		});
		return d.promise;
	}
});