//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('ejecListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.requestLiteList('EJECESTA', $scope.cntx);
		var srv2 = comc.request('ejec/list', $scope.cntx);
		var srv3 = comc.request('mpla/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function(){
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
		
		if (view === 'plan/list') {
			var fech = $scope.cntx.xchg.get('fech');
			var hora = $scope.cntx.xchg.get('hora');
			
			$scope.cntx.form.get('fech').data = $scope.cntx.xchg.get('fech');
			$scope.cntx.form.get('hora').data = $scope.cntx.xchg.get('hora');
			
			$scope.cntx.conf.set('mode', 'P');
			$scope.cntx.conf.set('back', true);
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de ejecuciones.                                                                                //
	// - 'P': Lista de ejecuciones de un pase Batch.                                                               //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'fech', true, false);
			ctxl.formField($scope.cntx, 'hora', false, true);
			ctxl.formBtn($scope.cntx, 'btAcep', true);
		} else if ($scope.cntx.conf.get('mode') === 'P') {
			ctxl.formField($scope.cntx, 'fech', true, true);
			ctxl.formField($scope.cntx, 'hora', true, true);
			ctxl.formBtn($scope.cntx, 'btAcep', false);
		} else {
			ctxl.formField($scope.cntx, 'fech', false, true);
			ctxl.formField($scope.cntx, 'hora', false, true);
			ctxl.formBtn($scope.cntx, 'btAcep', false);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('fech').data = 0;
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
	// Captura del evento de nueva búsqueda.                                                                       //
	//*************************************************************************************************************//
	$scope.fnForm = function(i) {
		var srv1 = comc.request('ejec/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			
		});
	}

	//*************************************************************************************************************//
	// Captura del evento de consulta de Log.                                                                      //
	//*************************************************************************************************************//
	$scope.fnLogpList = function(i) {
		var cntx = srv.getCntx('logp/list');
		
		var ejec = $scope.cntx.data.get('ejecList')[i];
		cntx.xchg.set('btch', ejec.btch);
		cntx.xchg.set('fepl', ejec.fepl);
		cntx.xchg.set('hopl', ejec.hopl);
		
		srv.go('ejec/list', $scope.cntx, 'logp/list', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de suspensión de ejecución.                                                              //
	//*************************************************************************************************************//
	$scope.fnEjecSusp = function(i) {
		var ejec = $scope.cntx.data.get('ejecList')[i];
		$scope.cntx.form.get('fech').data = ejec.fech;
		$scope.cntx.form.get('hora').data = ejec.hora;
		$scope.cntx.form.get('btch').data = ejec.btch;
		$scope.cntx.form.get('secu').data = ejec.secu;
		
		var srv1 = comc.request('ejec/susp', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('ejecList')[i] = $scope.cntx.data.get('ejec');
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
	$scope.cntx = srv.getCntx('ejec/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});