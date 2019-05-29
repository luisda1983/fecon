//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('concFormCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.requestLiteList('CONCTIPO', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
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
		
		$scope.cntx.conf.set('mode', 'N');
		
		if (view === 'conc/list') {
			var conc = $scope.cntx.xchg.get('conc');

			if (conc === undefined) {
				$scope.cntx.conf.set('mode', 'N');
				
				var cate = $scope.cntx.xchg.get('cate');
				$scope.cntx.data.set('cate', cate);
				
				$scope.cntx.form.get('cate').data = cate.iden;
			} else {
				$scope.cntx.conf.set('mode', 'E');
				
				var cate = $scope.cntx.xchg.get('cate');
				$scope.cntx.data.set('cate', cate);
				
				$scope.cntx.form.get('iden').data = conc.iden;
				$scope.cntx.form.get('cate').data = conc.cate;
				$scope.cntx.form.get('tipo').data = conc.tipo;
				$scope.cntx.form.get('desl').data = conc.desl;
				$scope.cntx.form.get('desc').data = conc.desc;
				$scope.cntx.form.get('orde').data = conc.orde;
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'N': Nuevo concepto.                                                                                      //
	// - 'E': Edición de concepto.                                                                                 //
	//*************************************************************************************************************//
	function view() {
		//Nuevo concepto
		if ($scope.cntx.conf.get('mode') === 'N') {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'cate', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'desl', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'orde', false, true);
		//Edición de concepto
		} else if ($scope.cntx.conf.get('mode') === 'E') {
			ctxl.formField($scope.cntx, 'iden', true, true);
			ctxl.formField($scope.cntx, 'cate', false, true);
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'desl', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'orde', true, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('iden').data = 0;
		//$scope.cntx.form.get('cate').data = 0; -- No se inicializa la categoría pues viene impuesta por flujo
		$scope.cntx.form.get('tipo').data = '';
		$scope.cntx.form.get('desl').data = '';
		$scope.cntx.form.get('desc').data = '';
		$scope.cntx.form.get('orde').data = 0;
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//***************************************                                **************************************//
	//***************************************  FUNCIONES DE MANEJO DE VISTA  **************************************//
	//***************************************                                **************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		srv.back(true, null);
	}

	//*************************************************************************************************************//
	// Captura del evento de envío de formulario de concepto.                                                      //
	//*************************************************************************************************************//
	$scope.fnForm = function() {
		var srv1 = comc.request('conc/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.cntx.xchg.set('conc', $scope.cntx.data.get('conc'));
			srv.back(true, $scope.cntx.xchg);
		});
	};

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	$scope.cntx = srv.getCntx('conc/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}	
});