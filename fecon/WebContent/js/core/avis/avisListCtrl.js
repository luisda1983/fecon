app.controller('avisListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	var srv1 = srvAvisList();
	$q.all([srv1]).then(function(){ /*srv.clearMsg(); */ });
	
	//Llamada al servicio de consulta de aviso
	function srvAvisList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/avis/get/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.avisList = data.OUTPUT['avisList'];
			d.resolve(data);
		});
		return d.promise;
	}
});