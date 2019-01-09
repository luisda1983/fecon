//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.controller('usuaExitCtrl', function($rootScope, $q, srv, comc) {

	//Realizamos el logout en BackEnd
	var srv1 = comc.request('usua/exit', null);
	$q.all([srv.stResp(true, srv1)]).then(function() {
		//Logout en FrontEnd
		$rootScope.esta.sesi     = 0;
		$rootScope.esta.lgonUsua = false;
		$rootScope.esta.usua     = null;
		
		//Recargamos el menú
		var srv1 = comc.request('menu/make', null);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			//Home
			srv.go(null, null, '/', null);
		});
	})
});