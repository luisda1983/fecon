//Factory que encapsula la comunicación con BackEnd
app.factory("comc", ['$rootScope', '$q', 'srv', 'coma', function($rootScope, $q, srv, coma) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	function request(name, cntx) {
		
		     if (name === 'lite/list') { return srvLiteList(cntx); }
		else if (name === 'lite/get' ) { return srvLiteGet(cntx);  } 
		else if (name === 'para/get' ) { return srvParaGet(cntx);  }
		else if (name === 'menu/make') { return srvMenuMake(cntx); }
		else if (name === 'ctmn/list') { return srvCtmnList(cntx); }
		else if (name === 'ctmn/acti') { return srvCtmnActi(cntx); }
		else if (name === 'ctmn/desa') { return srvCtmnDesa(cntx); }
		else if (name === 'ctmn/form') { return srvCtmnForm(cntx); }
		else if (name === 'dtmn/list') { return srvDtmnList(cntx); }
		else if (name === 'dtmn/acti') { return srvDtmnActi(cntx); }
		else if (name === 'dtmn/desa') { return srvDtmnDesa(cntx); }
		else if (name === 'dtmn/form') { return srvDtmnForm(cntx); }
		else if (name === 'avis/list') { return srvAvisList(cntx); }
		else if (name === 'invi/list') { return srvInviList(cntx); }
		else if (name === 'invi/envi') { return srvInviEnvi(cntx); }
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
			     if (tbla === 'BOOL')       { cntx.data.set('ltMBool'    , cntxLite.data.liteMap); cntx.data.set('ltLBool'    , cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'INVIESTA')   { cntx.data.set('ltInviesta', cntxLite.data.liteMap); d.resolve(); }
			else if (tbla === 'INVITIPO')   { cntx.data.set('ltInvitipo', cntxLite.data.liteMap); d.resolve(); }
			else if (tbla === 'MENUPERF')   { cntx.data.set('ltMMenuperf', cntxLite.data.liteMap); cntx.data.set('ltLMenuperf', cntxLite.data.liteList); d.resolve(); }
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
	// menu/make: Generación de menú.                                                                              //
	//*************************************************************************************************************//
	function srvMenuMake(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();
				
		var output = srv.call(targetHost + 'service/angular/menu/make/', dataRequest);
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

	//*************************************************************************************************************//
	// PRIVATE: srvCtmnList: Servicio de consuta de categoría de menú.                                             //
	//*************************************************************************************************************//
	function srvCtmnList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			perf: cntx.form.get('perf').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ctmn/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ctmnList', data.OUTPUT['ctmnList']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvCtmnActi: Servicio de activación de categoría de menú.                                          //
	//*************************************************************************************************************//
	function srvCtmnActi(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ctmn/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ctmn', data.OUTPUT['ctmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvCtmnDesa: Servicio de desactivación de categoría de menú.                                       //
	//*************************************************************************************************************//
	function srvCtmnDesa(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ctmn/desa/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ctmn', data.OUTPUT['ctmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvCtmnForm: Servicio de gestión de formulario de categoría de menú.                               //
	//*************************************************************************************************************//
	function srvCtmnForm(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data,
			perf: cntx.form.get('perf').data,
			desc: cntx.form.get('desc').data,
			acti: cntx.form.get('acti').data,
			orde: cntx.form.get('orde').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ctmn/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ctmn', data.OUTPUT['ctmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvDtmnList: Servicio de consuta de detalle de menú.                                               //
	//*************************************************************************************************************//
	function srvDtmnList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			ctmn: cntx.form.get('ctmn').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dtmn/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('dtmnList', data.OUTPUT['dtmnList']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvDtmnActi: Servicio de activación de detalle de menú.                                            //
	//*************************************************************************************************************//
	function srvDtmnActi(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dtmn/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('dtmn', data.OUTPUT['dtmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvDtmnDesa: Servicio de desactivación de detalle de menú.                                         //
	//*************************************************************************************************************//
	function srvDtmnDesa(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dtmn/desa/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('dtmn', data.OUTPUT['dtmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvDtmnForm: Servicio de gestión de formulario de detalle de menú.                                 //
	//*************************************************************************************************************//
	function srvDtmnForm(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data,
			desc: cntx.form.get('desc').data,
			acti: cntx.form.get('acti').data,
			orde: cntx.form.get('orde').data,
			path: cntx.form.get('path').data,
			icon: cntx.form.get('icon').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dtmn/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('dtmn', data.OUTPUT['dtmn']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
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

	//*************************************************************************************************************//
	// PRIVATE: srvInviList: Servicio de consuta de invitaciones.                                                  //
	//*************************************************************************************************************//
	function srvInviList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			esta: cntx.form.get('esta').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inviList', data.OUTPUT['inviList']);
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviEnvi: Servicio de envío de invitaciones.                                                    //
	//*************************************************************************************************************//
	function srvInviEnvi(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			mail: cntx.form.get('mail').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/envi/', dataRequest);
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
	// PRIVATE: srvInviAcep: Servicio de aceptación de invitación.                                                 //
	//*************************************************************************************************************//
	function srvInviAcep(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/acep/', dataRequest);
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
	// PRIVATE: srvInviRech: Servicio de rechazo de invitación.                                                    //
	//*************************************************************************************************************//
	function srvInviRech(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.get('iden').data
		};
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/rech/', dataRequest);
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
