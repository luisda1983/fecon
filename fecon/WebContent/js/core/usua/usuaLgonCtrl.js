app.controller('usuaLgonCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	inic();
	
	//Captura el evento de login en la aplicacion
	$scope.lgon = function() {
		var srv1 = srvLgon();		
		$q.all([srv1]).then(function() { /*No limpiamos mensajes*/ });
	};
	
	//Function que inicializa las variables usadas en la vista.
	function inic() {
		$scope.lgon = {
			iden : '',
			pass : ''
		};
	}
	
	//Llamada al servicio de login y captura del retorno. Si retorno OK hacemos el login.
	function srvLgon() {
		
		var dataObject = {
			iden: $scope.lgon.iden,
			pass: $scope.lgon.pass
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/lgon/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject('NOK');
			} else {
				srv.lgon(data);
				d.resolve(data);
			}
		});
		return d.promise;
	}
});