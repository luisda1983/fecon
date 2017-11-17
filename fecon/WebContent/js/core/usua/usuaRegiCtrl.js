app.controller('usuaRegiCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {

	inic();
	inicPara();
	
	var srv1 = srvParaGetRegiConf();
	var srv2 = srvParaGetInstDesc();
	
	$q.all([srv1, srv2]).then(function() { 
		view();
	});
	
	//Captura el evento de solitud de de invitación
	$scope.soli = function() {
		if ($scope.esta.mode === 'I') {
			$scope.esta.mode = 'S';
			//$scope.esta.soli = true;
			$scope.esta.vali = false;
			view();
		} else if ($scope.esta.mode === 'S') {
			var srv1 = srvInviSoli();
			$q.all([srv1]).then(function() {
				inic();
				view();
			});
		}
	};

	//Captura el evento de registro
	$scope.regi = function() {
		if ($scope.esta.mode === 'S') {
			$scope.esta.invi = true;
			$scope.esta.vali = false;
			view();
		} else if ($scope.esta.mode === 'I') {
			var srv1 = srvInviVali();
			$q.all([srv1]).then(function() {
				$scope.esta.invi = true;
				$scope.esta.vali = true;
				view();
			})
		} else if ($scope.esta.mode === 'V') {
			if ($scope.data.invi.tipo === 'I') {
				var srv1 = srvInstRegi();
				$q.all([srv1]).then(function() {
					//TODO
					//Hacer login
				})
			} else {
				//TODO: login de usuario
			}
		}
	}
	
	//Function que inicializa las variables usadas en la vista.
	function inic() {
		$scope.form = {
			invi : '',
			mail : '',
			desc : '',
			ldes : '',
			usua : '',
			pass : '',
			cpas : ''
		};
		
		$scope.show = {
			invi : false,
			mail : false,
			desc : false,
			usua : false,
			pass : false,
			cpas : false,
			regi : false,
			soli : false
		};

		$scope.read = {
			invi : false
		};

		$scope.data = {
			invi : null
		};
		
		$scope.esta = {
			vali : false,
			invi : false,
			mode : ''
		};
	}

	//Funcion de inicialización de parámetros
	function inicPara() {
		$scope.para = {
				conf : null,
				desc : null
			};

	}
	
	//Función de manejo de vista
	function view() {
		
		//Tenemos 5 modos de vista:
		// + I: Esperamos codigo de invitación para ser validada.
		// + S: Solicitamos invitación.
		// + V: Invitación validada, se permite registro
		// + L: Se permite registro sin invitación
		// + C: No se permite registro
		if ($scope.para.conf.pval.esta === 'C') {
			$scope.esta.mode = 'C';
		} else if ($scope.para.conf.pval.esta === 'L') {
			$scope.esta.mode = 'L';
		} else if ($scope.para.conf.pval.esta === 'I') {
			if ($scope.esta.invi === true) {
				if ($scope.esta.vali === true) {
					$scope.esta.mode = 'V';
				} else {
					$scope.esta.mode = 'I';
				}
			} else {
				$scope.esta.mode = 'S';
			}
		} else {
			$scope.esta.mode = 'C';
		}
		
		if ($scope.esta.mode === 'C') {
			$scope.show.invi = false;
			$scope.show.mail = false;
			$scope.show.desc = false;
			$scope.show.usua = false;
			$scope.show.pass = false;
			$scope.show.cpas = false;
			$scope.show.regi = false;
			$scope.show.soli = false;
			
			$scope.read.invi = false;
			
		} else if ($scope.esta.mode === 'L') {
			$scope.show.invi = false;
			$scope.show.mail = true;
			$scope.show.desc = true;
			$scope.show.usua = true;
			$scope.show.pass = true;
			$scope.show.cpas = true;
			$scope.show.regi = true;
			$scope.show.soli = false;
			
			$scope.read.invi = false;
			
		} else if ($scope.esta.mode === 'V') {
			$scope.show.invi = true;
			$scope.show.mail = true;
			$scope.show.desc = true;
			$scope.show.usua = true;
			$scope.show.pass = true;
			$scope.show.cpas = true;
			$scope.show.regi = true;
			$scope.show.soli = false;
			
			$scope.read.invi = true;
			
		} else if ($scope.esta.mode === 'S') {
			$scope.show.invi = false;
			$scope.show.mail = true;
			$scope.show.desc = false;
			$scope.show.usua = false;
			$scope.show.pass = false;
			$scope.show.cpas = false;
			$scope.show.regi = true;
			$scope.show.soli = true;
			
			$scope.read.invi = false;
			
		} else if ($scope.esta.mode === 'I') {
			$scope.show.invi = true;
			$scope.show.mail = false;
			$scope.show.desc = false;
			$scope.show.usua = false;
			$scope.show.pass = false;
			$scope.show.cpas = false;
			$scope.show.regi = true;
			$scope.show.soli = false;
			
			$scope.read.invi = false;
			
		}

		//El campo se descripción, se muestra según el parámetro DYNAMICFLD-REGINVDESC, con su texto asociado
		if ($scope.show.desc === true) {
			if ($scope.para.desc.pval.show === 'S') {
				$scope.show.desc = true;
				$scope.form.ldes = $scope.para.desc.pval.name;
			} else {
				$scope.show.desc = false;
				$scope.show.ldes = '';
			}
		}		
	}

	//Consulta del parámetro de configuración del registro
	function srvParaGetRegiConf() {
		var dataObject = {
			tbla: "APLICONFIG",
			clav: "CONFREGIST"
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/para/get/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.para.conf = data.OUTPUT['para'];
			d.resolve(data);
		})
		return d.promise;
	}
	
	//Consulta del parámetro de configuración del registro
	function srvParaGetInstDesc() {
		var dataObject = {
			tbla: "DYNAMICFLD",
			clav: "REGINVDESC"
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/para/get/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.para.desc = data.OUTPUT['para'];
			d.resolve(data);
		})
		return d.promise;
	}
	
	//Llamada al servicio de solicitud de invitación
	function srvInviSoli() {
		
		var dataObject = {
			mail: $scope.form.mail
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/soli/', dataObject);
		output.then(function() {
			var data = srv.getData();
			d.resolve(data);
		});
		return d.promise;
	}
	
	//Llamada al servicio de validación de invitación
	function srvInviVali() {
		
		var dataObject = {
			iden: $scope.form.invi
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/vali/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject("NOK");
			} else {
				$scope.data.invi = data.OUTPUT['invi'];
				d.resolve(data);
			}
		}, function() {
			d.reject("NOK");
		});
		return d.promise;
	}
	
	//Llamada al servicio de validación de invitación
	function srvInstRegi() {
		
		var dataObject = {
			iden : $scope.form.invi,
			mail : $scope.form.mail,
			//FIXME
			//desc : $scope.form.desc,
			usua : $scope.form.usua,
			pass : $scope.form.pass,
			cpas : $scope.form.cpas
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/proc/', dataObject);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject("NOK");
			} else {
				//$scope.data.invi = data.OUTPUT['invi'];
				d.resolve(data);
			}
		}, function() {
			d.reject("NOK");
		});
		return d.promise;
	}
});