//*************************************************************************************************************//
// Controladores de la arquitectura de aplicación.                                                             //
//   - appCtrl : inicialización de aplicación.                                                                 //
//   - homeCtrl: gestión de path '/'.                                                                          //
//   - secCtrl : controlador de seguridad.                                                                     //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 12.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//


//*************************************************************************************************************//
// appCtrl : Controlador encargado de inicializar la aplicación. Se ejecuta únicamente cuando se carga la      //
//           aplicación la primera vez.                                                                        //
//*************************************************************************************************************//
app.controller('appCtrl', function($rootScope, $scope, $q, srv, comc) {

	var srv1 = comc.requestLite('APPLITERAL', 'APPNOMBRE', null);
	var srv2 = comc.request('menu/make', null);
	
	$q.all([srv.stResp(true, srv1,srv2)]).then(function(){
		srv.go(null, null, '/', null);
	});
});

//*************************************************************************************************************//
// homeCtrl: Controlador encargado de capturar el home de la aplicación ('/').                                 //
//           Si hay sesión activa, se viaja al home de aplicación con usuario activo (lista de avisos).        //
//           Si no hay sesión activa, ya estamos en el home de aplicación sin usuario activo.                  //
//*************************************************************************************************************//
app.controller('homeCtrl', function($rootScope, srv) {
	if ($rootScope.esta.sesi !== 0) {
		srv.go(null, null, 'avis/list', null);
	}
});

//*************************************************************************************************************//
//secCtrl  : Controlador usado para restringir el acceso a las vistas no permitidas sin usuario activo         //
//           TODO: Este control debe modificarse... bien metiéndolo en el srv, controlando las transiciones,   //
//           o bien desarrollando un modelo de datos de vistas, que se validaría en la solicitud del cntx.     //
//*************************************************************************************************************//
app.controller('secCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv, comc) {
	if ($rootScope.esta.sesi === undefined || $rootScope.esta.sesi === 0) {
		$rootScope.esta.sesi     = 0;
		$rootScope.esta.lgonUsua = false;
		$rootScope.esta.usua     = null;
		
		var srv1 = comc.request('menu/make', null);
		
		$q.all([srv.stResp(true, srv1)]).then(function(){
			srv.go(null, null, '/', null);
		});
	} 
});