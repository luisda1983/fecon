//*************************************************************************************************************//
// Servicio Front End.                                                                                         //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 12.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//

app.factory("srv", ['$rootScope', '$http', '$location', '$q', '$route', '$mdDialog', '$timeout', '$mdSidenav', 'cntx', function($rootScope, $http, $location, $q, $route, $mdDialog, $timeout, $mdSidenav, cntx) {

	//*************************************************************************************************************//
	// Area de notificaciones.                                                                                     //
	//*************************************************************************************************************//
	$rootScope.notf = {
		esta: null,
		void: new Array(),
		auto: new Array(),
		info: new Array()
	};
	
	//*************************************************************************************************************//
	// Area de estado de aplicación.                                                                               //
	//*************************************************************************************************************//
	$rootScope.esta = {
		sesi    : 0,             //Identificador de sesión activa
		lgonUsua: false,         //Indicador de que hay un usuario activo en la aplicación
		usua    : null,          //Usuario activo en la aplicación
		load    : false,         //Indicador de comunicación en curso. Sirve para mostrar la pantalla de espera
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
	// Area de respuesta de servicio.                                                                              //
	//*************************************************************************************************************//
	var outData  = {};

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

	//*************************************************************************************************************//
	// PUBLIC: srv.call: Función de llamadas a backend.                                                            //
	//*************************************************************************************************************//
	function call(url, data) {

		console.log(url);
		
		$rootScope.esta.load = true;
		
		var res = null;
		outData = {};
		res = $http.post(url, data)
		.success(function(data, status, headers, config) {
			
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
				$rootScope.esta.load = false;
		});
		return res;
	};
	
	//*************************************************************************************************************//
	// PUBLIC: srv.stResp: Gestión de multirespuesta.                                                              //
	//*************************************************************************************************************//
	function stResp(end, ...promises) {
		var d = $q.defer();
		
		//Se esperan y valoran todos los promise recibido en la entrada.
		$q.all(promises).then(function() {
			//Si todas han finalizado correctamente, se realiza la gestión de notificaciones y devolvemos resolvemos
			//el promise de salida.
			var v = notify(end);
			$q.all([v]).then(function(){
				d.resolve();
			})
		}, function() {
			//Si hay algún error entre las respuestas, se realiza la gestión de notificaciones y rechazamos el 
			//promise de salida.
			var v = notify(end);
			$q.all([v]).then(function(){
				d.reject();
			})
		})
		return d.promise;
	}
	
	//*************************************************************************************************************//
	// PUBLIC: srv.getData: Obtención de los datos devueltos por el servicio.                                      //
	//*************************************************************************************************************//
	function getData() {
		return outData;
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

	//*************************************************************************************************************//
	// PUBLIC: srv.back: Función encargada de realizar la transición de retorno a la vista anterior.               //
	//*************************************************************************************************************//
	function back(keep) {
		if (cntxData.backPath !== null) {
			if (keep){
				$rootScope.esta.doingBack = true;
			}
			$location.path(cntxData.backPath);
		}
	}

	//*************************************************************************************************************//
	// PUBLIC: srv.goTran: Función que indica si hemos realizado una transición con estado.                        //
	//*************************************************************************************************************//
	function goTran() {
		return cntxData.goData !== null;
	}

	//*************************************************************************************************************//
	// PUBLIC: srv.backTran: Función que indica si hemos realizado una transición de retorno con estado.           //
	//*************************************************************************************************************//
	function backTran() {
		return cntxData.backData !== null;
	}

	//*************************************************************************************************************//
	// PUBLIC: srv.getOrigView: Función que devuelve el origen de una transición.                                  //
	//*************************************************************************************************************//
	function getOrigView() {
		return cntxData.backPath;
	}

	//*************************************************************************************************************//
	// PUBLIC: srv.getDestView: Función que devuelve el origen de una transición de retorno.                       //
	//*************************************************************************************************************//
	function getDestView() {
		return cntxData.goPath;
	}

	//*************************************************************************************************************//
	// PRIVATE: showNotify: Constructor del mdDialog que muestra las notificaciones.                               //
	//*************************************************************************************************************//
	function showNotify(notf) {
		return $mdDialog.show({
					locals:{data: notf},
					controller: NotifyCtrl,
					templateUrl: 'pages/core/notify.html?v.0.00.53',
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
	
	//*************************************************************************************************************//
	// PRIVATE: NotifyCtrl: Controlador del mdDialog de notificaciones.                                            //
	//*************************************************************************************************************//
	function NotifyCtrl($scope, data, $mdDialog) {
		$scope.data = data;
		
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
		
		//Función que gestiona la ocultación del popup de notificaciones
	    $scope.hide = function() {
	    	$mdDialog.hide();
	    };

	    //Función que gestiona la cancelación del popup de notificaciones
	    $scope.cancel = function() {
	    	$mdDialog.cancel();
	    };

	    //Función que gestiona la autorización del popup de notificaciones
	    $scope.auto = function(auto) {
	    	$mdDialog.hide(auto);
	    };
	}

	//*************************************************************************************************************//
	// PRIVATE: notify: Gestión de notificaciones.                                                                 //
	//*************************************************************************************************************//
	function notify(end) {
		var d = $q.defer();
		//Si tenemos notificaciones cargadas, y se ha indicado el fin de flujo. Mostramos las notificaciones.
		//A la vuelta inicializamos las notificaciones.
		if (end && $rootScope.notf.esta !== null && $rootScope.notf.esta !== '') {
			var promise = showNotify($rootScope.notf);
			$q.all([promise]).then(function() {
				d.resolve();
				inicNotf();
			});
		} else {
			d.resolve();
		}
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: inicNotf: Limpieza de área de notificaciones.                                                      //
	//*************************************************************************************************************//
	function inicNotf() {
		$rootScope.notf.esta = '';
		$rootScope.notf.void = new Array();
		$rootScope.notf.auto = new Array();
		$rootScope.notf.info = new Array();
	}

	//*************************************************************************************************************//
	// INTERFAZ PUBLICA SRV: Funciones que ofrece el servicio FrontEnd srv.                                        //
	//*************************************************************************************************************//
	return {
		call       : call,
		stResp     : stResp,        //Gestión estándar de respuestas a servicios
		getData    : getData,       //Obtención de los datos de respuesta del servicio
		getCntx    : getCntx,       //Función encaargada de la gestión de contextos de vistas
		go         : go,            //Función de transición entre vistas.
		back       : back,          //Función de transición de retorno
		getOrigView: getOrigView,   //Función que devuelve la vista desde la que hacemos una transición
		getDestView: getDestView,   //Función que devuelve la vista desde la que hacemos una transición de retorno
		//Funciones de consulta de indicadores de estado
		goTran     : goTran,        //Indica si estamos haciendo una transición con estado
		backTran   : backTran       //Indica si estamos haciendo un retorno con estado
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