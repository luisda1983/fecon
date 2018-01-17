app.controller('cuenTrasCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cuen/tras');
	
	if ($scope.cntx.form.ctor === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'O';
	}
	
	var srv1 = comc.request('cuen/list', $scope.cntx);	
	$q.all([srv.stResp(srv1)]).then(function() {
		view();
	});

	//Función que captura la cancelación de la vista
	$scope.fnCanc = function() {
		srv.backState(true);
	}

	//Funcion que realiza el traspaso
	$scope.fnForm = function() {
		
		var srv1 = comc.request('cuen/tras', $scope.cntx);
		
		$q.all([srv.stResp(srv1)]).then(function(){
			if ($scope.cntx.conf.mode === 'O') {
				$scope.fnCanc();
			} else {
				//TODO: inicializacion, como modo de vista I
				srv2 = comc.request('cuen/list', $scope.cntx);
				$scope.cntx.form.ctor = 0;
				$scope.cntx.form.ctde = 0;
				$scope.cntx.form.impo = 0;
				$scope.cntx.form.feva = 0;
			}
		});
		
		$q.all(srv1).then(function() {
//FIXME: con esta llamada no me salen las notificaciones
//			var srv2 = srvCuenList();
//			$q.all([srv2]).then(function(){
//				srv.clearMsg();
//			});
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	// + Modo 'N': Vista normal de traspaso
	// + Modo 'O': Vista de traspaso con cuenta origen definida
	function view() {
		//Traspaso normal
		if ($scope.cntx.conf.mode === 'N') {
			$scope.cntx.form.ctor = 0;
			$scope.cntx.form.ctde = 0;
			$scope.cntx.form.impo = 0;
			$scope.cntx.form.feva = 0;
			
			$scope.cntx.show.ctor = true;
			$scope.cntx.show.ctde = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.feva = true;
			
			$scope.cntx.read.ctor = false;
			$scope.cntx.read.ctde = false;
			$scope.cntx.read.impo = false;
			$scope.cntx.read.feva = false;
			
		//Traspaso con cuenta origen
		} else if ($scope.cntx.conf.mode === 'O') {
			
			$scope.cntx.show.ctor = true;
			$scope.cntx.show.ctde = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.feva = true;
			
			$scope.cntx.read.ctor = true;
			$scope.cntx.read.ctde = false;
			$scope.cntx.read.impo = false;
			$scope.cntx.read.feva = false;
		} 
	}
});