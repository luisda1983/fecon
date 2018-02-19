app.controller('usuaRegiCtrl', function($scope, $q, srv, comc, ctxl) {

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//****************************************                             ****************************************//
	//****************************************  FUNCIONES DE ARQUITECTURA  ****************************************//
	//****************************************                             ****************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Carga de vista                                                                                              //
	//*************************************************************************************************************//
	function loadView() {
		inicForm();
		
		var srv1 = comc.requestParaGet('C', 'APLICONFIG', 'CONFREGIST', $scope.cntx);
		var srv2 = comc.requestParaGet('C', 'DYNAMICFLD', 'REGINVDESC', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2)]).then(function() {
			var prConfregist = $scope.cntx.data.get('prConfregist');
			if (prConfregist.pval.esta === 'C') {
				$scope.cntx.conf.set('mode', 'C');
			} else if (prConfregist.pval.esta === 'L') {
				$scope.cntx.conf.set('mode', 'L');
			} else if (prConfregist.pval.esta === 'I') {
				$scope.cntx.conf.set('mode', 'S');
			} else {
				$scope.cntx.conf.set('mode', 'C');
			}
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		//Sin acciones adicionales
	}
	
	//*************************************************************************************************************//
	// Carga de vista, en transición con datos                                                                     //
	//*************************************************************************************************************//
	function loadViewGo() {
		var view = srv.getOrigView();
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'I': Esperamos código de invitación para ser validad.                                                     //
	// - 'S': Solicitud de invitación.                                                                             //
	// - 'V': Invitación validada.                                                                                 //
	// - 'L': Registro libre, sin invitación.                                                                      //
	// - 'C': Registro cerrado.                                                                                    //
	//*************************************************************************************************************//
	function view() {	
		if ($scope.cntx.conf.get('mode') === 'C') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'mail', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'V') {
			ctxl.formField($scope.cntx, 'invi', true, true);
			ctxl.formField($scope.cntx, 'mail', true, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'S') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'mail', true, false);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', true);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'I') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		}

		//El campo de descripción, se muestra según el parámetro DYNAMICFLD-REGINVDESC, con su texto asociado
		if ($scope.cntx.form.get('desc').show) {
			var prReginvdesc = $scope.cntx.data.get('prReginvdesc');
			if (prReginvdesc.pval.show === 'S') {
				$scope.cntx.form.get('ldes').data = prReginvdesc.pval.name;
			} else {
				ctxl.formField($scope.cntx, 'desc', false, true);
				$scope.cntx.form.get('ldes').data = '';
			}
		}		
	}
	
	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('invi').data = '';
		$scope.cntx.form.get('mail').data = '';
		$scope.cntx.form.get('desc').data = '';
		$scope.cntx.form.get('usua').data = '';
		$scope.cntx.form.get('pass').data = '';
		$scope.cntx.form.get('cpas').data = '';
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de solicitud de invitación.                                                              //
	//*************************************************************************************************************//
	$scope.fnSoli = function() {
		if ($scope.cntx.conf.get('mode') === 'S') {
			var srv1 = comc.request('invi/soli', $scope.cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				inicForm();
			});
		}
	};

	//*************************************************************************************************************//
	// Captura del evento de registro.                                                                             //
	//*************************************************************************************************************//
	$scope.fnRegi = function() {
		if ($scope.cntx.conf.get('mode') === 'S') {
			$scope.cntx.conf.set('mode', 'I');
			view();
		} else if ($scope.cntx.conf.get('mode') === 'I') {
			var srv1 = comc.request('invi/vali', $scope.cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				$scope.cntx.form.get('mail').data = $scope.cntx.data.get('invi').mail;
				$scope.cntx.conf.set('mode', 'V');
				view();
			})
		} else if ($scope.cntx.conf.get('mode') === 'V') {
			if ($scope.cntx.data.get('invi').tipo === 'I') {
				var srv1 = comc.request('invi/proc', cntx);
				$q.all([srv.stResp(true, srv1)]).then(function() {
					var lgonCntx = srv.getCntx('/lgon');
					lgonCntx.form.get('iden').data = $scope.cntx.form.get('usua').data;
					lgonCntx.form.get('pass').data = $scope.cntx.form.get('pass').data;
					alert(lgonCntx.form.get('iden').data);
					srv.go('usua/regi', $scope.cntx, '/lgon', lgonCntx);
				})
			} else {
				//TODO: registros de usuario
			}
		}
		//TODO: registro libre
	}

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		if ($scope.cntx.conf.get('mode') === 'V' || $scope.cntx.conf.get('mode') === 'I') {
			$scope.cntx.conf.set('mode', 'S');
			inicForm();
			view();
		}
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	//Generamos el contexto de la vista
	$scope.cntx = srv.getCntx('usua/regi');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}

});