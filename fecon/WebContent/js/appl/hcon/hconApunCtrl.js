app.controller('hconApunCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	inic();
	
	var srv1 = srvCateList();
	var srv2 = srvCuenList();
	
	$q.all([srv1, srv2]).then(function() { srv.clearMsg(); });
	
	
	//Function que inicializa las variables usadas en la vista.
	function inic() {
		$scope.apun = {
			cate : 0,
			conc : 0,
			cuen : 0,
			impo : 0,
			fval : '',
			desc : ''
		};
	}
	
	$scope.concChange = function() {
		var srv1 = srvConcList();
		$q.all([srv1]).then(function() { srv.clearMsg(); });
	};
	
	//Funcion que captura la solicitud de guardar un nuevo apunte
	$scope.save = function() {
		var srv1 = srvApunSave();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};

	//Funcion que guarda un apunte
	function srvApunSave() {
		var formatFval;
		if ($scope.apun.fval === undefined || $scope.apun.fval === '' || $scope.apun.fval === null) {
			formatFval = 0;
		} else {
			var yf=$scope.apun.fval.getFullYear();           
			var mf=$scope.apun.fval.getMonth() + 1;
			var df=$scope.apun.fval.getDate();
			formatFval = (yf*10000)+(mf*100)+df;
		}

		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			cuen : parseInt($scope.apun.cuen),
			cate : parseInt($scope.apun.cate),
			conc : parseInt($scope.apun.conc),
			impo : parseFloat($scope.apun.impo),
			feva : parseInt(formatFval),
			desc : $scope.apun.desc
		};
		  
		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/hcon/apun/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject('NOK');
			} else {
				inic();
			}
		});
		return d.promise;
	}
	  
	//Function que obtiene la lista de conceptos (categorias)
	function srvCateList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateList = data.OUTPUT['cateList'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que obtiene la lista de conceptos (de la categoria seleccionada)
	function srvConcList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			cate: parseInt($scope.apun.cate)
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/conc/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concList = data.OUTPUT['concList'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que obtiene la lista de cuentas
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
});
