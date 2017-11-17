app.controller('presNuevController', function($rootScope, $scope, $http, $routeParams, $q, srv) {
	
	inic(parseInt(0));
	
	var srv1 = srvGetAnua();
	var srv2 = srvParPeri();
	var srv3 = srvParMes();
	var srv4 = srvCateList();
	
	$q.all([srv1, srv2, srv3, srv4]).then(function() {
		srv.clearMsg();
		var srv5 = srvPrepList();
		var srv6 = srvPresList();
		$q.all([srv5, srv6]).then(function() {
			srv.clearMsg();
		});
	});

	//Funcion que inicializa las variables usadas en la vista
	function inic(anua) {
		
		$scope.prep = {
			anua: anua,
			iden: 0,
			cate: 0,
			conc: 0,
			peri: '',
			mini: "0",
			mfin: 0,
			impo: 0,
		};
	}

	//Funcion que captura el evento de cambio de categoria.
	$scope.cateChange = function() {
		if ($scope.prep.cate !== 0) {
			var srv1 = srvConcList();
			$q.all([srv1]).then(function() { srv.clearMsg(); });
		} else {
			$scope.concList = new Array();
		}
	};
	
	//Funcion que captura el evento de guardar.
	$scope.savePrep = function() {
		var srv1 = srvPrepSave();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};

	//Funcion que captura el evento de editar.
	$scope.editPrep = function(iden, cate, conc, peri, mini, mfin, impo) {
		$scope.prep.iden = iden;
		$scope.prep.cate = cate;
		
		var srv1 = srvConcList();
		$q.all([srv1]).then(function() {
			$scope.prep.conc = conc;
			$scope.prep.peri = peri;
			$scope.prep.mini = mini > 9 ? "" + mini: "0" + mini;
			$scope.prep.mfin = mfin > 9 ? "" + mfin: "0" + mfin;
			$scope.prep.impo = impo;
			srv.clearMsg();
		});
	};
	
	//Funcion que captura el evento de borrar.
	$scope.borrPrep = function(iden) {
		var srv1 = srvPrepBorr(iden);
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};

	//Funcion que captura el evento de copiar prevision del a�o anterior
	$scope.copy = function() {
		var srv1 = srvPrepCopy();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
	
	//Function que obtiene la anualidad a presupuestar.
	//  Obtiene la lista parametrica ANUALCONTA, le suma uno a la primera ocurrencia.
	function srvGetAnua() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/anua/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.prep.anua = data.prep.anua;
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Funcion que obtiene la lista parametrica PERIODICID.
	function srvParPeri() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			tbla     : 'PERIODICID',
			defa     : true
		};
		
		var d = $q.defer();
		var output = srv.call(targetHost + 'service/angular/parametro/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.parPeriList = data.parmList;
			$scope.parPeriMap  = data.parmMap;
			d.resolve(data);
		});
		return d.promise;
	}

	//Funcion que obtiene la lista parametrica CALENDAMES.
	function srvParMes() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			tbla     : 'CALENDAMES',
			defa     : true,
			defv     : "0"
		};
		
		var d = $q.defer();
		var output = srv.call(targetHost + 'service/angular/parametro/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.parMesList = data.parmList;
			$scope.parMesMap  = data.parmMap;
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que obtiene la lista de conceptos (categorias)
	function srvCateList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			defa     : true
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/cate/map/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateList = data.cateList;
			$scope.cateMap  = data.cateMap;
			d.resolve(data);
		});
		return d.promise;
	}

	
	//Servicio que obtiene la lista de conceptos (de la categoria seleccionada)
	function srvConcList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt($scope.prep.cate),
			defa     : true
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

	//Funcion que guarda una prevision de presupuesto
	function srvPrepSave() {
		
		var impo = $scope.prep.impo;
		impo = impo.toString().replace(',', '.');
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : parseInt($scope.prep.iden),
			anua : parseInt($scope.prep.anua),
			cate : parseInt($scope.prep.cate),
			conc : parseInt($scope.prep.conc),
			peri : $scope.prep.peri,
			mini : parseInt($scope.prep.mini),
			mfin : parseInt($scope.prep.mfin),
			impo : parseFloat(impo)
		};
		  
		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/save/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.status === 'OK') {
				d.resolve(data);
				inic($scope.prep.anua);
				var srv1 = srvPrepList();
				$q.all([srv1]).then(function() {
					srv.clearMsg();
				});
			} else {
				d.reject("NOK");
			}
		});
		return d.promise;
	}

	//Funcion que borra una prevision de presupuesto
	function srvPrepBorr(iden) {
		
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : parseInt(iden)
		};
		  
		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/borr/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
			inic($scope.prep.anua);
			var srv1 = srvPrepList();
			$q.all([srv1]).then(function() {
				srv.clearMsg();
			});
		});
		return d.promise;
	}

	//Funcion que copia una prevision de presupuesto
	function srvPrepCopy() {
		
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			anua     : parseInt($scope.prep.anua - 1)
		};
		  
		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/copy/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
			inic($scope.prep.anua);
			var srv1 = srvPrepList();
			$q.all([srv1]).then(function() {
				srv.clearMsg();
			});
		});
		return d.promise;
	}

	//Function que obtiene la lista de prevision de presupuesto
	function srvPrepList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			anua     : parseInt($scope.prep.anua)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.prepList = data.prepList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que obtiene un resumen del a�o anterior
	function srvPresList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			anua     : parseInt($scope.prep.anua - 1)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/prep/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.prepAnteList = data.prepList;
			d.resolve(data);
		});
		return d.promise;
	}

});