///////////////////////////////////////////////////////////////////////////////////////////////
// Controlador cuenCuad: cuadre de cuenta.                                                   //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  10/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

app.controller('cuenCuadCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	inic();
	
	var srv1 = srvCuenList();	
	$q.all([srv1]).then(function(){ srv.clearMsg(); });
	
	//Funcion de calcular en tiempo real el importe de cuadre.
	$scope.actuImpo = function() {
		var sald = $scope.cuad.sald;
		sald = sald.toString().replace(',', '.');
		$scope.cuad.sald = sald;
		
		if (isNaN(parseFloat(sald))) {
			$scope.cuad.impo = (0 - parseFloat($scope.cuad.cuen.sald)).toFixed(2);
		} else {
			$scope.cuad.impo = (parseFloat($scope.cuad.sald) - parseFloat($scope.cuad.cuen.sald)).toFixed(2);
		}
	};
	
	//Funcion que se ejecuta al seleccionar una cuenta.
	$scope.cuenSelect = function() {
		$scope.cuad.impo = 0;
		$scope.actuImpo();
	};
	
	//Funcion que se ejecuta al pulsar el boton de cuadre.
	$scope.cuadrar = function() {
		$scope.showResult = true;
		$scope.disa.cuen = true;
		
		var srv1 = srvCateList();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};

	//Function que se ejecuta al seleccionar una categoria de concepto
	$scope.cateChange = function() {
		var srv1 = srvConcList($scope.cuad.cate);
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
	
	//Funcion que ejecuta el servicio de cuadre y, si ok, vuelve a consultar las cuentas.
	$scope.cuadrarApunte = function() {

		var srv1 = srvCuenCuad();
		$q.all(srv1).then(function() {
			var srv2 = srvCuenList();
			$q.all([srv2]).then(function(){
				srv.clearMsg(); 
			});
		});
	};

	//Funcion que inicializa las variables usadas en la vista
	function inic() {
		
		$scope.cuad = {
			cuen : 0,
			sald : 0,
			impo : 0,
			cate : 0,
			conc : 0
		};
	
		$scope.disa = {
			cuen : false,
			sald : false,
			impo : false,
			cate : false,
			conc : false
		};

		$scope.showResult = false;
	}
	
	//Funcion que cuadra una cuenta
	function srvCuenCuad() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt($scope.cuad.cate),
			conc     : parseInt($scope.cuad.conc),
			impo     : parseFloat($scope.cuad.impo),
			iden     : parseInt($scope.cuad.cuen.iden)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/cuad/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.status === 'OK') {
				inic();
				d.resolve(data);
			} else {
				d.reject('NOK');
			}
		});
		return d.promise;
	}
	
	//Funcion que obtiene la lista de cuentas.
	function srvCuenList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cuenList = data.OUTPUT['cuenList'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que obtiene la lista de categorias.
	function srvCateList() {
		var dataObject = {
				idSesion : parseInt($rootScope.idSesion)
			};

			var d = $q.defer();
				
			var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
			output.then(function() {
				var data = srv.getData();
				$scope.cateList = data.cateList;
				d.resolve(data);
			});
			return d.promise;
	}
	
	//Funcion que obtiene una lista de conceptos.
	function srvConcList(iden) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt(iden)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/list/', dataObject);
		output.then(function(){
			var data = srv.getData();
			$scope.concList = data.concList;
			d.resolve(data);
		});
		return d.promise;
	}

});