app.controller('cuenCuadCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	$scope.form = {
		cuen : 0,
		sald : 0,
		impo : 0,
		cate : 0,
		conc : 0
	}

	$scope.conf = {
		mode : ""
	}

	$scope.show = {
		btCuad : false,
		arApun : false
	}
	
	var srv1 = srvCuenList();
	
	$q.all([srv1]).then(function(){
		$scope.conf.mode = "C";
		view();
	});

	//Función encargada de manejar la vista, y sus modos de presentación
	function view() {
		if ($scope.conf.mode === 'C') { //Cuadre de cuenta
			$scope.show.arApun = false;
			$scope.show.btCuad = true;
		} else if ($scope.conf.mode === 'A') { //Apunte de cuadre
			$scope.show.arApun = true;
			$scope.show.btCuad = false;
		}
	}

	//Funcion de calcular en tiempo real el importe de cuadre.
	$scope.fnImpoRfsh = function() {
		if (!isNaN(parseFloat($scope.form.sald))) {
			var sald = $scope.form.sald;
			sald = sald.toString().replace(',', '.');
			$scope.form.sald = parseFloat(sald);
		} 
		
		if (isNaN(parseFloat(sald))) {
			$scope.form.impo = parseFloat((0 - parseFloat($scope.form.cuen.sald)).toFixed(2));
		} else {
			$scope.form.impo = parseFloat((parseFloat($scope.form.sald) - parseFloat($scope.form.cuen.sald)).toFixed(2));
		}
	};
	
	//Funcion que se ejecuta al seleccionar una cuenta.
	$scope.fnCuenSelc = function() {
		$scope.form.impo = parseFloat(0);
		$scope.fnImpoRfsh();
	};

	//Función que captura el cambio de categoria
	$scope.fnCateChng = function() {
		var srv1 = srvConcList();
		$q.all([srv1]).then(function(){  });
	}
	
	//Función que captura el evento de cuadre, para realizar la transición a la vista de apunte
	$scope.fnCuad = function() {
		if ($scope.form.impo !== 0) {
			var srv1 = srvCateList();
			$q.all([srv1]).then(function(){ 
				$scope.conf.mode = 'A';
				view();
			});
		}
	}

	//Función que captura el evento de efectuar el apunte de cuadre.
	$scope.fnApun = function() {
		var srv1 = srvCuenCuad();
		$q.all([srv1]).then(function() {
			$scope.conf.mode = 'C';
			//TODO: inicializar, porque debemos leer nuevamente las cuentas.
			view();
		});
	}
	
	//Función que captura la cancelación del modo Apunte de cuadre
	$scope.fnCanc = function() {
		$scope.form.cate = 0;
		$scope.form.conc = 0;
		
		$scope.conf.mode = 'C';
		
		view();
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

	//Funcion que cuadra una cuenta
	function srvCuenCuad() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			cate: parseInt($scope.form.cate.iden),
			conc: parseInt($scope.form.conc.iden),
			impo: parseFloat($scope.form.impo),
			sald: parseFloat($scope.form.cuen.sald).toFixed(2), //TODO: validación de concurrencia. No funciona por redondeo en backend
			cuen: parseInt($scope.form.cuen.iden)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/cuad/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject('NOK');
			} else {
				//TODO: encapsular
				var srv1 = srvCuenList();
				
				$q.all([srv1]).then(function(){
					$scope.form.cuen = 0;
					$scope.form.sald = 0;
					$scope.form.impo = 0;
					$scope.form.cate = 0;
					$scope.form.conc = 0;

					$scope.conf.mode = "C";
					view();
					d.resolve(data);
				});
			}
		});
		return d.promise;
	}

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
	
	//Funcion que obtiene una lista de conceptos.
	function srvConcList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt($scope.form.cate.iden)
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