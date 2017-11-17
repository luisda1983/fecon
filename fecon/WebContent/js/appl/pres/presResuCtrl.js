app.controller('presResuCtrl', function($rootScope, $scope, $http, $routeParams, $q, $mdMedia, srv) {

	$scope.conf = {
		item : -1
	}

	var srv1 = srvPresResu();
	var srv2 = srvLitePresEsta();

	$q.all([srv1, srv2]).then(function(){
		presResuChart();
	});

	//Obtiene el resumen presupuestario
	function srvPresResu() {
		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: 'LT01'
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataObject);
		output.then(function() {
			var data = srv.getData();
			$scope.presList = data.OUTPUT['presList'];
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
			$scope.ltPresEsta = data.OUTPUT['liteMap'];
			d.resolve(data);
		});
		return d.promise;
	}

	
	//Función que despliega el menú de acciones
	$scope.openMenu = function($mdOpenMenu, ev) {
		//FIXME: en versiones recientes de angular cambiar mdOpenMenu por mdMenu, y la apertura es mdMenu.open(ev)
		$mdOpenMenu(ev);
	};

	//Función que amplia un registro de la lista
	$scope.xpnd = function(i) {
		if ($scope.conf.item === i) {
			$scope.conf.item = -1;
		} else {
			$scope.conf.item = i;
		}
	}

	//Función para navegar al detalle de un año
	$scope.fnAnua = function(i) {
		var cntx = srv.getCntx('pres/anua');
		var pres = $scope.presList[i];
		cntx.form.anua = pres.anua;
		srv.go(null, null, 'pres/anua', cntx);
	}
	
	//Función para navegar al desglose por conceptos
	$scope.fnConc = function(i) {
		var cntx = srv.getCntx('pres/conc');
		var pres = $scope.presList[i];
		cntx.form.anua = pres.anua;
		srv.go(null, null, 'pres/conc', cntx);
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
		var anuaList = $scope.presList;

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
		new Chart(ctx).Bar(data, options);
		
	}
});