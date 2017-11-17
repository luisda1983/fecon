///////////////////////////////////////////////////////////////////////////////////////////////
// Configuracion principal de la aplicacion.                                                 //
//    - Configuracion de desarrollo y produccion.                                            //
//    - Logica de controlador (route).                                                       //
//    - Servicio principal del lado del cliente, que provee comuniacion estandar con los WS, //
//          gestion de mensajes, logIn, logOut.                                              //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  08/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

//Configuracion
var app = angular.module('app', ['ngRoute','ngMaterial']);
//URL Desarrollo
var targetHost = 'http://localhost:8080/fecon/';
//URL Produccion
//var targetHost = 'http://fecon-luisda1983.rhcloud.com/';

//Configuracion de tema primario y secundario
app.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default').primaryPalette('indigo').accentPalette('orange');
});

//Logica de controlador
app.config(function($routeProvider) {
	
	$routeProvider
		.when('/', {
			templateUrl : 'pages/home.html',
			controller  : 'homeController'
		})
		.when('/tare/list', {
			templateUrl : 'pages/tare/list.html',
			controller  : 'tareaListController'
		})
		.when('/login', {
			templateUrl : 'pages/app/login.html',
			controller  : 'loginController'
		})
		.when('/logout', {
			templateUrl : 'pages/app/login.html',
			controller  : 'logoutController'
		})
		.when('/pres/mesp', {
			templateUrl : 'pages/pres/presMesp.html',
			controller  : 'presMespController'
		})
		.when('/pres/mesp/:anua/:mesp', {
			templateUrl : 'pages/pres/presMesp.html',
			controller  : 'presMespController'
		})
		.when('/pres/anua', {
			templateUrl : 'pages/pres/presAnua.html',
			controller  : 'presAnuaController'
		})
		.when('/pres/anua/:anua', {
			templateUrl : 'pages/pres/presAnua.html',
			controller  : 'presAnuaController'
		})
		.when('/pres/conc/:anua?', {
			templateUrl : 'pages/pres/presConc.html',
			controller  : 'presConcController'
		})
		.when('/pres/resu', {
			templateUrl : 'pages/pres/presResu.html',
			controller  : 'presResuController'
		})
		.when('/pres/nuev', {
			templateUrl : 'pages/pres/presNuev.html',
			controller  : 'presNuevController'
		})
		.when('/hcon/apun', {
			templateUrl : 'pages/hcon/apun.html',
			controller  : 'apunController'
		})
		.when('/hcon/list/:anua?/:mesp?/:cate?/:conc?', {
			templateUrl : 'pages/hcon/list.html',
			controller  : 'hconListController'
		})
		.when('/cuen/list', {
			templateUrl : 'pages/cuen/list.html',
			controller  : 'cuenListCtrl'
		})
		.when('/cuen/tras/:orig?', {
			templateUrl : 'pages/cuen/tras.html',
			controller  : 'cuenTrasCtrl'
		})
		.when('/cuen/cuad', {
			templateUrl : 'pages/cuen/cuad.html',
			controller  : 'cuenCuadCtrl'
		})
		.when('/adm/conc', {
			templateUrl : 'pages/adm/conc.html',
			controller  : 'admConcCtrl'
		})
		.otherwise({
	        redirectTo: '/'
	    });
});

