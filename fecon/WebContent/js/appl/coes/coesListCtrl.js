app.controller('coesListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('coes/list', $scope.cntx);

		$q.all([srv.stResp(true, srv1)]).then(function() {
			view();
		});	
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();
		
		//Si venimos del formulario de conceptos y tenemos un concepto en el área de intercambio, lo cargamos en la lista
		if (view === 'coes/form') {
			var coes = $scope.cntx.xchg.get('coes');

			if (coes !== undefined) {
				var indx = $scope.cntx.xchg.get('indx');
				if (indx >= 0) {
					$scope.cntx.data.get('coesList')[indx] = coes;
				} else {
					$scope.cntx.data.get('coesList').push(coes);
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
		
		if (view === 'hcon/form') {
			$scope.cntx.conf.set('mode', 'S');
		} else {
			$scope.cntx.conf.set('mode', 'L');
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de códigos específicos.                                                                        //
	// - 'S': Selección de códigos específicos.                                                                    //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'desc', true, false);
		} else if ($scope.cntx.conf.get('mode') === 'S') {
			ctxl.formField($scope.cntx, 'desc', true, false);
		} else {
			ctxl.formField($scope.cntx, 'desc', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('desc').data = '';
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
	// Captura del evento de crear nuevo código específico.                                                        //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('coes/form');
		
		cntx.xchg.set('indx', -1);
		
		srv.go('coes/list', $scope.cntx, 'coes/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de edición de código específico.                                                         //
	//*************************************************************************************************************//
	$scope.fnEdit = function(i) {
		var cntx = srv.getCntx('coes/form');
		
		var coes = $scope.cntx.data.get('coesList')[i];
		cntx.xchg.set('coes', coes);
		cntx.xchg.set('indx', i);
		
		srv.go('coes/list', $scope.cntx, 'coes/form', cntx);
	}
		
	//*************************************************************************************************************//
	// Despliegue de menú de acciones.                                                                             //
	//*************************************************************************************************************//
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};

	//*************************************************************************************************************//
	// Click en registro de una lista.                                                                             //
	//*************************************************************************************************************//
	$scope.clic = function(i) {
		if ($scope.cntx.conf.get('mode') === 'S') {
			
		} else {
			if ($scope.cntx.conf.get('idx1') === i) {
				$scope.cntx.conf.set('idx1', -1);
			} else {
				$scope.cntx.conf.set('idx1', i);
			}
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
	$scope.cntx = srv.getCntx('coes/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});