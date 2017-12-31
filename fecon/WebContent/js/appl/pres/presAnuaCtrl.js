app.controller('presAnuaCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv) {

	var chart = null;

	$scope.cntx = srv.getCntx('pres/anua');

	var srv1 = srvLiteAnualidad();
	var srv2 = srvLiteMes();
	var srv3 = srvCateList();
	var srv4 = srvConcList();
	var srv5 = srvLitePresEsta();

	$q.all([srv1, srv2, srv3, srv4, srv5]).then(function() {
		var srv6 = srvParaPeripresup();
		$q.all([srv6]).then(function() {
			if ($scope.cntx.data.prPeripresup.pval.anac !== 'undefined' &&
				$scope.cntx.data.prPeripresup.pval.anac > 0) {
				if ($scope.cntx.form.anua === 0) {
					$scope.cntx.form.anua = $scope.cntx.data.prPeripresup.pval.anac;
				}
			}
			var srv7 = srvPresAnua();
			$q.all([srv7]).then(function() {
				presAnuaChart();
			});
		});		
	});
	
	//Obtiene el resumen presupuestario para un determinado año
	function srvPresAnua() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'LT02',
			anua: parseInt($scope.cntx.form.anua)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.presList = data.OUTPUT['presList'];
			$scope.cntx.data.presListAnua = data.OUTPUT['presListAnua'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Cambio de estado de partida presupuestaria
	function srvPresEsta() {
		
		var esta = "";
		
		if ($scope.cntx.data.pres.esta === 'A') {
			esta = 'C';
		} else {
			esta = 'A';
		}
		
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			anua: parseInt($scope.cntx.data.pres.anua),
			mesp: parseInt($scope.cntx.data.pres.mesp),
			cate: parseInt($scope.cntx.data.pres.cate),
			conc: parseInt($scope.cntx.data.pres.conc),
			esta: esta
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/esta/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.pres = data.OUTPUT['pres'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de parámetros por instalación: Anualidad
	function srvLiteAnualidad() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'ANUALIDAD'
		};
		
		var d = $q.defer();
		
		
		var output = srv.call(targetHost + 'service/angular/lite/list', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltAnualidad = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de literales: Estados de Presupuesto
	function srvLiteMes() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'MES'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltMes = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de literales: Estados de Presupuesto
	function srvLitePresEsta() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'PRESESTA'
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.ltPresesta = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Llamada al servicio de consulta de parámetros: Periodo actual del presupuesto
	function srvParaPeripresup() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'I',
			tbla: 'PERIPRESUP' //Necesitamos que obtenga el parámetro de la instalación
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/para/get/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cntx.data.prPeripresup = data.OUTPUT['para'];
			d.resolve(data);
		});
		return d.promise;
	}

	//TODO: adaptar a nuevas categorias y conceptos
	//Function que recupera el mapa de categorias
	function srvCateList() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.cateMap = data.OUTPUT['cateListMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	//Function que recupera el mapa de conceptos
	function srvConcList() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion)
		};

		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/map/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.concMap = data.concMap;
			d.resolve(data);
		});
		return d.promise;
	}

	//Captura el evento del cambio en el desplegable de anyo
	$scope.anuaChng = function() {
		if (chart != null) {
			chart.destroy();
		}
		var srv1 = srvPresAnua();
		$q.all([srv1]).then(function(){
			presAnuaChart(); 
		});
	};

	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};

	//Función que amplia un registro de la lista
	$scope.xpnd = function(i, j) {
		//Lista de meses
		if (j === 1) {
			if ($scope.cntx.conf.itm1 === i) {
				$scope.cntx.conf.itm1 = -1;
				$scope.cntx.conf.itm2 = -1;
			} else {
				$scope.cntx.conf.itm1 = i;
				$scope.cntx.conf.itm2 = -1;
			}
		//Lista de partidas anuales
		} else if (j === 2) {
			if ($scope.cntx.conf.itm2 === i) {
				$scope.cntx.conf.itm1 = -1;
				$scope.cntx.conf.itm2 = -1;
			} else {
				$scope.cntx.conf.itm2 = i;
				$scope.cntx.conf.itm1 = -1;
			}
		} else {
			$scope.cntx.conf.itm1 = -1;
			$scope.cntx.conf.itm2 = -1;
		}
	}

	//Función para navegar al detalle de un mes
	$scope.fnMesp = function(i) {
		var cntx = srv.getCntx('pres/mesp');
		var pres = $scope.cntx.data.presList[i];
		cntx.form.anua = pres.anua;
		cntx.form.mesp = pres.mesp;
		srv.go(null, null, 'pres/mesp', cntx);
	}

	//Funcion para cerrar las partidas anuales
	$scope.fnEsta = function(i) {
		$scope.cntx.data.pres = $scope.cntx.data.presListAnua[i];
		
		var srv1 = srvPresEsta();
		$q.all([srv1]).then(function() {
			$scope.cntx.data.presListAnua[i] = $scope.cntx.data.pres;	
		})
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
		
		var anuaList = $scope.cntx.data.presAnuaList;
		var mespList = $scope.cntx.data.presList;
		var mesMap   = $scope.cntx.data.ltMes;  
		
		var labelList = new Array();
		var impoList = new Array();
		var imtoList = new Array();
		var desvList = new Array();
		var desv = 0;
		
//		if (anuaList.length > 0) {
//			labelList.push("ANUAL");
//			var impo = 0;
//			var imto = 0;
//			
//			for (var i=0; i<anuaList.length; i++) {
//				impo = impo + anuaList[i].impo;
//				imto = imto + anuaList[i].imto;
//				if (anuaList[i].esta === 'C') {
//					desv = desv + anuaList[i].desv;
//				}
//			}
//			
//			impoList.push(impo);
//			imtoList.push(imto);
//			desvList.push(desv);
//		}
		
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