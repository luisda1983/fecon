//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('instListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		
		var srv1 = comc.requestLiteList('INSTESTA', $scope.cntx);
		var srv2 = comc.requestLiteList('INSTTIPO', $scope.cntx);
		var srv3 = comc.request('inst/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function(){
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();

		//Si venimos del formulario de categorías y tenemos una categoría en el área de intercambio, lo cargamos en la lista
		if (view === 'inst/form') {
			var inst = $scope.cntx.xchg.get('inst');
			
			if (inst !== 'undefined' && inst !== null) {
				var indx = $scope.cntx.xchg.get('indx')
				$scope.cntx.data.get('instList')[indx] = inst;
			}
		}
		
		ctxl.clearXchg($scope.cntx);
	}
	
	//*************************************************************************************************************//
	// Carga de vista, en transición con datos                                                                     //
	//*************************************************************************************************************//
	function loadViewGo() {
		var view = srv.getOrigView();
		
		$scope.cntx.conf.set('mode', 'L');
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de invitaciones.                                                                               //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'esta', true, false);
			ctxl.formField($scope.cntx, 'srch', true, false);
		} else {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'esta', false, true);
			ctxl.formField($scope.cntx, 'srch', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('esta').data = '';
		$scope.cntx.form.get('srch').data = '';
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de cambio en el desplegable estado.                                                      //
	//*************************************************************************************************************//
	$scope.fnEstaChng = function() {
		var srv1 = comc.request('inst/list', $scope.cntx);
		
		srv.stResp(true, srv1);
	}

	//*************************************************************************************************************//
	// Captura del evento de consultar usuarios de instalación.                                                    //
	//*************************************************************************************************************//
	$scope.fnInstUsua = function(i) {
		var cntx = srv.getCntx('usua/list');
		
		cntx.xchg.set('inst', $scope.cntx.data.get('instList')[i]);
		
		srv.go('inst/list', $scope.cntx, 'usua/list', cntx);
	}
	
	//*************************************************************************************************************//
	// Captura del evento de activación de instalación.                                                            //
	//*************************************************************************************************************//
	$scope.fnInstActi = function(i) {
		var inst = $scope.cntx.data.get('instList')[i];
		$scope.cntx.form.get('iden').data = inst.iden;
		
		var srv1 = comc.request('inst/acti', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('instList')[i] = $scope.cntx.data.get('inst');
		});
	}

	//*************************************************************************************************************//
	// Captura del evento de desactivación de instalación.                                                         //
	//*************************************************************************************************************//
	$scope.fnInstInac = function(i) {
		var inst = $scope.cntx.data.get('instList')[i];
		$scope.cntx.form.get('iden').data = inst.iden;
		
		var srv1 = comc.request('inst/inac', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('instList')[i] = $scope.cntx.data.get('inst');
		});
		
	}

	//*************************************************************************************************************//
	// Captura del evento de hacer instalación Premium.                                                            //
	//*************************************************************************************************************//
	$scope.fnInstPrem = function(i) {
		var inst = $scope.cntx.data.get('instList')[i];
		$scope.cntx.form.get('iden').data = inst.iden;
		
		var srv1 = comc.request('inst/prem', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('instList')[i] = $scope.cntx.data.get('inst');
		});
	}

	//*************************************************************************************************************//
	// Captura del evento de quitar Premium a instalación.                                                         //
	//*************************************************************************************************************//
	$scope.fnInstNorm = function(i) {
		var inst = $scope.cntx.data.get('instList')[i];
		$scope.cntx.form.get('iden').data = inst.iden;
		
		var srv1 = comc.request('inst/norm', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('instList')[i] = $scope.cntx.data.get('inst');
		});
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
	$scope.cntx = srv.getCntx('inst/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});