//Factory que encapsula las opciones a nivel de servicio en el lado del  cliente
app.factory("srv", ['$rootScope', '$http', '$location', '$q', function($rootScope, $http, $location, $q) {

	//Almacenamiento de response
	var outData  = {};
	
	//Almacenamiento interno de errores
	var infoList = new Array();
	var warnList = new Array();

	var toggleMap = new Object();
	
	//Area de errores que se muestra en el cliente
	$rootScope.msg = {
			error : null,
			infoList : new Array(),
			warnList : new Array()
	};
	
	//Despliegue de menu
	$rootScope.toggle = function(id) {
		$rootScope.idSelected = id;
		var now = toggleMap[id];
		if (now === 'undefined') {
			toggleMap[id] = 'S';
		} else if (now === 'S') {
			toggleMap[id] = 'N';
		} else {
			toggleMap[id] = 'S';
		}
		$rootScope.toggleMap = toggleMap;
	};
	
	$rootScope.data = {
		menuList : null
	};
	
	//Inicializamos (ningun menu desplegado)
	$rootScope.idSelected = 0;

	//Inicializamos icono de "cargando"
	$rootScope.loading = false;
	
	//Funcion que efetua llamada el WS, controla el error de sesion, y gestiona errores
	function call(url, data) {
		
		$rootScope.loading = true;
		
		var res = null;
		outData = {};
		res = $http.post(url, data)
		.success(function(data, status, headers, config) {
			if (data.status === 'SS') {
				$location.path('/login');
			} else {
				outData = data;
				addInfoList(data.infoList);
				addWarnList(data.warnList);
				setError(data.error);
			}
		});
		return res;
	};
	
	//Datos obtenidos desde el WS
	function getData() {
		return outData;
	}
	
	//Inicializa el area interna de mensajes
	function clearMsg() {
		error    = null;
		infoList = new Array();
		warnList = new Array();
		
		$rootScope.loading = false;
	}
	
	//A�ade informativos al area de informativos y los muestra
	function addInfoList(il) {
		if (infoList === undefined) {
			infoList = il;
		} else {
			if (il === undefined || il === null) {
			} else {
				for(var i = 0; i < il.length; i++) {
					infoList.push(il[i]);
				}
			}
		}
		$rootScope.msg.infoList = infoList;
	}
	
	//A�ade warning al area de warnings y los muestra
	function addWarnList(wl) {
		if (warnList === undefined) {
			warnList = wl;
		} else {
			if (wl === undefined || wl === null) {
				
			} else {
				for (var i = 0; i < wl.length; i++) {
					warnList.push(wl[i]);
				}
			}
		}
		$rootScope.msg.warnList = warnList;
	}

	//Tratamiento del error de aplicacion
	function setError(er) {
		error = er;
		$rootScope.msg.error = error;
	}

	//Efectua el login en la aplicacion, guardando el idSesion, usuario y obteniendo el menu.
	function logIn(loginResponse) {
		$rootScope.idSesion = loginResponse.idSesion;
		$rootScope.usua = loginResponse.usua.iden;
		menu();
		$location.path('/');
	}
	
	//Efectua el logout borrando usuario, idSesion y obteniendo el menu externo.
	function logOut() {
		$rootScope.idSesion = 0;
		$rootScope.usua = null;
		menu();
		$location.path('/login');
	}

	//Configuracion del path principal de la aplicacion (con login)
	function home() {
		$location.path('tare/list');
	}
	
	//Obtencion del menu mediante el WS correspondiente
	function menu() {
		var dataObject = {
			idSesion : parseInt($rootScope.idSesion),
		};
		var res = $http.post(targetHost + 'service/angular/menu/list/', dataObject);
		res.success(function(data, status, headers, config) {
			$rootScope.menuList = data.menuList;
			$rootScope.data.menuList = data.menuList;
			toggleMap = new Object();
		});
	}
	
	//Interfaz del servicio srv
	return {
		call     : call,
		getData  : getData,
		clearMsg : clearMsg,
		logIn    : logIn,
		logOut   : logOut,
		home     : home
	};
}]);

app.filter('sumByKey', function(){
	return function (data, key) {
        if (typeof (data) === 'undefined' || typeof (key) === 'undefined') {
            return 0;
        }

        var sum = 0;
        for (var i = data.length - 1; i >= 0; i--) {
            sum += parseFloat(data[i][key]);
        }

        return sum;
    };
});

app.filter('extendList', function() {
	return function(data, key) {
		
		if (typeof (data) === 'undefined' || typeof (key) === 'undefined') {
            return;
        }
		var a = new Array();
		for (var i = 0; i < data.length; i++) {
			l = data[i][key];
			for (var j = 0; j < l.length; j++) {
				a.push(l[j]);
			}
		}
		return a;
	};
});

app.filter('yyyymmddFmt', function() {
	return function(yyyymmdd) {
		if (yyyymmdd.toString().length < 8) {
			return '00/00/0001';
		}
		var yyyy = yyyymmdd.toString().substring(0, 4);
		var mm   = yyyymmdd.toString().substring(4, 6);
		var dd   = yyyymmdd.toString().substring(6, 8);
		return dd + '/' + mm + '/' + yyyy;
	};
});

app.filter('hhmmssFmt', function() {
	return function(hhmmss) {
		var s = hhmmss.toString();
		while (s.length < 6) {
			s = '0' + s;
		}
		var hh = s.substring(0, 2);
		var mm = s.substring(2, 4);
		var ss = s.substring(4, 6);
		return hh + ':' + mm + ':' + ss;
	};
});