app.controller('cateListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cate/list');

	var srv1 = comc.request('cate/list', $scope.cntx);
	$q.all([srv.stResp(srv1)]).then(function() {
		view();
	});	

	//Función que pasa a la creación de una nueva categoría
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('cate/form');
		srv.go('cate/list', $scope.cntx, 'cate/form', cntx);
	}
	
	//Función que carga el modo de edición de cuenta
	$scope.fnEdit = function(i) {
		var cate = $scope.cntx.data.cateList[i];
		var cntx = srv.getCntx('cate/form');
		cntx.form.iden = cate.iden;
		cntx.form.desl = cate.desl;
		cntx.form.desc = cate.desc;
		cntx.form.orde = cate.orde;
		cntx.form.pres = cate.pres;
		srv.go('cate/list', $scope.cntx, 'cate/form', cntx);
	}

	//Función que pasa a los conceptos de la categoría
	$scope.fnConc = function(i) {
		var cate = $scope.cntx.data.cateList[i];
		var cntx = srv.getCntx('conc/list');
		cntx.form.cate = cate.iden;
		srv.go('cate/list', $scope.cntx, 'conc/list', cntx);
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

	}
});