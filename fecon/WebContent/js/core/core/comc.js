//Factory que encapsula la comunicación con BackEnd
app.factory("comc", ['$rootScope', '$q', 'srv', 'coma', function($rootScope, $q, srv, coma) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	function request(name, cntx) {
		
		     if (name === 'lite/list') { return srvLiteList(cntx); }
		else if (name === 'lite/get' ) { return srvLiteGet(cntx);  } 
		else if (name === 'para/get' ) { return srvParaGet(cntx);  }
		else if (name === 'menu/get' ) { return srvMenuGet(cntx);  }
		else if (name === 'avis/list') { return srvAvisList(cntx); }
		else if (name === 'invi/list') { return srvInviList(cntx); }
		else if (name === 'invi/acep') { return srvInviAcep(cntx); }
		else if (name === 'invi/rech') { return srvInviRech(cntx); }
		else if (name === 'invi/soli') { return srvInviSoli(cntx); }
		else if (name === 'invi/vali') { return srvInviVali(cntx); }
		else if (name === 'invi/proc') { return srvInviProc(cntx); }
		else if (name === 'usua/lgon') { return srvUsuaLgon(cntx); }    //Login de Usuario
		else if (name === 'usua/exit') { return srvUsuaExit(cntx); }
		else {
			return coma.request(name, cntx);
		}
	}

	//*************************************************************************************************************//
	// PUBLIC: requestLiteList: Función para obtención de tablas de literales.                                     //
	//*************************************************************************************************************//
	function requestLiteList(tbla, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Creamos y formateamos el contexto de consulta de lista de literales
		var vForm = {
			tbla: ''
		};
		var vData = {
			liteList: new Array(),
			liteMap : new Map()
		};
		var cntxLite = {
			form: vForm,
			data: vData
		};
		cntxLite.form.tbla = tbla;
		
		//Request a la operación de lista de literales
		var srv1 = request('lite/list', cntxLite);
		$q.all([srv1]).then(function() {
			//Mapeamos la lista de literales al campo asociado al mismo y resolvemos el promise
			     if (tbla === 'BOOL')       { cntx.data.ltBool       = cntxLite.data.liteList; cntx.data.ltMBool       = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'INVIESTA')   { cntx.data.ltInviesta   = cntxLite.data.liteList; cntx.data.ltMInviesta   = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'INVITIPO')   { cntx.data.ltInvitipo   = cntxLite.data.liteList; cntx.data.ltMInvitipo   = cntxLite.data.liteMap; d.resolve(); }
			//Si no tenemos mapeada la tabla de literales, buscamos en el componente de aplicación
			else { 
				var appLite = coma.requestLiteList(cntxLite, cntx);
				$q.all([appLite]).then(function() {
					d.resolve();
				}), function() {
					d.reject();
				}
			}
		   //Si hay errores en la operación, rechazamos el promise
		}, function() {
			d.reject();
		})
		return d.promise;
	}

	//*************************************************************************************************************//
	// PUBLIC: requestLite: Función para obtención de literal.                                                     //
	//*************************************************************************************************************//
	function requestLite(tbla, clav, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Obtenemos y formateamos el contexto de consulta de lista de literales
		var cntxLite = srv.getCntx('lite/get');
		var vForm = {
			tbla: '',
			clav: ''
		};
		var vData = {
			lite: null
		};
		var cntxLite = {
			form: vForm,
			data: vData
		};
		cntxLite.form.tbla = tbla;
		cntxLite.form.clav = clav;
		
		//Request a la operación de lista de literales
		var srv1 = request('lite/get', cntxLite);
		$q.all([srv1]).then(function() {
			//Mapeamos la lista de literales al campo asociado al mismo y resolvemos el promise (ARQ)
			     if (tbla === 'APPLITERAL' && clav === 'APPNOMBRE') { $rootScope.data.applNomb = cntxLite.data.lite.desc; d.resolve(); }
			//else if
			//Si no tenemos mapeado el literal, buscamos en el componente de aplicación
			else { 
				var appLite = coma.requestLite(cntxLite, cntx);
				$q.all([appLite]).then(function() {
					d.resolve();
				}), function() {
					d.reject();
				}
			}
		   //Si hay errores en la operación, rechazamos el promise
		}, function() {
			d.reject();
		})
		return d.promise;
	}

	//Funcion para lectura de parámetros
	function requestParaGet(tipo, tbla, clav, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Obtenemos y formateamos el contexto de consulta de parámetros
		var vForm = {
			tipo: '',
			tbla: '',
			clav: ''
		};
		var vData = {
			para: null
		};
		var cntxPara = {
			form: vForm,
			data: vData
		};
		cntxPara.form.tipo = tipo;
		cntxPara.form.tbla = tbla;
		cntxPara.form.clav = clav;
		
		//Request a la operación de consulta de parámetros
		var srv1 = request('para/get', cntxPara);
		$q.all([srv1]).then(function() {
			//Mapeamos el parámetro al campo asociado al mismo y resolvemos el promise
			     if (tbla === 'APLICONFIG' && clav === 'CONFREGIST') { cntx.data.set('prConfregist', cntxPara.data.para); d.resolve(); }
			else if (tbla === 'DYNAMICFLD' && clav === 'REGINVDESC') { cntx.data.set('prReginvdesc', cntxPara.data.para); d.resolve(); }
			//else if
			//Si no tenemos mapeado el parámetro, buscamos en el componente de aplicación
			else { 
				var appPara = coma.requestParaGet(cntxPara, cntx);
				$q.all([appPara]).then(function() {
					d.resolve();
				}), function() {
					d.reject();
				}
			}
		   //Si hay errores en la operación, rechazamos el promise
		}, function() {
			d.reject();
		})
		return d.promise;
	}

	//Interfaz del servicio comc
	return {
		request        : request,
		requestLite    : requestLite,     //Consulta de literal
		requestLiteList: requestLiteList, //Consulta de tablas de literales
		requestParaGet : requestParaGet
	};
	
	//*************************************************************************************************************//
	// lite/list: Recuperación de una lista de literales.                                                          //
	//*************************************************************************************************************//
	function srvLiteList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: cntx.form.tbla
		};

		var d = $q.defer();
				
		var output = srv.call(targetHost + 'service/angular/lite/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.liteList = data.OUTPUT['liteList'];
				cntx.data.liteMap  = data.OUTPUT['liteMap'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// lite/get: Recuperación de un literal.                                                                       //
	//*************************************************************************************************************//
	function srvLiteGet(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			tbla: cntx.form.tbla,
			clav: cntx.form.clav
		};

		var d = $q.defer();
				
		var output = srv.call(targetHost + 'service/angular/lite/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.lite = data.OUTPUT['lite'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// para/get: Consulta de parámetros                           //
	////////////////////////////////////////////////////////////////
	function srvParaGet(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: cntx.form.tipo,
			tbla: cntx.form.tbla,
			clav: cntx.form.clav
		};

		var d = $q.defer();
				
		var output = srv.call(targetHost + 'service/angular/para/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.para = data.OUTPUT['para'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// menu/get: Consulta de menú.                                                                                 //
	//*************************************************************************************************************//
	function srvMenuGet(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
				
		var output = srv.call(targetHost + 'service/angular/menu/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				$rootScope.data.menuList = data.OUTPUT['menu'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

    ////////////////////////////////////////////////////////////////
	// avis/list: Consulta de avisos                              //
	////////////////////////////////////////////////////////////////
	function srvAvisList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/avis/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.avisList = data.OUTPUT['avisList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

    ////////////////////////////////////////////////////////////////
	// invi/list: Consulta de invitaciones                        //
	////////////////////////////////////////////////////////////////
	function srvInviList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			esta: cntx.form.esta
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.inviList = data.OUTPUT['inviList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

    ////////////////////////////////////////////////////////////////
	// invi/acep: Aceptación de invitaciones                      //
	////////////////////////////////////////////////////////////////
	function srvInviAcep(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.iden
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/acep/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}

    ////////////////////////////////////////////////////////////////
	// invi/rech: Rechazo de invitaciones                         //
	////////////////////////////////////////////////////////////////
	function srvInviRech(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.iden
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/acep/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviSoli: Servicio de solicitud de invitación.                                                  //
	//*************************************************************************************************************//
	function srvInviSoli(cntx) {
		var dataRequest = {
			mail: cntx.form.get('mail').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/soli/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviVali: Servicio de validación de invitación.                                                 //
	//*************************************************************************************************************//
	function srvInviVali(cntx) {
		var dataRequest = {
			iden: cntx.form.get('invi').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/vali/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('invi', data.OUTPUT['invi']);
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviProc: Servicio de procesado de invitación.                                                  //
	//*************************************************************************************************************//
	function srvInviProc(cntx) {
		var dataRequest = {
			iden : cntx.form.get('invi').data,
			mail : cntx.form.get('mail').data,
			//FIXME
			//desc : $scope.form.desc,
			usua : cntx.form.get('usua').data,
			pass : cntx.form.get('pass').data,
			cpas : cntx.form.get('cpas').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/proc/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('invi', data.OUTPUT['invi']);
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvUsuaLgon: Servicio de login de usuario.                                                         //
	//*************************************************************************************************************//
	function srvUsuaLgon(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data,
			pass: cntx.form.get('pass').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/lgon/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('sesi', data.OUTPUT['sesi']);
				cntx.data.set('usua', data.OUTPUT['usua']);
				d.resolve(data);
			}
		});
		return d.promise;
	}

    ////////////////////////////////////////////////////////////////
	// usua/exit: Logout de usuario                               //
	////////////////////////////////////////////////////////////////
	function srvUsuaExit(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/exit/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				d.resolve(data);
			}
		});
		return d.promise;
	}
}]);
