app.controller('hconFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('hcon/form');

	
	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}

	var srv1 = comc.request('cate/list', $scope.cntx);
	var srv2 = comc.request('cuen/list', $scope.cntx);
	var srv3 = comc.requestLiteList('HCONMDTIPO', $scope.cntx);
	$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function() {
		view();
	});
	
	//Función que captura el camio de categoria
	$scope.fnConcChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		srv.stResp(true, srv1);
	};

	//Función que captura el cambio de tipo de modificación
	$scope.fnTipoChng = function() {
		view();
	};

	//Funcion que captura la solicitud de guardar un nuevo apunte
	$scope.fnForm = function() {
		var srv1 = comc.request('hcon/form', $scope.cntx);
		$q.all([srv.stResp(true, srv1)]).then(function(){
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
			$scope.cntx.form.tipo = '';
			$scope.cntx.form.iden = 0;
			$scope.cntx.form.cate = 0;
			$scope.cntx.form.conc = 0;
			$scope.cntx.form.cuen = 0;
			$scope.cntx.form.impo = 0;
			$scope.cntx.form.feva = 0;
			$scope.cntx.form.desc = '';
			
			$scope.cntx.show.tipo = false;
			$scope.cntx.show.iden = false;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			$scope.cntx.show.cuen = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.feva = true;
			$scope.cntx.show.desc = true;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.iden = true;
			$scope.cntx.read.cate = false;
			$scope.cntx.read.conc = false;
			$scope.cntx.read.cuen = false;
			$scope.cntx.read.impo = false;
			$scope.cntx.read.feva = false;
			$scope.cntx.read.desc = false;
			
		//Edición de apunte
		} else if ($scope.cntx.conf.mode === 'E') {
			$scope.cntx.show.tipo = true;
			$scope.cntx.show.iden = false;
			$scope.cntx.show.cate = true;
			$scope.cntx.show.conc = true;
			$scope.cntx.show.cuen = true;
			$scope.cntx.show.impo = true;
			$scope.cntx.show.feva = true;
			$scope.cntx.show.desc = true;
			
			$scope.cntx.read.tipo = false;
			$scope.cntx.read.iden = true;
			$scope.cntx.read.cate = true;
			$scope.cntx.read.conc = true;
			$scope.cntx.read.cuen = true;
			$scope.cntx.read.impo = true;
			$scope.cntx.read.feva = true;
			$scope.cntx.read.desc = true;

			//Cambiar fecha
			if ($scope.cntx.form.tipo = 'MD01') {
				$scope.cntx.read.feva = false;
			//Cambiar descripción
			} else if ($scope.cntx.form.tipo = 'MD02') {
				
			//Cambiar importe
			} else if ($scope.cntx.form.tipo = 'MD03') {
			
			//Cambiar categoría
			} else if ($scope.cntx.form.tipo = 'MD04') {
			
			}
		} 
	}
});
