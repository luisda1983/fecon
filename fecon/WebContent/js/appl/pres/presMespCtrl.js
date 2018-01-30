app.controller('presMespCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv, comc) {

	$scope.cntx = srv.getCntx('pres/mesp');
		
	var srv1 = comc.requestLiteList('ANUALIDAD', $scope.cntx);
	var srv2 = comc.requestLiteList('MES', $scope.cntx);
	var srv3 = comc.requestLiteList('PRESESTA', $scope.cntx);
	var srv4 = comc.request('cate/list', $scope.cntx);
	var srv5 = comc.request('conc/full', $scope.cntx);

	$q.all([srv.stResp(srv1, srv2, srv3, srv4, srv5)]).then(function() {
		var srv6 = comc.requestParaGet('I', 'PERIPRESUP', '', $scope.cntx);
		$q.all([srv.stResp(srv6)]).then(function() {
			if ($scope.cntx.data.prPeripresup.pval.anac !== 'undefined' &&
				$scope.cntx.data.prPeripresup.pval.anac > 0 &&
				$scope.cntx.data.prPeripresup.pval.msac > 0) {
				if ($scope.cntx.form.anua === 0 || $scope.cntx.form.mesp === 0) {
					$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
					$scope.cntx.form.mesp = $scope.cntx.data.prPeripresup.pval.msac;
				}
			}
			var srv7 = comc.request('pres/mesp', $scope.cntx);
			$q.all([srv.stResp(srv7)]).then(function() {
				view();
			});	
		});		
	});

	//Captura el evento del cambio en el desplegable de anyo/mes
	$scope.fnAnuaMespChng = function() {
		var srv1 = comc.request('pres/mesp', $scope.cntx);
		srv.stResp(srv1);
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
	
	//Funcion para cerrar las partidas
	$scope.fnEsta = function(cate, i) {
		$scope.cntx.data.pres = $scope.cntx.data.presListMap[cate][i];
		
		var srv1 = comc.request('pres/esta', $scope.cntx);
		$q.all([srv.stResp(srv1)]).then(function() {
			$scope.cntx.data.presListMap[cate][i] = $scope.cntx.data.pres;	
		})
	}

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		//TODO
	}

});