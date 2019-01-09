//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('cuenCuadCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.request('cuen/list', $scope.cntx);

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

		$scope.cntx.conf.set('mode', 'C');
		
		if (view === 'cuen/list') {
			var cuen = $scope.cntx.xchg.get('cuen');

			if (cuen !== undefined) {
				$scope.cntx.conf.set('mode', 'C');				
				$scope.cntx.form.get('cuen').data = cuen.iden;
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'C': Cuadre de cuenta.                                                                                    //
	// - 'A': Apunte de cuadre.                                                                                    //
	//*************************************************************************************************************//
	function view() {
		//Cuadre de cuenta
		if ($scope.cntx.conf.get('mode') === 'C') {
			ctxl.formField($scope.cntx, 'cuen', true, false);
			ctxl.formField($scope.cntx, 'sald', true, false);
			ctxl.formField($scope.cntx, 'impo', true, true);
			ctxl.formField($scope.cntx, 'cate', false, false);
			ctxl.formField($scope.cntx, 'conc', false, false);
			ctxl.formBtn($scope.cntx, 'btCuad', true);
			ctxl.formSection($scope.cntx, 'stApun', false);
		//Apunte de cuadre
		} else if ($scope.cntx.conf.get('mode') === 'A') {
			ctxl.formField($scope.cntx, 'cuen', true, true);
			ctxl.formField($scope.cntx, 'sald', true, true);
			ctxl.formField($scope.cntx, 'impo', true, true);
			ctxl.formField($scope.cntx, 'cate', true, false);
			ctxl.formField($scope.cntx, 'conc', true, false);
			ctxl.formBtn($scope.cntx, 'btCuad', false);
			ctxl.formSection($scope.cntx, 'stApun', true);
		}
	}
	
	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('cuen').data = 0;
		$scope.cntx.form.get('sald').data = 0;
		$scope.cntx.form.get('impo').data = 0;
		$scope.cntx.form.get('cate').data = 0;
		$scope.cntx.form.get('conc').data = 0;
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
	// Captura del evento de selección de cuenta.                                                                  //
	//*************************************************************************************************************//
	$scope.fnCuenSelc = function() {
		$scope.cntx.form.get('impo').data = parseFloat(0);
		$scope.fnImpoRfsh();
	};
	
	//*************************************************************************************************************//
	// Captura del evento de selección de categoria.                                                               //
	//*************************************************************************************************************//
	$scope.fnCateChng = function() {
		var srv1 = comc.request('conc/list', $scope.cntx);
		
		srv.stResp(true, srv1);
	}

	//*************************************************************************************************************//
	// Captura del evento de cuadre y realiza la transición a la vista de apunte.                                  //
	//*************************************************************************************************************//
	$scope.fnCuad = function() {
		if ($scope.cntx.form.get('impo').data !== 0) {
			var srv1 = comc.request('cate/list', $scope.cntx);
			
			$q.all([srv.stResp(true, srv1)]).then(function() {
				$scope.cntx.conf.set('mode', 'A');
				view();
			})
		}
	}

	//*************************************************************************************************************//
	// Captura del evento de volver del modo apunte de cuadre.                                                     //
	//*************************************************************************************************************//
	$scope.fnBack = function() {
		$scope.cntx.form.get('cate').data = 0;
		$scope.cntx.form.get('conc').data = 0;
		
		$scope.cntx.conf.set('mode', 'C');
		
		view();
	}

	//*************************************************************************************************************//
	// Captura del evento de envío de formulario de cuadre de cuenta.                                              //
	//*************************************************************************************************************//
	$scope.fnApun = function() {
		var srv1 = comc.request('cuen/cuad', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			var srv2 = comc.request('cuen/list', $scope.cntx);
			
			$q.all([srv.stResp(true, srv2)]).then(function() {
				inicForm();
				$scope.cntx.conf.set('mode', 'C');
				view();
			})
		})
	}

	//*************************************************************************************************************//
	// Funcion de calculo en tiempo real del importe de cuadre.                                                    //
	//*************************************************************************************************************//
	$scope.fnImpoRfsh = function() {
		if (!isNaN(parseFloat($scope.cntx.form.get('sald').data))) {
			var sald = $scope.cntx.form.get('sald').data;
			sald = sald.toString().replace(',', '.');
			$scope.cntx.form.get('sald').data = parseFloat(sald);
		} 
		
		if (isNaN(parseFloat(sald))) {
			$scope.cntx.form.get('impo').data = parseFloat((0 - parseFloat($scope.cntx.form.get('cuen').data.sald)).toFixed(2));
		} else {
			$scope.cntx.form.get('impo').data = parseFloat((parseFloat($scope.cntx.form.get('sald').data) - parseFloat($scope.cntx.form.get('cuen').data.sald)).toFixed(2));
		}
	};
	
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	$scope.cntx = srv.getCntx('cuen/cuad');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});