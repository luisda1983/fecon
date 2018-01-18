app.controller('cuenCuadCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cuen/cuad');
	
	var srv1 = comc.request('cuen/list', $scope.cntx);	
	$q.all([srv.stResp(srv1)]).then(function() {
		$scope.cntx.conf.mode = "C";
		view();
	});

	//Funcion que se ejecuta al seleccionar una cuenta.
	$scope.fnCuenSelc = function() {
		$scope.cntx.form.impo = parseFloat(0);
		$scope.fnImpoRfsh();
	};

	//Funcion de calcular en tiempo real el importe de cuadre.
	$scope.fnImpoRfsh = function() {
		if (!isNaN(parseFloat($scope.cntx.form.sald))) {
			var sald = $scope.cntx.form.sald;
			sald = sald.toString().replace(',', '.');
			$scope.cntx.form.sald = parseFloat(sald);
		} 
		
		if (isNaN(parseFloat(sald))) {
			$scope.cntx.form.impo = parseFloat((0 - parseFloat($scope.cntx.form.cuen.sald)).toFixed(2));
		} else {
			$scope.cntx.form.impo = parseFloat((parseFloat($scope.cntx.form.sald) - parseFloat($scope.cntx.form.cuen.sald)).toFixed(2));
		}
	};

	//Función que captura el cambio de categoria
	$scope.fnCateChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		srv.stResp(srv1);
	}

	//Función que captura el evento de cuadre, para realizar la transición a la vista de apunte
	$scope.fnCuad = function() {
		if ($scope.cntx.form.impo !== 0) {
			var srv1 = comc.request('cate/list', $scope.cntx);
			$q.all([srv.stResp(srv1)]).then(function() {
				$scope.cntx.conf.mode = 'A';
				view();
			})
		}
	}

	//Función que captura la cancelación del modo Apunte de cuadre
	$scope.fnCanc = function() {
		$scope.cntx.form.cate = 0;
		$scope.cntx.form.conc = 0;
		
		$scope.cntx.conf.mode = 'C';
		
		view();
	}

	
	//Función que captura el evento de efectuar el apunte de cuadre.
	$scope.fnApun = function() {
		var srv1 = comc.request('cuen/cuad', $scope.cntx);
		$q.all([srv.stResp(srv1)]).then(function() {
			var srv2 = comc.request('cuen/list', $scope.cntx);
			$q.all([srv.stResp(srv2)]).then(function() {
				//TODO: inicializar con view
				$scope.cntx.form.cuen = 0;
				$scope.cntx.form.sald = 0;
				$scope.cntx.form.impo = 0;
				$scope.cntx.form.cate = 0;
				$scope.cntx.form.conc = 0;
				$scope.cntx.conf.mode = 'C';
				view();
			})
		})
	}
	
	//Función encargada de manejar la vista, y sus modos de presentación
	// - Modo "C": Cuadre de cuenta
	// - Modo "A": Apunte de cuadre de cuenta
	function view() {
		//Cuadre de cuenta
		if ($scope.cntx.conf.mode === 'C') {
			$scope.cntx.form.cuen = 0;
			$scope.cntx.form.sald = 0;
			$scope.cntx.form.impo = 0;
			$scope.cntx.form.cate = 0;
			$scope.cntx.form.conc = 0;
			
			$scope.cntx.show.cuen = true;
			$scope.cntx.show.sald = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.cate = false;
			$scope.cntx.show.conc = false;
			
			$scope.cntx.show.btCuad = true;
			$scope.cntx.show.stApun = false;
			
			$scope.cntx.read.cuen = false;
			$scope.cntx.read.sald = false;
			$scope.cntx.read.impo = true;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
	
		//Apunte de cuadre
		} else if ($scope.cntx.conf.mode === 'A') { 
			
			$scope.cntx.show.cuen = true;
			$scope.cntx.show.sald = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			
			$scope.cntx.show.btCuad = false;
			$scope.cntx.show.stApun = true;
			
			$scope.cntx.read.cuen = true;
			$scope.cntx.read.sald = true;
			$scope.cntx.read.impo = true;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
		}
	}
});