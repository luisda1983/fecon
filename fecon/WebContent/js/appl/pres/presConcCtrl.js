app.controller('presConcCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv, comc) {

	$scope.cntx = srv.getCntx('pres/conc');
	
	var srv1 = comc.requestLiteList('ANUALIDAD', $scope.cntx);
	var srv2 = comc.requestLiteList('PRESESTA', $scope.cntx);
	var srv3 = comc.request('cate/list', $scope.cntx);
	var srv4 = comc.request('conc/full', $scope.cntx);

	$q.all([srv.stResp(false, srv1, srv2, srv3, srv4)]).then(function() {
		var srv5 = comc.requestParaGet('I', 'PERIPRESUP', '', $scope.cntx);
		$q.all([srv.stResp(false, srv5)]).then(function() {
			if ($scope.cntx.data.prPeripresup.pval.anac !== 'undefined' &&
				$scope.cntx.data.prPeripresup.pval.anac > 0) {
				$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
				if ($scope.cntx.form.anua === 0) {
					$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
				}
			}
			var srv7 = comc.request('pres/conc', $scope.cntx);
			$q.all([srv.stResp(true, srv7)]).then(function() {
				view();
			});	
		});		
	});

	//Captura el evento del cambio en el desplegable de anyo
	$scope.fnAnuaChng = function() {
		var srv1 = comc.request('pres/anua', $scope.cntx);
		srv.stResp(true, srv1);
	};

	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};

	//Función que amplia un registro de la lista
	$scope.xpnd = function(cate, i) {
		if ($scope.cntx.conf.item === i) {
			$scope.cntx.conf.item = -1;
			$scope.cntx.conf.cate = -1;
		} else {
			$scope.cntx.conf.item = i;
			$scope.cntx.conf.cate = cate;
		}
	}

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		
	}

});