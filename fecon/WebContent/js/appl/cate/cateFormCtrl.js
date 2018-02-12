app.controller('cateFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('cate/form');
	
	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}
	
	var srv1 = comc.requestLiteList('BOOL', $scope.cntx);
	$q.all([srv.stResp(true, srv1)]).then(function() {
		view();
	});	
	
	//Función que captura la cancelación de la vista
	$scope.fnCanc = function() {
		srv.back();
	}
	
	//Función que captura el submit del formulario
	$scope.fnForm = function() {
		var srv1 = comc.request('cate/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.fnCanc();
		});
	};
	
	//Función encargada de manejar la vista, y sus modos de presentación
	// - mode 'N': Nueva categoría
	// - mode 'E': Edición de categoría
	function view() {
		//Nueva categoría
		if ($scope.cntx.conf.mode === 'N') {
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.desl = '';
			$scope.cntx.form.desc = '';
			$scope.cntx.form.orde = 0;
			$scope.cntx.form.pres = '';
			
			$scope.cntx.show.iden = false;
			$scope.cntx.show.desl = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.orde = false;
			$scope.cntx.show.pres = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.desl = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.orde = true;
			$scope.cntx.read.pres = false;
		
		//Edición de categoría
		} else if ($scope.cntx.conf.mode === 'E') {
			
			$scope.cntx.show.iden = true;
			$scope.cntx.show.desl = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.orde = true;
			$scope.cntx.show.pres = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.desl = false;
			$scope.cntx.read.desc = false;
			$scope.cntx.read.orde = true;
			$scope.cntx.read.pres = true;
		}
	}

});