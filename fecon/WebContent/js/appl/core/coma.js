//*****************************************************************************************************************//
//Factory de comunicación con BackEnd, para los servicios de aplicación.                                           //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.factory("coma", ['$rootScope', '$q', 'srv', 'form', function($rootScope, $q, srv, form) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	
	function request(name, cntx) {
		
		var d = $q.defer();
		
		     if (name === 'cate/form')      { return srvCateForm(cntx);     }
		else if (name === 'cate/get')       { return srvCateGet(cntx);      } 
		else if (name === 'cate/list')      { return srvCateList(cntx);     }
		else if (name === 'coes/form')      { return srvCoesForm(cntx);     }
		else if (name === 'coes/list')      { return srvCoesList(cntx);     }
		else if (name === 'conc/form')      { return srvConcForm(cntx);     }
		else if (name === 'conc/get')       { return srvConcGet(cntx);      }
		else if (name === 'conc/full')      { return srvConcFull(cntx);     }
		else if (name === 'conc/list')      { return srvConcList(cntx);     }
		else if (name === 'cont/list')      { return srvContList(cntx);     }
		else if (name === 'cuen/form')      { return srvCuenForm(cntx);     }
		else if (name === 'cuen/list')      { return srvCuenList(cntx);     }
		else if (name === 'cuen/tras')      { return srvCuenTras(cntx);     }
		else if (name === 'cuen/cuad')      { return srvCuenCuad(cntx);     }
		else if (name === 'hcon/anul')      { return srvHconAnul(cntx);     }
		else if (name === 'hcon/form')      { return srvHconForm(cntx);     }
		else if (name === 'hcon/list')      { return srvHconList(cntx);     }
		else if (name === 'hcon/pres/gest') { return srvHconPresGest(cntx); }
		else if (name === 'pres/anua')      { return srvPresAnua(cntx);     }
		else if (name === 'pres/calc')      { return srvPresCalc(cntx);     }
		else if (name === 'pres/cier')      { return srvPresCier(cntx);     }
		else if (name === 'pres/conc')      { return srvPresConc(cntx);     }
		else if (name === 'pres/cons')      { return srvPresCons(cntx);     }
		else if (name === 'pres/esta')      { return srvPresEsta(cntx);     }
		else if (name === 'pres/mesp')      { return srvPresMesp(cntx);     }
		else if (name === 'pres/resu')      { return srvPresResu(cntx);     }
		else if (name === 'trad/get' )      { return srvTradGet(cntx);      }
		else if (name === 'trad/form')      { return srvTradForm(cntx);     }
		else if (name === 'trad/list')      { return srvTradList(cntx);     }
		else {
			d.reject();
			return d.promise;
		}
	}

	//*************************************************************************************************************//
	// PUBLIC: requestLiteList: Mapeado de tablas de literales de aplicación.                                      //
	//*************************************************************************************************************//
	function requestLiteList(cntxLite, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();

		//Mapeamos la lista de literales de aplicación al campo asociado al mismo y resolvemos el promise
		     if (cntxLite.form.tbla === 'CUENTIPO')   { cntx.data.set('ltCuentipo'  , cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'HCONLTTIPO') { cntx.data.set('ltHconlttipo', cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'HCONMDTIPO') { cntx.data.set('ltHconmdtipo', cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'ANUALIDAD')  { cntx.data.set('ltAnualidad' , cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'PRESESTA')   { cntx.data.set('ltPresesta'  , cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'CONCTIPO')   { cntx.data.set('ltConctipo'  , cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'COESTIPO')   { cntx.data.set('ltCoestipo'  , cntxLite.data.liteMap); d.resolve(); }
		else if (cntxLite.form.tbla === 'TRADTIPO')   { cntx.data.set('ltTradtipo'  , cntxLite.data.liteMap); d.resolve(); }
		//else if
		//Si no tenemos mapeada la tabla de literales, rechazamos el promise
		else { d.reject(); }
		
		return d.promise;
	}

	//*************************************************************************************************************//
	// PUBLIC: requestLite: Mapeado de literales de aplicación.                                                    //
	//*************************************************************************************************************//
	function requestLite(cntxLite, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Mapeamos el literal de aplicación al campo asociado al mismo y resolvemos el promise
		//Sustituir el siguiente en cuanto tengamos uno de aplicación
		     if (cntxLite.form.tbla === 'APPLITERAL' && cntxLite.form.clav === 'APPNOMBRE') { $cntx.data.lite = cntxLite.data.lite; d.resolve(); }
		//else if
		//Si no tenemos mapeada la tabla de literales, rechazamos el promise
		else { d.reject(); }

		return d.promise;
	}

	//*************************************************************************************************************//
	// PUBLIC: requestParaGet: Mapeado de parámetros de aplicación.                                                //
	//*************************************************************************************************************//
	function requestParaGet(cntxPara, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Mapeamos el parámetro de aplicación al campo asociado al mismo y resolvemos el promise
		if (cntxPara.form.tbla === 'PERIPRESUP') { cntx.data.set('prPeripresup', cntxPara.data.para); d.resolve(); }
		//else if
		//Si no tenemos mapeado el parámetro, rechazamos el promise
		else { d.reject(); }

		return d.promise;
	}

	//Interfaz del servicio coma
	return {
		request: request,
		requestLite    : requestLite,        //Mapeado de literales de aplicación
		requestLiteList: requestLiteList,    //Mapeado de tablas de literales de aplicación
		requestParaGet : requestParaGet      //Mapeado de parámetros de aplicación
	};

	//*************************************************************************************************************//
	// PRIVATE: srvCateForm: Servicio de grabado de categorías.                                                    //
	//*************************************************************************************************************//
	function srvCateForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			desl: cntx.form.get('desl').data,
			desc: cntx.form.get('desc').data,
			pres: cntx.form.get('pres').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cate/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cate', data.OUTPUT['cate']);
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
	// PRIVATE: srvCateGet: Servicio de consulta de categoria.                                                     //
	//*************************************************************************************************************//
	function srvCateGet(cntx) {
		var dataRequest = {
			iden: cntx.form.get('cate').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cate/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cate', data.OUTPUT['cate']);
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
	// PRIVATE: srvCateList: Servicio de consulta de categorías.                                                   //
	//*************************************************************************************************************//
	function srvCateList(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cate/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cateList', data.OUTPUT['cateList']);
				cntx.data.set('cateListMap', data.OUTPUT['cateListMap']);
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
	// PRIVATE: srvCoesForm: Servicio de grabado de códigos específicos.                                           //
	//*************************************************************************************************************//
	function srvCoesForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			tipo: cntx.form.get('tipo').data,
			desc: cntx.form.get('desc').data,
			favo: cntx.form.get('favo').data,
			trad: cntx.form.get('trad').data,
			cate: cntx.form.get('cate').data,
			conc: cntx.form.get('conc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/coes/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('coes', data.OUTPUT['coes']);
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
	// PRIVATE: srvCoesList: Servicio de consulta de códigos específicos.                                          //
	//*************************************************************************************************************//
	function srvCoesList(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/coes/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('coesList', data.OUTPUT['coesList']);
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
	// PRIVATE: srvConcForm: Servicio de grabado de conceptos.                                                     //
	//*************************************************************************************************************//
	function srvConcForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			cate: cntx.form.get('cate').data,
			tipo: cntx.form.get('tipo').data,
			desl: cntx.form.get('desl').data,
			desc: cntx.form.get('desc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('conc', data.OUTPUT['conc']);
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
	// PRIVATE: srvConcFull: Servicio de consulta completa de conceptos.                                           //
	//*************************************************************************************************************//
	function srvConcFull(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/full/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('concFullList', data.OUTPUT['concListMap']);
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
	// PRIVATE: srvConcGet: Servicio de consulta de concepto.                                                      //
	//*************************************************************************************************************//
	function srvConcGet(cntx) {
		var dataRequest = {
			iden: cntx.form.get('conc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/conc/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('conc', data.OUTPUT['conc']);
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
	// PRIVATE: srvConcList: Servicio de consulta de conceptos.                                                    //
	//*************************************************************************************************************//
	function srvConcList(cntx) {
		var dataRequest = {
			cate: cntx.form.get('cate').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				//TODO: ver si realmente era necesario el map
				cntx.data.set('concList', data.OUTPUT['concList']);
				cntx.data.set('concListMap', data.OUTPUT['concListMap']);
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
	// PRIVATE: srvContList: Servicio de consulta de configuracion contable.                                       //
	//*************************************************************************************************************//
	function srvContList(cntx) {
		var dataRequest = {
			coes: cntx.form.get('coes').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cont/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				//TODO: ver si realmente era necesario el map
				cntx.data.set('contList', data.OUTPUT['contList']);
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
	// PRIVATE: srvCuenForm: Servicio de grabado de cuenta.                                                        //
	//*************************************************************************************************************//
	function srvCuenForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			tipo: cntx.form.get('tipo').data,
			desc: cntx.form.get('desc').data,
			sald: cntx.form.get('sald').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cuen/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cuen', data.OUTPUT['cuen']);
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
	// PRIVATE: srvCuenList: Servicio de consulta de cuentas.                                                      //
	//*************************************************************************************************************//
	function srvCuenList(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cuen/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cuenList', data.OUTPUT['cuenList']);
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
	// PRIVATE: srvCuenTras: Servicio de traspaso de cuentas.                                                      //
	//*************************************************************************************************************//
	function srvCuenTras(cntx) {		
		var dataRequest = {
			ctor: cntx.form.get('ctor').data,
			ctde: cntx.form.get('ctde').data,
			impo: form.impo(cntx.form.get('impo').data),
			feva: form.date(cntx.form.get('feva').data)
		};
		setBase(dataRequest, cntx);
			
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cuen/tras/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ctor', data.OUTPUT['ctor']);
				cntx.data.set('ctde', data.OUTPUT['ctde']);
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
	// PRIVATE: srvCuenCuad: Servicio de cuadre de cuentas.                                                        //
	//*************************************************************************************************************//
	function srvCuenCuad(cntx) {
		alert(cntx.form.get('cuen').data.sald);
		var dataRequest = {
			cate: cntx.form.get('cate').data,
			conc: cntx.form.get('conc').data,
			impo: cntx.form.get('impo').data,
			sald: cntx.form.get('cuen').data.sald.toFixed(2), //TODO: validación de concurrencia. No funciona por redondeo en backend
			cuen: cntx.form.get('cuen').data.iden
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/cuad/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
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
	// PRIVATE: srvHconAnul: Servicio de anulación de apunte.                                                      //
	//*************************************************************************************************************//
	function srvHconAnul(cntx) {
		var dataRequest = {
			iden : cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/anul/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
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
	// PRIVATE: srvHconForm: Servicio de grabado de apunte.                                                        //
	//*************************************************************************************************************//
	function srvHconForm(cntx) {
		var dataRequest = {
			tipo: cntx.form.get('tipo').data,
			iden: cntx.form.get('iden').data,
			cuen: cntx.form.get('cuen').data,
			cate: cntx.form.get('cate').data,
			conc: cntx.form.get('conc').data,
			impo: cntx.form.get('impo').data,
			feva: form.date(cntx.form.get('feva').data),
			desc: cntx.form.get('desc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/hcon/apun/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('cuen', data.OUTPUT['cuen']);
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
	// PRIVATE: srvHconList: Servicio de consulta de apuntes.                                                      //
	//*************************************************************************************************************//
	function srvHconList(cntx) {
		var dataRequest = {
			tipo : cntx.form.get('tipo').data,
			anua : cntx.form.get('anua').data,
			mesh : cntx.form.get('mesh').data,
			cate : cntx.form.get('cate').data,
			conc : cntx.form.get('conc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('hconList', data.OUTPUT['hconList']);
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
	// PRIVATE: srvHconPresGest: Servicio de gestión de apunte respecto al presupuesto.                            //
	//*************************************************************************************************************//
	function srvHconPresGest(cntx) {
		var dataRequest = {
			iden : cntx.form.get('iden').data,
			acci : cntx.form.get('acci').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/pres/gest/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('hcon', data.OUTPUT['hcon']);
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
	// PRIVATE: srvPresAnua: Servicio de consulta de presupuesto anual.                                            //
	//*************************************************************************************************************//
	function srvPresAnua(cntx) {
		var dataRequest = {
			tipo: 'LT02',
			anua: cntx.form.get('anua').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('presList', data.OUTPUT['presList']);
				cntx.data.set('presListAnua', data.OUTPUT['presListAnua']);
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
	// PRIVATE: srvPresCier: Servicio de cierre de mes.                                                            //
	//*************************************************************************************************************//
	function srvPresCier(cntx) {
		var dataRequest = {
			anua: cntx.form.get('anua').data,
			mesp: cntx.form.get('mesp').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/pres/cier/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('anua', data.OUTPUT['anua']);
				cntx.data.set('mesp', data.OUTPUT['mesp']);
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
	// PRIVATE: srvPresCalc: Servicio de consulta de presupuesto previo al apunte.                                 //
	//*************************************************************************************************************//
	function srvPresCalc(cntx) {
		var dataRequest = {
			fech: form.date(cntx.form.get('feva').data),
			cate: cntx.form.get('cate').data,
			conc: cntx.form.get('conc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/pres/calc/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('pres', data.OUTPUT['pres']);
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
	// PRIVATE: srvPresConc: Servicio de consulta de presupuesto anual por conceptos.                              //
	//*************************************************************************************************************//
	function srvPresConc(cntx) {
		var dataRequest = {
			tipo: 'LT04',
			anua: cntx.form.get('anua').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('presCateList', data.OUTPUT['presList']);
				cntx.data.set('presListMap', data.OUTPUT['presListMap']);
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
	// PRIVATE: srvPresCons: Servicio de consulta precierre de mes.                                                //
	//*************************************************************************************************************//
	function srvPresCons(cntx) {
		var dataRequest = {
			anua: cntx.form.get('anua').data,
			mesp: cntx.form.get('mesp').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/pres/cons/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('presList', data.OUTPUT['presList']);
				cntx.data.set('presAnuaList', data.OUTPUT['presAnuaList']);
				cntx.data.set('cuenList', data.OUTPUT['cuenList']);
				cntx.data.set('impoNpre', data.OUTPUT['impoNpre']);
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
	// PRIVATE: srvPresEsta: Servicio de cambio de estado de partida presupuestaria.                               //
	//*************************************************************************************************************//
	function srvPresEsta(cntx) {
		var dataRequest = {
			anua: cntx.form.get('anua').data,
			mesp: cntx.form.get('mesp').data,
			cate: cntx.form.get('cate').data,
			conc: cntx.form.get('conc').data,
			esta: cntx.form.get('esta').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/esta/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('pres', data.OUTPUT['pres']);
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
	// PRIVATE: srvPresMesp: Servicio de consulta de presupuesto mensual.                                          //
	//*************************************************************************************************************//
	function srvPresMesp(cntx) {
		var dataRequest = {
			tipo: 'LT03',
			anua: cntx.form.get('anua').data,
			mesp: cntx.form.get('mesp').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('presCateList', data.OUTPUT['presList']);
				cntx.data.set('presListMap', data.OUTPUT['presListMap']);
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
	// PRIVATE: srvPresResu: Servicio de consulta de resumen de presupuesto.                                       //
	//*************************************************************************************************************//
	function srvPresResu(cntx) {
		var dataRequest = {
			tipo: 'LT01'
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('presList', data.OUTPUT['presList']);
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
	// PRIVATE: srvTradGet: Servicio de consulta de traducción.                                                    //
	//*************************************************************************************************************//
	function srvTradGet(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/trad/get/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('trad', data.OUTPUT['trad']);
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
	// PRIVATE: srvTradForm: Servicio de grabado de traducciones.                                                  //
	//*************************************************************************************************************//
	function srvTradForm(cntx) {

		var dataRequest = {
			iden: cntx.form.get('iden').data,
			nomb: cntx.form.get('nomb').data,
			tip1: cntx.form.get('tip1').data,
			dom1: cntx.form.get('dom1').data,
			ide1: cntx.form.get('ide1').data,
			obl1: cntx.form.get('obl1').data,
			tip2: cntx.form.get('tip2').data,
			dom2: cntx.form.get('dom2').data,
			ide2: cntx.form.get('ide2').data,
			obl2: cntx.form.get('obl2').data,
			tip3: cntx.form.get('tip3').data,
			dom3: cntx.form.get('dom3').data,
			ide3: cntx.form.get('ide3').data,
			obl3: cntx.form.get('obl3').data,
			desc: cntx.form.get('desc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/trad/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('trad', data.OUTPUT['trad']);
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
	// PRIVATE: srvTradList: Servicio de consulta de traducciones.                                                 //
	//*************************************************************************************************************//
	function srvTradList(cntx) {
		var dataRequest = {

		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/trad/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('tradList', data.OUTPUT['tradList']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//FIXME: código duplicado en comc. Adaptar una solución similar a la generación del contexto (cntx/ctxa/ctxl)
	function setBase(request, cntx) {
		request.DEVICE = $rootScope.esta.DEVICE;
		request.sesi   = parseInt($rootScope.esta.sesi);
	}

	function setCont(request, cntx) {
		if (cntx.conf.get('CONT_ACTV')) {
			var CONT_NUMB = cntx.conf.get('CONT_NUMB');
			if (CONT_NUMB !== 'undefined' && CONT_NUMB > 0) {
				console.log('Continuación: ' + CONT_NUMB);
				request.CONT_NUMB = CONT_NUMB;
			}
		}
	}
	
	function lookCont(data, cntx) {
		var MORE_DATA = data.OUTPUT['MORE_DATA'];
		if (MORE_DATA !== 'undefined' && MORE_DATA === true) {
			cntx.conf.set('MORE_DATA', true);
			var CONT_NUMB = data.OUTPUT['CONT_NUMB'];
			if (CONT_NUMB !== 'undefined' && CONT_NUMB > 0) {
				cntx.conf.set('CONT_NUMB', CONT_NUMB);
			}
		} else {
			cntx.conf.set('MORE_DATA', false);
		}
	}
	
	function endCont(data, cntx) {
		cntx.conf.set('CONT_ACTV', false);
	}
}]);
