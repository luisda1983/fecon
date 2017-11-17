app.controller('presMespCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv) {

	$scope.cntx = srv.getCntx('pres/mesp');
		
	var srv1 = srvLiteAnualidad();
	var srv2 = srvLiteMes();
	var srv3 = srvCateList();
	var srv4 = srvConcList();
	var srv5 = srvLitePresEsta();

	$q.all([srv1, srv2, srv3, srv4, srv5]).then(function() {
		var srv6 = srvParaPeripresup();
		$q.all([srv6]).then(function() {
			if ($scope.cntx.data.prPeripresup.pval.anac !== 'undefined' &&
				$scope.cntx.data.prPeripresup.pval.anac > 0 &&
				$scope.cntx.data.prPeripresup.pval.msac > 0) {
				if ($scope.cntx.form.anua === 0 || $scope.cntx.form.mesp === 0) {
					$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
					$scope.cntx.form.mesp = $scope.cntx.data.prPeripresup.pval.msac;
				}
			}
			var srv7 = srvPresMesp();	
		});		
	});
	
	//Obtiene el resumen presupuestario para un determinado mes
	function srvPresMesp() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'LT03',
			anua: parseInt($scope.cntx.form.anua),
			mesp: parseInt($scope.cntx.form.mesp)
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

	//Cambio de estado de partida presupuestaria
	function srvPresEsta() {
		
		var esta = "";
		
		if ($scope.cntx.data.pres.esta === 'A') {
			esta = 'C';
		} else {
			esta = 'A';
		}
		
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			anua: parseInt($scope.cntx.data.pres.anua),
			mesp: parseInt($scope.cntx.data.pres.mesp),
			cate: parseInt($scope.cntx.data.pres.cate),
			conc: parseInt($scope.cntx.data.pres.conc),
			esta: esta
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/esta/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.pres = data.OUTPUT['pres'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de literales: Estados de Presupuesto
	function srvLiteMes() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'MES'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltMes = data.OUTPUT['liteMap'];
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
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/map/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateMap = data.cateMap;
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

	//Captura el evento del cambio en el desplegable de anyo/mes
	$scope.anuaMespChng = function() {
		var srv1 = srvPresMesp();
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
		
		var srv1 = srvPresEsta();
		$q.all([srv1]).then(function() {
			$scope.cntx.data.presListMap[cate][i] = $scope.cntx.data.pres;	
		})
	}

});