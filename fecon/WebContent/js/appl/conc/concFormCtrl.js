app.controller('concFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {
	
	$scope.cntx = srv.getCntx('conc/form');
	
	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}
	view();
		
	//Función que captura la cancelación de la vista
	$scope.fnCanc = function() {
		srv.backState(true);
	}
	
	//Function que captura el submit del formulario
	$scope.fnForm = function() {
		var srv1 = comc.request('conc/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.fnCanc();
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	// - mode 'N': Nuevo concepto
	// - mode 'E': Edición de concepto
	function view() {
		//Nuevo concepto
		if ($scope.cntx.conf.mode === 'N') {
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.desl = '';
			$scope.cntx.form.desc = '';
			$scope.cntx.form.orde = 0;
			
			$scope.cntx.show.iden = false;
			$scope.cntx.show.cate = false;
			$scope.cntx.show.desl = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.orde = false;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.cate = true;
			$scope.cntx.read.desl = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.orde = true;
		
		//Edición de concepto
		} else if ($scope.cntx.conf.mode === 'E') {
			
			$scope.cntx.show.iden = true;
			$scope.cntx.show.cate = false;
			$scope.cntx.show.desl = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.orde = true;
			$scope.cntx.show.pres = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.cate = true;
			$scope.cntx.read.desl = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.orde = true;
			$scope.cntx.read.pres = false;
		}
	}

});