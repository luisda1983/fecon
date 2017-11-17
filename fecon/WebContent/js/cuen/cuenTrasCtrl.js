///////////////////////////////////////////////////////////////////////////////////////////////
// Controlador traspaso de cuenta.                                                           //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  10/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

app.controller('cuenTrasCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	var parOrig = $routeParams.orig;	
	if (parOrig === undefined) { parOrig = 0;};
	inic(parOrig);
	
	var srv1 = srvCuenList();	
	$q.all([srv1]).then(function(){ srv.clearMsg(); });
	
	//Funcion que realiza un traspaso
	$scope.traspasar = function() {
		
		var srv1 = srvCuenTras();
		$q.all(srv1).then(function() {
			var srv2 = srvCuenList();
			$q.all([srv2]).then(function(){
				srv.clearMsg(); 
			});
		});
	};
	
	//Function que inicializa las variables usadas en la vista.
	function inic(orig) {
		
		$scope.cuen = {
			orig : parseInt(orig),
			dest : 0,
			impo : 0
		};
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
	
	//Funcion que realiza un traspaso entre cuentas.
	function srvCuenTras() {
		var impo = $scope.cuen.impo;
		impo = impo.toString().replace(',', '.');
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			orig : $scope.cuen.orig,
			dest : $scope.cuen.dest,
			impo : parseFloat(impo)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/tras/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.status === 'OK') {
				inic();
				d.resolve(data);
			} else {
				d.reject("NOK");
			}
		});
		return d.promise;
	}
});