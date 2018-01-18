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
		else if (name === 'cuen/list') { return srvCuenList(cntx); }
		else if (name === 'cuen/tras') { return srvCuenTras(cntx); }
		else if (name === 'cuen/cuad') { return srvCuenCuad(cntx); }
		else if (name === 'hcon/form') { return srvHconForm(cntx); }
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
			fmtFval = (yf*10000)+(mf*100)+df;
		}

		//TODO: meter el iden para permitir la edición
		var dataRequest = {
			sesi: parseInt($rootScope.esta.sesi),
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

}]);
