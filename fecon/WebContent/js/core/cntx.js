//Factory que encapsula la gestión de contexto de la aplicación
app.factory("cntx", ['$q', function($q) {
	
	//Función que devuelve el Contexto de la vista recibida.
	function getCntx(view) {
		
		var cntx = null;
		
		     if (view === 'pres/anua') { return getPresAnuaCntx(); }
		else if (view === 'pres/conc') { return getPresConcCntx(); }
		else if (view === 'pres/mesp') { return getPresMespCntx(); }
		
		return cntx;
	}
		
	//Interfaz del servicio cntx
	return {
		getCntx: getCntx
	};
	
	//Contexto presAnua
	function getPresAnuaCntx() {
		var vForm = {
			anua: 0
		};
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltPresesta: null,
			ltMes: null,
			presListAnua: new Array(),
			presList: new Array(),
			pres: null
		};
		var vConf = {
			itm1: -1,
			itm2: -1
		};
		cntx = {
			form: vForm,
			data: vData,
			conf: vConf
		};
		return cntx;
	}
	
	//Contexto presMesp
	function getPresMespCntx() {
		var vForm = {
			anua: 0,
			mesp: 0
		};
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltPresesta: null,
			ltMes: null,
			presCateList: new Array(),
			presListMap : new Map(),
			pres: null
		};
		var vConf = {
			item: -1,
			cate: -1
		}
		cntx = {
			form: vForm,
			data: vData,
			conf: vConf
		};
		return cntx;
	}
	
	//Contexto presConc
	function getPresConcCntx() {
		var vForm = {
			anua: 0
		};
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltPresesta: null,
			presCateList: new Array(),
			presListMap: new Map()
		};
		var vConf = {
			item: -1,
			cate: -1
		}
		cntx = {
			form: vForm,
			data: vData,
			conf: vConf
		};
		return cntx;
	}
}]);
