app.controller('stmeListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		
		var srv1 = comc.request('stme/list', $scope.cntx);
		var srv2 = comc.requestLiteList('ANYO', $scope.cntx);
		var srv3 = comc.requestLiteList('MES', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function(){
			$scope.cntx.conf.set('mode', 'L');
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();
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
	// - 'L': Lista de estadísticas diarias.                                                                       //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'anyo', true, false);
			ctxl.formField($scope.cntx, 'mess', true, false);
			ctxl.formBtn($scope.cntx, 'btAcep', true);
		} else {
			ctxl.formField($scope.cntx, 'anyo', false, true);
			ctxl.formField($scope.cntx, 'mess', false, true);
			ctxl.formBtn($scope.cntx, 'btAcep', false);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('anyo').data = 0;
		$scope.cntx.form.get('mess').data = 0;
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
		var srv1 = comc.request('stme/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			
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
	// Solicitar continuación.                                                                                     //
	//*************************************************************************************************************//
	$scope.fnCont = function() {
		$scope.cntx.conf.set('CONT_ACTV', true);
		var srv1 = comc.request('stme/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			
		});
	}
	
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	//Generamos el contexto de la vista
	$scope.cntx = srv.getCntx('stme/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}

});