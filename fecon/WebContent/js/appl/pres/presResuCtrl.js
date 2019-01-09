//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('presResuCtrl', function($scope, $q, $mdMedia, srv, comc, ctxl) {
	
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
		var srv1 = comc.requestLiteList('PRESESTA', $scope.cntx);
		var srv2 = comc.request('pres/resu', $scope.cntx);

		$q.all([srv.stResp(true, srv1, srv2)]).then(function() {
			view();
			presResuChart();
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
		
		loadView();
	}

	//*************************************************************************************************************//
	// Función encargada de manejar la vista y sus modos de presentación                                           //
	// - 'L': Lista.                                                                                               //
	//*************************************************************************************************************//
	function view() {
		if ($scope.cntx.conf.get('mode') === 'L') {

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
	// Captura del evento de detalle de año.                                                                       //
	//*************************************************************************************************************//
	$scope.fnAnua = function(i) {
		var cntx = srv.getCntx('pres/anua');
		
		var pres = $scope.cntx.data.get('presList')[i];
		cntx.xchg.set('anua', pres.anua);
		
		srv.go('pres/resu', $scope.cntx, 'pres/anua', cntx);
	}

	//*************************************************************************************************************//
	// Captura del evento de desglose por conceptos.                                                               //
	//*************************************************************************************************************//
	$scope.fnConc = function(i) {
		var cntx = srv.getCntx('pres/conc');
		
		var pres = $scope.cntx.data.get('presList')[i];
		cntx.xchg.set('anua', pres.anua);
		
		srv.go('pres/resu', $scope.cntx, 'pres/conc', cntx);
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
	$scope.xpnd = function(i) {
		if ($scope.cntx.conf.get('idx1') === i) {
			$scope.cntx.conf.set('idx1', -1);
		} else {
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
	
	var chart = null;
	
	//Generamos el contexto de la vista
	$scope.cntx = srv.getCntx('pres/resu');

	if (srv.goTran()) {
		loadViewGo();
	} else if (srv.backTran()) {
		loadViewBack();
	} else {
		loadView();
	}
	
	function presResuChart() {
		var options = {
		    //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		    scaleBeginAtZero : true,
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
		    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

		};
		var anuaList = $scope.cntx.data.get('presList');

		var max = 9;
		var b = $mdMedia('xs');
		
		if (b) {
			max = 4;
		}
		
		b = $mdMedia('sm');
		
		if (b) {
			max = 5;
		}

		//Nos quedamos con un maximo de 10 a�os
		if (anuaList.length > max) {
			anuaList = anuaList.slice(anuaList.length - (max), anuaList.length);
		}
		
		var labelList = new Array();
		var ingrList = new Array();
		var gastList = new Array();
		
		var last = 0;
		for (var i=0; i<anuaList.length; i++) {
			var pres = anuaList[i];
			last = pres.anua;
			labelList.push(last);
			ingrList.push(pres.toha);
			gastList.push((-1) * pres.tode);
		};
		while (labelList.length < max) {
			last = last + 1;
			labelList.push(last);
			ingrList.push(0);
			gastList.push(0);
		}
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
	            data: ingrList
	        },
	        {
	            label: "My Second dataset",
	            fillColor: "rgba(151,187,205,0.2)",
	            strokeColor: "rgba(151,187,205,1)",
	            pointColor: "rgba(151,187,205,1)",
	            pointStrokeColor: "#fff",
	            pointHighlightFill: "#fff",
	            pointHighlightStroke: "rgba(151,187,205,1)",
	            data: gastList
	        }
		    ]
		};
		
		var ctx = document.getElementById("presResuChart").getContext("2d");
		if (chart !== null) {
			chart.destroy();
		}
		
		chart = new Chart(ctx).Bar(data, options);		
	}
});