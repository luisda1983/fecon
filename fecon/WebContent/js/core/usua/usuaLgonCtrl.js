app.controller('usuaLgonCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('usua/lgon');
	view();
	
	//Captura el evento de login en la aplicacion
	$scope.fnLgon = function() {
		//Realizamos el login en backend
		var srv1 = comc.request('usua/lgon', $scope.cntx);
		$q.all([srv.stResp(true, srv1)]).then(function(){
			//Establecemos los datos de la sesión en FrontEnd
			$rootScope.esta.sesi     = $scope.cntx.data.sesi;
			$rootScope.esta.lgonUsua = true;
			$rootScope.esta.usua     = $scope.cntx.data.usua;
			
			//Volvemos a solicitar el menú, para obtener el correspondiente al usuario activo
			var srv2 = comc.request('menu/get', null);
			$q.all([srv.stResp(true, srv2)]).then(function() {
				//Transición al home
				srv.go(null, null, '/', null);
			})
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		//TODO
	}

});