//Factory que encapsula la comunicación con BackEnd
app.factory("comc", ['$rootScope', '$q', 'srv', 'coma', function($rootScope, $q, srv, coma) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	function request(name, cntx) {
		
		     if (name === 'lite/list') { return srvLiteList(cntx); }
		else if (name === 'para/get')  { return srvParaGet(cntx);  }
		else if (name === 'avis/list') { return srvAvisList(cntx); }
		else if (name === 'invi/list') { return srvInviList(cntx); }
		else if (name === 'invi/acep') { return srvInviAcep(cntx); }
		else if (name === 'invi/rech') { return srvInviRech(cntx); }
		//else if (name === 'servicioCore') { }
		else {
			return coma.request(name, cntx);
		}
	}

	//Funcion para lectura de lista de literales
	function requestLiteList(tbla, cntx) {
		//Creamos el promise de retorno
		var d = $q.defer();
		
		//Obtenemos y formateamos el contexto de consulta de lista de literales
		var cntxLite = srv.getCntx('lite/list');
		cntxLite.form.tbla = tbla;
		
		//Request a la operación de lista de literales
		var srv1 = request('lite/list', cntxLite);
		$q.all([srv1]).then(function() {
			//Mapeamos la lista de literales al campo asociado al mismo y resolvemos el promise
			     if (tbla === 'BOOL')       { cntx.data.ltBool       = cntxLite.data.liteList; cntx.data.ltMBool       = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'INVIESTA')   { cntx.data.ltInviesta   = cntxLite.data.liteList; cntx.data.ltMInviesta   = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'INVITIPO')   { cntx.data.ltInvitipo   = cntxLite.data.liteList; cntx.data.ltMInvitipo   = cntxLite.data.liteMap; d.resolve(); }
			//TODO: los literales de aplicación no deberían estar aquí
			else if (tbla === 'CUENTIPO')   { cntx.data.ltCuentipo   = cntxLite.data.liteList; cntx.data.ltMCuentipo   = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'HCONLTTIPO') { cntx.data.ltHconlttipo = cntxLite.data.liteList; cntx.data.ltMHconlttipo = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'ANUALIDAD')  { cntx.data.ltAnualidad  = cntxLite.data.liteList; cntx.data.ltMAnualidad  = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'MES')        { cntx.data.ltMes        = cntxLite.data.liteList; cntx.data.ltMMes        = cntxLite.data.liteMap; d.resolve(); }
			else if (tbla === 'PRESESTA')   { cntx.data.ltPresesta   = cntxLite.data.liteList; cntx.data.ltMPresesta   = cntxLite.data.liteMap; d.resolve(); }
			//else if
			//Si no tenemos mapeada la tabla de literales, rechazamos el promise
			else { d.reject(); }
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
		var cntxPara = srv.getCntx('para/get');
		cntxPara.form.tipo = tipo;
		cntxPara.form.tbla = tbla;
		cntxPara.form.clav = clav;
		
		//Request a la operación de consulta de parámetros
		var srv1 = request('para/get', cntxPara);
		$q.all([srv1]).then(function() {
			//Mapeamos el parámetro al campo asociado al mismo y resolvemos el promise
			     if (tbla === 'PERIPRESUP') { cntx.data.prPeripresup = cntxPara.data.para; d.resolve(); }
			//else if
			//Si no tenemos mapeado el parámetro, rechazamos el promise
			else { d.reject(); }
		   //Si hay errores en la operación, rechazamos el promise
		}, function() {
			d.reject();
		})
		return d.promise;
	}

	//Interfaz del servicio comc
	return {
		request        : request,
		requestLiteList: requestLiteList,
		requestParaGet : requestParaGet
	};
	
	////////////////////////////////////////////////////////////////
	// lite/list: Lista de tabla de literales                     //
	////////////////////////////////////////////////////////////////
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
		
		var output = srv.call(targetHost + 'service/angular/rech/acep/', dataRequest);
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
