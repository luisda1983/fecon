//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('concListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('conc/list', $scope.cntx);

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
		if (view === 'conc/form') {
			var conc = $scope.cntx.xchg.get('conc');

			if (conc !== undefined) {
				var indx = $scope.cntx.xchg.get('indx');
				if (indx >= 0) {
					$scope.cntx.data.get('concList')[indx] = conc;
				} else {
					$scope.cntx.data.get('concList').push(conc);
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
		
		if (view === 'cate/list') {
			var cate = $scope.cntx.xchg.get('cate');
			$scope.cntx.data.set('cate', cate);
			$scope.cntx.form.get('cate').data = cate.iden;
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de conceptos.                                                                                  //
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
	//*********************************                                           *********************************//
	//*********************************  FUNCIONES AUXILIARES DE MANEJO DE VISTA  *********************************//
	//*********************************               (LISTAS)                    *********************************//
	//*********************************                                           *********************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de crear nuevo concepto.                                                                 //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('conc/form');
		
		cntx.xchg.set('cate', $scope.cntx.data.get('cate'));
		cntx.xchg.set('indx', -1);
		
		srv.go('conc/list', $scope.cntx, 'conc/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de edición de concepto.                                                                  //
	//*************************************************************************************************************//
	$scope.fnEdit = function(i) {
		var cntx = srv.getCntx('conc/form');
		
		var conc = $scope.cntx.data.get('concList')[i];
		cntx.xchg.set('cate', $scope.cntx.data.get('cate'));
		cntx.xchg.set('conc', conc);
		cntx.xchg.set('indx', i);
		
		srv.go('conc/list', $scope.cntx, 'conc/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		srv.back(true, null);
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
	$scope.cntx = srv.getCntx('conc/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});