//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('coesFormCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv2 = comc.requestLiteList('COESTIPO', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1, srv2)]).then(function() {
			view();
		});
	}

	//*************************************************************************************************************//
	// Carga de vista, en transición de retorno                                                                    //
	//*************************************************************************************************************//
	function loadViewBack() {
		var view = srv.getDestView();
		
		if (view === 'trad/list') {
			var trad = $scope.cntx.xchg.get('trad');
			
			if (trad === undefined) {
				
			} else {
				$scope.cntx.form.get('trad').data = trad.iden;
				$scope.cntx.form.get('tradText').data = trad.nomb;
			}
		}
		
		if (view === 'cate/list') {
			var cate = $scope.cntx.xchg.get('cate');
			var conc = $scope.cntx.xchg.get('conc');
			
			if (cate === undefined || conc === undefined) {
				
			} else {
				$scope.cntx.form.get('cate').data = cate.iden;
				$scope.cntx.form.get('cateText').data = cate.desc;
				$scope.cntx.form.get('conc').data = conc.iden;
				$scope.cntx.form.get('concText').data = conc.desc;
			}
		}
		ctxl.clearXchg($scope.cntx);
	}
	
	//*************************************************************************************************************//
	// Carga de vista, en transición con datos                                                                     //
	//*************************************************************************************************************//
	function loadViewGo() {
		var view = srv.getOrigView();
		
		$scope.cntx.conf.set('mode', 'N');

		if (view === 'coes/list') {
			var coes = $scope.cntx.xchg.get('coes');

			if (coes === undefined) {
				$scope.cntx.conf.set('mode', 'N');
			} else {
				$scope.cntx.conf.set('mode', 'E');
				$scope.cntx.form.get('iden').data = coes.iden;
				$scope.cntx.form.get('coes').data = coes.iden;
				$scope.cntx.form.get('desc').data = coes.desc;
				$scope.cntx.form.get('tipo').data = coes.tipo;
				$scope.cntx.form.get('favo').data = coes.favo;
				$scope.cntx.form.get('cate').data = coes.cate;
				$scope.cntx.form.get('conc').data = coes.conc;
				
				var srv1 = comc.request('cate/get', $scope.cntx);
				var srv2 = comc.request('conc/get', $scope.cntx);
				$q.all([srv.stResp(true, srv1, srv2)]).then(function() {
					$scope.cntx.form.get('cateText').data = $scope.cntx.data.get('cate').desc;
					$scope.cntx.form.get('concText').data = $scope.cntx.data.get('conc').desc;
				});	
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'N': Nuevo código específico.                                                                             //
	// - 'E': Edición de código específico.                                                                        //
	//*************************************************************************************************************//
	function view() {
		//Nuevo código específico
		if ($scope.cntx.conf.get('mode') === 'N') {
			ctxl.formField($scope.cntx, 'iden', false, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'favo', true, false);
			//ctxl.formField($scope.cntx, 'trad', false, true);
			//ctxl.formField($scope.cntx, 'tradText', true, true);
			ctxl.formField($scope.cntx, 'cate', false, true);
			ctxl.formField($scope.cntx, 'cateText', true, true);
			ctxl.formField($scope.cntx, 'conc', false, true);
			ctxl.formField($scope.cntx, 'concText', true, true);
		//Edición de código específico
		} else if ($scope.cntx.conf.get('mode') === 'E') {
			ctxl.formField($scope.cntx, 'iden', true, true);
			ctxl.formField($scope.cntx, 'desc', true, false);
			ctxl.formField($scope.cntx, 'tipo', true, false);
			ctxl.formField($scope.cntx, 'favo', true, false);
			//ctxl.formField($scope.cntx, 'trad', false, true);
			//ctxl.formField($scope.cntx, 'tradText', true, true);
			ctxl.formField($scope.cntx, 'cate', false, true);
			ctxl.formField($scope.cntx, 'cateText', true, true);
			ctxl.formField($scope.cntx, 'conc', false, true);
			ctxl.formField($scope.cntx, 'concText', true, true);
		}
	}

	//*************************************************************************************************************//
	// Función de inicialización del formulario de la vista                                                        //
	//*************************************************************************************************************//
	function inicForm() {
		$scope.cntx.form.get('iden').data = 0;
		$scope.cntx.form.get('desc').data = '';
		$scope.cntx.form.get('tipo').data = '';
		$scope.cntx.form.get('favo').data = 'N';
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
		var srv1 = comc.request('coes/form', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			$scope.cntx.xchg.set('coes', $scope.cntx.data.get('coes'));
			srv.back(true, $scope.cntx.xchg);
		});
	};

	//*************************************************************************************************************//
	// Captura del evento de seleccionar traducción.                                                               //
	//*************************************************************************************************************//
	$scope.fnTrad = function() {
		var cntx = srv.getCntx('trad/list');
				
		srv.go('coes/form', $scope.cntx, 'trad/list', cntx);
	};

	//*************************************************************************************************************//
	// Captura del evento de seleccionar categoría.                                                                //
	//*************************************************************************************************************//
	$scope.fnCate = function() {
		var cntx = srv.getCntx('cate/list');
				
		srv.go('coes/form', $scope.cntx, 'cate/list', cntx);
	};
	
	//*************************************************************************************************************//
	// Captura del evento de crear contabilidad.                                                                   //
	//*************************************************************************************************************//
	$scope.fnNuev = function() {
		var cntx = srv.getCntx('cont/form');
		
		var coes = $scope.cntx.form.get('iden').data;
		var trad = $scope.cntx.form.get('trad').data;
		
		cntx.xchg.set('coes', coes);
		cntx.xchg.set('trad', trad);

		srv.go('coes/form', $scope.cntx, 'cont/form', cntx);
	};

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	$scope.cntx = srv.getCntx('coes/form');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}	
});