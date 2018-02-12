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
	
	//*************************************************************************************************************//
	// Area de estado de aplicación.                                                                               //
	//*************************************************************************************************************//
	$rootScope.esta = {
		sesi    : 0,             //Identificador de sesión activa
		lgonUsua: false,         //Indicador de que hay un usuario activo en la aplicación
		usua    : null,          //Usuario activo en la aplicación
		load    : false,         //Indicador de comunicación en curso. Sirve para mostrar la pantalla de espera
		srvStack: 0,
		doingGo : false,         //Indicador de transición entre vistas activa
		doingBack: false         //Indicador de retorno de vista
	};

	//*************************************************************************************************************//
	// Area de datos generales de aplicación.                                                                      //
	//*************************************************************************************************************//
	$rootScope.data = {
		applNomb: '',   //Nombre de la aplicación
		menuList: null  //Menú de la aplicación
	};
	
	//*************************************************************************************************************//
	// Area de datos de contexto/transiciones de la aplicación.                                                    //
	//*************************************************************************************************************//
	var cntxData = {
		goData  : null,   //Datos en origen de la transición
		goPath  : null,   //Dirección destino de la transición
		backData: null,   //Datos de retorno de la transición
		backPath: null    //Dirección de retorno de la transición
	};

	//*************************************************************************************************************//
	// fnMenuMove: Función encargada de mostrar/ocultar el menú de aplicación                                      //
	//*************************************************************************************************************//
	$rootScope.fnMenuMove = function() {
		$mdSidenav('left').toggle();
	}
	
	//*************************************************************************************************************//
	// fnMenuGo: Función que maneja la navegación desde el menú.                                                   //
	//*************************************************************************************************************//
	$rootScope.fnMenuGo = function(url) {
		//Vamos a la URL
		$location.url(url);
		//Cerramos el menú lateral
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
	

	function showAdvanced(notf) {
		return $mdDialog.show({
					locals:{data: notf},
					controller: NotifyController,
					templateUrl: 'pages/core/notify.html?v.0.00.44a',
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
		
		$rootScope.esta.load = true;
		$rootScope.esta.srvStack = $rootScope.esta.srvStack + 1;
		
		var res = null;
		outData = {};
		res = $http.post(url, data)
		.success(function(data, status, headers, config) {
			
			$rootScope.notf.srvc = $rootScope.notf.srvc - 1;
			console.log(url + " " + $rootScope.notf.srvc);
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
//				if (data.EXEC_RC === 'V' || data.EXEC_RC === 'I') {
//					
//					if ($rootScope.notf.srvc === 0) {
//						console.log(url + "showMsg");
//						var promise = showAdvanced($rootScope.notf);
//						$q.all([promise]).then(function() {
//							//Logica en autorizaciones
//						
//							//Eliminamos los mensajes ya mostrados
//							clearMsg();
//						});
//						console.log(url + "hideMsg");
//					}
//				}
				$rootScope.esta.load = false;
		});
		return res;
	};

	//Función que inicializa un proceso Front
	// 1.- Inicializa el área de mensajes
	function startProc() {
		$rootScope.notf.esta = null;
		$rootScope.notf.void = new Array();
		$rootScope.notf.auto = new Array();
		$rootScope.notf.info = new Array();
	};
	
	//Función que finaliza un proceso Front:
	// 1.- Muestra los mensajes.
	function endProc() {
//		var d = $q.defer();
//		if ($rootScope.notf.esta !== null) {
//			var msg = showAdvanced($rootScope.notf);
//			$q.all([msg]).then(function() {
//				d.resolve();
//			});
//		} else {
//			d.resolve();
//		}
//		return d.promise;
	}

	//Función que realiza las opciones estándares de respuesta ante n llamadas a servicios:
	// Finaliza el proceso. 
	function stResp(end, ...promises) {
		var d = $q.defer();
		
		$q.all(promises).then(function() {
			//Ok
			var v = notify(end);
			$q.all([v]).then(function(){
				d.resolve();
			})
		}, function() {
			//No-ok
			var v = notify(end);
			$q.all([v]).then(function(){
				d.reject();
			})
		})
		return d.promise;
	}

	function notify(end) {
		var d = $q.defer();
		if (end && $rootScope.notf.esta !== null && $rootScope.notf.esta !== '') {
			var promise = showAdvanced($rootScope.notf);
			$q.all([promise]).then(function() {
				d.resolve();
				inicNotf();
			});
		} else {
			d.resolve();
		}
		return d.promise;
	}
	
	function inicNotf() {
		$rootScope.notf.esta = '';
		$rootScope.notf.void = new Array();
		$rootScope.notf.auto = new Array();
		$rootScope.notf.info = new Array();
	}
	
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

	//Efectua el login en la aplicacion, guardando el identificador de sesion, usuario y obteniendo el menu.
	function lgon(cntx) {
		
		$rootScope.esta.sesi     = cntx.data.sesi;
		$rootScope.esta.lgonUsua = true;
		$rootScope.esta.usua     = cntx.data.usua;
		
		srvMenu();
		$location.path('/');
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
			$rootScope.data.menuList = data.OUTPUT['menu'];
			d.resolve(data);
		});
		return d.promise;
	}
	
	//*************************************************************************************************************//
	// PUBLIC: srv.getCntx: Función encargada de generar contexto de vista, teniendo en cuenta transiciones con    //
	//                      datos o retornos.                                                                      //
	//*************************************************************************************************************//
	function getCntx(view) {
		//Verificamos si estamos haciendo una transición con estado
		if ($rootScope.esta.doingGo) {
			//Verificamos que el goPath, es el mismo que para el que se pide el contexto
			if (view === cntxData.goPath) {
				//Verificamos que tenemos cargado el goData
				if (cntxData.goData !== null) {
					//En ese caso devolvemos el goData guardado
					$rootScope.esta.doingGo = false;
					return cntxData.goData;
				}
			}
		}
		//Verificamos si estamos haciendo un retorno
		if ($rootScope.esta.doingBack) {
			//Verificamos que el backPath, es el misma que para el que se pide el contexto
			if (view === cntxData.backPath) {
				//Verificamos que el backData está cargado
				if (cntxData.backData !== null) {
					//En ese caso devolvemos el backData guardado
					$rootScope.esta.doingBack = false;
					return cntxData.backData;
				}
			}
		}
		//En cualquier otro caso, inicializamos el estado y devolvemos un contexto inicializado
		$rootScope.esta.doingGo = false;
		$rootScope.esta.doingBack = false;
		cntxData.goPath = '';
		cntxData.goData = null;
		cntxData.backPath = '';
		cntxData.backData = null;
		
		return cntx.getCntx(view);
	}
	
	//*************************************************************************************************************//
	// PUBLIC: srv.go: Función encargada de realizar la transición entre vistas.                                   //
	//*************************************************************************************************************//
	function go(currentPath, currentData, goPath, goData) {
		//Activamos indicador de que estamos haciendo una transición con estado
		$rootScope.esta.doingGo = true;
	
		//Guardamos el estado
		cntxData.goPath   = goPath;
		cntxData.goData   = goData;
		cntxData.backPath = currentPath;
		cntxData.backData = currentData;

		//Vamos al path solicitado
		$location.path(goPath);
	}

	//C-Función para volver a la vista anterior
	function back() {
		if (cntxData.backPath !== null) {
			$location.path(cntxData.backPath);
		}
	}
	
	//C-Función para volver a la vista anterior, con la posibilidad de mantener el estado
	function backState(keep) {
		if (cntxData.backPath !== null) {
			if (keep) {
				//Activamos indicador de que estamos haciendo un retorno con estado
				$rootScope.esta.doingBack = true;
			}
			$location.path(cntxData.backPath);
		}
	}
	
	//Interfaz del servicio srv
	return {
		call     : call,
		startProc: startProc,
		endProc  : endProc,
		stResp   : stResp,
		getData  : getData,
		clearMsg : clearMsg,
		lgon     : lgon,
		getCntx  : getCntx,     //Función encaargada de la gestión de contextos de vistas
		go       : go,          //Función de transición entre vistas.
		back     : back,
		backState: backState
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