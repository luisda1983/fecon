app.controller('inviListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, $mdMenu) {

	$scope.conf = {
		item : -1,
		load : false
	}
	
	$scope.form = {
		esta : ""
	};
	
	var srv1 = srvLiteInviEsta();
	var srv2 = srvLiteInviTipo();
	
	$q.all([srv1, srv2]).then(function(){
		loadList();
		$scope.conf.load = true;
	});

	function loadList() {
		var srv1 = srvInviList();
		
		$q.all([srv1]).then(function() { });
	}

	//Llamada al servicio de consulta de invitaciones
	function srvInviList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			esta: $scope.form.esta
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.inviList = data.OUTPUT['inviList'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Llamada al servicio de rechazo de invitación
	function srvInviRech(iden) {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: iden
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/rech/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de aceptación de invitación
	function srvInviAcep(iden) {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: iden
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/acep/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de literales: Estados de Invitacion
	function srvLiteInviEsta() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'INVIESTA'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.ltInviEsta = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Llamada al servicio de consulta de literales: Tipos de Invitacion
	function srvLiteInviTipo() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'INVITIPO'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.ltInviTipo = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Función para capturar la aceptación de una invitación
	$scope.inviAcep = function(iden) {
		var srv1 = srvInviAcep(iden);
		
		$q.all([srv1]).then(function() { loadList(); })
	}
	
	//Función para capturar el rechazo de una invitación
	$scope.inviRech = function(iden) {
		var srv1 = srvInviRech(iden);
		
		$q.all([srv1]).then(function(){ loadList(); });
	}
	
	//Funcion para capturar la selección del desplegable de estado de invitacion
	$scope.estaChng = function() {
		if ($scope.conf.load) {
			loadList();
		}
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
});