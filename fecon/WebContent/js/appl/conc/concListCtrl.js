app.controller('concListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	$scope.cntx = srv.getCntx('conc/list');
	
	var srv1 = srvConcList();
	
	$q.all([srv1]).then(function(){
		
	});
	
	//Funcion que obtiene la lista de conceptos.
	function srvConcList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			cate: parseInt($scope.cntx.form.cate)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.concList = data.OUTPUT['concList'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Función que pasa a la creación de un nuevo concepto
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('conc/form');
		cntx.form.cate = $scope.cntx.form.cate;
		srv.go('conc/list', $scope.cntx, 'conc/form', cntx);
	}
	
	//Función que carga el modo de edición de concepto
	$scope.fnEdit = function(i) {
		var conc = $scope.cntx.data.concList[i];
		var cntx = srv.getCntx('conc/form');
		cntx.form.iden = conc.iden;
		cntx.form.cate = conc.cate;
		cntx.form.desl = conc.desl;
		cntx.form.desc = conc.desc;
		cntx.form.orde = conc.orde;
		srv.go('conc/list', $scope.cntx, 'conc/form', cntx);
	}

	//Función que retorna
	$scope.fnCanc = function() {
		srv.backState(true);
	};
	
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