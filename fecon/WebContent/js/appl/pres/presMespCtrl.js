//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('presMespCtrl', function($scope, $q, srv, comc, ctxl) {

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
		var srv1 = comc.requestLiteList('ANUALIDAD', $scope.cntx);
		var srv2 = comc.requestLiteList('MES', $scope.cntx); 
		var srv3 = comc.requestLiteList('PRESESTA', $scope.cntx);
		var srv4 = comc.request('cate/list', $scope.cntx);
		var srv5 = comc.request('conc/full', $scope.cntx);

		$q.all([srv.stResp(false, srv1, srv2, srv3, srv4, srv5)]).then(function() {
			var srv6 = comc.requestParaGet('I', 'PERIPRESUP', '', $scope.cntx);
			
			$q.all([srv.stResp(false, srv6)]).then(function() {
				if ($scope.cntx.data.get('prPeripresup').pval.anac !== 'undefined' &&
					$scope.cntx.data.get('prPeripresup').pval.anac > 0 &&
					$scope.cntx.data.get('prPeripresup').pval.msac > 0) {
					if ($scope.cntx.form.get('anua').data === 0 || $scope.cntx.form.get('mesp').data === 0) {
						$scope.cntx.form.get('anua').data = $scope.cntx.data.get('prPeripresup').pval.anac;
						$scope.cntx.form.get('mesp').data = $scope.cntx.data.get('prPeripresup').pval.msac;
					}
				}
				var srv7 = comc.request('pres/mesp', $scope.cntx);
				
				$q.all([srv.stResp(true, srv7)]).then(function() {
					view();
				});	
			});		
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

		$scope.cntx.conf.set('mode', 'L');
		
		if (view === 'pres/anua') {
			var anua = $scope.cntx.xchg.get('anua');
			var mesp = $scope.cntx.xchg.get('mesp');
			
			if (anua !== undefined && mesp !== undefined) {
				$scope.cntx.form.get('anua').data = anua;
				$scope.cntx.form.get('mesp').data = mesp;
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista.                                                                                               //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'anua', false, true);
			ctxl.formField($scope.cntx, 'mesp', false, true);
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
	// Captura del evento de cancelación.                                                                          //
	//*************************************************************************************************************//
	$scope.fnCanc = function() {
		srv.back(true, null);
	}

	//*************************************************************************************************************//
	// Captura del evento de cambio de año/mes.                                                                    //
	//*************************************************************************************************************//
	$scope.fnAnuaMespChng = function() {
		var srv1 = comc.request('pres/mesp', $scope.cntx);
		
		srv.stResp(true, srv1);
	};

	//*************************************************************************************************************//
	// Captura del evento de cambio de estado de partida.                                                          //
	//*************************************************************************************************************//
	$scope.fnEsta = function(cate, i) {
		var pres = $scope.cntx.data.get('presListMap')[cate][i];
		
		$scope.cntx.form.get('cate').data = pres.cate;
		$scope.cntx.form.get('conc').data = pres.conc;
		
		var esta = "";
		if (pres.esta === 'A') {
			esta = 'C';
		} else {
			esta = 'A';
		}
		$scope.cntx.form.get('esta').data = esta;
		
		var srv1 = comc.request('pres/esta', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function() {
			$scope.cntx.data.get('presListMap')[cate][i] = $scope.cntx.data.get('pres');	
		})
	}

	//*************************************************************************************************************//
	// Captura del evento de apuntes de categoría.                                                                 //
	//*************************************************************************************************************//
	$scope.fnApuc = function(i) {
		var cntx = srv.getCntx('hcon/list');
		
		var pres = $scope.cntx.data.get('presCateList')[i];
		cntx.xchg.set('anua', pres.anua);
		cntx.xchg.set('mesp', pres.mesp);
		cntx.xchg.set('cate', pres.cate);
		
		srv.go('pres/mesp', $scope.cntx, 'hcon/list', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de apuntes de concepto.                                                                  //
	//*************************************************************************************************************//
	$scope.fnApun = function(i, j) {
		var cntx = srv.getCntx('hcon/list');
		
		var pres = $scope.cntx.data.get('presListMap')[i][j];
		cntx.xchg.set('anua', pres.anua);
		cntx.xchg.set('mesp', pres.mesp);
		cntx.xchg.set('cate', pres.cate);
		cntx.xchg.set('conc', pres.conc);
		
		srv.go('pres/mesp', $scope.cntx, 'hcon/list', cntx);
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
	$scope.xpnd = function(i, j) {
		if ($scope.cntx.conf.get('idx2') === j) {
			$scope.cntx.conf.set('idx1', -1);
			$scope.cntx.conf.set('idx2', -1);
		} else {
			$scope.cntx.conf.set('idx2', j);
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
	$scope.cntx = srv.getCntx('pres/mesp');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
});