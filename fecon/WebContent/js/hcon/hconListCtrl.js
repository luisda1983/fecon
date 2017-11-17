app.controller('hconListController', function($rootScope, $scope, $http, $routeParams, $q, srv, $filter) {
	
	var parAnua = $routeParams.anua;
	var parMesp = $routeParams.mesp;
	var parCate = $routeParams.cate;
	var parConc = $routeParams.conc;
	
	if (parAnua === undefined) { parAnua = 0;};
	if (parMesp === undefined) { parMesp = 0;};
	if (parCate === undefined) { parCate = 0;};
	if (parConc === undefined) { parConc = 0;};
	
	inic(parAnua, parMesp, parCate, parConc);

	var srv1 = srvParAnua();
	var srv2 = srvParMes();

	$q.all([srv1, srv2]).then(function() {
		
		var srv3 = srvConcMap();
		var srv4 = srvCateMap();
		
		$q.all([srv3, srv4]).then(function() {
			$scope.refresh();
			srv.clearMsg();
		});
		
//		var srv3 = srvPresMesp();
		//var srv4 = srvConcMap();
//		var srv4 = srvCateMap();
//		
//		$q.all([srv3, srv4]).then(function() {
			 
//		});
	});

	//Function que inicializa las variables usadas en la vista.
	function inic(parAnua, parMesp, parCate, parConc) {
		$scope.hcon =  {
			anua : parAnua,
			mesp : parMesp,
			cate : parseInt(parCate),
			conc : parseInt(parConc),
			sort : '',
			sortList : new Array()
		};
//		$scope.esta = {
//			anua : $scope.hcon.anua,
//			mesp : $scope.hcon.mesp,
//			cate : 0,
//			conc : $scope.hcon.conc
//		};
		$scope.esta = {
			anua : 0,
			mesp : 0,
			cate : 0,
			conc : 0
		};
	}

	function consultar() {
		var cons = false;
		
		if ($scope.hcon.anua !== 0 && $scope.hcon.mesp !== 0) { cons = true; };
		if ($scope.hcon.anua !== 0 && $scope.hcon.cate !== 0) { cons = true; };
		
		return cons;
	}
	
	$scope.refresh = function refresh() {
		if ($scope.hcon.cate !== $scope.esta.cate && $scope.hcon.cate !== 0) {
			if ($scope.esta.conc !== 0) {
				$scope.hcon.conc = 0;
			}
			var srv1 = srvConcList();
			$q.all([srv1]).then(function() {
				$scope.esta.cate = $scope.hcon.cate;
				srv.clearMsg();
			});
		}
		if (consultar()) {
			var srv1 = srvHconList();
			$q.all([srv1]).then(function() {
				srv.clearMsg();
			});
		}
		$scope.esta.anua = $scope.hcon.anua;
		$scope.esta.mesp = $scope.hcon.mesp;
		$scope.esta.cate = $scope.hcon.cate;
		$scope.esta.conc = $scope.hcon.conc;
	};

	$scope.sort = function sort(camp) {
		$scope.hcon.sort = camp;
		$scope.hcon.sortList = $filter('orderBy')($scope.hconList, camp);
	};
	
	$scope.resu = function resu(i) {
		if ($scope.hcon.sort !== '') {
			if (i < $scope.hcon.sortList.length - 1) {
				var actual = $scope.hcon.sortList[i][$scope.hcon.sort];
				var siguiente = $scope.hcon.sortList[i+1][$scope.hcon.sort];
				if (actual === siguiente) {
					return false;
				} else {
					return true;
				}
			} else {
				if ( i === $scope.hcon.sortList.length - 1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	};
	
//	var srv1 = srvConcMap();
//	
//	$q.all([srv1]).then(function() {
//		var srv2 = srvHconList();
//		$q.all([srv2]).then(function() {
//			srv.clearMsg();
//		});
//	});

	//Funcion que captura la solicitud de anulacion de un apunte
	$scope.anul = function anul(iden) {
		var srv1 = srvHconAnul(iden);
		$q.all([srv1]).then(function() {
			var srv2 = srvHconList();
			$q.all([srv2]).then(function() {
				srv.clearMsg();
			});
		});
	};
	
	//Function que obtiene la lista de apuntes
	function srvHconList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			anua     : parseInt($scope.hcon.anua),
			mesp     : parseInt($scope.hcon.mesp),
			cate     : parseInt($scope.hcon.cate),
			conc     : parseInt($scope.hcon.conc)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.hconList = data.hconList;
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Function que llama al servicio de anulacion de apuntes
	function srvHconAnul(iden) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden     : parseInt(iden)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/anul/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}
	//Function que recupera el mapa de conceptos
	function srvConcMap() {
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
	
	//Function que obtiene la lista de conceptos (de la categoria seleccionada)
	function srvConcList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt($scope.hcon.cate)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/conc/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concList = data.concList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que recupera el mapa de categorias
	function srvCateMap() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/map/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateMap = data.cateMap;
			$scope.cateList = data.cateList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Funcion que obtiene la lista parametrica ANUALCONTA.
	function srvParAnua() {
		var d = $q.defer();
		$http.get(targetHost + 'service/angular/parametro/ANUALCONTA/').success(function(data) {
			$scope.parAnuaList = data.parmList;
//			if ($scope.hcon.anua === 0) {
//				$scope.hcon.anua = data.parmList[0].clav;
//			}
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Funcion que obtiene la lista parametrica CALENDAMES.
	function srvParMes() {
		var d = $q.defer();
		$http.get(targetHost + 'service/angular/parametro/CALENDAMES/').success(function(data) {
			$scope.parMesList = data.parmList;
			if ($scope.hcon.mesp === 0) {
				$scope.hcon.mesp = $scope.parMesList[0].clav;
			}
			d.resolve(data);
		});
		return d.promise;
	}

});