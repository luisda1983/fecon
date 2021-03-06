app.controller('domiListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('domi/list', $scope.cntx);

		$q.all([srv.stResp(true, srv1)]).then(function(){
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();

		//Si venimos del formulario de dominios y tenemos un dominio en el área de intercambio, lo cargamos en la lista
		if (view === 'domi/form') {

			var domi = $scope.cntx.xchg.get('domi');
			
			if (domi !== undefined && domi !== null) {
				var indx = $scope.cntx.xchg.get('indx')
				if (indx >= 0) {
					$scope.cntx.data.get('domiList')[indx] = domi;
				} else {
					$scope.cntx.data.get('domiList').push(domi);
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
		
		$scope.cntx.conf.set('mode', 'L');
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de dominios.                                                                                   //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			
		} else {
			
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de crear nuevo dominio.                                                                  //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('domi/form');
		
		cntx.xchg.set('indx', -1);
		
		srv.go('domi/list', $scope.cntx, 'domi/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de consultar detalle de dominio.                                                         //
	//*************************************************************************************************************//
	$scope.fnDomiDele = function(i) {
		var cntx = srv.getCntx('dele/list');
		
		cntx.xchg.set('domi', $scope.cntx.data.get('domiList')[i]);
		
		srv.go('domi/list', $scope.cntx, 'dele/list', cntx);
	}
	
	//*************************************************************************************************************//
	// Captura del evento de modificación de dominio.                                                              //
	//*************************************************************************************************************//
	$scope.fnDomiModi = function(i) {
		var cntx = srv.getCntx('domi/form');
		
		var domi = $scope.cntx.data.get('domiList')[i];
		cntx.xchg.set('domi', domi);
		cntx.xchg.set('indx', i);
		
		srv.go('domi/list', $scope.cntx, 'domi/form', cntx);
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
	$scope.cntx = srv.getCntx('domi/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});