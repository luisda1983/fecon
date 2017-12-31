app.controller('cateListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	$scope.cntx = srv.getCntx('cate/list');
	
	var srv1 = srvCateList();
	
	$q.all([srv1]).then(function(){
		
	});
	
	//Funcion que obtiene la lista de cuentas.
	function srvCateList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.cateList = data.OUTPUT['cateList'];
			d.resolve(data);
		});
		return d.promise;
	}

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