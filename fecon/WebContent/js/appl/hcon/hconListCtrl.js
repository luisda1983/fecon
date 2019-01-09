//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('hconListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.requestLiteList('HCONLTTIPO', $scope.cntx);
		var srv2 = comc.requestLiteList('ANUALIDAD', $scope.cntx);
		var srv3 = comc.requestLiteList('MES', $scope.cntx); 
		var srv4 = comc.request('cate/list', $scope.cntx);
		var srv5 = comc.request('conc/full', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1,srv2,srv3,srv4,srv5)]).then(function() {
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();
		
		//Si venimos del formulario de apuntes y tenemos un apunte en el área de intercambio, lo cargamos en la lista
		if (view === 'hcon/form') {
			var hcon = $scope.cntx.xchg.get('hcon');

			if (hcon !== undefined) {
				var indx = $scope.cntx.xchg.get('indx');
				if (indx >= 0) {
					$scope.cntx.data.get('hconList')[indx] = hcon;
				} 
			}
		}

		ctxl.clearXchg($scope.cntx);
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición con datos                                                                     //
	//*************************************************************************************************************//
	function loadViewGo() {
		var view = srv.getOrigView();
		
		$scope.cntx.conf.set('mode', 'T');

		if (view === 'pres/anua') {
			var anua = $scope.cntx.xchg.get('anua');
			var mesp = $scope.cntx.xchg.get('mesp');
			var cate = $scope.cntx.xchg.get('cate');
			var conc = $scope.cntx.xchg.get('conc');
			
			if (mesp === undefined || mesp === 0) {
				$scope.cntx.conf.set('mode', 'B');
				$scope.cntx.form.get('tipo').data = 'LT02';
				$scope.cntx.form.get('anua').data = anua;
				$scope.cntx.form.get('cate').data = cate;
				$scope.fnCateChng();
				$scope.cntx.form.get('conc').data = conc;
				$scope.fnForm();
			} else {
				$scope.cntx.conf.set('mode', 'B');
				$scope.cntx.form.get('tipo').data = 'LT01';
				$scope.cntx.form.get('anua').data = anua;
				$scope.cntx.form.get('mesh').data = mesp;
				$scope.fnForm();
			}
		} else if (view === 'pres/mesp') {
			var anua = $scope.cntx.xchg.get('anua');
			var mesp = $scope.cntx.xchg.get('mesp');
			var cate = $scope.cntx.xchg.get('cate');
			var conc = $scope.cntx.xchg.get('conc');
			
			$scope.cntx.conf.set('mode', 'B');
			$scope.cntx.form.get('tipo').data = 'LT03';
			$scope.cntx.form.get('anua').data = anua;
			$scope.cntx.form.get('mesh').data = mesp;
			$scope.cntx.form.get('cate').data = cate;
			$scope.fnCateChng();
			$scope.cntx.form.get('conc').data = conc;
			$scope.fnForm();
		} else if (view === 'pres/conc') {
			var anua = $scope.cntx.xchg.get('anua');
			var cate = $scope.cntx.xchg.get('cate');
			var cate = $scope.cntx.xchg.get('cate');
			var conc = $scope.cntx.xchg.get('conc');
			
			$scope.cntx.conf.set('mode', 'B');
			$scope.cntx.form.get('tipo').data = 'LT02';
			$scope.cntx.form.get('anua').data = anua;
			$scope.cntx.form.get('mesh').data = mesp;
			$scope.cntx.form.get('cate').data = cate;
			$scope.fnCateChng();
			$scope.cntx.form.get('conc').data = conc;
			$scope.fnForm();
		}
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'T': Selección de tipo de consulta.                                                                       //
	// - 'S': Tipo seleccionado.                                                                                   //
	// - 'B': Tipo bloqueado.                                                                                      //
	// - 'LT01': Consulta de apuntes por mes.                                                                      //
	// - 'LT02': Consulta por año y concepto.                                                                      //
	// - 'LT03': Consulta por mes y concepto.                                                                      //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'T') {
			//Selección de tipo de consulta
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'anua', false, true);
			ctxl.formField($scope.cntx, 'mesh', false, true);
			ctxl.formField($scope.cntx, 'cate', false, true);
			ctxl.formField($scope.cntx, 'conc', false, true);
		} else if ($scope.cntx.conf.get('mode') === 'S') {
			ctxl.formField($scope.cntx, 'tipo', true, false);
			
			if ($scope.cntx.form.get('tipo').data === 'LT01') {
				//Consulta de apuntes por mes
				ctxl.formField($scope.cntx, 'anua', true, false);
				ctxl.formField($scope.cntx, 'mesh', true, false);
				ctxl.formField($scope.cntx, 'cate', false, true);
				ctxl.formField($scope.cntx, 'conc', false, true);
			} else if ($scope.cntx.form.get('tipo').data === 'LT02') {
				//Consulta por año y concepto
				ctxl.formField($scope.cntx, 'anua', true, false);
				ctxl.formField($scope.cntx, 'mesh', false, true);
				ctxl.formField($scope.cntx, 'cate', true, false);
				ctxl.formField($scope.cntx, 'conc', true, false);
			} else if ($scope.cntx.form.get('tipo').data === 'LT03') {
				ctxl.formField($scope.cntx, 'anua', true, false);
				ctxl.formField($scope.cntx, 'mesh', true, false);
				ctxl.formField($scope.cntx, 'cate', true, false);
				ctxl.formField($scope.cntx, 'conc', true, false);
			}
		} else if ($scope.cntx.conf.get('mode') === 'B') {
			ctxl.formField($scope.cntx, 'tipo', false, true);
			
			if ($scope.cntx.form.get('tipo').data === 'LT01') {
				//Consulta de apuntes por mes
				ctxl.formField($scope.cntx, 'anua', true, true);
				ctxl.formField($scope.cntx, 'mesh', true, true);
				ctxl.formField($scope.cntx, 'cate', false, true);
				ctxl.formField($scope.cntx, 'conc', false, true);
			} else if ($scope.cntx.form.get('tipo').data === 'LT02') {
				//Consulta por año y concepto
				ctxl.formField($scope.cntx, 'anua', true, true);
				ctxl.formField($scope.cntx, 'mesh', false, true);
				ctxl.formField($scope.cntx, 'cate', true, true);
				if (cntx.form.get('conc').data === 0 || cntx.form.get('conc').data === undefined) {
					ctxl.formField($scope.cntx, 'conc', true, false);
				} else {
					ctxl.formField($scope.cntx, 'conc', true, true);
				}
			} else if ($scope.cntx.form.get('tipo').data === 'LT03') {
				ctxl.formField($scope.cntx, 'anua', true, true);
				ctxl.formField($scope.cntx, 'mesh', true, true);
				ctxl.formField($scope.cntx, 'cate', true, true);
				if (cntx.form.get('conc').data === 0 || cntx.form.get('conc').data === undefined) {
					ctxl.formField($scope.cntx, 'conc', true, false);
				} else {
					ctxl.formField($scope.cntx, 'conc', true, true);
				}
			}
		} 
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************                                           *********************************//
	//*********************************  FUNCIONES AUXILIARES DE MANEJO DE VISTA  *********************************//
	//*********************************               (LISTAS)                    *********************************//
	//*********************************                                           *********************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		srv.back(true, null);
	}

	//*************************************************************************************************************//
	// Captura del evento de listar apuntes.                                                                       //
	//*************************************************************************************************************//
	$scope.fnForm = function() {
		var srv1 = comc.request('hcon/list', $scope.cntx);
		
		srv.stResp(true, srv1);
	}
	
	//*************************************************************************************************************//
	// Captura del evento de edición de cuenta.                                                                    //
	//*************************************************************************************************************//
	$scope.fnEdit = function(i) {
		var cntx = srv.getCntx('hcon/form');
		
		var hcon = $scope.cntx.data.get('hconList')[i];
		cntx.xchg.set('hcon', hcon);
		cntx.xchg.set('indx', i);
		
		srv.go('hcon/list', $scope.cntx, 'hcon/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del cambio de tipo de consulta.                                                                     //
	//*************************************************************************************************************//
	$scope.fnTipoChng = function() {
		$scope.cntx.conf.set('mode', 'S');
		view();
	}

	//*************************************************************************************************************//
	// Captura del cambio de categoria.                                                                            //
	//*************************************************************************************************************//
	$scope.fnCateChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		
		srv.stResp(true, srv1);
	}

	//*************************************************************************************************************//
	// Captura de la anulación de apunte.                                                                          //
	//*************************************************************************************************************//
	$scope.fnAnul = function anul(i) {
		$scope.cntx.form.get('iden').data = $scope.cntx.data.get('hconList')[i].iden;
		
		var srv1 = comc.request('hcon/anul', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.form.get('iden').data = 0;
			$scope.cntx.data.get('hconList').splice(i, 1);
		});
	};

	//*************************************************************************************************************//
	// Captura de la exclusión de apunte del presupuesto.                                                          //
	//*************************************************************************************************************//
	$scope.fnExcl = function excl(i) {
		$scope.cntx.form.get('iden').data = $scope.cntx.data.get('hconList')[i].iden;
		$scope.cntx.form.get('acci').data = 'E';
		
		var srv1 = comc.request('hcon/pres/gest', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('hconList')[i] = $scope.cntx.data.get('hcon');
			$scope.cntx.form.get('iden').data = 0;
			$scope.cntx.form.get('acci').data = '';
		});
	}
	
	//*************************************************************************************************************//
	// Captura de la inclusión de apunte en el presupuesto.                                                        //
	//*************************************************************************************************************//
	$scope.fnIncl = function incl(i) {
		$scope.cntx.form.get('iden').data = $scope.cntx.data.get('hconList')[i].iden;
		$scope.cntx.form.get('acci').data = 'I';
		
		var srv1 = comc.request('hcon/pres/gest', $scope.cntx); 
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('hconList')[i] = $scope.cntx.data.get('hcon');
			$scope.cntx.form.get('iden').data = 0;
			$scope.cntx.form.get('acci').data = '';
		});
	}

	//*************************************************************************************************************//
	// Despliegue de menú de acciones.                                                                             //
	//*************************************************************************************************************//
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};
	
	//*************************************************************************************************************//
	// Despliegue de registro de una lista.                                                                        //
	//*************************************************************************************************************//
	$scope.xpnd = function(i) {
		if ($scope.cntx.conf.get('idx1') === i) {
			$scope.cntx.conf.set('idx1', -1);
		} else {
			$scope.cntx.conf.set('idx1', i);
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
	$scope.cntx = srv.getCntx('hcon/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});