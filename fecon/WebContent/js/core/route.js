//Logica de controlador
app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html?v.0.00.48',
			controller  : 'homeCtrl'
		})
		.when('/lgon', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.48',
			controller  : 'usuaLgonCtrl'
		})
		.when('/logout', {
			templateUrl : 'pages/core/usua/usuaLgon.html?v.0.00.48',
			controller  : 'usuaExitCtrl'
		})
		.when('/usua/regi', {
			templateUrl : 'pages/core/usua/usuaRegi.html?v.0.00.48',
			controller  : 'usuaRegiCtrl'
		})
		.when('/avis/list', {
			templateUrl : 'pages/core/avis/avisList.html?v.0.00.48',
			controller  : 'avisListCtrl'
		})
		.when('/invi/list', {
			templateUrl : 'pages/core/invi/inviList.html?v.0.00.48',
			controller  : 'inviListCtrl'
		})
		.when('/cuen/list', {
			templateUrl : 'pages/appl/cuen/cuenList.html?v.0.00.48',
			controller  : 'cuenListCtrl'
		})
		.when('/cuen/form', {
			templateUrl : 'pages/appl/cuen/cuenForm.html?v.0.00.48',
			controller  : 'cuenFormCtrl'
		})
		.when('/cuen/cuad', {
			templateUrl : 'pages/appl/cuen/cuenCuad.html?v.0.00.48',
			controller  : 'cuenCuadCtrl'
		})
		.when('/cuen/tras/', {
			templateUrl : 'pages/appl/cuen/cuenTras.html?v.0.00.48',
			controller  : 'cuenTrasCtrl'
		})
		.when('/pres/resu', {
			templateUrl : 'pages/appl/pres/presResu.html?v.0.00.48',
			controller  : 'presResuCtrl'
		})
		.when('/pres/anua', {
			templateUrl : 'pages/appl/pres/presAnua.html?v.0.00.48',
			controller  : 'presAnuaCtrl'
		})
		.when('/pres/mesp', {
			templateUrl : 'pages/appl/pres/presMesp.html?v.0.00.48',
			controller  : 'presMespCtrl'
		})
		.when('/pres/conc', {
			templateUrl : 'pages/appl/pres/presConc.html?v.0.00.48',
			controller  : 'presConcCtrl'
		})
		.when('/hcon/apun', {
			templateUrl : 'pages/appl/hcon/hconForm.html?v.0.00.48',
			controller  : 'hconFormCtrl'
		})
		.when('/hcon/list/', {
			templateUrl : 'pages/appl/hcon/hconList.html?v.0.00.48',
			controller  : 'hconListCtrl'
		})
		.when('/cate/list/', {
			templateUrl : 'pages/appl/cate/cateList.html?v.0.00.48',
			controller  : 'cateListCtrl'
		})
		.when('/cate/form', {
			templateUrl : 'pages/appl/cate/cateForm.html?v.0.00.48',
			controller  : 'cateFormCtrl'
		})
		.when('/conc/list', {
			templateUrl : 'pages/appl/conc/concList.html?v.0.00.48',
			controller  : 'concListCtrl'
		})
		.when('/conc/form', {
			templateUrl : 'pages/appl/conc/concForm.html?v.0.00.48',
			controller  : 'concFormCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});