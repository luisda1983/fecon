app.controller('cuenListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	$scope.form = {
		titl : '',
		iden : 0,
		tipo : '',
		desc : '',
		sald : 0
	}
	
	$scope.show = {
		list : false,
		form : false,
		iden : false,
		tipo : false,
		desc : false,
		sald : false
	}
	
	$scope.read = {
		iden : false,
		tipo : false,
		desc : false,
		sald : false
	}
	
	$scope.conf = {
		item : -1,
		load : false,
		mode : ''
	}

	var srv1 = srvLiteCuenTipo();
	
	$q.all([srv1]).then(function(){
		loadList();
		$scope.conf.load = true;
		$scope.conf.mode = 'L';
		view();
	});
	
	function loadList() {
		var srv1 = srvCuenList();
		
		$q.all([srv1]).then(function() { });
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

	//Llamada al servicio de consulta de literales: Estados de Invitacion
	function srvLiteCuenTipo() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'CUENTIPO'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.ltCuenTipo = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Función encargada de manejar la vista, y sus modos de presentación
	function view() {
		if ($scope.conf.mode === 'L') {
			$scope.form.titl = "Cuentas";
			
			$scope.show.list = true;
			$scope.show.form = false;
			$scope.show.iden = false;
			$scope.show.tipo = false;
			$scope.show.desc = false;
			$scope.show.sald = false;
		} else if ($scope.conf.mode === 'E') {
			$scope.form.titl = "Edición de cuenta (" + $scope.form.iden + ")";
			
			$scope.show.list = false;
			$scope.show.form = true;
			
			$scope.show.iden = true;
			$scope.show.tipo = true;
			$scope.show.desc = true;
			$scope.show.sald = true;
			
			$scope.read.iden = true;
			$scope.read.tipo = false;
			$scope.read.desc = false;
			$scope.read.sald = true;
		} else if ($scope.conf.mode === 'N') {
			$scope.form.titl = "Alta de cuenta";
			
			$scope.show.list = false;
			$scope.show.form = true;
			
			$scope.show.iden = false;
			$scope.show.tipo = true;
			$scope.show.desc = true;
			$scope.show.sald = true;
			
			$scope.read.iden = true;
			$scope.read.tipo = false;
			$scope.read.desc = false;
			$scope.read.sald = false;
		}
	}
	
	//Función que carga el modo de edición de cuenta
	$scope.fnEdit = function(i) {
		var cuen = $scope.cuenList[i];
		$scope.form.iden = cuen.iden;
		$scope.form.tipo = cuen.tipo;
		$scope.form.desc = cuen.desc;
		$scope.form.sald = cuen.sald;
		$scope.conf.mode = 'E';
		view();
	}

	//Función que captura la entrada en modo alta de cuenta
	$scope.fnNuev = function() {
		$scope.conf.mode = 'N';
		view();
	}
	
	//Función que captura la cancelación del modo edición/alta
	$scope.fnCanc = function() {
		$scope.form.iden = 0;
		$scope.form.tipo = '';
		$scope.form.desc = '';
		$scope.form.sald = 0;
		$scope.conf.mode = 'L';
		view();
	}
	
	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};
	
	//Función que amplia un registro de la lista
	$scope.xpnd = function(i) {
		if ($scope.conf.item === i) {
			$scope.conf.item = -1;
		} else {
			$scope.conf.item = i;
		}
	}
	
	//Function que captura el submit del formulario
	$scope.form = function() {
		var srv1 = srvCuenForm();
		$q.all([srv1]).then(function() {
			loadList();
			$scope.fnCanc();
		})
	};
	
	function srvCuenForm() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: $scope.form.iden,
			tipo: $scope.form.tipo,
			desc: $scope.form.desc,
			sald: $scope.form.sald
		};

		var d = $q.defer();
			
		var output = srv.call(targetHost + 'service/angular/cuen/form/', dataObject);
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