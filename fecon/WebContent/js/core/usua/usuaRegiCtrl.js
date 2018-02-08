app.controller('usuaRegiCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('usua/regi');

	var srv1 = comc.requestParaGet('C', 'APLICONFIG', 'CONFREGIST', $scope.cntx);
	var srv2 = comc.requestParaGet('C', 'DYNAMICFLD', 'REGINVDESC', $scope.cntx);
	
	$q.all([srv.stResp(srv1, srv2)]).then(function() { 
		view();
	});
	
	//Captura el evento de solitud de de invitación
	$scope.fnSoli = function() {
		if ($scope.cntx.conf.mode === 'I') {
			$scope.cntx.conf.mode = 'S';
			//$scope.cntx.conf.soli = true;
			$scope.cntx.conf.vali = false;
			view();
		} else if ($scope.cntx.conf.mode === 'S') {
			var srv1 = comc.request('invi/soli', $scope.cntx);
			$q.all([srv.stResp(srv1)]).then(function() {
				$scope.cntx.form.invi = '';
				$scope.cntx.form.mail = '';
				$scope.cntx.form.desc = '';
				$scope.cntx.form.ldes = '';
				$scope.cntx.form.usua = '';
				$scope.cntx.form.pass = '';
				$scope.cntx.form.cpas = '';
				$scope.cntx.conf.mode = '';
				view();
			});
		}
	};

	//Captura el evento de registro
	$scope.fnRegi = function() {
		if ($scope.cntx.conf.mode === 'S') {
			$scope.cntx.conf.invi = true;
			$scope.cntx.conf.vali = false;
			view();
		} else if ($scope.cntx.conf.mode === 'I') {
			var srv1 = comc.request('invi/vali', $scope.cntx);
			$q.all([srv.stResp(srv1)]).then(function() {
				$scope.cntx.conf.invi = true;
				$scope.cntx.conf.vali = true;
				view();
			})
		} else if ($scope.cntx.conf.mode === 'V') {
			if ($scope.cntx.data.invi.tipo === 'I') {
				var srv1 = comc.request('invi/proc', cntx);
				$q.all([srv.stResp(srv1)]).then(function() {
					//TODO
					//Hacer login
				})
			} else {
				//TODO: login de usuario
			}
		}
	}
	
	//Función de manejo de vista
	function view() {
		
		//Tenemos 5 modos de vista:
		// + I: Esperamos codigo de invitación para ser validada.
		// + S: Solicitamos invitación.
		// + V: Invitación validada, se permite registro
		// + L: Se permite registro sin invitación
		// + C: No se permite registro
		if ($scope.cntx.data.prConfregist.pval.esta === 'C') {
			$scope.cntx.conf.mode = 'C';
		} else if ($scope.cntx.data.prConfregist.pval.esta === 'L') {
			$scope.cntx.conf.mode = 'L';
		} else if ($scope.cntx.data.prConfregist.pval.esta === 'I') {
			if ($scope.cntx.conf.invi === true) {
				if ($scope.cntx.conf.vali === true) {
					$scope.cntx.conf.mode = 'V';
				} else {
					$scope.cntx.conf.mode = 'I';
				}
			} else {
				$scope.cntx.conf.mode = 'S';
			}
		} else {
			$scope.cntx.conf.mode = 'C';
		}
		
		if ($scope.cntx.conf.mode === 'C') {
			$scope.cntx.show.invi = false;
			$scope.cntx.show.mail = false;
			$scope.cntx.show.desc = false;
			$scope.cntx.show.usua = false;
			$scope.cntx.show.pass = false;
			$scope.cntx.show.cpas = false;
			$scope.cntx.show.regi = false;
			$scope.cntx.show.soli = false;
			
			$scope.cntx.read.invi = false;
			
		} else if ($scope.cntx.conf.mode === 'L') {
			$scope.cntx.show.invi = false;
			$scope.cntx.show.mail = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.usua = true;
			$scope.cntx.show.pass = true;
			$scope.cntx.show.cpas = true;
			$scope.cntx.show.regi = true;
			$scope.cntx.show.soli = false;
			
			$scope.cntx.read.invi = false;
			
		} else if ($scope.cntx.conf.mode === 'V') {
			$scope.cntx.show.invi = true;
			$scope.cntx.show.mail = true;
			$scope.cntx.show.desc = true;
			$scope.cntx.show.usua = true;
			$scope.cntx.show.pass = true;
			$scope.cntx.show.cpas = true;
			$scope.cntx.show.regi = true;
			$scope.cntx.show.soli = false;
			
			$scope.cntx.read.invi = true;
			
		} else if ($scope.cntx.conf.mode === 'S') {
			$scope.cntx.show.invi = false;
			$scope.cntx.show.mail = true;
			$scope.cntx.show.desc = false;
			$scope.cntx.show.usua = false;
			$scope.cntx.show.pass = false;
			$scope.cntx.show.cpas = false;
			$scope.cntx.show.regi = true;
			$scope.cntx.show.soli = true;
			
			$scope.cntx.read.invi = false;
			
		} else if ($scope.cntx.conf.mode === 'I') {
			$scope.cntx.show.invi = true;
			$scope.cntx.show.mail = false;
			$scope.cntx.show.desc = false;
			$scope.cntx.show.usua = false;
			$scope.cntx.show.pass = false;
			$scope.cntx.show.cpas = false;
			$scope.cntx.show.regi = true;
			$scope.cntx.show.soli = false;
			
			$scope.cntx.read.invi = false;
			
		}

		//El campo se descripción, se muestra según el parámetro DYNAMICFLD-REGINVDESC, con su texto asociado
		if ($scope.cntx.show.desc === true) {
			if ($scope.cntx.data.prReginvdesc.pval.show === 'S') {
				$scope.cntx.show.desc = true;
				$scope.cntx.form.ldes = $scope.data.prReginvdesc.pval.name;
			} else {
				$scope.cntx.show.desc = false;
				$scope.cntx.form.ldes = '';
			}
		}		
	}
});