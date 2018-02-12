app.controller('hconListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc, $filter) {

	$scope.cntx = srv.getCntx('hcon/list');
		
	var srv1 = comc.requestLiteList('HCONLTTIPO', $scope.cntx);
	var srv2 = comc.requestLiteList('ANUALIDAD', $scope.cntx);
	var srv3 = comc.requestLiteList('MES', $scope.cntx);
	var srv4 = comc.request('cate/list', $scope.cntx);
	var srv5 = comc.request('conc/full', $scope.cntx); 

	$q.all([srv.stResp(true, srv1,srv2,srv3,srv4,srv5)]).then(function() {
		$scope.cntx.conf.mode = 'T';
		view();
	});

	$scope.fnTipoChng = function() {
		$scope.cntx.conf.mode = $scope.cntx.form.tipo;
		view();
	}

	$scope.fnCateChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		srv.stResp(true, srv1);
	}

	$scope.fnForm = function() {
		var srv1 = comc.request('hcon/list', $scope.cntx); 
		srv.stResp(true, srv1);
	}

	//Funcion que captura la solicitud de anulacion de un apunte
	$scope.fnAnul = function anul(indx) {
		$scope.cntx.conf.iden = $scope.cntx.data.hconList[indx].iden;
		var srv1 = comc.request('hcon/anul', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.conf.iden = 0;
			var srv2 = comc.request('hcon/list', $scope.cntx);
			srv.stResp(true, srv2);
		});
	};

	//Funcion que excluye un apunte de su partida presupuestaria
	$scope.fnExcl = function excl(indx) {
		$scope.cntx.conf.iden = $scope.cntx.data.hconList[indx].iden;
		$scope.cntx.conf.acci = 'E';
		
		var srv1 = comc.request('hcon/pres/gest', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.conf.iden = 0;
			$scope.cntx.conf.acci = '';
		});
	}
	
	//Funcion que incluye un apunte en su partida presupuestaria
	$scope.fnIncl = function incl(indx) {
		$scope.cntx.conf.iden = $scope.cntx.data.hconList[indx].iden;
		$scope.cntx.conf.acci = 'I';
		
		var srv1 = comc.request('hcon/pres/gest', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.conf.iden = 0;
			$scope.cntx.conf.acci = '';
		});
	}

	function view() {
		//Seleccionar Tipo de Consulta
		if ($scope.cntx.conf.mode === 'T') {
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.anua = false;
			$scope.cntx.show.mesh = false;
			$scope.cntx.show.cate = false;
			$scope.cntx.show.conc = false;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.anua = true;
			$scope.cntx.read.mesh = true;
			$scope.cntx.read.cate = true;
			$scope.cntx.read.conc = true;
		//Consulta por mes
		} else if ($scope.cntx.conf.mode === 'LT01') { 
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.anua = true;
			$scope.cntx.show.mesh = true;
			$scope.cntx.show.cate = false;
			$scope.cntx.show.conc = false;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.anua = false;
			$scope.cntx.read.mesh = false;
			$scope.cntx.read.cate = true;
			$scope.cntx.read.conc = true;
		//Consulta por año y concepto
		} else if ($scope.cntx.conf.mode === 'LT02') {  
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.anua = true;
			$scope.cntx.show.mesh = false;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.anua = false;
			$scope.cntx.read.mesh = true;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
		//Consulta por mes y concepto
		} else if ($scope.cntx.conf.mode === 'LT03') { 
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.anua = true;
			$scope.cntx.show.mesh = true;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.anua = false;
			$scope.cntx.read.mesh = false;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
		}
	}

	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};
	
	//Función que amplia un registro de la lista
	$scope.xpnd = function(i) {
		if ($scope.conf.item === i) {
			$scope.conf.item = -1;
		} else {
			$scope.conf.item = i;
		}
	}
});