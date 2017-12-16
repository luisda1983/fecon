app.controller('hconListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, $filter) {

	$scope.form = {
		tipo: '',
		anua: 0,
		mesh: 0,
		cate: 0,
		conc: 0
	};
	
	$scope.show = {
		tipo: false,
		anua: false,
		mesh: false,
		cate: false,
		conc: false
	};
	
	$scope.conf = {
		mode: '',
		item: -1,
		iden: 0
	};
	
	var srv1 = srvLiteHconLtTipo();
	var srv2 = srvLiteAnualidad();
	var srv3 = srvLiteMes();
	var srv4 = srvCateMap();
	var srv5 = srvConcMap();

	$q.all([srv1, srv2, srv3, srv4, srv5]).then(function() {
		$scope.conf.mode = 'T';
		view();
		srv.clearMsg();
	});

	function view() {
		//Seleccionar Tipo de Consulta
		if ($scope.conf.mode === 'T') {
			$scope.show.tipo = true;
			$scope.show.anua = false;
			$scope.show.mesh = false;
			$scope.show.cate = false;
			$scope.show.conc = false;
		} else if ($scope.conf.mode === 'LT01') { //Consulta por mes
			$scope.show.tipo = true;
			$scope.show.anua = true;
			$scope.show.mesh = true;
			$scope.show.cate = false;
			$scope.show.conc = false;
		} else if ($scope.conf.mode === 'LT02') { //Consulta por año y concepto 
			$scope.show.tipo = true;
			$scope.show.anua = true;
			$scope.show.mesh = false;
			$scope.show.cate = true;
			$scope.show.conc = true;
		} else if ($scope.conf.mode === 'LT03') { //Consulta por mes y concepto
			$scope.show.tipo = true;
			$scope.show.anua = true;
			$scope.show.mesh = true;
			$scope.show.cate = true;
			$scope.show.conc = true;
		} else {
			$scope.show.tipo = true;
			$scope.show.anua = false;
			$scope.show.mesh = false;
			$scope.show.cate = false;
			$scope.show.conc = false;
		}
	}

	$scope.fnTipoChng = function() {
		$scope.conf.mode = $scope.form.tipo;
		view();
	}
	
	$scope.fnCateChng = function() {
		var srv1 = srvConcList();
		$q.all([srv1]).then(function() {
			
		});
	}
	
	$scope.fnList = function() {
		var srv1 = srvHconList();
		$q.all([srv1]).then(function() {
			
		});
	}

	//Funcion que captura la solicitud de anulacion de un apunte
	$scope.fnAnul = function anul(iden) {
		$scope.conf.iden = $scope.hconList[iden].iden;
		var srv1 = srvHconAnul();
		
		$q.all([srv1]).then(function() {
			$scope.conf.iden = 0;
			var srv2 = srvHconList();
			$q.all([srv2]).then(function() {
				srv.clearMsg();
			});
		});
	};

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
			cate     : parseInt($scope.form.cate)
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

	//Llamada al servicio de consulta de literales: Tipos de lista de Hcon
	function srvLiteHconLtTipo() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'HCONLTTIPO'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.ltHconLtTipo = data.OUTPUT['liteMap'];
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
			$scope.ltAnualidad = data.OUTPUT['liteMap'];
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
			$scope.ltMes = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Function que obtiene la lista de apuntes
	function srvHconList() {
		var dataObject = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo : $scope.form.tipo,
			anua : parseInt($scope.form.anua),
			mesh : parseInt($scope.form.mesh),
			cate : parseInt($scope.form.cate),
			conc : parseInt($scope.form.conc)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.hconList = data.OUTPUT['hconList'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Function que llama al servicio de anulacion de apuntes
	function srvHconAnul() {
		var dataObject = {
			sesi : parseInt($rootScope.esta.sesi),
			iden : parseInt($scope.conf.iden)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/anul/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}

	
	
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