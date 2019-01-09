//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('ctmnFormCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.requestLiteList('MENUPERF', $scope.cntx);
		var srv2 = comc.requestLiteList('BOOL'    , $scope.cntx);
		
		$q.all([srv.stResp(false, srv1)]).then(function(){
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
		
		$scope.cntx.conf.set('mode', 'M');
		
		if (view === 'ctmn/list') {
			var ctmn = $scope.cntx.xchg.get('ctmn');
			
			$scope.cntx.form.get('iden').data = ctmn.iden;
			$scope.cntx.form.get('perf').data = ctmn.perf;
			$scope.cntx.form.get('desc').data = ctmn.desc;
			$scope.cntx.form.get('acti').data = ctmn.acti;
			$scope.cntx.form.get('orde').data = ctmn.orde;
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'M': Modificación.                                                                                        //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'M') {
			ctxl.formField($scope.cntx, 'iden', true, true);
			ctxl.formField($scope.cntx, 'perf', true, true);
			ctxl.formField($scope.cntx, 'acti', true, true);
			ctxl.formField($scope.cntx, 'orde', true, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
		} else {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'perf', false, true);
			ctxl.formField($scope.cntx, 'acti', false, true);
			ctxl.formField($scope.cntx, 'orde', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('perf').data = '';
		$scope.cntx.form.get('acti').data = '';
		$scope.cntx.form.get('orde').data = 0;
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
	// Captura del evento de envío de formulario de categoría de menú.                                             //
	//*************************************************************************************************************//
	$scope.fnForm = function(i) {
		var srv1 = comc.request('ctmn/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.xchg.set('ctmn', $scope.cntx.data.get('ctmn'));
			srv.back(true, $scope.cntx.xchg);
		});
	}

	//*************************************************************************************************************//
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function(i) {
		srv.back(true, null);
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	//Generamos el contexto de la vista
	$scope.cntx = srv.getCntx('ctmn/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});