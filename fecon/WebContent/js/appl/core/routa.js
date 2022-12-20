//*****************************************************************************************************************//
// Opciones de navegación propias de la aplicación.                                                                //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/cuen/list', {
			templateUrl : 'pages/appl/cuen/cuenList.html?v.1.01.04',
			controller  : 'cuenListCtrl'
		})
		.when('/cuen/form', {
			templateUrl : 'pages/appl/cuen/cuenForm.html?v.1.01.04',
			controller  : 'cuenFormCtrl'
		})
		.when('/cuen/cuad', {
			templateUrl : 'pages/appl/cuen/cuenCuad.html?v.1.01.04',
			controller  : 'cuenCuadCtrl'
		})
		.when('/cuen/tras/', {
			templateUrl : 'pages/appl/cuen/cuenTras.html?v.1.01.04',
			controller  : 'cuenTrasCtrl'
		})
		.when('/pres/resu', {
			templateUrl : 'pages/appl/pres/presResu.html?v.1.01.04',
			controller  : 'presResuCtrl'
		})
		.when('/pres/anua', {
			templateUrl : 'pages/appl/pres/presAnua.html?v.1.01.04',
			controller  : 'presAnuaCtrl'
		})
		.when('/pres/mesp', {
			templateUrl : 'pages/appl/pres/presMesp.html?v.1.01.04',
			controller  : 'presMespCtrl'
		})
		.when('/pres/conc', {
			templateUrl : 'pages/appl/pres/presConc.html?v.1.01.04',
			controller  : 'presConcCtrl'
		})
		.when('/pres/cons', {
			templateUrl : 'pages/appl/pres/presCons.html?v.1.01.04',
			controller  : 'presConsCtrl'
		})
		.when('/hcon/form', {
			templateUrl : 'pages/appl/hcon/hconForm.html?v.1.01.04',
			controller  : 'hconFormCtrl'
		})
		.when('/hcon/list/', {
			templateUrl : 'pages/appl/hcon/hconList.html?v.1.01.04',
			controller  : 'hconListCtrl'
		})
		.when('/cate/list/', {
			templateUrl : 'pages/appl/cate/cateList.html?v.1.01.04',
			controller  : 'cateListCtrl'
		})
		.when('/cate/form', {
			templateUrl : 'pages/appl/cate/cateForm.html?v.1.01.04',
			controller  : 'cateFormCtrl'
		})
		.when('/coes/form/', {
			templateUrl : 'pages/appl/coes/coesForm.html?v.1.01.04',
			controller  : 'coesFormCtrl'
		})
		.when('/coes/list/', {
			templateUrl : 'pages/appl/coes/coesList.html?v.1.01.04',
			controller  : 'coesListCtrl'
		})
		.when('/conc/list', {
			templateUrl : 'pages/appl/conc/concList.html?v.1.01.04',
			controller  : 'concListCtrl'
		})
		.when('/conc/form', {
			templateUrl : 'pages/appl/conc/concForm.html?v.1.01.04',
			controller  : 'concFormCtrl'
		})
		.when('/trad/list', {
			templateUrl : 'pages/appl/trad/tradList.html?v.1.01.04',
			controller  : 'tradListCtrl'
		})
		.when('/trad/form', {
			templateUrl : 'pages/appl/trad/tradForm.html?v.1.01.04',
			controller  : 'tradFormCtrl'
		})
		.when('/cont/form', {
			templateUrl : 'pages/appl/cont/contForm.html?v.1.01.04',
			controller  : 'contFormCtrl'
		})
});