//Factory que encapsula la gestión de contexto de la aplicación
//TODO: en pres/conc... he visto que en el data no es necesario definir el detalle... al llamar a comc, se añade el 
//      atributo y se tiene visibilidad... analizar si se puede eliminar el detalle siempre
app.factory("cntx", ['$q', 'ctxa', function($q, ctxa) {
	
	//*************************************************************************************************************//
	// PUBLIC: getCntx: Función para la generación de vistas.                                                      //
	//*************************************************************************************************************//
	function getCntx(view) {
		
		var cntx = null;

		//Buscamos la vista para devolver su contexto.
		     if (view === 'avis/list') { return getAvisListCntx(); }
		else if (view === 'invi/list') { return getInviListCntx(); }
		else if (view === 'usua/lgon') { return getUsuaLgonCntx(); }
		else if (view === 'usua/regi') { return getUsuaRegiCntx(); }
		else {
			//En caso de no encontrarla, buscamos entre las vistas de la aplicación
			return ctxa.getCntx(view);
		}
	}

	//*************************************************************************************************************//
	// INTERFAZ PUBLICA CNTX: Funciones que ofrece el servicio cntx.                                               //
	//*************************************************************************************************************//
	return {
		getCntx: getCntx
	};

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
