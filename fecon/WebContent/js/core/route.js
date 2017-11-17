//Logica de controlador
app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.0.00.31',
			controller  : 'homeController'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.31',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.31',
			controller  : 'usuaExitCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.0.00.31',
			controller  : 'usuaRegiCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.0.00.31',
			controller  : 'avisListCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.0.00.31',
			controller  : 'inviListCtrl'
		})
		.when('/cuen/list', {
			templateUrl : 'pages/appl/cuen/cuenList.html?v.0.00.31',
			controller  : 'cuenListCtrl'
		})
		.when('/pres/resu', {
			templateUrl : 'pages/appl/pres/presResu.html?v.0.00.31',
			controller  : 'presResuCtrl'
		})
		.when('/pres/anua', {
			templateUrl : 'pages/appl/pres/presAnua.html?v.0.00.31',
			controller  : 'presAnuaCtrl'
		})
		.when('/pres/mesp', {
			templateUrl : 'pages/appl/pres/presMesp.html?v.0.00.31',
			controller  : 'presMespCtrl'
		})
		.when('/pres/conc', {
			templateUrl : 'pages/appl/pres/presConc.html?v.0.00.31',
			controller  : 'presConcCtrl'
		})
		.when('/pres/nuev', {
			templateUrl : 'pages/pres/presNuev.html?v.0.00.31',
			controller  : 'presNuevController'
		})
		.when('/hcon/apun', {
			templateUrl : 'pages/hcon/apun.html?v.0.00.31',
			controller  : 'apunController'
		})
		.when('/hcon/list/:anua?/:mesp?/:cate?/:conc?', {
			templateUrl : 'pages/hcon/list.html?v.0.00.31',
			controller  : 'hconListController'
		})
		.when('/cuen/tras/:orig?', {
			templateUrl : 'pages/cuen/tras.html?v.0.00.31',
			controller  : 'cuenTrasCtrl'
		})
		.when('/cuen/cuad', {
			templateUrl : 'pages/cuen/cuad.html?v.0.00.31',
			controller  : 'cuenCuadCtrl'
		})
		.when('/adm/conc', {
			templateUrl : 'pages/adm/conc.html?v.0.00.31',
			controller  : 'admConcCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});