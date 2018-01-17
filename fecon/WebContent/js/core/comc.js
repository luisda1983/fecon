//Factory que encapsula la comunicación con BackEnd
app.factory("comc", ['$rootScope', '$q', 'srv', 'coma', function($rootScope, $q, srv, coma) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	function request(name, cntx) {
		
		if      (name === 'lite/list') { return srvLiteList(cntx); }
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
			     if (tbla === 'BOOL')     { cntx.data.ltBool     = cntxLite.data.liteList; d.resolve(); }
			//TODO: los literales de aplicación no deberían estar aquí
			else if (tbla === 'CUENTIPO') { cntx.data.ltCuentipo = cntxLite.data.liteList; d.resolve(); }
			//else if
			//Si no tenemos mapeada la tabla de literales, rechazamos el promise
			else { d.reject(); }
		   //Si hay errores en la operación, rechazamos el promise
		}, function() {
			d.reject();
		})
		return d.promise;
	}
	
	//Interfaz del servicio comc
	return {
		request: request,
		requestLiteList: requestLiteList
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
				d.reject('NOK');
			} else {
				cntx.data.liteList = data.OUTPUT['liteList'];
				d.resolve(data);
			}
		});
		return d.promise;
	}
}]);
