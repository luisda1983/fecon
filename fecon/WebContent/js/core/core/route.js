//*************************************************************************************************************//
// Opciones de navegación propias del CORE.                                                                    //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 12.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.0.00.59',
			controller  : 'homeCtrl'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.59',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.59',
			controller  : 'usuaExitCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.0.00.59',
			controller  : 'usuaRegiCtrl'
		})
		.when('/usua/list', {
			templateUrl : 'pages/core/usua/usuaList.html?v.0.00.59',
			controller  : 'usuaListCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.0.00.59',
			controller  : 'avisListCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.0.00.59',
			controller  : 'inviListCtrl'
		})
		.when('/invi/envi', {
			templateUrl : 'pages/core/invi/inviEnvi.html?v.0.00.59',
			controller  : 'inviEnviCtrl'
		})
		.when('/ctmn/list', {
			templateUrl : 'pages/core/ctmn/ctmnList.html?v.0.00.59',
			controller  : 'ctmnListCtrl'
		})
		.when('/ctmn/form', {
			templateUrl : 'pages/core/ctmn/ctmnForm.html?v.0.00.59',
			controller  : 'ctmnFormCtrl'
		})
		.when('/dtmn/list', {
			templateUrl : 'pages/core/dtmn/dtmnList.html?v.0.00.59',
			controller  : 'dtmnListCtrl'
		})
		.when('/dtmn/form', {
			templateUrl : 'pages/core/dtmn/dtmnForm.html?v.0.00.59',
			controller  : 'dtmnFormCtrl'
		})
		.when('/inst/list', {
			templateUrl : 'pages/core/inst/instList.html?v.0.00.59',
			controller  : 'instListCtrl'
		})
		.when('/mpla/list', {
			templateUrl : 'pages/core/mpla/mplaList.html?v.0.00.59',
			controller  : 'mplaListCtrl'
		})
		.when('/btch/list', {
			templateUrl : 'pages/core/btch/btchList.html?v.0.00.59',
			controller  : 'btchListCtrl'
		})
		.when('/ejec/list', {
			templateUrl : 'pages/core/ejec/ejecList.html?v.0.00.59',
			controller  : 'ejecListCtrl'
		})
		.when('/plan/list', {
			templateUrl : 'pages/core/plan/planList.html?v.0.00.59',
			controller  : 'planListCtrl'
		})
		.when('/logp/list', {
			templateUrl : 'pages/core/logp/logpList.html?v.0.00.59',
			controller  : 'logpListCtrl'
		})
		.when('/sesi/list', {
			templateUrl : 'pages/core/sesi/sesiList.html?v.0.00.59',
			controller  : 'sesiListCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});