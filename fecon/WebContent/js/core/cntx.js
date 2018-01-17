//Factory que encapsula la gestión de contexto de la aplicación
app.factory("cntx", ['$q', function($q) {
	
	//Función que devuelve el Contexto de la vista recibida.
	function getCntx(view) {
		
		var cntx = null;
		
		     if (view === 'pres/anua') { return getPresAnuaCntx(); }
		else if (view === 'pres/conc') { return getPresConcCntx(); }
		else if (view === 'pres/mesp') { return getPresMespCntx(); }
		else if (view === 'cate/list') { return getCateListCntx(); }
		else if (view === 'cate/form') { return getCateFormCntx(); }
		else if (view === 'conc/list') { return getConcListCntx(); }
		else if (view === 'conc/form') { return getConcFormCntx(); }
		else if (view === 'cuen/list') { return getCuenListCntx(); }
		else if (view === 'cuen/form') { return getCuenFormCntx(); }
		else if (view === 'cuen/tras') { return getCuenTrasCntx(); }
		else if (view === 'lite/list') { return getLiteListCntx(); }

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
	
	//Contexto cateList
	function getCateListCntx() {
		var vForm = {
			ndat: null
		};
		var vShow = {
			ndat: null
		};
		var vRead = {
			ndat: null
		}
		var vData = {
			cateList: new Array()
		};
		var vConf = {
			item: -1
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}
	
	//Contexto cateForm
	function getCateFormCntx() {
		var vForm = {
			iden: 0,
			desl: '',
			desc: '',
			orde: 0,
			pres: ''
		};
		var vShow = {
			iden: false,
			desl: false,
			desc: false,
			orde: false,
			pres: false
		};
		var vRead = {
			iden: false,
			desl: false,
			desc: false,
			orde: false,
			pres: false
		};
		var vData = {
			cate: null,
			ltBool: new Array()
		};
		var vConf = {
			mode: ''
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto concList
	function getConcListCntx() {
		var vForm = {
			cate: 0
		};
		var vShow = {
			cate: false
		};
		var vRead = {
			cate: true
		};
		var vData = {
			concList: null
		};
		var vConf = {
			item: -1
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto concForm
	function getConcFormCntx() {
		var vForm = {
			iden: 0,
			cate: 0,
			desl: '',
			desc: '',
			orde: 0
		};
		var vShow = {
			iden: false,
			cate: false,
			desl: false,
			desc: false,
			orde: false
		};
		var vRead = {
			iden: false,
			cate: false,
			desl: false,
			desc: false,
			orde: false
		};
		var vData = {
			cate: null,
			conc: null
		};
		var vConf = {
			mode: ''
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto cuenList
	function getCuenListCntx() {
		var vForm = {
			ndat: null
		};
		var vShow = {
			ndat: null
		};
		var vRead = {
			ndat: null
		}
		var vData = {
			cuenList: null
		};
		var vConf = {
			item: -1
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto cuenForm
	function getCuenFormCntx() {
		var vForm = {
			iden : 0,
			tipo : '',
			desc : '',
			sald : 0
		};
		var vShow = {
			iden: false,
			tipo: false,
			desc: false,
			sald: false
		};
		var vRead = {
			iden: false,
			tipo: false,
			desc: false,
			sald: false
		};
		var vData = {
			cuen: null,
			ltCuentipo: null
		};
		var vConf = {
			mode: ''
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto cuenTras
	function getCuenTrasCntx() {
		var vForm = {
			ctor : 0,
			ctde : 0,
			impo : 0,
			feva : 0
		};
		var vShow = {
			ctor: false,
			ctde: false,
			impo: false,
			feva: false
		};
		var vRead = {
			ctor: false,
			ctde: false,
			impo: false,
			feva: false
		};
		var vData = {
			cuenList: null
		};
		var vConf = {
			mode: ''
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto liteList
	function getLiteListCntx() {
		var vForm = {
			tbla: ''
		};
		var vShow = {
			tbla: false
		};
		var vRead = {
			tbla: false
		};
		var vData = {
			liteList: new Array()
		};
		var vConf = {
			mode: ''
		};
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

}]);
