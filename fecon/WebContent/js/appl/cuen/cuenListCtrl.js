app.controller('cuenListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cuen/list');
	
	var srv1 = comc.request('cuen/list', $scope.cntx);
	var srv2 = comc.requestLiteList('CUENTIPO', $scope.cntx);
	
	$q.all([srv.stResp(srv1,srv2)]).then(function() {
		view();
	});

	//Función que captura la entrada en modo alta de cuenta
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('cuen/form');
		srv.go('cuen/list', $scope.cntx, 'cuen/form', cntx);
	}

	//Función que carga el modo de edición de cuenta
	$scope.fnEdit = function(i) {
		var cntx = srv.getCntx('cuen/form');
		var cuen = $scope.cntx.data.cuenList[i];
		cntx.form.iden = cuen.iden;
		cntx.form.tipo = cuen.tipo;
		cntx.form.desc = cuen.desc;
		cntx.form.sald = cuen.sald;
		srv.go('cuen/list', $scope.cntx, 'cuen/form', cntx);
	}

	//Función que viaja al traspaso de cuenta
	$scope.fnTras = function(i) {
		var cntx = srv.getCntx('cuen/tras');
		var cuen = $scope.cntx.data.cuenList[i];
		//TODO: cargar datos
		srv.go('cuen/list', $scope.cntx, 'cuen/form', cntx);
	}
	
	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		
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
});