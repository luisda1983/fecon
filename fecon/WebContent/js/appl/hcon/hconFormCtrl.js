app.controller('hconFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('hcon/form');

	
	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}

	var srv1 = comc.request('cate/list', $scope.cntx);
	var srv2 = comc.request('cuen/list', $scope.cntx);
	$q.all([srv.stResp(srv1, srv2)]).then(function() {
		view();
	});
	
	//Función que captura el camio de categoria
	$scope.fnConcChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		srv.stResp(srv1);
	};
	
	//Funcion que captura la solicitud de guardar un nuevo apunte
	$scope.fnForm = function() {
		var srv1 = comc.request('hcon/form', $scope.cntx);
		$q.all([srv.stResp(srv1)]).then(function(){
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.cate = 0;
			$scope.cntx.form.conc = 0;
			$scope.cntx.form.cuen = 0;
			$scope.cntx.form.impo = 0;
			$scope.cntx.form.feva = 0;
			$scope.cntx.form.desc = '';
		});
	};

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Modo "N": Nuevo apunte
	function view() {
		//Nuevo apunte
		if ($scope.cntx.conf.mode === 'N') {
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.cate = 0;
			$scope.cntx.form.conc = 0;
			$scope.cntx.form.cuen = 0;
			$scope.cntx.form.impo = 0;
			$scope.cntx.form.feva = 0;
			$scope.cntx.form.desc = '';
			
			$scope.cntx.show.iden = false;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			$scope.cntx.show.cuen = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.feva = true;
			$scope.cntx.show.desc = true;
			
			$scope.cntx.read.iden = true;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
			$scope.cntx.read.cuen = false;
			$scope.cntx.read.impo = false;
			$scope.cntx.read.feva = false;
			$scope.cntx.read.desc = false;
			
		//Edición de apunte
		} else if ($scope.cntx.conf.mode === 'E') {
			//No implementado
		} 
	}
});
