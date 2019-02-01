//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('hconFormCtrl', function($scope, $q, srv, comc, ctxl, form) {

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
		var srv1 = comc.request('cate/list', $scope.cntx);
		var srv2 = comc.request('cuen/list', $scope.cntx);
		var srv3 = comc.requestLiteList('HCONMDTIPO', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2, srv3)]).then(function() {
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
		
		if (view === 'hcon/list') {
			var hcon = $scope.cntx.xchg.get('hcon');

			if (hcon === undefined) {
				$scope.cntx.conf.set('mode', 'N');
			} else {
				$scope.cntx.conf.set('mode', 'E');

				$scope.cntx.form.get('iden').data = hcon.iden;
				$scope.cntx.form.get('cate').data = hcon.cate;
				$scope.cntx.form.get('conc').data = hcon.conc;
				$scope.cntx.form.get('cuen').data = hcon.cuen;
				$scope.cntx.form.get('impo').data = hcon.impo;
				$scope.cntx.form.get('feva').data = form.toDate(hcon.feva);
				$scope.cntx.form.get('desc').data = hcon.desc;
				
				$scope.fnConcChng();
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'N': Nuevo apunte.                                                                                        //
	// - 'E': Edición de apunte.                                                                                   //
	//*************************************************************************************************************//
	function view() {
		//Nuevo apunte
		if ($scope.cntx.conf.get('mode') === 'N') {
			ctxl.formField($scope.cntx, 'tipo', false, true);
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'cate', true, false);
			ctxl.formField($scope.cntx, 'conc', true, false);
			ctxl.formField($scope.cntx, 'cuen', true, false);
			ctxl.formField($scope.cntx, 'impo', true, false);
			ctxl.formField($scope.cntx, 'feva', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);

			if ($scope.cntx.data.get('pres') !== undefined &&
				$scope.cntx.data.get('pres') !== null) {
				ctxl.formSection($scope.cntx, 'stPres', true);
			} else {
				ctxl.formSection($scope.cntx, 'stPres', false);
			}
		//Edición de apunte
		} else if ($scope.cntx.conf.get('mode') === 'E') {
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'cate', true, true);
			ctxl.formField($scope.cntx, 'conc', true, true);
			ctxl.formField($scope.cntx, 'cuen', true, true);
			ctxl.formField($scope.cntx, 'impo', true, true);
			ctxl.formField($scope.cntx, 'feva', true, true);
			ctxl.formField($scope.cntx, 'desc', true, true);
			ctxl.formSection($scope.cntx, 'stPres', false);
			
			if ($scope.cntx.form.get('tipo').data === 'MD01') {
				//Cambio de fecha
				ctxl.formField($scope.cntx, 'feva', true, false);
			} else if ($scope.cntx.form.get('tipo').data === 'MD02') {
				//Cambio de descripción
			} else if ($scope.cntx.form.get('tipo').data === 'MD03') {
				//Cambio de importe
			} else if ($scope.cntx.form.get('tipo').data === 'MD04') {
				//Cambio de categoría
			}
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('tipo').data = '';
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('cate').data = 0;
		$scope.cntx.form.get('conc').data = 0;
		$scope.cntx.form.get('cuen').data = 0;
		$scope.cntx.form.get('impo').data = 0;
		$scope.cntx.form.get('feva').data = new Date();
		$scope.cntx.form.get('desc').data = '';
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
	// Captura del evento de cambio de categoria.                                                                  //
	//*************************************************************************************************************//
	$scope.fnCateChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.cntx.form.get('conc').data = 0;
			presCalc();
		});
	};

	//*************************************************************************************************************//
	// Captura del evento de cambio de concepto.                                                                   //
	//*************************************************************************************************************//
	$scope.fnConcChng = function() {
		presCalc();
	};

	//*************************************************************************************************************//
	// Captura del evento de cambio de fecha valor.                                                                //
	//*************************************************************************************************************//
	$scope.fnFevaChng = function() {
		presCalc();
	};
	
	//*************************************************************************************************************//
	// Captura del evento de cambio de tipo de modificación.                                                       //
	//*************************************************************************************************************//
	$scope.fnTipoChng = function() {
		view();
	};

	//*************************************************************************************************************//
	// Captura del evento de envío de formulario de apunte.                                                        //
	//*************************************************************************************************************//
	$scope.fnForm = function() {
		var srv1 = comc.request('hcon/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			inicForm();
			srv.back(true, null);
		});
	};

	//Consulta de datos del presupuesto que correspondería al apunte
	function presCalc() {
		//Consultamos si tenemos fecha, categoría y concepto
		if ($scope.cntx.form.get('feva').data !== 0 &&
			$scope.cntx.form.get('cate').data !== 0 &&
			$scope.cntx.form.get('conc').data !== 0) {
			var srv1 = comc.request('pres/calc', $scope.cntx);
			
			$q.all([srv.stResp(true, srv1)]).then(function() {
				view();
			});
		} else {
			if ($scope.cntx.data.get('pres') !== undefined &&
				$scope.cntx.data.get('pres') !== null) {
				$scope.cntx.data.set('pres', null);
			}
			view();
		}
	}
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	$scope.cntx = srv.getCntx('hcon/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});
