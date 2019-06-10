//*****************************************************************************************************************//
// Opciones de navegación propias del CORE.                                                                        //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.1.01.02',
			controller  : 'homeCtrl'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.1.01.02',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.1.01.02',
			controller  : 'usuaExitCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.1.01.02',
			controller  : 'avisListCtrl'
		})
		.when('/btch/list', {
			templateUrl : 'pages/core/btch/btchList.html?v.1.01.02',
			controller  : 'btchListCtrl'
		})
		.when('/ctmn/form', {
			templateUrl : 'pages/core/ctmn/ctmnForm.html?v.1.01.02',
			controller  : 'ctmnFormCtrl'
		})
		.when('/ctmn/list', {
			templateUrl : 'pages/core/ctmn/ctmnList.html?v.1.01.02',
			controller  : 'ctmnListCtrl'
		})
		.when('/ctrl/list', {
			templateUrl : 'pages/core/ctrl/ctrlList.html?v.1.01.02',
			controller  : 'ctrlListCtrl'
		})
		.when('/dtmn/form', {
			templateUrl : 'pages/core/dtmn/dtmnForm.html?v.1.01.02',
			controller  : 'dtmnFormCtrl'
		})
		.when('/dtmn/list', {
			templateUrl : 'pages/core/dtmn/dtmnList.html?v.1.01.02',
			controller  : 'dtmnListCtrl'
		})
		.when('/dele/form', {
			templateUrl : 'pages/core/dele/deleForm.html?v.1.01.02',
			controller  : 'deleFormCtrl'
		})
		.when('/dele/list', {
			templateUrl : 'pages/core/dele/deleList.html?v.1.01.02',
			controller  : 'deleListCtrl'
		})
		.when('/domi/form', {
			templateUrl : 'pages/core/domi/domiForm.html?v.1.01.02',
			controller  : 'domiFormCtrl'
		})
		.when('/domi/list', {
			templateUrl : 'pages/core/domi/domiList.html?v.1.01.02',
			controller  : 'domiListCtrl'
		})
		.when('/ejec/list', {
			templateUrl : 'pages/core/ejec/ejecList.html?v.1.01.02',
			controller  : 'ejecListCtrl'
		})
		.when('/inst/list', {
			templateUrl : 'pages/core/inst/instList.html?v.1.01.02',
			controller  : 'instListCtrl'
		})
		.when('/invi/envi', {
			templateUrl : 'pages/core/invi/inviEnvi.html?v.1.01.02',
			controller  : 'inviEnviCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.1.01.02',
			controller  : 'inviListCtrl'
		})
		.when('/logp/list', {
			templateUrl : 'pages/core/logp/logpList.html?v.1.01.02',
			controller  : 'logpListCtrl'
		})
		.when('/mpla/list', {
			templateUrl : 'pages/core/mpla/mplaList.html?v.1.01.02',
			controller  : 'mplaListCtrl'
		})
		.when('/plan/list', {
			templateUrl : 'pages/core/plan/planList.html?v.1.01.02',
			controller  : 'planListCtrl'
		})
		.when('/sesi/list', {
			templateUrl : 'pages/core/sesi/sesiList.html?v.1.01.02',
			controller  : 'sesiListCtrl'
		})
		.when('/stdi/list', {
			templateUrl : 'pages/core/stdi/stdiList.html?v.1.01.02',
			controller  : 'stdiListCtrl'
		})
		.when('/stme/list', {
			templateUrl : 'pages/core/stme/stmeList.html?v.1.01.02',
			controller  : 'stmeListCtrl'
		})
		.when('/stst/list', {
			templateUrl : 'pages/core/stst/ststList.html?v.1.01.02',
			controller  : 'ststListCtrl'
		})
		.when('/usua/list', {
			templateUrl : 'pages/core/usua/usuaList.html?v.1.01.02',
			controller  : 'usuaListCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.1.01.02',
			controller  : 'usuaRegiCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});