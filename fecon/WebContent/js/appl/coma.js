//Factory que encapsula la comunicación con BackEnd, para los servicios de aplicación
app.factory("coma", ['$rootScope', '$q', 'srv', function($rootScope, $q, srv) {
	
	//Función por la que pasarán todas las llamadas a BackEnd.
	//La entrada es un identificador de llamada y un contexto.
	//La salida es un promise.
	
	function request(name, cntx) {
		
		var d = $q.defer();
		
		     if (name === 'cate/form') { return srvCateForm(cntx); }
		else if (name === 'cate/list') { return srvCateList(cntx); }
		else if (name === 'conc/form') { return srvConcForm(cntx); }
		else if (name === 'conc/list') { return srvConcList(cntx); }
		else if (name === 'cuen/form') { return srvCuenForm(cntx); }
		else {
			d.reject();
			return d.promise;
		}
	}
		
	//Interfaz del servicio coma
	return {
		request: request
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

}]);
