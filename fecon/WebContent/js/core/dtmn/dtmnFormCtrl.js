app.controller('dtmnFormCtrl', function($scope, $q, srv, comc, ctxl) {

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

		var srv1 = comc.requestLiteList('BOOL'    , $scope.cntx);
		
		$q.all([srv.stResp(false, srv1)]).then(function(){
			$scope.cntx.conf.set('mode', 'M');
			view();
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
		
		if (view === 'dtmn/list') {
			var dtmn = $scope.cntx.xchg.get('dtmn');
			$scope.cntx.form.get('ctmn').data = dtmn.ctmn;
			$scope.cntx.form.get('iden').data = dtmn.iden;
			$scope.cntx.form.get('desc').data = dtmn.desc;
			$scope.cntx.form.get('acti').data = dtmn.acti;
			$scope.cntx.form.get('orde').data = dtmn.orde;
			$scope.cntx.form.get('path').data = dtmn.path;
			$scope.cntx.form.get('icon').data = dtmn.icon;
			
			$scope.cntx.data.set('ctmnList', $scope.cntx.xchg.get('ctmnList'));
		}
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'M': Modificación.                                                                                        //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'M') {
			ctxl.formField($scope.cntx, 'ctmn', true, true);
			ctxl.formField($scope.cntx, 'iden', true, true);
			ctxl.formField($scope.cntx, 'acti', true, true);
			ctxl.formField($scope.cntx, 'orde', true, true);
			ctxl.formField($scope.cntx, 'path', true, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'icon', true, false);
		} else {
			ctxl.formField($scope.cntx, 'ctmn', false, true);
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'acti', false, true);
			ctxl.formField($scope.cntx, 'orde', false, true);
			ctxl.formField($scope.cntx, 'path', false, true);
			ctxl.formField($scope.cntx, 'desc', false, true);
			ctxl.formField($scope.cntx, 'icon', false, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('ctmn').data = 0;
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('acti').data = '';
		$scope.cntx.form.get('orde').data = 0;
		$scope.cntx.form.get('desc').data = '';
		$scope.cntx.form.get('path').data = '';
		$scope.cntx.form.get('icon').data = '';
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
		var srv1 = comc.request('dtmn/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.xchg.set('dtmn', $scope.cntx.data.get('dtmn'));
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
	$scope.cntx = srv.getCntx('dtmn/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}

});