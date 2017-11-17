///////////////////////////////////////////////////////////////////////////////////////////////
// Controlador administracion de conceptos.                                                  //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  10/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

app.controller('admConcCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	inic();
	
	var srv1 = srvCateList();
	var srv2 = srvParTipoConcep();
	$q.all([srv1, srv2]).then(function() { srv.clearMsg(); });

	//Inicializacion de los datos de la vista
	function inic() {

		inicData();
		
		$scope.esta = {
			cateList : false,
			cateEdit : false,
			cateView : false,
			cateIden : 0,
			concList : false,
			concEdit : false
		};
	}

	function inicData() {
		$scope.cate = {
			iden : 0,
			desc : ""
		};
			
		$scope.conc = {
			iden : 0,
			cate : 0,
			desc : "",
			tipo : "",
			domi : 0
		};
	}
	
	function mode(mode, cate) {
		if (mode === 'cateList') {
			$scope.esta.cateList = true;
			$scope.esta.cateEdit = false;
			$scope.esta.cateView = false,
			$scope.esta.cateIden = 0;
			$scope.esta.concList = false;
			$scope.esta.concEdit = false;
		} else if (mode === 'cateView') {
			$scope.esta.cateList = false;
			$scope.esta.cateEdit = false;
			$scope.esta.cateView = true;
			$scope.esta.cateIden = cate;
			$scope.esta.concList = true;
			$scope.esta.concEdit = false;
		} else if (mode === 'cateEdit') {
			$scope.esta.cateList = false;
			$scope.esta.cateEdit = true;
			$scope.esta.cateView = false,
			$scope.esta.cateIden = cate;
			$scope.esta.concList = false;
			$scope.esta.concEdit = false;
		} else if (mode === 'concEdit') {
			$scope.esta.cateList = false;
			$scope.esta.cateEdit = false;
			$scope.esta.cateView = true,
			$scope.esta.cateIden = cate;
			$scope.esta.concList = false;
			$scope.esta.concEdit = true;
		} else {
			inic();
		}
	}
	
	//Captura el evento de guardar una categoria (creacion y edicion)
	$scope.saveCate = function() {	
		var srv1 = srvCateSave();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
	
	//Captura el evento de activar y desactivar una categoria
	$scope.actiCate = function(iden, acti) {
		var srv1 = srvCateActi(iden, acti);
		$q.all([srv1]).then(function() { srv.clearMsg(); });
	};
	
	//Captura el evento de subir/bajar una categoria en su orden
	$scope.moveCate = function(iden, down) {
		var srv1 = srvCateMove(iden, down);
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
		
	//Function que captura el evento de ver el detalle de una categoria
	$scope.verCate = function(cate) {	
		mode('cateView', cate);
		var srv1 = srvConcList(cate);
		$q.all([srv1]).then(function() { srv.clearMsg(); });
	};
	
	//Funcion que captura la solicitud de edicion de una categoria
	$scope.editCate = function(iden, desc) {
		mode('cateEdit', iden);
		$scope.cate.iden = iden;
		$scope.cate.desc = desc;
	};

	//Funcion que captura la solicitud de editar un concepto
	$scope.editConc = function(iden, desc, tipo, domi) {
		mode('concEdit', $scope.esta.cateIden);
		$scope.conc.iden = iden;
		$scope.conc.cate = $scope.esta.cateIden;
		$scope.conc.desc = desc;
		$scope.conc.tipo = tipo;
		$scope.conc.domi = domi;
	};
	
	//Captura el evento de guardar un concepto (creacion y edicion)
	$scope.saveConc = function() {
		var srv1 = srvConcSave();
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};
	
	//Captura el evento de subir/bajar una categoria en su orden
	$scope.moveConc = function(iden, cate, down) {
		var srv1 = srvConcMove(iden, cate, down);
		$q.all([srv1]).then(function(){ srv.clearMsg(); });
	};

	//Function que captura la cancelacion del modo de edicion de categoria
	$scope.cancCate = function() {
		inicData();
		concList = new Array();
		mode('cateList', 0);
	};

	$scope.cancConc = function() {
		inicData();
		mode('cateView', $scope.esta.cateIden);
	};
	
	//Captura el evento de activar y desactivar un concepto
	$scope.actiConc = function(iden, acti) {
		var srv1 = srvConcActi(iden, acti);
		$q.all([srv1]).then(function() { srv.clearMsg(); });
	};

	//Llamada al servicio de consulta de categorias.
	function srvCateList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			full     : true
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateList = data.cateList;
			mode('cateList', 0);
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Llamada al servicio de consulta de conceptos.
	function srvConcList(cate) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			cate     : parseInt(cate),
			full     : true
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concList = data.concList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio que mueve un concepto (arriba/abajo).
	function srvCateMove(iden, down) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : iden,
			down : down
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/move/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateList = data.cateList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio que mueve un concepto (arriba/abajo).
	function srvConcMove(iden, cate, down) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : iden,
			cate : cate,
			down : down
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/move/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concList = data.concList;
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio que activa/desactiva un concepto
	function srvCateActi(iden, acti) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : iden,
			acti : !acti
		};
	
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/acti/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio que activa/desactiva un concepto
	function srvConcActi(iden, acti) {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : iden,
			acti : !acti
		};
	
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/acti/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}

	//Servicio para guardar categoria en creaci�n o edici�n
	function srvCateSave() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : $scope.cate.iden,
			desc : $scope.cate.desc,
			full : true
		};
		
		var d = $q.defer();
		
		var output;
		if ($scope.cate.iden === 0) {
			output = srv.call(targetHost + 'service/angular/cate/nuev/', dataObject);
		} else {
			output = srv.call(targetHost + 'service/angular/cate/edit/', dataObject);
		}
		output.then(function() {
			var data = srv.getData();
			if (data.status === 'OK') {
				$scope.cateList = data.cateList;
				$scope.cate = {
						iden : 0,
						desc : ''
				};
				mode('cateList', 0);
			}
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Servicio para guardar concepto en creacion o edicion
	function srvConcSave() {
		$scope.conc.idsu = $scope.verSupe;
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
			iden : parseInt($scope.conc.iden),
			cate : parseInt($scope.esta.cateIden),
			desc : $scope.conc.desc,
			tipo : $scope.conc.tipo,
			domi : parseInt($scope.conc.domi),
			full : true
		};
		
		var d = $q.defer();
		
		var output;
		if ($scope.conc.iden === 0) {
			output = srv.call(targetHost + 'service/angular/conc/nuev/', dataObject);
		} else {
			output = srv.call(targetHost + 'service/angular/conc/edit/', dataObject);
		}
		output.then(function() {
			var data = srv.getData();
			if (data.status === 'OK') {
				$scope.concList = data.concList;
				$scope.conc = {
					iden : 0,
					cate : 0,
					desc : "",
					tipo : "",
					domi : 0
				};
				mode('cateView', $scope.esta.cateIden);
			}
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Funcion que obtiene la lista parametrica TIPOCONCEP.
	function srvParTipoConcep() {
		var d = $q.defer();
		$http.get(targetHost + 'service/angular/parametro/TIPOCONCEP/').success(function(data) {
			$scope.parTipo = data.parmMap;
			$scope.parTipoList = data.parmList;
			$scope.parTipoConc = data.parmMap;
			d.resolve(data);
		});
		return d.promise;
	}

});