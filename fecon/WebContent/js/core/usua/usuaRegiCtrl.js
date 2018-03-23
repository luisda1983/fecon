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
		
		var srv1 = comc.requestParaGet('C', 'APLICONFIG', 'CONFREGIST', $scope.cntx);
		var srv2 = comc.requestParaGet('C', 'APLICONFIG', 'MULTIINSTA', $scope.cntx);
		var srv3 = comc.requestParaGet('C', 'DYNAMICFLD', 'REGINVDESC', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function() {
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
	// - 'C'  : Registro cerrado.                                                                                  //
	// - 'L'  : Registro libre, sin seleccionar tipo.                                                              //
	// - 'LI' : Registro libre, de instalación, sin seleccionar si el usuario existe o es nuevo.                   //
	// - 'LIN': Registro libre, de instalación y con nuevo usuario.                                                //
	// - 'LIE': Registro libre, de instalación y con usuario existente.                                            //
	// - 'LU' : Registro libre, de usuario, sin seleccionar si el usuario existe o es nuevo.                       //
	// - 'LUN': Registro libre, de usuario y con nuevo usuario.                                                    //
	// - 'LUE': Registro libre, de usuario y con usuario existente.                                                //
	// - 'S'  : Solicitud de invitación.                                                                           //
	// - 'I'  : Registro por invitación (espera código de instalación).                                            //
	// - 'IIN': Registro por invitación, de instalación y con nuevo usuario.                                       //
	// - 'IIE': Registro por invitación, de instalación y con usuario existente.                                   //
	// - 'IUN': Registro por invitación, de usuario y con nuevo usuario.                                           //
	// - 'IUE': Registro por invitación, de usuario y con usuario existente.                                       //
	//*************************************************************************************************************//
	function view() {	
		if ($scope.cntx.conf.get('mode') === 'C') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'LI') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', true, false);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'LIN') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', true, true);
			ctxl.formField($scope.cntx, 'mail', true, false);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'LIE') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', true, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'LU') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', true, false);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', true, false);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'LUN') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', true, false);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', true, true);
			ctxl.formField($scope.cntx, 'mail', true, false);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'LUE') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, true);
			ctxl.formField($scope.cntx, 'codi', true, false);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', true, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'S') {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', true, false);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', true);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
		} else if ($scope.cntx.conf.get('mode') === 'I') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'IIN') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', true, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'IIE') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', true, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'IUN') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', true, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', true, false);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else if ($scope.cntx.conf.get('mode') === 'IUE') {
			ctxl.formField($scope.cntx, 'invi', true, false);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', true, true);
			ctxl.formField($scope.cntx, 'usua', true, false);
			ctxl.formField($scope.cntx, 'pass', true, false);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', true);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', true);
		} else {
			ctxl.formField($scope.cntx, 'invi', false, true);
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'codi', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'numo', false, true);
			ctxl.formField($scope.cntx, 'mail', false, true);
			ctxl.formField($scope.cntx, 'usua', false, true);
			ctxl.formField($scope.cntx, 'pass', false, true);
			ctxl.formField($scope.cntx, 'cpas', false, true);
			ctxl.formBtn($scope.cntx, 'btRegi', false);
			ctxl.formBtn($scope.cntx, 'btSoli', false);
			ctxl.formBtn($scope.cntx, 'btCanc', false);
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
		
		//El campo usuario nuevo o existente, está condicionado al parámetro APLICONFIG-MULTIINSTA
		if ($scope.cntx.form.get('numo').show) {
			var prMultiinsta = $scope.cntx.data.get('prMultiinsta');
			if (prMultiinsta.pval.mlti === 'N') {
				$scope.cntx.form.get('numo').data = 'N';
				ctxl.formField($scope.cntx, 'numo', false, true);
				if ($scope.cntx.conf.get('mode') === 'LI')  { $scope.cntx.conf.set('mode', 'LIN'); view(); }
				if ($scope.cntx.conf.get('mode') === 'LU')  { $scope.cntx.conf.set('mode', 'LUN'); view(); }
				if ($scope.cntx.conf.get('mode') === 'IIE') { $scope.cntx.conf.set('mode', 'IIN'); view(); }
				if ($scope.cntx.conf.get('mode') === 'IUE') { $scope.cntx.conf.set('mode', 'IUN'); view(); }
			}
		}
	}
	
	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('invi').data = '';
		$scope.cntx.form.get('tipo').data = '';
		$scope.cntx.form.get('codi').data = '';
		$scope.cntx.form.get('desc').data = '';
		$scope.cntx.form.get('numo').data = '';
		$scope.cntx.form.get('mail').data = '';
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
	// Captura del evento de cambio de selección de tipo de registro.                                              //
	//*************************************************************************************************************//
	$scope.fnTipoChng = function() {
		//Si el registro es libre, pasamos a Libre-Usuario o Libre-Instalación, según lo seleccionado
		if ($scope.cntx.conf.get('mode') === 'L') {
			$scope.cntx.conf.set('mode', $scope.cntx.conf.get('mode')+ $scope.cntx.form.get('tipo').data);
			view();
		}
	};

	//*************************************************************************************************************//
	// Captura del evento de cambio de selección de nuevo usuario o existente.                                     //
	//*************************************************************************************************************//
	$scope.fnNumoChng = function() {
		//Si el registro proviene de la rama libre, se añadimos Nuevo o Existente, según lo seleccionado
		if ($scope.cntx.conf.get('mode').substring(0,1) === 'L') {
			$scope.cntx.conf.set('mode', $scope.cntx.conf.get('mode')+ $scope.cntx.form.get('numo').data);
			view();
		}
		
		//Si el registro proviene de la rama por invitación, se añadimos Nuevo o Existente, según lo seleccionado
		if ($scope.cntx.conf.get('mode').substring(0,1) === 'I') {
			$scope.cntx.conf.set('mode', $scope.cntx.conf.get('mode')+ $scope.cntx.form.get('numo').data);
			view();
		}
	};

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

		//Modos de registro de instalación
		if ($scope.cntx.conf.get('mode') === 'LIE' ||
		    $scope.cntx.conf.get('mode') === 'LIN') {
			var srv1 = comc.request('inst/regi', $scope.cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				var lgonCntx = srv.getCntx('/lgon');
				lgonCntx.xchg.set('usua', $scope.cntx.data.get('usua'));
				srv.go('usua/regi', $scope.cntx, '/lgon', lgonCntx);
			})
		}
		
		//Modos de registro de usuario
		if ($scope.cntx.conf.get('mode') === 'LUE' ||
	        $scope.cntx.conf.get('mode') === 'LUN') {
			var srv1 = comc.request('usua/regi', $scope.cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				var lgonCntx = srv.getCntx('/lgon');
				lgonCntx.xchg.set('usua', $scope.cntx.data.get('usua'));
				srv.go('usua/regi', $scope.cntx, '/lgon', lgonCntx);
			})
		}
		
		//Transición a captura de invitación
		if ($scope.cntx.conf.get('mode') === 'S') {
			$scope.cntx.conf.set('mode', 'I');
			view();
			return;
		} 
		
		//Se valida la invitación y avanzamos, según el tipo de invitación
		if ($scope.cntx.conf.get('mode') === 'I') {
			var srv1 = comc.request('invi/vali', $scope.cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				var invi = $scope.cntx.data.get('invi');
				if (invi.tipo === 'I') {
					$scope.cntx.conf.set('mode', 'II');
				} else if (invi.tipo === 'U') {
					$scope.cntx.conf.set('mode', 'IU');
				}
				$scope.cntx.form.get('mail').data = invi.mail;
				if ($scope.cntx.data.get('exus')) {
					$scope.cntx.form.get('numo').data = 'E';
					$scope.cntx.conf.set('mode', $scope.cntx.conf.get('mode') + 'E');
				} else {
					$scope.cntx.form.get('numo').data = 'N';
					$scope.cntx.conf.set('mode', $scope.cntx.conf.get('mode') + 'N');
				}
				
				view();
			})
		} 
		
		if ($scope.cntx.conf.get('mode') === 'IIN' ||
			$scope.cntx.conf.get('mode') === 'IIE' ||
			$scope.cntx.conf.get('mode') === 'IUN' ||
			$scope.cntx.conf.get('mode') === 'IUE') {
			var srv1 = comc.request('invi/proc', cntx);
			$q.all([srv.stResp(true, srv1)]).then(function() {
				var lgonCntx = srv.getCntx('/lgon');
				lgonCntx.xchg.set('usua', $scope.cntx.data.get('usua'));
				srv.go('usua/regi', $scope.cntx, '/lgon', lgonCntx);
			})
		}
	}

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		//Por la rama de registro libre, la cancelación vuelve al punto de partida
		if ($scope.cntx.conf.get('mode').substring(0,1) === 'L') {
			inicForm();
			$scope.cntx.conf.set('mode', 'L');
			view();
			return;
		}
		
		//Si estamos en captura de invitación, el cancelar vuelve a la solicitud de invitación
		if ($scope.cntx.conf.get('mode') === 'I') {
			inicForm();
			$scope.cntx.conf.set('mode', 'S');
			view();
			return;
		}

		//Por la rama de registro por invitación, la cancelación vuelve a la captura de la invitación
		if ($scope.cntx.conf.get('mode').substring(0,1) === 'I') {
			inicForm();
			$scope.cntx.conf.set('mode', 'I');
			view();
			return;
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