app.controller('avisListCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	$scope.cntx = srv.getCntx('avis/list');

	var srv1 = comc.request('avis/list', $scope.cntx);
	$q.all([srv.stResp(true, srv1)]).then(function() {
		view();
	});

	//Función encargada de manejar la vista, y sus modos de presentación
	// - Esta vista no tiene formulario, por lo que no tiene modos de presentación
	function view() {
		//TODO
	}

});