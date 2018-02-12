//Factory que encapsula la gestión de contexto de la aplicación
//TODO: en pres/conc... he visto que en el data no es necesario definir el detalle... al llamar a comc, se añade el 
//      atributo y se tiene visibilidad... analizar si se puede eliminar el detalle siempre
app.factory("cntx", ['$q', function($q) {
	
	//Función que devuelve el Contexto de la vista recibida.
	function getCntx(view) {
		
		var cntx = null;
		
		     if (view === 'pres/anua') { return getPresAnuaCntx(); }
		else if (view === 'pres/conc') { return getPresConcCntx(); }
		else if (view === 'pres/mesp') { return getPresMespCntx(); }
		else if (view === 'pres/resu') { return getPresResuCntx(); }
		else if (view === 'cate/list') { return getCateListCntx(); }
		else if (view === 'cate/form') { return getCateFormCntx(); }
		else if (view === 'conc/list') { return getConcListCntx(); }
		else if (view === 'conc/form') { return getConcFormCntx(); }
		else if (view === 'cuen/list') { return getCuenListCntx(); }
		else if (view === 'cuen/form') { return getCuenFormCntx(); }
		else if (view === 'cuen/tras') { return getCuenTrasCntx(); }
		else if (view === 'cuen/cuad') { return getCuenCuadCntx(); }
		else if (view === 'hcon/form') { return getHconFormCntx(); }
		else if (view === 'hcon/list') { return getHconListCntx(); }
		else if (view === 'lite/list') { return getLiteListCntx(); }
		else if (view === 'lite/get' ) { return getLiteGetCntx();  }   //Contexto lite/get : consulta de literal
		else if (view === 'para/get')  { return getParaGetCntx();  }

		else if (view === 'avis/list') { return getAvisListCntx(); }
		else if (view === 'invi/list') { return getInviListCntx(); }
		else if (view === 'usua/lgon') { return getUsuaLgonCntx(); }
		else if (view === 'usua/regi') { return getUsuaRegiCntx(); }
		
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
		var vShow = {
			anua: false
		};
		var vRead = {
			anua: false
		};
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltMAnualidad: null,
			ltPresesta: null,
			ltMPresesta: null,
			ltMes: null,
			ltMMes: null,
			cateList: null,
			cateMap: null,
			concFullList: null,
			concFullListMap: null,
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
			show: vShow,
			read: vRead,
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
		var vShow = {
			anua: false,
			mesp: false
		};
		var vRead = {
			anua: false,
			mesp: false
		};
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltMAnualidad: null,
			ltPresesta: null,
			ltMPresesta: null,
			ltMes: null,
			ltMMes: null,
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
			show: vShow,
			read: vRead,
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
		var vShow = {
			anua: false
		};
		var vRead = {
			anua: false
		}
		var vData = {
			prPeripresup: null,
			ltAnualidad: null,
			ltMAnualidad: null,
			ltPresesta: null,
			ltMPresesta: null,
			presCateList: new Array(),
			presListMap: new Map()
		};
		var vConf = {
			item: -1,
			cate: -1
		}
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
			data: vData,
			conf: vConf
		};
		return cntx;
	}

	//Contexto presResu
	function getPresResuCntx() {
		var vForm = {
			ndat: null
		};
		var vShow = {
			ndat: false
		};
		var vRead = {
			ndat: false
		}
		var vData = {
			ltPresesta: null,
			ltMPresesta: null,
			presList: new Array()
		};
		var vConf = {
			item: -1
		}
		cntx = {
			form: vForm,
			show: vShow,
			read: vRead,
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
			cateList: null,
			cateMap : null
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
			ltBool: new Array(),
			ltMBool: null
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
			ltCuentipo: null,
			ltMCuentipo: null
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

	//Contexto cuenCuad
	function getCuenCuadCntx() {
		var vForm = {
			cuen : 0,
			sald : 0,
			impo : 0,
			cate : 0,
			conc : 0
		};
		var vShow = {
			cuen: false,
			sald: false,
			impo: false,
			cate: false,
			conc: false,
			btCuad: false,
			stApun: false
		};
		var vRead = {
			cuen: false,
			sald: false,
			impo: false,
			cate: false,
			conc: false
		};
		var vData = {
			cuenList: null,
			cateList: null,
			cateMap : null,
			concList: null
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

	//Contexto hconForm
	function getHconFormCntx() {
		var vForm = {
			iden : 0,
			cate : 0,
			conc : 0,
			cuen : 0,
			impo : 0,
			feva : 0,
			desc : ''
		};
		var vShow = {
			iden: false,
			cate: false,
			conc: false,
			cuen: false,
			impo: false,
			feva: false,
			desc: false
		};
		var vRead = {
			iden: false,
			cate: false,
			conc: false,
			cuen: false,
			impo: false,
			feva: false,
			desc: false
		};
		var vData = {
			cuenList: null,
			cateList: null,
			cateMap : null,
			concList: null
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

	//Contexto hconList
	function getHconListCntx() {
		var vForm = {
			tipo : 0,
			anua : 0,
			mesh : 0,
			cate : 0,
			conc : 0
		};
		var vShow = {
			tipo: false,
			anua: false,
			mesh: false,
			cate: false,
			conc: false
		};
		var vRead = {
			tipo: false,
			anua: false,
			mesh: false,
			cate: false,
			conc: false
		};
		var vData = {
			ltHconlttipo: null,
			ltMHconlttipo: null,
			ltAnualidad : null,
			ltMAnualidad: null,
			ltMes       : null,
			ltMMes      : null,
			hconList    : new Array(), //No puede ser null por que es usada en un filter
			cateList    : null,
			cateMap     : null,
			concList    : null,
			concFullList: null,
			concFullMap : null
		};
		var vConf = {
			mode: '',
			item: -1,
			iden: 0,
			acci: ''
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

	//*************************************************************************************************************//
	// Contexto lite/get : contexto de consulta de un literal.                                                     //
	//*************************************************************************************************************//
	function getLiteGetCntx() {
		var vForm = {
			tbla: '',
			clav: ''
		};
		var vShow = {
			tbla: false,
			clav: false  
		};
		var vRead = {
			tbla: false,
			clav: false
		};
		var vData = {
			lite: null
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

	//Contexto paraGet
	function getParaGetCntx() {
		var vForm = {
			tipo: '',
			tbla: '',
			clav: ''
		};
		var vShow = {
			tipo: false,
			tbla: false,
			clav: false
		};
		var vRead = {
			tipo: false,
			tbla: false,
			clav: false
		};
		var vData = {
			para: null
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

	//Contexto avisList
	function getAvisListCntx() {
		var vForm = {
			ndat: null
		};
		var vShow = {
			ndat: false
		};
		var vRead = {
			ndat: false
		};
		var vData = {
			avisList: null
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

	//Contexto inviList
	function getInviListCntx() {
		var vForm = {
			esta: '',
			iden: 0
		};
		var vShow = {
			esta: false,
			iden: false
		};
		var vRead = {
			esta: false,
			iden: false
		};
		var vData = {
			inviList: null,
			ltInviesta: null,
			ltMInviesta: null,
			ltInvitipo: null,
			ltMInvitipo: null
		};
		var vConf = {
			mode: '',
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

	//Contexto usuaLgon
	function getUsuaLgonCntx() {
		var vForm = {
			iden: '',
			pass: ''
		};
		var vShow = {
			iden: false,
			pass: false
		};
		var vRead = {
			iden: false,
			pass: false
		};
		var vData = {
			invi: null,
			usua: null
		};
		var vConf = {
			mode: '',
			vali: false,
			invi: false
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

	//Contexto usuaRegi
	function getUsuaRegiCntx() {
		var vForm = {
			invi : '',
			mail : '',
			desc : '',
			ldes : '',
			usua : '',
			pass : '',
			cpas : ''
		};
		var vShow = {
			invi : false,
			mail : false,
			desc : false,
			usua : false,
			pass : false,
			cpas : false,
			btRegi : false,
			btSoli : false
		};
		var vRead = {
			invi : false,
			mail : false,
			desc : false,
			usua : false,
			pass : false,
			cpas : false,
		};
		var vData = {
			sesi: null,
			prConfregist: null,
			prReginvdesc: null
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
