//*************************************************************************************************************//
// usuaExitCtrl: LogOut de usuario de la aplicación.                                                           //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 12.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//
app.controller('usuaExitCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {

	//Realizamos el logout en BackEnd
	var srv1 = comc.request('usua/exit', null);
	$q.all([srv.stResp(true, srv1)]).then(function() {
		//Logout en FrontEnd
		$rootScope.esta.sesi     = 0;
		$rootScope.esta.lgonUsua = false;
		$rootScope.esta.usua     = null;
		
		//Recargamos el menú
		var srv1 = comc.request('menu/get', null);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			//Home
			srv.go(null, null, '/', null);
		});
	})
});