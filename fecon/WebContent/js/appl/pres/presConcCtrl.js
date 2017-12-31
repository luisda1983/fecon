app.controller('presConcCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv) {

	$scope.cntx = srv.getCntx('pres/conc');
	
	var srv1 = srvLiteAnualidad();
	var srv2 = srvCateList();
	var srv3 = srvConcList();
	var srv4 = srvLitePresEsta();

	$q.all([srv1, srv2, srv3, srv4]).then(function() {
		var srv5 = srvParaPeripresup();
		$q.all([srv5]).then(function() {
			if ($scope.cntx.data.prPeripresup.pval.anac !== 'undefined' &&
				$scope.cntx.data.prPeripresup.pval.anac > 0) {
				$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
				if ($scope.cntx.form.anua === 0) {
					$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
				}
			}
			var srv6 = srvPresConc();	
		});		
	});
	
	//Obtiene el resumen presupuestario para un determinado año
	function srvPresConc() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'LT04',
			anua: parseInt($scope.cntx.form.anua)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.presCateList = data.OUTPUT['presList'];
			$scope.cntx.data.presListMap  = data.OUTPUT['presListMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de parámetros por instalación: Anualidad
	function srvLiteAnualidad() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'ANUALIDAD'
		};
		
		var d = $q.defer();
		
		
		var output = srv.call(targetHost + 'service/angular/lite/list', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltAnualidad = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de literales: Estados de Presupuesto
	function srvLitePresEsta() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'PRESESTA'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltPresesta = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de parámetros: Periodo actual del presupuesto
	function srvParaPeripresup() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'I',
			tbla: 'PERIPRESUP' //Necesitamos que obtenga el parámetro de la instalación
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/para/get/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.prPeripresup = data.OUTPUT['para'];
			d.resolve(data);
		});
		return d.promise;
	}

	//TODO: adaptar a nuevas categorias y conceptos
	//Function que recupera el mapa de categorias
	function srvCateList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateMap = data.OUTPUT['cateListMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que recupera el mapa de conceptos
	function srvConcList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/map/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concMap = data.concMap;
			d.resolve(data);
		});
		return d.promise;
	}

	//Captura el evento del cambio en el desplegable de anyo
	$scope.anuaChng = function() {
		var srv1 = srvPresAnua();
		$q.all([srv1]).then(function(){
			presAnuaChart(); 
		});
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

});