//Factory que encapsula las opciones a nivel de servicio en el lado del  cliente
app.factory("srv", ['$rootScope', '$http', '$location', '$q', '$route', '$mdDialog', '$timeout', '$mdSidenav', 'cntx', function($rootScope, $http, $location, $q, $route, $mdDialog, $timeout, $mdSidenav, cntx) {

	//C-Area de notificaciones
	$rootScope.notf = {
		esta: null,
		void: new Array(),
		auto: new Array(),
		info: new Array(),
		srvc: 0
	};
	
	//C-Area de estado de aplicación
	$rootScope.esta = {
		sesi    : 0,
		usuaRegi: false,
		usua    : null,
		menuList: new Array(),
		viewMode: 'L',
		load    : false,
		srvStack: 0,
		doingGo : false
	};

	//C-Area de datos de contexto de la aplicación
	var cntxData = {
		goData  : null,
		goPath  : null,
		backData: null,
		backPath: null
	};
	
	//C-Area de literales de aplicación
	$rootScope.lite = {
		titu: ''
	};

	//PUBLIC: Efectua la inicializacion de la aplicacion.
	function inicApp() {

		//Obtenemos el nombre de la aplicación.
		srvLiteNombGet();
	
		//TODO: obtener algunos parámetros
		$rootScope.idSesion = 0;
		$rootScope.sesi = 0;
		$rootScope.usua = null;		
		//menu();
		srvMenu();
		$location.path('/');
	}

	//C-Funcion encargada de mostrar el mostrar el menú
	$rootScope.showMenu = function() {
		$mdSidenav('left').toggle();
	}
	
	//C-Funcion que maneja los iconos del menú. Va a la dirección indicada y oculta el menú
	$rootScope.go = function(url) {
		//Siempre que cambiamos de vista, configuramos el modo View Loading
		$rootScope.esta.viewMode = "L";
		//Vamos a la URL, y cerramos el menú lateral
		$location.url(url);
		$mdSidenav('left').close();
	};
	
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
	
	$rootScope.data = {
		menuList : null
	};
	
	//Inicializamos icono de "cargando"
	$rootScope.loading = false;
	$rootScope.esta.load = false;

	function showAdvanced(notf) {
		return $mdDialog.show({
					locals:{data: notf},
					controller: NotifyController,
					templateUrl: 'pages/core/notify.html?v.0.00.42',
					parent: angular.element(document.body),
					//targetEvent: ev,
					clickOutsideToClose:true,
					fullscreen: true
	    		})
	    		.then(function(auto) {
	    			//Tratamiento para autorizaciones (true/false). Si no es autorizacion será false.
	    		}, function() {
	    			//$scope.status = 'You cancelled the dialog.';
	    			//Tratamiento para cancelado de mddialog
	    		});
	};
	
	//C-Controlador del Dialogo de Notificaciones
	function NotifyController($scope, data, $mdDialog) {
		$scope.data = data;
		console.log("NFCtrl " + data.esta);
		//console.log("NFCtrl " + data.info[0].iden);
		//Evaluacion de estados
		$scope.isVoid = function() { return (data.esta === 'V'); }
		$scope.isAuto = function() { return (data.esta === 'O'); }
		$scope.isInfo = function() { return (data.esta === 'I'); }
		
		//Traducción de estados
		$scope.tradEsta = function() {
			trad = "";
			
			if ($scope.isVoid()) { trad = "Error"; }
			if ($scope.isAuto()) { trad = "Autorización"; }
			if ($scope.isInfo()) { trad = "Información"; }
			
			return trad;
		}
		
	    $scope.hide = function() {
	    	$mdDialog.hide();
	    };

	    $scope.cancel = function() {
	    	$mdDialog.cancel();
	    };

	    $scope.auto = function(auto) {
	    	$mdDialog.hide(auto);
	    };
	}

	//Funcion que efetua llamada el WS, controla el error de sesion, y gestiona errores
	//TODO: implementar el modo de ejecucion (View Loading - Event Processing)
	//      Realizar el tratamiento de errores segun el modo, de manera que en view loading, haya un contador de servicios, 
	//        se acumulen las notificaciones, y se muetren cuando hayan finalizado.
	function call(url, data) {

		console.log(url);
		$rootScope.notf.srvc = $rootScope.notf.srvc + 1;
		
		$rootScope.loading = true;
		$rootScope.esta.load = true;
		$rootScope.esta.srvStack = $rootScope.esta.srvStack + 1;
		
		var res = null;
		outData = {};
		res = $http.post(url, data)
		.success(function(data, status, headers, config) {
			
			$rootScope.notf.srvc = $rootScope.notf.srvc - 1;
			console.log(url + " " + $rootScope.notf.srvc);
			//Convivencia con controladores antiguos
			if (typeof data.OUTPUT == 'undefined') {
				outData = data;
				addInfoList(data.infoList);
				addWarnList(data.warnList);
				setError(data.error);
				
				if (data.status === 'ER') { 
					$rootScope.notf.esta = 'V';
					$rootScope.notf.void.push(data.error);
					console.log(url + " C-Size: " + $rootScope.notf.void.length);
				}

				if (data.status === 'ER') {
					if ($rootScope.notf.srvc === 0) {
						console.log(url + "showMsg");
						var promise = showAdvanced($rootScope.notf);
						$q.all([promise]).then(function() {
							//Logica en autorizaciones
						
							//Eliminamos los mensajes ya mostrados
							clearMsg();
						});
						console.log(url + "hideMsg");
					}
				}
			} else {
				outData = data;
				if (data.EXEC_RC === 'V') {
					$rootScope.notf.esta = data.EXEC_RC;
					$rootScope.notf.void.push(data.EXEC_VOID);
					console.log("VOID " + data.EXEC_VOID);
					console.log(url + " Size: " + $rootScope.notf.void.length);
					//TODO: acumulación de informativos y autorizaciones
				} else if (data.EXEC_RC === 'O') {
					
				} else if (data.EXEC_RC === 'I') {
					console.log("INFO " + data.EXEC_INFO_LIST[0].iden);
					$rootScope.notf.esta = data.EXEC_RC;
					//$rootScope.notf.info.push(data.EXEC_INFO_LIST);
					addInfo(data.EXEC_INFO_LIST);
				}
				if (data.EXEC_RC === 'V' || data.EXEC_RC === 'I') {
					
					if ($rootScope.notf.srvc === 0) {
						console.log(url + "showMsg");
						var promise = showAdvanced($rootScope.notf);
						$q.all([promise]).then(function() {
							//Logica en autorizaciones
						
							//Eliminamos los mensajes ya mostrados
							clearMsg();
						});
						console.log(url + "hideMsg");
					}
				}
			}
//			if (data.status === 'SS') {
//				$location.path('/lgon');
//			} else {
//TODO: este bloque se puede usar para algun tipo de convivencia, si fuera necesario

				//TODO: contemplar llamadas en paralelo (not sure)
				$rootScope.loading = false;
				$rootScope.esta.load = false;
				
				if ($rootScope.esta.viewMode === 'L') {
					$rootScope.esta.srvStack = $rootScope.esta.srvStack - 1;
					if ($rootScope.esta.srvStack === 0) {
						//clearMsg();
					}
				} else {
					//clearMsg();
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

		$rootScope.notf.esta = null;
		$rootScope.notf.void = new Array();
		$rootScope.notf.auto = new Array();
		$rootScope.notf.info = new Array();

		//Convivencia, pendiente de eliminar al convertir los datos de los controladores antiguos
		error    = null;
		infoList = new Array();
		warnList = new Array();
		
		$rootScope.loading = false;
		$rootScope.esta.load = false;
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
	
	//C-Incorpora informativos al area de notificaciones
	function addInfo(il) {
		if (il === undefined || il === null) {
			return;
		} else {
			for (var i = 0; i < il.length; i++) {
				$rootScope.notf.info.push(il[i]);
			}
		}
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
	function lgon(response) {
		$rootScope.idSesion = response.OUTPUT['sesi'];
		
		$rootScope.esta.sesi     = response.OUTPUT['sesi'];
		$rootScope.esta.usuaRegi = true;
		$rootScope.esta.usua     = response.OUTPUT['usua'];
		
		$rootScope.sesi = response.OUTPUT['sesi'];
		$rootScope.usua = response.OUTPUT['usua'];
		//menu();
		srvMenu();
		$location.path('/');
	}
	
	//Efectua el logout borrando usuario, idSesion y obteniendo el menu externo.
	function logOut() {
		$rootScope.idSesion = 0;
		$rootScope.sesi = 0;
		
		$rootScope.esta.sesi     = 0;
		$rootScope.esta.usuaRegi = false;
		$rootScope.esta.usua     = null;
		
		$rootScope.usua = null;
		srvMenu();
		//menu();
		$location.path('/lgon');
	}

	//Configuracion del path principal de la aplicacion (con login)
	function home() {
		if ($rootScope.esta.sesi !== 0) {
			$location.path('avis/list');
		}
	}
		
	//C-Servicio de obtención de menú.
	function srvMenu() {

		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi)
		};
		
		var d = $q.defer();
		
		var output = call(targetHost + 'service/angular/menu/get/', dataObject);
		output.then(function() {
			var data = getData();
			$rootScope.esta.menuList = data.OUTPUT['menu'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//C-Servicio de obtención de literal: nombre de aplicación.
	function srvLiteNombGet() {

		var dataObject = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: 'APPLITERAL',
			clav: 'APPNOMBRE'
		};
		
		var d = $q.defer();
		
		var output = call(targetHost + 'service/angular/lite/get/', dataObject);
		output.then(function() {
			var data = getData();
			var lite = data.OUTPUT['lite'];
			if (lite !== undefined) {
				$rootScope.lite.nomb = lite.desc;
			}
			d.resolve(data);
		});
		return d.promise;
	}

	//C-Función que devuelve el Contexto de la vista recibida.
	function getCntx(view) {
		//Verificamos si estamos haciendo una transición con estado
		if ($rootScope.esta.doingGo) {
			//Verificamos que el goPath, es el mismo que para el que se pide el contexto
			if (view === cntxData.goPath) {
				//En ese caso devolvemos el goData guardado
				return cntxData.goData;
			}
		}
		//En cualquier otro caso, inicializamos el estado y devolvemos un contexto inicializado
		$rootScope.esta.doingGo = false;
		cntx.goPath = '';
		cntx.goData = null;
		cntx.backPath = '';
		cntx.backData = null;
		
		return cntx.getCntx(view);
	}
	
	//C-Función para hacer transición con datos entre vistas.
	function go(currentPath, currentData, goPath, goData) {
		//Activamos indicador de que estamos haciendo una transición con estado
		$rootScope.esta.doingGo = true;
	
		//Guardamos el estado
		cntxData.goPath = goPath;
		cntxData.goData = goData;
		cntxData.backPath = currentPath;
		cntxData.backData = currentData;

		//Finalmente vamos al path indicado
		$location.path(goPath);
	}

	//C-Función para volver a la vista anterior
	function back() {
		if (cntxData.backPath !== null) {
			$location.path(cntxData.backPath);
		}
	}
	
	//Interfaz del servicio srv
	return {
		inicApp  : inicApp,
		call     : call,
		getData  : getData,
		clearMsg : clearMsg,
		lgon     : lgon,
		logOut   : logOut,
		home     : home,
		getCntx  : getCntx,
		go       : go,
		back     : back
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

//C-En uso en CORE
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

//C-En uso en CORE
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