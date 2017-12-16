app.controller('cuenTrasCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	$scope.form = {
		ctor : 0,
		ctde : 0,
		impo : 0,
		feva : 0
	}
	
	var srv1 = srvCuenList();	
	$q.all([srv1]).then(function(){  });
	
	//Funcion que realiza un traspaso
	$scope.fnTras = function() {
		
		var srv1 = srvCuenTras();
		$q.all(srv1).then(function() {
//FIXME: con esta llamada no me salen las notificaciones
//			var srv2 = srvCuenList();
//			$q.all([srv2]).then(function(){
//				srv.clearMsg();
//			});
		});
	};
	
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
		var impo = $scope.form.impo;
		impo = impo.toString().replace(',', '.');
		
		var fmtFeva;
		if ($scope.form.feva === undefined || $scope.form.feva === '' || $scope.form.feva === null) {
			fmtFeva = 0;
		} else {
			var yf=$scope.form.feva.getFullYear();           
			var mf=$scope.form.feva.getMonth() + 1;
			var df=$scope.form.feva.getDate();
			fmtFeva = (yf*10000)+(mf*100)+df;
		}
		
		var dataObject = {
			sesi : parseInt($rootScope.esta.sesi),
			ctor : $scope.form.ctor.iden,
			ctde : $scope.form.ctde.iden,
			impo : parseFloat(impo),
			feva : parseInt(fmtFeva),
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/tras/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				alert(1);
				d.reject('NOK');
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}
});