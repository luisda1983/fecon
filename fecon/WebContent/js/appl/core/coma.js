//Factory que encapsula la comunicación con BackEnd, para los servicios de aplicación
app.factory("coma", ['$rootScope', '$q', 'srv', function($rootScope, $q, srv) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	
	function request(name, cntx) {
		
		var d = $q.defer();
		
		     if (name === 'cate/form')      { return srvCateForm(cntx);     }
		else if (name === 'cate/list')      { return srvCateList(cntx);     }
		else if (name === 'conc/form')      { return srvConcForm(cntx);     }
		else if (name === 'conc/full')      { return srvConcFull(cntx);     }
		else if (name === 'conc/list')      { return srvConcList(cntx);     }
		else if (name === 'cuen/form')      { return srvCuenForm(cntx);     }
		else if (name === 'cuen/list')      { return srvCuenList(cntx);     }
		else if (name === 'cuen/tras')      { return srvCuenTras(cntx);     }
		else if (name === 'cuen/cuad')      { return srvCuenCuad(cntx);     }
		else if (name === 'hcon/anul')      { return srvHconAnul(cntx);     }
		else if (name === 'hcon/form')      { return srvHconForm(cntx);     }
		else if (name === 'hcon/list')      { return srvHconList(cntx);     }
		else if (name === 'hcon/pres/gest') { return srvHconPresGest(cntx); }
		else if (name === 'pres/anua')      { return srvPresAnua(cntx);     }
		else if (name === 'pres/conc')      { return srvPresConc(cntx);     }
		else if (name === 'pres/esta')      { return srvPresEsta(cntx);     }
		else if (name === 'pres/mesp')      { return srvPresMesp(cntx);     }
		else if (name === 'pres/resu')      { return srvPresResu(cntx);     }
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
		     if (cntxLite.form.tbla === 'CUENTIPO')   { cntx.data.ltCuentipo   = cntxLite.data.liteList; cntx.data.ltMCuentipo   = cntxLite.data.liteMap; d.resolve(); }
		else if (cntxLite.form.tbla === 'HCONLTTIPO') { cntx.data.ltHconlttipo = cntxLite.data.liteList; cntx.data.ltMHconlttipo = cntxLite.data.liteMap; d.resolve(); }
		else if (cntxLite.form.tbla === 'HCONMDTIPO') { cntx.data.ltHconmdtipo = cntxLite.data.liteList; cntx.data.ltMHconmdtipo = cntxLite.data.liteMap; d.resolve(); }
		else if (cntxLite.form.tbla === 'ANUALIDAD')  { cntx.data.ltAnualidad  = cntxLite.data.liteList; cntx.data.ltMAnualidad  = cntxLite.data.liteMap; d.resolve(); }
		else if (cntxLite.form.tbla === 'MES')        { cntx.data.ltMes        = cntxLite.data.liteList; cntx.data.ltMMes        = cntxLite.data.liteMap; d.resolve(); }
		else if (cntxLite.form.tbla === 'PRESESTA')   { cntx.data.ltPresesta   = cntxLite.data.liteList; cntx.data.ltMPresesta   = cntxLite.data.liteMap; d.resolve(); }
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
		if (cntxPara.form.tbla === 'PERIPRESUP') { cntx.data.prPeripresup = cntxPara.data.para; d.resolve(); }
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

	////////////////////////////////////////////////////////////////
	// cate/form: edición/alta de categorías                      //
	////////////////////////////////////////////////////////////////
	function srvCateForm(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.iden,
			desl: cntx.form.desl,
			desc: cntx.form.desc,
			pres: cntx.form.pres
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cate/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.cate = data.OUTPUT['cate'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//FIXME: cambiar cateMap por cateListMap
	////////////////////////////////////////////////////////////////
	// cate/list: lista de categorías                             //
	////////////////////////////////////////////////////////////////
	function srvCateList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cate/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.cateList = data.OUTPUT['cateList'];
				cntx.data.cateMap  = data.OUTPUT['cateListMap'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// conc/form: edición/alta de conceptos                       //
	////////////////////////////////////////////////////////////////

	function srvConcForm(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			iden: cntx.form.iden,
			cate: cntx.form.cate,
			desl: cntx.form.desl,
			desc: cntx.form.desc
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.conc = data.OUTPUT['conc'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// conc/full: lista de conceptos (completa)                   //
	////////////////////////////////////////////////////////////////
	function srvConcFull(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/full/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.concFullList = data.OUTPUT['concList'];
				cntx.data.concFullMap  = data.OUTPUT['concListMap'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// conc/list: lista de conceptos                              //
	////////////////////////////////////////////////////////////////
	function srvConcList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			cate: parseInt(cntx.form.cate)
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/conc/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.concList = data.OUTPUT['concList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// cuen/form: edición/alta de cuentas                         //
	////////////////////////////////////////////////////////////////

	function srvCuenForm(cntx) {
		var dataRequest = {
				sesi: parseInt($rootScope.esta.sesi),
				iden: cntx.form.iden,
				tipo: cntx.form.tipo,
				desc: cntx.form.desc,
				sald: cntx.form.sald
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cuen/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.cuen = data.OUTPUT['cuen'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// cuen/list: lista de cuentas                                //
	////////////////////////////////////////////////////////////////
	function srvCuenList(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi)
		};

		var d = $q.defer();

		var output = srv.call(targetHost + 'service/angular/cuen/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.cuenList = data.OUTPUT['cuenList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// cuen/tras: traspaso entre cuentas                          //
	////////////////////////////////////////////////////////////////
	function srvCuenTras(cntx) {
		var impo = cntx.form.impo;
		impo = impo.toString().replace(',', '.');
		
		var fmtFeva;
		if (cntx.form.feva === undefined || cntx.form.feva === '' || cntx.form.feva === null) {
			fmtFeva = 0;
		} else {
			var yf=cntx.form.feva.getFullYear();           
			var mf=cntx.form.feva.getMonth() + 1;
			var df=cntx.form.feva.getDate();
			fmtFeva = (yf*10000)+(mf*100)+df;
		}
		
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			ctor : cntx.form.ctor,
			ctde : cntx.form.ctde,
			impo : parseFloat(impo),
			feva : parseInt(fmtFeva),
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/tras/', dataRequest);
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
	// cuen/cuad: cuadre de cuentas                               //
	////////////////////////////////////////////////////////////////
	function srvCuenCuad(cntx) {
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			cate: parseInt(cntx.form.cate),
			conc: parseInt(cntx.form.conc),
			impo: parseFloat(cntx.form.impo),
			sald: parseFloat(cntx.form.cuen.sald).toFixed(2), //TODO: validación de concurrencia. No funciona por redondeo en backend
			cuen: parseInt(cntx.form.cuen.iden)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/cuen/cuad/', dataRequest);
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
	// hcon/anul: anulación de apuntes                            //
	////////////////////////////////////////////////////////////////
	function srvHconAnul(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			iden : parseInt(cntx.conf.iden)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/anul/', dataRequest);
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
	// hcon/form: alta/modificación de apuntes                    //
	////////////////////////////////////////////////////////////////
	function srvHconForm(cntx) {
		var fmtFeva;
		if (cntx.form.feva === undefined || cntx.form.feva === '' || cntx.form.feva === null) {
			fmtFeva = 0;
		} else {
			var yf=cntx.form.feva.getFullYear();           
			var mf=cntx.form.feva.getMonth() + 1;
			var df=cntx.form.feva.getDate();
			fmtFeva = (yf*10000)+(mf*100)+df;
		}
		
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			tipo: cntx.form.tipo,
			iden: parseInt(cntx.form.iden),
			cuen: parseInt(cntx.form.cuen),
			cate: parseInt(cntx.form.cate),
			conc: parseInt(cntx.form.conc),
			impo: parseFloat(cntx.form.impo),
			feva: parseInt(fmtFeva),
			desc: cntx.form.desc
		};
		
		var d = $q.defer();
		
		//FIXME: homogeneizar la llamada a backend como hcon-form
		var output = srv.call(targetHost + 'service/angular/hcon/apun/', dataRequest);
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
	// hcon/list: lista de apuntes                                //
	////////////////////////////////////////////////////////////////
	function srvHconList(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo : cntx.form.tipo,
			anua : parseInt(cntx.form.anua),
			mesh : parseInt(cntx.form.mesh),
			cate : parseInt(cntx.form.cate),
			conc : parseInt(cntx.form.conc)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.hconList = data.OUTPUT['hconList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	//TODO: probablemente sea mejor tener en front-end dos servicios, con la acción a huevo... y aunque quedara
	//      como está, la acción e identificador no deben estar en el área de configuración
	////////////////////////////////////////////////////////////////
	// hcon/pres/gest: gestión de apuntes respecto al presupuesto //
	////////////////////////////////////////////////////////////////
	function srvHconPresGest(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			iden : parseInt(cntx.conf.iden),
			acci : cntx.conf.acci
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/hcon/pres/gest/', dataRequest);
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
	// pres/anua: consulta de presupuesto anual                   //
	////////////////////////////////////////////////////////////////
	function srvPresAnua(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo: 'LT02',
			anua: parseInt(cntx.form.anua)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.presList = data.OUTPUT['presList'];
				cntx.data.presListAnua = data.OUTPUT['presListAnua'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// pres/conc: consulta de presupuesto anual por conceptos     //
	////////////////////////////////////////////////////////////////
	function srvPresConc(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo: 'LT04',
			anua: parseInt(cntx.form.anua)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.presCateList = data.OUTPUT['presList'];
				cntx.data.presListMap  = data.OUTPUT['presListMap'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// pres/esta: cambio de estado de partida presupuestaria      //
	////////////////////////////////////////////////////////////////
	function srvPresEsta(cntx) {

		var esta = "";
		
		if (cntx.data.pres.esta === 'A') {
			esta = 'C';
		} else {
			esta = 'A';
		}

		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
			anua: parseInt(cntx.data.pres.anua),
			mesp: parseInt(cntx.data.pres.mesp),
			cate: parseInt(cntx.data.pres.cate),
			conc: parseInt(cntx.data.pres.conc),
			esta: esta
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/esta/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.pres = data.OUTPUT['pres'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// pres/mesp: consulta de presupuesto mensual                 //
	////////////////////////////////////////////////////////////////
	function srvPresMesp(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo: 'LT03',
			anua: parseInt(cntx.form.anua),
			mesp: parseInt(cntx.form.mesp)
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.presCateList = data.OUTPUT['presList'];
				cntx.data.presListMap  = data.OUTPUT['presListMap'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// pres/resu: consulta de resumen de presupuesto              //
	////////////////////////////////////////////////////////////////
	function srvPresResu(cntx) {
		var dataRequest = {
			sesi : parseInt($rootScope.esta.sesi),
			tipo: 'LT01'
		};
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/pres/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.presList = data.OUTPUT['presList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}

}]);