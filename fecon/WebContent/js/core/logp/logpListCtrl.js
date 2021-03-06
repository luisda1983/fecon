app.controller('logpListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('logp/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();

		ctxl.clearXchg($scope.cntx);
	}
	
	//*************************************************************************************************************//
	// Carga de vista, en transición con datos                                                                     //
	//*************************************************************************************************************//
	function loadViewGo() {
		var view = srv.getOrigView();
		
		$scope.cntx.conf.set('mode', 'L');
		
		if (view === 'ejec/list') {
			$scope.cntx.form.get('tipo').data = 'B';
			$scope.cntx.form.get('iden').data = $scope.cntx.xchg.get('btch');
			$scope.cntx.form.get('fech').data = $scope.cntx.xchg.get('fepl');
			$scope.cntx.form.get('hora').data = $scope.cntx.xchg.get('hopl');
		} else if (view === 'plan/list') {
			$scope.cntx.form.get('tipo').data = 'P';
			$scope.cntx.form.get('iden').data = 'BATCH';
			$scope.cntx.form.get('fech').data = $scope.cntx.xchg.get('fech');
			$scope.cntx.form.get('hora').data = $scope.cntx.xchg.get('hora');
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Log.                                                                                                 //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'fech', false, true);
			ctxl.formField($scope.cntx, 'hora', false, true);
		} else {
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'fech', false, true);
			ctxl.formField($scope.cntx, 'hora', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('tipo').data = '';
		$scope.cntx.form.get('iden').data = '';
		$scope.cntx.form.get('fech').data = 0;
		$scope.cntx.form.get('hora').data = 0;
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de volver.                                                                               //
	//*************************************************************************************************************//
	$scope.fnBack = function(i) {
		srv.back(true, null);
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
	$scope.cntx = srv.getCntx('logp/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});