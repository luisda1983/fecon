//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('presAnuaCtrl', function($scope, $q, srv, comc, ctxl) {

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
		
		$q.all([srv.stResp(true, srv1,srv2,srv3,srv4,srv5)]).then(function() {
			var srv6 = comc.requestParaGet('I', 'PERIPRESUP', '', $scope.cntx);
			
			$q.all([srv.stResp(false, srv6)]).then(function() {
				if ($scope.cntx.data.get('prPeripresup').pval.anac !== 'undefined' &&
					$scope.cntx.data.get('prPeripresup').pval.anac > 0) {
					if ($scope.cntx.form.get('anua').data === 0) {
						$scope.cntx.form.get('anua').data = $scope.cntx.data.get('prPeripresup').pval.anac;
					}
				}
				var srv7 = comc.request('pres/anua', $scope.cntx);
				
				$q.all([srv.stResp(true, srv7)]).then(function() {
					view();
					presAnuaChart();
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
		
		if (view === 'pres/resu') {
			var anua = $scope.cntx.xchg.get('anua');
			if (anua !== undefined) {
				$scope.cntx.form.get('anua').data = anua;
				$scope.cntx.conf.set('mode', 'B');
			}
		}
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista.                                                                                               //
	// - 'B': Año bloqueado.                                                                                       //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {
			ctxl.formField($scope.cntx, 'anua', true, false);
		} else if ($scope.cntx.conf.get('mode') === 'B') {
			ctxl.formField($scope.cntx, 'anua', true, true);
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
	// Captura del evento de cambio de año.                                                                        //
	//*************************************************************************************************************//
	$scope.fnAnuaChng = function() {
		if (chart != null) {
			chart.destroy();
		}
		var srv1 = comc.request('pres/anua', $scope.cntx);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			presAnuaChart(); 
		});
	};

	//*************************************************************************************************************//
	// Captura del evento de detalle de mes.                                                                       //
	//*************************************************************************************************************//
	$scope.fnMesp = function(i) {
		var cntx = srv.getCntx('pres/mesp');
		
		var pres = $scope.cntx.data.get('presList')[i];
		cntx.xchg.set('anua', pres.anua);
		cntx.xchg.set('mesp', pres.mesp);
		
		srv.go('pres/anua', $scope.cntx, 'pres/mesp', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de apuntes por mes.                                                                      //
	//*************************************************************************************************************//
	$scope.fnApum = function(i) {
		var cntx = srv.getCntx('hcon/list');
		
		var pres = $scope.cntx.data.get('presList')[i];
		cntx.xchg.set('anua', pres.anua);
		cntx.xchg.set('mesp', pres.mesp);
		
		srv.go('pres/anua', $scope.cntx, 'hcon/list', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de apuntes de partida anual.                                                             //
	//*************************************************************************************************************//
	$scope.fnApua = function(i) {
		var cntx = srv.getCntx('hcon/list');
		
		var pres = $scope.cntx.data.get('presListAnua')[i];
		cntx.xchg.set('anua', pres.anua);
		cntx.xchg.set('cate', pres.cate);
		cntx.xchg.set('conc', pres.conc);
		
		srv.go('pres/anua', $scope.cntx, 'hcon/list', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de cierre de partida anual.                                                              //
	//*************************************************************************************************************//
	$scope.fnEsta = function(i) {
		var pres = $scope.cntx.data.get('presListAnua')[i];
		
		$scope.cntx.form.get('anua').data = pres.anua;
		$scope.cntx.form.get('mesp').data = pres.mesp;
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
			$scope.cntx.data.get('presListAnua')[i] = $scope.cntx.data.get('pres');	
		})
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
		//Lista de meses
		if (j === 1) {
			if ($scope.cntx.conf.get('idx1') === i) {
				$scope.cntx.conf.set('idx1', -1);
				$scope.cntx.conf.set('idx2', -1);
			} else {
				$scope.cntx.conf.set('idx1', i);
				$scope.cntx.conf.set('idx2', -1);
			}
		//Lista de partidas anuales
		} else if (j === 2) {
			if ($scope.cntx.conf.get('idx2') === i) {
				$scope.cntx.conf.set('idx1', -1);
				$scope.cntx.conf.set('idx2', -1);
			} else {
				$scope.cntx.conf.set('idx1', -1);
				$scope.cntx.conf.set('idx2', i);
			}
		} else {
			$scope.cntx.conf.set('idx1', -1);
			$scope.cntx.conf.set('idx2', -1);
		}
	}

	//*************************************************************************************************************//
	//*************************************************************************************************************//
	//*********************************************                  **********************************************//
	//*********************************************  CARGA DE VISTA  **********************************************//
	//*********************************************                  **********************************************//
	//*************************************************************************************************************//
	//*************************************************************************************************************//
	
	var chart = null;
	
	//Generamos el contexto de la vista
	$scope.cntx = srv.getCntx('pres/anua');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}

	function presAnuaChart() {	
		var options = {
		    //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		    scaleBeginAtZero : false,
		    //Boolean - Whether grid lines are shown across the chart
		    scaleShowGridLines : true,
		    //String - Colour of the grid lines
		    scaleGridLineColor : "rgba(0,0,0,.05)",
		    //Number - Width of the grid lines
		    scaleGridLineWidth : 1,
		    //Boolean - Whether to show horizontal lines (except X axis)
		    scaleShowHorizontalLines: true,
		    //Boolean - Whether to show vertical lines (except Y axis)
		    scaleShowVerticalLines: true,
		    //Boolean - If there is a stroke on each bar
		    barShowStroke : true,
		    //Number - Pixel width of the bar stroke
		    barStrokeWidth : 2,
		    //Number - Spacing between each of the X value sets
		    barValueSpacing : 5,
		    //Number - Spacing between data sets within X values
		    barDatasetSpacing : 1,
		    //String - A legend template
		    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
		    datasetFill: false
		};
		
		var anuaList = $scope.cntx.data.get('presAnuaList');
		var mespList = $scope.cntx.data.get('presList');
		var mesMap   = $scope.cntx.data.get('ltMes');  
		
		var labelList = new Array();
		var impoList = new Array();
		var imtoList = new Array();
		var desvList = new Array();
		var desv = 0;
				
		for (var i=0; i<mespList.length; i++) {
			var pres = mespList[i];
				
			if (pres.esta === 'C') {
				desv = desv + pres.desv;
			} 

			labelList.push(mesMap[pres.mesp].desc);
			impoList.push(pres.impo);
			imtoList.push(pres.imto);
			desvList.push(desv);
		};
		var data = {
			labels: labelList,
			datasets: [
			    {
			    	label: "My First dataset",
			        fillColor: "rgba(220,220,220,0.2)",
			        strokeColor: "rgba(220,220,220,1)",
			        pointColor: "rgba(220,220,220,1)",
			        pointStrokeColor: "#fff",
			        pointHighlightFill: "#fff",
			        pointHighlightStroke: "rgba(220,220,220,1)",
			        data: impoList
			    },
			    {
			        label: "My Second dataset",
			        fillColor: "rgba(151,187,205,0.2)",
			        strokeColor: "rgba(151,187,205,1)",
			        pointColor: "rgba(151,187,205,1)",
			        pointStrokeColor: "#fff",
			        pointHighlightFill: "#fff",
			        pointHighlightStroke: "rgba(151,187,205,1)",
			        data: imtoList
			    },
			    {
			        label: "My third dataset",
			        type: "line",
			        fillColor: "rgba(151,187,205,0.2)",
			        strokeColor: "rgba(151,187,205,1)",
			        pointColor: "rgba(151,187,205,1)",
			        pointStrokeColor: "#fff",
			        pointHighlightFill: "#fff",
			        pointHighlightStroke: "rgba(151,187,205,1)",
			        data: desvList
			    }
			]
		};

		var ctx = document.getElementById("presAnuaChart").getContext("2d");
		if (chart !== null) {
			chart.destroy();
		}
		
		chart = new Chart(ctx).LineBar(data, options);		
	}
});