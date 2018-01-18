app.controller('cuenFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cuen/form');

	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}
	
	var srv1 = comc.requestLiteList('CUENTIPO', $scope.cntx);	
	$q.all([srv.stResp(srv1)]).then(function() {
		view();
	});

	//Función que captura la cancelación de la vista
	$scope.fnCanc = function() {
		srv.backState(true);
	}

	//Function que captura el submit del formulario
	$scope.fnForm = function() {
		var srv1 = comc.request('cuen/form', $scope.cntx);
		
		$q.all([srv.stResp(srv1)]).then(function(){
			$scope.fnCanc();
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	function view() {
		//Nueva cuenta
		if ($scope.cntx.conf.mode === 'N') {
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.tipo = '';
			$scope.cntx.form.desc = '';
			$scope.cntx.form.sald = 0;
			
			$scope.cntx.show.iden = false;
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.sald = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.sald = false;
			
		//Edición de cuenta
		} else if ($scope.cntx.conf.mode === 'E') {
	
			$scope.cntx.show.iden = true;
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.sald = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.sald = true;
		} 
	}
});