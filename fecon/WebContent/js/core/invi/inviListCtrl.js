app.controller('inviListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, $mdMenu, comc) {

	$scope.cntx = srv.getCntx('invi/list');
	
	var srv1 = comc.requestLiteList('INVIESTA', $scope.cntx);
	var srv2 = comc.requestLiteList('INVITIPO', $scope.cntx);
	
	$q.all([srv.stResp(srv1, srv2)]).then(function(){
		var srv3 = comc.request('invi/list', $scope.cntx);
		srv.stResp(srv3);
	});
		
	//Función para capturar la aceptación de una invitación
	$scope.fnInviAcep = function(i) {
		var invi = $scope.cntx.data.inviList[i];
		$scope.cntx.form.iden = invi.iden;
		var srv1 = comc.request('invi/acep', $scope.cntx);
		
		$q.all([srv.stResp(srv1)]).then(function() {
			var srv2 = comc.request('invi/list', $scope.cntx);
			srv.stResp(srv2);
		});
	}
	
	//Función para capturar el rechazo de una invitación
	$scope.fnInviRech = function(i) {
		var invi = $scope.cntx.data.inviList[i];
		$scope.cntx.form.iden = invi.iden;
		var srv1 = comc.request('invi/rech', $scope.cntx);
		
		$q.all([srv.stResp(srv1)]).then(function() {
			var srv2 = comc.request('invi/list', $scope.cntx);
			srv.stResp(srv2);
		});
	}
	
	//Funcion para capturar la selección del desplegable de estado de invitacion
	$scope.fnEstaChng = function() {
		var srv1 = comc.request('invi/list', $scope.cntx);
		srv.stResp(srv1);
	}
	
	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};
	
	//Función que amplia un registro de la lista
	$scope.xpnd = function(i) {
		if ($scope.cntx.conf.item === i) {
			$scope.cntx.conf.item = -1;
		} else {
			$scope.cntx.conf.item = i;
		}
	}

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		//TODO
	}

});