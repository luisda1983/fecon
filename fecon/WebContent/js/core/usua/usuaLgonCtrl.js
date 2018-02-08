app.controller('usuaLgonCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('usua/lgon');
	view();
	
	//Captura el evento de login en la aplicacion
	$scope.fnLgon = function() {
		var srv1 = comc.request('usua/lgon', $scope.cntx);
		$q.all([srv.stResp(srv1)]).then(function(){
			srv.lgon($scope.cntx);
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		//TODO
	}

});