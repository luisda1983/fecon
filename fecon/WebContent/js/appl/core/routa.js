//*****************************************************************************************************************//
// Opciones de navegación propias de la aplicación.                                                                //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//

app.config(function($routeProvider) {
	
	$routeProvider
		.when('/cuen/list', {
			templateUrl : 'pages/appl/cuen/cuenList.html?v.0.00.62',
			controller  : 'cuenListCtrl'
		})
		.when('/cuen/form', {
			templateUrl : 'pages/appl/cuen/cuenForm.html?v.0.00.62',
			controller  : 'cuenFormCtrl'
		})
		.when('/cuen/cuad', {
			templateUrl : 'pages/appl/cuen/cuenCuad.html?v.0.00.62',
			controller  : 'cuenCuadCtrl'
		})
		.when('/cuen/tras/', {
			templateUrl : 'pages/appl/cuen/cuenTras.html?v.0.00.62',
			controller  : 'cuenTrasCtrl'
		})
		.when('/pres/resu', {
			templateUrl : 'pages/appl/pres/presResu.html?v.0.00.63',
			controller  : 'presResuCtrl'
		})
		.when('/pres/anua', {
			templateUrl : 'pages/appl/pres/presAnua.html?v.0.00.63',
			controller  : 'presAnuaCtrl'
		})
		.when('/pres/mesp', {
			templateUrl : 'pages/appl/pres/presMesp.html?v.0.00.63',
			controller  : 'presMespCtrl'
		})
		.when('/pres/conc', {
			templateUrl : 'pages/appl/pres/presConc.html?v.0.00.63',
			controller  : 'presConcCtrl'
		})
		.when('/hcon/form', {
			templateUrl : 'pages/appl/hcon/hconForm.html?v.0.00.63',
			controller  : 'hconFormCtrl'
		})
		.when('/hcon/list/', {
			templateUrl : 'pages/appl/hcon/hconList.html?v.0.00.63',
			controller  : 'hconListCtrl'
		})
		.when('/cate/list/', {
			templateUrl : 'pages/appl/cate/cateList.html?v.0.00.63',
			controller  : 'cateListCtrl'
		})
		.when('/cate/form', {
			templateUrl : 'pages/appl/cate/cateForm.html?v.0.00.63',
			controller  : 'cateFormCtrl'
		})
		.when('/conc/list', {
			templateUrl : 'pages/appl/conc/concList.html?v.0.00.63',
			controller  : 'concListCtrl'
		})
		.when('/conc/form', {
			templateUrl : 'pages/appl/conc/concForm.html?v.0.00.63',
			controller  : 'concFormCtrl'
		})
});