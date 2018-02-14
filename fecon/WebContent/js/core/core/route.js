//*************************************************************************************************************//
// Opciones de navegación propias del CORE.                                                                    //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 12.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.0.00.52',
			controller  : 'homeCtrl'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.52',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.52',
			controller  : 'usuaExitCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.0.00.52',
			controller  : 'usuaRegiCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.0.00.52',
			controller  : 'avisListCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.0.00.52',
			controller  : 'inviListCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});