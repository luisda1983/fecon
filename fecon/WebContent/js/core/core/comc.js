//*****************************************************************************************************************//
// Factory que gestiona la comunicación con BackEnd                                                                //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.factory("comc", ['$rootScope', '$q', 'srv', 'form', 'coma', function($rootScope, $q, srv, form, coma) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	function request(name, cntx) {
		
		     if (name === 'lite/list') { return srvLiteList(cntx); }
		else if (name === 'lite/get' ) { return srvLiteGet(cntx);  } 
		else if (name === 'para/get' ) { return srvParaGet(cntx);  }
		else if (name === 'avis/list') { return srvAvisList(cntx); }
		else if (name === 'btch/acti') { return srvBtchActi(cntx); }
		else if (name === 'btch/list') { return srvBtchList(cntx); }
		else if (name === 'btch/susp') { return srvBtchSusp(cntx); }
		else if (name === 'ctmn/acti') { return srvCtmnActi(cntx); }
		else if (name === 'ctmn/desa') { return srvCtmnDesa(cntx); }
		else if (name === 'ctmn/form') { return srvCtmnForm(cntx); }
		else if (name === 'ctmn/list') { return srvCtmnList(cntx); }
		else if (name === 'ctrl/list') { return srvCtrlList(cntx); }
		else if (name === 'dele/form') { return srvDeleForm(cntx); }
		else if (name === 'dele/list') { return srvDeleList(cntx); }
		else if (name === 'domi/form') { return srvDomiForm(cntx); }
		else if (name === 'domi/list') { return srvDomiList(cntx); }
		else if (name === 'dtmn/acti') { return srvDtmnActi(cntx); }
		else if (name === 'dtmn/desa') { return srvDtmnDesa(cntx); }
		else if (name === 'dtmn/form') { return srvDtmnForm(cntx); }
		else if (name === 'dtmn/list') { return srvDtmnList(cntx); }
		else if (name === 'ejec/list') { return srvEjecList(cntx); }
		else if (name === 'inst/acti') { return srvInstActi(cntx); }
		else if (name === 'inst/inac') { return srvInstInac(cntx); }
		else if (name === 'inst/list') { return srvInstList(cntx); }
		else if (name === 'inst/norm') { return srvInstNorm(cntx); }
		else if (name === 'inst/prem') { return srvInstPrem(cntx); }
		else if (name === 'inst/regi') { return srvInstRegi(cntx); }
		else if (name === 'invi/acep') { return srvInviAcep(cntx); }
		else if (name === 'invi/envi') { return srvInviEnvi(cntx); }
		else if (name === 'invi/list') { return srvInviList(cntx); }
		else if (name === 'invi/proc') { return srvInviProc(cntx); }
		else if (name === 'invi/rech') { return srvInviRech(cntx); }
		else if (name === 'invi/soli') { return srvInviSoli(cntx); }
		else if (name === 'invi/vali') { return srvInviVali(cntx); }
		else if (name === 'logp/list') { return srvLogpList(cntx); }
		else if (name === 'menu/make') { return srvMenuMake(cntx); }
		else if (name === 'mpla/acti') { return srvMplaActi(cntx); }
		else if (name === 'mpla/desa') { return srvMplaDesa(cntx); }
		else if (name === 'mpla/list') { return srvMplaList(cntx); }
		else if (name === 'notf/edit') { return srvNotfEdit(cntx); }
		else if (name === 'notf/list') { return srvNotfList(cntx); }
		else if (name === 'plan/list') { return srvPlanList(cntx); }
		else if (name === 'sesi/list') { return srvSesiList(cntx); }
		else if (name === 'stdi/list') { return srvStdiList(cntx); }
		else if (name === 'stme/list') { return srvStmeList(cntx); }
		else if (name === 'stst/list') { return srvStstList(cntx); }
		else if (name === 'usua/acti') { return srvUsuaActi(cntx); }
		else if (name === 'usua/exit') { return srvUsuaExit(cntx); }
		else if (name === 'usua/inac') { return srvUsuaInac(cntx); }     
		else if (name === 'usua/lgon') { return srvUsuaLgon(cntx); }
		else if (name === 'usua/list') { return srvUsuaList(cntx); }
		else if (name === 'usua/regi') { return srvUsuaRegi(cntx); }
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
			     if (tbla === 'BOOL')       { cntx.data.set('ltMBool'   , cntxLite.data.liteMap); cntx.data.set('ltLBool'     , cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'ANYO')       { cntx.data.set('ltMAnyo'   , cntxLite.data.liteMap); cntx.data.set('ltLAnyo'     , cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'MES')        { cntx.data.set('ltMes'     , cntxLite.data.liteMap); d.resolve(); }
			else if (tbla === 'INVIESTA')   { cntx.data.set('ltInviesta', cntxLite.data.liteMap); d.resolve(); }
			else if (tbla === 'INVITIPO')   { cntx.data.set('ltInvitipo', cntxLite.data.liteMap); d.resolve(); }
			else if (tbla === 'MENUPERF')   { cntx.data.set('ltMMenuperf', cntxLite.data.liteMap); cntx.data.set('ltLMenuperf', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'INSTESTA')   { cntx.data.set('ltMInstesta', cntxLite.data.liteMap); cntx.data.set('ltLInstesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'INSTTIPO')   { cntx.data.set('ltMInsttipo', cntxLite.data.liteMap); cntx.data.set('ltLInsttipo', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'USUAPERF')   { cntx.data.set('ltMUsuaperf', cntxLite.data.liteMap); cntx.data.set('ltLUsuaperf', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'SESIESTA')   { cntx.data.set('ltMSesiesta', cntxLite.data.liteMap); cntx.data.set('ltLSesiesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'MPLAESTA')   { cntx.data.set('ltMMplaesta', cntxLite.data.liteMap); cntx.data.set('ltLMplaesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'BTCHESTA')   { cntx.data.set('ltMBtchesta', cntxLite.data.liteMap); cntx.data.set('ltLBtchesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'BTCHTIPO')   { cntx.data.set('ltMBtchtipo', cntxLite.data.liteMap); cntx.data.set('ltLBtchtipo', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'EJECESTA')   { cntx.data.set('ltMEjecesta', cntxLite.data.liteMap); cntx.data.set('ltLEjecesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'PLANESTA')   { cntx.data.set('ltMPlanesta', cntxLite.data.liteMap); cntx.data.set('ltLPlanesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'STSTREEJ')   { cntx.data.set('ltMStstreej', cntxLite.data.liteMap); cntx.data.set('ltLStstreej', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'SESIDVCE')   { cntx.data.set('ltMSesidvce', cntxLite.data.liteMap); cntx.data.set('ltLSesidvce', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'CTRLESTA')   { cntx.data.set('ltMCtrlesta', cntxLite.data.liteMap); cntx.data.set('ltLCtrlesta', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'CTRLTIAC')   { cntx.data.set('ltMCtrltiac', cntxLite.data.liteMap); cntx.data.set('ltLCtrltiac', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'NOTFAPLI')   { cntx.data.set('ltMNotfapli', cntxLite.data.liteMap); cntx.data.set('ltLNotfapli', cntxLite.data.liteList); d.resolve(); }
			else if (tbla === 'NOTFTIPO')   { cntx.data.set('ltMNotftipo', cntxLite.data.liteMap); cntx.data.set('ltLNotftipo', cntxLite.data.liteList); d.resolve(); }
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

	//*************************************************************************************************************//
	// PUBLIC: requestParaGet: Función para lectura de parámetros.                                                 //
	//*************************************************************************************************************//
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
			else if (tbla === 'APLICONFIG' && clav === 'MULTIINSTA') { cntx.data.set('prMultiinsta', cntxPara.data.para); d.resolve(); }
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
			tbla: cntx.form.tbla
		};
		setBase(dataRequest, cntx);

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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// lite/get: Recuperación de un literal.                                                                       //
	//*************************************************************************************************************//
	function srvLiteGet(cntx) {
		var dataRequest = {
			tbla: cntx.form.tbla,
			clav: cntx.form.clav
		};
		setBase(dataRequest, cntx);

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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	////////////////////////////////////////////////////////////////
	// para/get: Consulta de parámetros                           //
	////////////////////////////////////////////////////////////////
	function srvParaGet(cntx) {
		var dataRequest = {
			tipo: cntx.form.tipo,
			tbla: cntx.form.tbla,
			clav: cntx.form.clav
		};
		setBase(dataRequest, cntx);

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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvMenuMake: Servicio de generación de menú.                                                       //
	//*************************************************************************************************************//
	function srvMenuMake(cntx) {
		var dataRequest = {
				
		};
		setBase(dataRequest, cntx);

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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvCtmnList: Servicio de consuta de categoría de menú.                                             //
	//*************************************************************************************************************//
	function srvCtmnList(cntx) {
		var dataRequest = {
			perf: cntx.form.get('perf').data
		};
		setBase(dataRequest, cntx);
	  
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
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
			iden: cntx.form.get('iden').data,
			perf: cntx.form.get('perf').data,
			desc: cntx.form.get('desc').data,
			acti: cntx.form.get('acti').data,
			orde: cntx.form.get('orde').data
		};
		setBase(dataRequest, cntx);
	  
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
			ctmn: cntx.form.get('ctmn').data
		};
		setBase(dataRequest, cntx);
	  
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
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
			ctmn: cntx.form.get('ctmn').data,
			iden: cntx.form.get('iden').data,
			desc: cntx.form.get('desc').data,
			acti: cntx.form.get('acti').data,
			orde: cntx.form.get('orde').data,
			path: cntx.form.get('path').data,
			icon: cntx.form.get('icon').data
		};
		setBase(dataRequest, cntx);
	  
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

	//*************************************************************************************************************//
	// PRIVATE: srvInstList: Servicio de consuta de instalaciones.                                                 //
	//*************************************************************************************************************//
	function srvInstList(cntx) {
		var dataRequest = {
			esta: cntx.form.get('esta').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('instList', data.OUTPUT['instList']);
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
	// PRIVATE: srvInstActi: Servicio de activación de instalación.                                                //
	//*************************************************************************************************************//
	function srvInstActi(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inst', data.OUTPUT['inst']);
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
	// PRIVATE: srvInstInac: Servicio de inactivación de instalación.                                              //
	//*************************************************************************************************************//
	function srvInstInac(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/inac/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inst', data.OUTPUT['inst']);
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
	// PRIVATE: srvInstPrem: Servicio de conversión de instalación en Premium.                                     //
	//*************************************************************************************************************//
	function srvInstPrem(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/prem/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inst', data.OUTPUT['inst']);
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
	// PRIVATE: srvInstNorm: Servicio de eliminación de Premium a instalación.                                     //
	//*************************************************************************************************************//
	function srvInstNorm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/norm/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inst', data.OUTPUT['inst']);
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
	// PRIVATE: srvInstRegi: Servicio de registro de instalación.                                                  //
	//*************************************************************************************************************//
	function srvInstRegi(cntx) {
		var dataRequest = {
			desc: cntx.form.get('desc').data,
			numo: cntx.form.get('numo').data,
			mail: cntx.form.get('mail').data,
			usua: cntx.form.get('usua').data,
			pass: cntx.form.get('pass').data,
			cpas: cntx.form.get('cpas').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/inst/regi/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('inst', data.OUTPUT['inst']);
				cntx.data.set('usua', data.OUTPUT['usua']);
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
			
		};
		setBase(dataRequest, cntx);
	  
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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviList: Servicio de consuta de invitaciones.                                                  //
	//*************************************************************************************************************//
	function srvInviList(cntx) {
		var dataRequest = {
			esta: cntx.form.get('esta').data
		};
		setBase(dataRequest, cntx);
	  
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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviEnvi: Servicio de envío de invitaciones.                                                    //
	//*************************************************************************************************************//
	function srvInviEnvi(cntx) {
		var dataRequest = {
			mail: cntx.form.get('mail').data
		};
		setBase(dataRequest, cntx);
	  
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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviAcep: Servicio de aceptación de invitación.                                                 //
	//*************************************************************************************************************//
	function srvInviAcep(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

	//*************************************************************************************************************//
	// PRIVATE: srvInviRech: Servicio de rechazo de invitación.                                                    //
	//*************************************************************************************************************//
	function srvInviRech(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
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
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
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
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/soli/', dataRequest);
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
	// PRIVATE: srvInviVali: Servicio de validación de invitación.                                                 //
	//*************************************************************************************************************//
	function srvInviVali(cntx) {
		var dataRequest = {
			iden: cntx.form.get('invi').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/vali/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('invi', data.OUTPUT['invi']);
				cntx.data.set('exis', data.OUTPUT['exis']);
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
	// PRIVATE: srvInviProc: Servicio de procesado de invitación.                                                  //
	//*************************************************************************************************************//
	function srvInviProc(cntx) {
		var dataRequest = {
			iden : cntx.form.get('invi').data,
			desc : cntx.form.get('desc').data,
			numo : cntx.form.get('numo').data,
			mail : cntx.form.get('mail').data,
			usua : cntx.form.get('usua').data,
			pass : cntx.form.get('pass').data,
			cpas : cntx.form.get('cpas').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/invi/proc/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('invi', data.OUTPUT['invi']);
				cntx.data.set('usua', data.OUTPUT['usua']);
				cntx.data.set('inst', data.OUTPUT['inst']);
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
	// PRIVATE: srvSesiList: Servicio de consulta de sesiones.                                                     //
	//*************************************************************************************************************//
	function srvSesiList(cntx) {
		var dataRequest = {
			esta: cntx.form.get('esta').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/sesi/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('sesiList', data.OUTPUT['sesiList']);
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
	// PRIVATE: srvUsuaLgon: Servicio de login de usuario.                                                         //
	//*************************************************************************************************************//
	function srvUsuaLgon(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			pass: cntx.form.get('pass').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/lgon/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('sesi', data.OUTPUT['sesi']);
				cntx.data.set('usua', data.OUTPUT['usua']);
				cntx.data.set('diip', data.OUTPUT['diip']);
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
	// PRIVATE: srvUsuaExit: Servicio de logout de usuario.                                                        //
	//*************************************************************************************************************//
	function srvUsuaExit(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/exit/', dataRequest);
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
	// PRIVATE: srvUsuaRegi: Servicio de registro de usuario.                                                      //
	//*************************************************************************************************************//
	function srvUsuaRegi(cntx) {
		var dataRequest = {
			codi: cntx.form.get('codi').data,
			numo: cntx.form.get('numo').data,
			mail: cntx.form.get('mail').data,
			iden: cntx.form.get('usua').data,
			pass: cntx.form.get('pass').data,
			cpas: cntx.form.get('cpas').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/regi/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('usua', data.OUTPUT['usua']);
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
	// PRIVATE: srvUsuaList: Servicio de consulta de usuarios.                                                     //
	//*************************************************************************************************************//
	function srvUsuaList(cntx) {
		var dataRequest = {
			inst: cntx.form.get('inst').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('usuaList', data.OUTPUT['usuaList']);
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
	// PRIVATE: srvUsuaActi: Servicio de activación de usuario.                                                    //
	//*************************************************************************************************************//
	function srvUsuaActi(cntx) {
		var dataRequest = {
			inst: cntx.form.get('inst').data,
			iden: cntx.form.get('usua').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('usua', data.OUTPUT['usua']);
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
	// PRIVATE: srvUsuaInac: Servicio de inactivación de usuario.                                                  //
	//*************************************************************************************************************//
	function srvUsuaInac(cntx) {
		var dataRequest = {
			inst: cntx.form.get('inst').data,
			iden: cntx.form.get('usua').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/usua/inac/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('usua', data.OUTPUT['usua']);
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
	// PRIVATE: srvMplaList: Servicio de consuta de maestro de planificación.                                      //
	//*************************************************************************************************************//
	function srvMplaList(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/mpla/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('mplaList', data.OUTPUT['mplaList']);
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
	// PRIVATE: srvNotfEdit: Servicio de edición de notificaciones.                                                //
	//*************************************************************************************************************//
	function srvNotfEdit(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			tipo: cntx.form.get('tipo').data,
			desc: cntx.form.get('desc').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/notf/edit/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('notf', data.OUTPUT['notf']);
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
	// PRIVATE: srvNotfList: Servicio de consuta de notificaciones.                                                //
	//*************************************************************************************************************//
	function srvNotfList(cntx) {
		var dataRequest = {
			apli: cntx.form.get('apli').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/notf/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('notfList', data.OUTPUT['notfList']);
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
	// PRIVATE: srvMplaActi: Servicio de activación de maestro de planificación.                                   //
	//*************************************************************************************************************//
	function srvMplaActi(cntx) {
		var dataRequest = {
			hora: cntx.form.get('hora').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/mpla/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('mpla', data.OUTPUT['mpla']);
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
	// PRIVATE: srvMplaDesa: Servicio de desactivación de maestro de planificación.                                //
	//*************************************************************************************************************//
	function srvMplaDesa(cntx) {
		var dataRequest = {
			hora: cntx.form.get('hora').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/mpla/desa/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('mpla', data.OUTPUT['mpla']);
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
	// PRIVATE: srvBtchList: Servicio de consuta de procesos Batch.                                                //
	//*************************************************************************************************************//
	function srvBtchList(cntx) {
		var dataRequest = {
			
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/btch/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('btchList', data.OUTPUT['btchList']);
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
	// PRIVATE: srvBtchActi: Servicio de activación de proceso Batch.                                              //
	//*************************************************************************************************************//
	function srvBtchActi(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/btch/acti/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('btch', data.OUTPUT['btch']);
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
	// PRIVATE: srvBtchSusp: Servicio de suspensión de proceso Batch.                                              //
	//*************************************************************************************************************//
	function srvBtchSusp(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/btch/susp/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('btch', data.OUTPUT['btch']);
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
	// PRIVATE: srvEjecList: Servicio de consuta de ejecuciones.                                                   //
	//*************************************************************************************************************//
	function srvEjecList(cntx) {
		var fmtFech;
		var fech = cntx.form.get('fech').data;
		if (fech === undefined || fech === '' || fech === null) {
			fmtFech = 0;
		} else {
			var yf=fech.getFullYear();           
			var mf=fech.getMonth() + 1;
			var df=fech.getDate();
			fmtFech = (yf*10000)+(mf*100)+df;
		}
		//Es necesario indicar que consultamos por hora (por que el 0, es una hora válida)
		var inpl = false;
		if (cntx.conf.get('mode') === 'P') {
			inpl = true;
		}
		
		var dataRequest = {
			fech: fmtFech,
			hora: cntx.form.get('hora').data,
			inpl: inpl
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ejec/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('ejecList', data.OUTPUT['ejecList']);
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
	// PRIVATE: srvPlanList: Servicio de consuta de pases Batch.                                                   //
	//*************************************************************************************************************//
	function srvPlanList(cntx) {
		var fmtFech;
		var fech = cntx.form.get('fech').data;
		if (fech === undefined || fech === '' || fech === null) {
			fmtFech = 0;
		} else {
			var yf=fech.getFullYear();           
			var mf=fech.getMonth() + 1;
			var df=fech.getDate();
			fmtFech = (yf*10000)+(mf*100)+df;
		}
		var dataRequest = {
			fech: fmtFech,
			proc: cntx.form.get('proc').data
		};
		setBase(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/plan/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('planList', data.OUTPUT['planList']);
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
	// PRIVATE: srvLogpList: Servicio de consuta de Log.                                                           //
	//*************************************************************************************************************//
	function srvLogpList(cntx) {
		var dataRequest = {
			tipo: cntx.form.get('tipo').data,
			iden: cntx.form.get('iden').data,
			fech: cntx.form.get('fech').data,
			hora: cntx.form.get('hora').data
		};
		setBase(dataRequest, cntx);
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/logp/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('logpList', data.OUTPUT['logpList']);
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
	// PRIVATE: srvStstList: Servicio de consuta de Estadísticas.                                                  //
	//*************************************************************************************************************//
	function srvStstList(cntx) {
		var fmtFech;
		var fech = cntx.form.get('fech').data;
		if (fech === undefined || fech === '' || fech === null) {
			fmtFech = 0;
		} else {
			var yf=fech.getFullYear();           
			var mf=fech.getMonth() + 1;
			var df=fech.getDate();
			fmtFech = (yf*10000)+(mf*100)+df;
		}
		var dataRequest = {
			fech: fmtFech
		};
		setBase(dataRequest, cntx);
		setCont(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/stst/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				lookCont(data, cntx);
				if (cntx.conf.get('CONT_ACTV')) {
					cntx.data.set('ststList', cntx.data.get('ststList').concat(data.OUTPUT['ststList']));
				} else {
					cntx.data.set('ststList', data.OUTPUT['ststList']);
				}
				endCont(data, cntx);
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
	// PRIVATE: srvStdiList: Servicio de consuta de Estadísticas Diarias.                                          //
	//*************************************************************************************************************//
	function srvStdiList(cntx) {
		var fmtFech;
		var fech = cntx.form.get('fech').data;
		if (fech === undefined || fech === '' || fech === null) {
			fmtFech = 0;
		} else {
			var yf=fech.getFullYear();           
			var mf=fech.getMonth() + 1;
			var df=fech.getDate();
			fmtFech = (yf*10000)+(mf*100)+df;
		}
		var dataRequest = {
			fech: fmtFech
		};
		setBase(dataRequest, cntx);
		setCont(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/stdi/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				lookCont(data, cntx);
				if (cntx.conf.get('CONT_ACTV')) {
					cntx.data.set('stdiList', cntx.data.get('stdiList').concat(data.OUTPUT['stdiList']));
				} else {
					cntx.data.set('stdiList', data.OUTPUT['stdiList']);
				}
				endCont(data, cntx);
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
	// PRIVATE: srvStmeList: Servicio de consuta de Estadísticas Mensuales.                                        //
	//*************************************************************************************************************//
	function srvStmeList(cntx) {
		var dataRequest = {
			anyo: cntx.form.get('anyo').data,
			mess: cntx.form.get('mess').data
		};
		setBase(dataRequest, cntx);
		setCont(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/stme/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				lookCont(data, cntx);
				if (cntx.conf.get('CONT_ACTV')) {
					cntx.data.set('stmeList', cntx.data.get('stmeList').concat(data.OUTPUT['stmeList']));
				} else {
					cntx.data.set('stmeList', data.OUTPUT['stmeList']);
				}
				endCont(data, cntx);
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
	// PRIVATE: srvCtrlList: Servicio de consuta de Controladores.                                                 //
	//*************************************************************************************************************//
	function srvCtrlList(cntx) {
		var dataRequest = {
		};
		setBase(dataRequest, cntx);
		setCont(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/ctrl/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				lookCont(data, cntx);
				if (cntx.conf.get('CONT_ACTV')) {
					cntx.data.set('ctrlList', cntx.data.get('ctrlList').concat(data.OUTPUT['ctrlList']));
				} else {
					cntx.data.set('ctrlList', data.OUTPUT['ctrlList']);
				}
				endCont(data, cntx);
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
	// PRIVATE: srvDomiList: Servicio de consuta de Dominios.                                                      //
	//*************************************************************************************************************//
	function srvDomiList(cntx) {
		var dataRequest = {
		};
		setBase(dataRequest, cntx);
		setCont(dataRequest, cntx);
		
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/domi/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('domiList', data.OUTPUT['domiList']);
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
	// PRIVATE: srvDomiForm: Servicio de gestión de formulario de dominio.                                         //
	//*************************************************************************************************************//
	function srvDomiForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			nomb: cntx.form.get('nomb').data,
			desc: cntx.form.get('desc').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/domi/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('domi', data.OUTPUT['domi']);
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
	// PRIVATE: srvDeleList: Servicio de listado de detalle de dominio.                                            //
	//*************************************************************************************************************//
	function srvDeleList(cntx) {
		var dataRequest = {
			domi: cntx.form.get('domi').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dele/list/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('deleList', data.OUTPUT['deleList']);
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
	// PRIVATE: srvDeleForm: Servicio de gestión de formulario de elementos de dominio.                            //
	//*************************************************************************************************************//
	function srvDeleForm(cntx) {
		var dataRequest = {
			iden: cntx.form.get('iden').data,
			domi: cntx.form.get('domi').data,
			valo: cntx.form.get('valo').data
		};
		setBase(dataRequest, cntx);
	  
		var d = $q.defer();
		
		var output = srv.call(targetHost + 'service/angular/dele/form/', dataRequest);
		output.then(function() {
			var data = srv.getData();
			if (data.EXEC_RC === 'V') {
				d.reject();
			} else {
				cntx.data.set('dele', data.OUTPUT['dele']);
				d.resolve(data);
			}
		}, function() {
			var status = srv.getData();
			srv.frontNotify('FRNT-00001', 'Error de comunicaciones (' + status + ')');
			d.reject();
		});
		return d.promise;
	}

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
