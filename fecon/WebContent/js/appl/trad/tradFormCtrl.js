//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('tradFormCtrl', function($scope, $q, srv, comc, ctxl) {

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

		var srv1 = comc.requestLiteList('BOOL', $scope.cntx);
		var srv2 = comc.requestLiteList('TRADTIPO', $scope.cntx);
		var srv3 = comc.request('domi/list', $scope.cntx);
		
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

		if (view === 'trad/list') {
			var trad = $scope.cntx.xchg.get('trad');

			if (trad === undefined) {
				$scope.cntx.conf.set('mode', 'N');
			} else {
				$scope.cntx.conf.set('mode', 'E');
				$scope.cntx.form.get('iden').data = trad.iden;
				$scope.cntx.form.get('nomb').data = trad.nomb;
				$scope.cntx.form.get('tip1').data = trad.tip1;
				$scope.cntx.form.get('dom1').data = trad.dom1;
				$scope.cntx.form.get('ide1').data = trad.ide1;
				$scope.cntx.form.get('obl1').data = trad.obl1;
				$scope.cntx.form.get('tip2').data = trad.tip2;
				$scope.cntx.form.get('dom2').data = trad.dom2;
				$scope.cntx.form.get('ide2').data = trad.ide2;
				$scope.cntx.form.get('obl2').data = trad.obl2;
				$scope.cntx.form.get('tip3').data = trad.tip3;
				$scope.cntx.form.get('dom3').data = trad.dom3;
				$scope.cntx.form.get('ide3').data = trad.ide3;
				$scope.cntx.form.get('obl3').data = trad.obl3;
				$scope.cntx.form.get('desc').data = trad.desc;
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'N': Nueva traducción.                                                                                    //
	// - 'E': Edición de traducción.                                                                               //
	//*************************************************************************************************************//
	function view() {
		//Nueva traducción
		if ($scope.cntx.conf.get('mode') === 'N') {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'nomb', true, false);
			ctxl.formField($scope.cntx, 'tip1', true, false);
			ctxl.formField($scope.cntx, 'dom1', true, false);
			ctxl.formField($scope.cntx, 'ide1', true, false);
			ctxl.formField($scope.cntx, 'obl1', true, false);
			ctxl.formField($scope.cntx, 'tip2', true, false);
			ctxl.formField($scope.cntx, 'dom2', true, false);
			ctxl.formField($scope.cntx, 'ide2', true, false);
			ctxl.formField($scope.cntx, 'obl2', true, false);
			ctxl.formField($scope.cntx, 'tip3', true, false);
			ctxl.formField($scope.cntx, 'dom3', true, false);
			ctxl.formField($scope.cntx, 'ide3', true, false);
			ctxl.formField($scope.cntx, 'obl3', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);
		//Edición de traducción
		} else if ($scope.cntx.conf.get('mode') === 'E') {
			ctxl.formField($scope.cntx, 'iden', true, true);
			ctxl.formField($scope.cntx, 'nomb', true, false);
			ctxl.formField($scope.cntx, 'tip1', true, false);
			ctxl.formField($scope.cntx, 'dom1', true, false);
			ctxl.formField($scope.cntx, 'ide1', true, false);
			ctxl.formField($scope.cntx, 'obl1', true, false);
			ctxl.formField($scope.cntx, 'tip2', true, false);
			ctxl.formField($scope.cntx, 'dom2', true, false);
			ctxl.formField($scope.cntx, 'ide2', true, false);
			ctxl.formField($scope.cntx, 'obl2', true, false);
			ctxl.formField($scope.cntx, 'tip3', true, false);
			ctxl.formField($scope.cntx, 'dom3', true, false);
			ctxl.formField($scope.cntx, 'ide3', true, false);
			ctxl.formField($scope.cntx, 'obl3', true, false);
			ctxl.formField($scope.cntx, 'desc', true, false);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('nomb').data = '';
		$scope.cntx.form.get('tip1').data = '';
		$scope.cntx.form.get('dom1').data = 0;
		$scope.cntx.form.get('ide1').data = '';
		$scope.cntx.form.get('obl1').data = '';
		$scope.cntx.form.get('tip2').data = '';
		$scope.cntx.form.get('dom2').data = 0;
		$scope.cntx.form.get('ide2').data = '';
		$scope.cntx.form.get('obl2').data = '';
		$scope.cntx.form.get('tip3').data = '';
		$scope.cntx.form.get('dom3').data = 0;
		$scope.cntx.form.get('ide3').data = '';
		$scope.cntx.form.get('obl3').data = '';
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
	// Captura del evento de envío de formulario de categoría.                                                     //
	//*************************************************************************************************************//
	$scope.fnForm = function() {
		var srv1 = comc.request('trad/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.cntx.xchg.set('trad', $scope.cntx.data.get('trad'));
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
	
	$scope.cntx = srv.getCntx('trad/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}	
});