//*****************************************************************************************************************//
// Opciones de navegación propias del CORE.                                                                        //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.1.00.01',
			controller  : 'homeCtrl'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.1.00.01',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.1.00.01',
			controller  : 'usuaExitCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.1.00.01',
			controller  : 'usuaRegiCtrl'
		})
		.when('/usua/list', {
			templateUrl : 'pages/core/usua/usuaList.html?v.1.00.01',
			controller  : 'usuaListCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.1.00.01',
			controller  : 'avisListCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.1.00.01',
			controller  : 'inviListCtrl'
		})
		.when('/invi/envi', {
			templateUrl : 'pages/core/invi/inviEnvi.html?v.1.00.01',
			controller  : 'inviEnviCtrl'
		})
		.when('/ctmn/list', {
			templateUrl : 'pages/core/ctmn/ctmnList.html?v.1.00.01',
			controller  : 'ctmnListCtrl'
		})
		.when('/ctmn/form', {
			templateUrl : 'pages/core/ctmn/ctmnForm.html?v.1.00.01',
			controller  : 'ctmnFormCtrl'
		})
		.when('/dtmn/list', {
			templateUrl : 'pages/core/dtmn/dtmnList.html?v.1.00.01',
			controller  : 'dtmnListCtrl'
		})
		.when('/dtmn/form', {
			templateUrl : 'pages/core/dtmn/dtmnForm.html?v.1.00.01',
			controller  : 'dtmnFormCtrl'
		})
		.when('/inst/list', {
			templateUrl : 'pages/core/inst/instList.html?v.1.00.01',
			controller  : 'instListCtrl'
		})
		.when('/mpla/list', {
			templateUrl : 'pages/core/mpla/mplaList.html?v.1.00.01',
			controller  : 'mplaListCtrl'
		})
		.when('/btch/list', {
			templateUrl : 'pages/core/btch/btchList.html?v.1.00.01',
			controller  : 'btchListCtrl'
		})
		.when('/ejec/list', {
			templateUrl : 'pages/core/ejec/ejecList.html?v.1.00.01',
			controller  : 'ejecListCtrl'
		})
		.when('/plan/list', {
			templateUrl : 'pages/core/plan/planList.html?v.1.00.01',
			controller  : 'planListCtrl'
		})
		.when('/logp/list', {
			templateUrl : 'pages/core/logp/logpList.html?v.1.00.01',
			controller  : 'logpListCtrl'
		})
		.when('/sesi/list', {
			templateUrl : 'pages/core/sesi/sesiList.html?v.1.00.01',
			controller  : 'sesiListCtrl'
		})
		.when('/stst/list', {
			templateUrl : 'pages/core/stst/ststList.html?v.1.00.01',
			controller  : 'ststListCtrl'
		})
		.when('/stdi/list', {
			templateUrl : 'pages/core/stdi/stdiList.html?v.1.00.01',
			controller  : 'stdiListCtrl'
		})
		.when('/stme/list', {
			templateUrl : 'pages/core/stme/stmeList.html?v.1.00.01',
			controller  : 'stmeListCtrl'
		})
		.when('/ctrl/list', {
			templateUrl : 'pages/core/ctrl/ctrlList.html?v.1.00.01',
			controller  : 'ctrlListCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});