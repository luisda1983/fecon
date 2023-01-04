app.controller('tradListCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('trad/list', $scope.cntx);

		$q.all([srv.stResp(true, srv1)]).then(function() {
			view();
		});	
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();
		
		//Si venimos del formulario de categorías y tenemos una categoría en el área de intercambio, lo cargamos en la lista
		if (view === 'trad/form') {
			var trad = $scope.cntx.xchg.get('trad');

			if (trad !== undefined) {
				var indx = $scope.cntx.xchg.get('indx');
				if (indx >= 0) {
					$scope.cntx.data.get('tradList')[indx] = trad;
				} else {
					$scope.cntx.data.get('tradList').push(trad);
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
		
		if (view === 'coes/form') {
			$scope.cntx.conf.set('mode', 'S');
		} else {
			$scope.cntx.conf.set('mode', 'L');
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista de traducciones.                                                                               //
	// - 'S': Selección de traducciones.                                                                           //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {

		} else if ($scope.cntx.conf.get('mode') === 'S') {

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
	// Captura del evento de crear nueva traducción.                                                               //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('trad/form');
		
		cntx.xchg.set('indx', -1);
		
		srv.go('trad/list', $scope.cntx, 'trad/form', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de edición de traducción.                                                                //
	//*************************************************************************************************************//
	$scope.fnEdit = function(i) {
		var cntx = srv.getCntx('trad/form');
		
		var trad = $scope.cntx.data.get('tradList')[i];
		cntx.xchg.set('trad', trad);
		cntx.xchg.set('indx', i);
		
		srv.go('trad/list', $scope.cntx, 'trad/form', cntx);
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
			$scope.cntx.xchg.set('trad', $scope.cntx.data.get('tradList')[i]);
			srv.back(true, $scope.cntx.xchg);
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
	$scope.cntx = srv.getCntx('trad/list');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});