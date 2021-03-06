//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('notfListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		
		var srv1 = comc.requestLiteList('NOTFAPLI', $scope.cntx);
		var srv2 = comc.requestLiteList('NOTFTIPO', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2)]).then(function(){
			$scope.cntx.form.get('apli').data = $scope.cntx.data.get('ltLNotfapli')[0].clav;
			
			var srv3 = comc.request('notf/list', $scope.cntx);
			$q.all([srv.stResp(true, srv3)]).then(function() {
				view();
			})
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();

		//Si venimos del formulario de notificacion y tenemos una notificacion en el área de intercambio, lo cargamos en la lista
		if (view === 'notf/form') {
			var notf = $scope.cntx.xchg.get('notf');
			if (notf !== 'undefined' && notf !== null) {
				var indx = $scope.cntx.xchg.get('indx')
				$scope.cntx.data.get('notfList')[indx] = notf;
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
	// - 'L': Lista de notificaciones.                                                                             //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'apli', true, false);
		} else {
			ctxl.formField($scope.cntx, 'apli', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('apli').data = '';
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de cambio en la aplicación.                                                              //
	//*************************************************************************************************************//
	$scope.fnApliChng = function() {
		var srv1 = comc.request('notf/list', $scope.cntx);
		
		srv.stResp(true, srv1);
	}

	//*************************************************************************************************************//
	// Captura del evento de modificación de notificación.                                                         //
	//*************************************************************************************************************//
	$scope.fnNotfModi = function(i) {
		var cntx = srv.getCntx('notf/form');
		
		var notf = $scope.cntx.data.get('notfList')[i];
		cntx.xchg.set('notf', notf);
		cntx.xchg.set('indx', i);
		
		srv.go('notf/list', $scope.cntx, 'notf/form', cntx);
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
	$scope.cntx = srv.getCntx('notf/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});