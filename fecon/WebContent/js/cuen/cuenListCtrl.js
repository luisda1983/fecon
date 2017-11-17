///////////////////////////////////////////////////////////////////////////////////////////////
// Controlador cuenList: Lista de cuenta, creaci�n y modificaci�n.                           //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  10/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

app.controller('cuenListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {
	
	inic();
	
	var srv1 = srvCuenList();	
	var srv2 = srvParTipoCuenta();
	
	$q.all([srv1, srv2]).then(function(){ srv.clearMsg(); });
	
	//Function que trata el evento de graba una cuenta (nueva o edici�n).
	$scope.save = function() {
	
		var url;
		if ($scope.ncuen.iden === 0) {
			url = targetHost + 'service/angular/cuen/nuev/';
		} else {
			url = targetHost + 'service/angular/cuen/edit/';
		}
		var srv1 = srvCuenSave(url);		
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
	
	//Function que captura el evento de edicion
	$scope.editar = function(iden, desc, tipo, sald) {
		$scope.ncuen.iden = iden;
		$scope.ncuen.desc = desc;
		$scope.ncuen.tipo = tipo;
		$scope.ncuen.sald = sald;
	};
	
	//Function que captura la cancelacion de la edicion
	$scope.cancelar = function() {
		$scope.ncuen.iden = 0;
		$scope.ncuen.desc = '';
		$scope.ncuen.tipo = '';
		$scope.ncuen.sald = 0;
	};
	
	//Function que inicializa las variables usadas en la vista.
	function inic() {
		
		$scope.ncuen = {
			iden : 0,
			tipo : '',
			desc : '',
			sald : 0
		};
	}
	
	//Funcion que obtiene la lista de cuentas.
	function srvCuenList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cuenList = data.cuenList;
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Funcion que obtiene la lista parametrica TIPOCUENTA.
	function srvParTipoCuenta() {
		var d = $q.defer();
		$http.get(targetHost + 'service/angular/parametro/TIPOCUENTA/').success(function(data) {
			$scope.parTipo = data.parmMap;
			$scope.parTipoList = data.parmList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que graba una cuenta en la BBDD.
	function srvCuenSave(url) {
		var d = $q.defer();
		
		var sald = $scope.ncuen.sald;
		sald = sald.toString().replace(',', '.');
		
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : $scope.ncuen.iden,
			desc : $scope.ncuen.desc,
			tipo : $scope.ncuen.tipo,
			sald : parseFloat(sald)
		};
		
		var output = srv.call(url, dataObject);
		output.then(function() {
			$scope.response = srv.getMsg();
			var data = srv.getData();
			if (data.status === 'OK') {
				$scope.cuenList = data.cuenList;
				d.resolve(data);
				inic();
			} else {
				d.reject("NOK");
			}
		});
		return d.promise;
	}
});