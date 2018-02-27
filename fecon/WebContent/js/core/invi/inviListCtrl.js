app.controller('inviListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, $mdMenu, comc, ctxl) {

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
		
		var srv1 = comc.requestLiteList('INVIESTA', $scope.cntx);
		var srv2 = comc.requestLiteList('INVITIPO', $scope.cntx);
		
		$q.all([srv.stResp(false, srv1, srv2)]).then(function(){
			var srv3 = comc.request('invi/list', $scope.cntx);
			$q.all([srv.stResp(true, srv3)]).then(function(){
				$scope.cntx.conf.set('mode', 'L');
				view();
			});
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
	// - 'L': Lista de invitaciones.                                                                               //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'esta', true, false);
			ctxl.formField($scope.cntx, 'iden', false, true);
		} else {
			ctxl.formField($scope.cntx, 'esta', false, true);
			ctxl.formField($scope.cntx, 'iden', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('esta').data = '';
		$scope.cntx.form.get('iden').data = 0;
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de enviar nueva invitación.                                                              //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('invi/envi');
		srv.go('invi/list', $scope.cntx, 'invi/envi', cntx);
	}
	
	//*************************************************************************************************************//
	// Captura del evento de aceptación de invitación.                                                             //
	//*************************************************************************************************************//
	$scope.fnInviAcep = function(i) {
		var invi = $scope.cntx.data.get('inviList')[i];
		$scope.cntx.form.get('iden').data = invi.iden;
		var srv1 = comc.request('invi/acep', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('inviList')[i] = $scope.cntx.data.get('invi');
		});
	}
	
	//*************************************************************************************************************//
	// Captura del evento de rechazo de invitación.                                                                //
	//*************************************************************************************************************//
	$scope.fnInviRech = function(i) {
		var invi = $scope.cntx.data.get('inviList')[i];
		$scope.cntx.form.get('iden').data = invi.iden;
		var srv1 = comc.request('invi/rech', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('inviList')[i] = $scope.cntx.data.get('invi');
		});
	}
	
	//*************************************************************************************************************//
	// Captura del evento de cambio de estado de invitación.                                                       //
	//*************************************************************************************************************//
	$scope.fnEstaChng = function() {
		var srv1 = comc.request('invi/list', $scope.cntx);
		srv.stResp(true, srv1);
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
	$scope.cntx = srv.getCntx('invi/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}

});