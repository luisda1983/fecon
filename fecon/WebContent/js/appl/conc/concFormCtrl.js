app.controller('concFormCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {
	
	$scope.cntx = srv.getCntx('conc/form');
	
	if ($scope.cntx.form.iden === 0) {
		$scope.cntx.conf.mode = 'N';
	} else {
		$scope.cntx.conf.mode = 'E';
	}
	view();
	
	//Función encargada de manejar la vista, y sus modos de presentación
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
	
	//Función que captura la cancelación de la vista
	$scope.fnCanc = function() {
		srv.backState(true);
	}
	
	//Function que captura el submit del formulario
	$scope.form = function() {
		var srv1 = srvConcForm();
		$q.all([srv1]).then(function() {
			$scope.fnCanc();
		})
	};
	
	function srvConcForm() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: $scope.cntx.form.iden,
			cate: $scope.cntx.form.cate,
			desl: $scope.cntx.form.desl,
			desc: $scope.cntx.form.desc
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/conc/form/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject('NOK');
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}
});