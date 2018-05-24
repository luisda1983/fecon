//Factory que encapsula la gestión de contexto de la aplicación
//TODO: en pres/conc... he visto que en el data no es necesario definir el detalle... al llamar a comc, se añade el 
//      atributo y se tiene visibilidad... analizar si se puede eliminar el detalle siempre
app.factory("cntx", ['$q', 'ctxa', 'ctxl', function($q, ctxa, ctxl) {
	
	//*************************************************************************************************************//
	// PUBLIC: getCntx: Función para la generación de vistas.                                                      //
	//*************************************************************************************************************//
	function getCntx(view) {
		
		var cntx = null;

		//Buscamos la vista para devolver su contexto.
		     if (view === 'avis/list') { return getAvisListCntx(); }
		else if (view === 'btch/list') { return getBtchListCntx(); }
		else if (view === 'ctmn/form') { return getCtmnFormCntx(); }
		else if (view === 'ctmn/list') { return getCtmnListCntx(); }
		else if (view === 'dtmn/form') { return getDtmnFormCntx(); }
		else if (view === 'dtmn/list') { return getDtmnListCntx(); }
		else if (view === 'ejec/list') { return getEjecListCntx(); }
		else if (view === 'inst/list') { return getInstListCntx(); }
		else if (view === 'invi/envi') { return getInviEnviCntx(); }
		else if (view === 'invi/list') { return getInviListCntx(); }
		else if (view === 'logp/list') { return getLogpListCntx(); }
		else if (view === 'mpla/list') { return getMplaListCntx(); }
		else if (view === 'plan/list') { return getPlanListCntx(); }
		else if (view === 'sesi/list') { return getSesiListCntx(); }
		else if (view === 'usua/list') { return getUsuaListCntx(); }
		else if (view === 'usua/regi') { return getUsuaRegiCntx(); }
		else if (view === '/lgon')     { return getLgonCntx();     }
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

	//*************************************************************************************************************//
	// Contexto: invi/list - Lista de invitaciones.                                                                //
	//*************************************************************************************************************//
	function getInviListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('esta', ctxl.makeField(''));
		cntx.form.set('iden', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: invi/envi - Envío de invitaciones.                                                                //
	//*************************************************************************************************************//
	function getInviEnviCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('mail', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: /lgon - Login de usuario.                                                                         //
	//*************************************************************************************************************//
	function getLgonCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(''));
		cntx.form.set('pass', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: usua/regi - Registro de usuario.                                                                  //
	//*************************************************************************************************************//
	function getUsuaRegiCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('invi', ctxl.makeField(''));
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('ldes', ctxl.makeLabel(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('codi', ctxl.makeField(''));
		cntx.form.set('numo', ctxl.makeField(''));
		cntx.form.set('mail', ctxl.makeField(''));
		cntx.form.set('usua', ctxl.makeField(''));
		cntx.form.set('pass', ctxl.makeField(''));
		cntx.form.set('cpas', ctxl.makeField(''));

		cntx.form.set('btRegi', ctxl.makeBtn('Registrar'));
		cntx.form.set('btSoli', ctxl.makeBtn('Solicitar'));
		cntx.form.set('btCanc', ctxl.makeBtn('Cancelar'));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: usua/list - Lista de usuarios.                                                                    //
	//*************************************************************************************************************//
	function getUsuaListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('inst', ctxl.makeField(0));
		cntx.form.set('usua', ctxl.makeField(''));
		cntx.form.set('srch', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: ctmn/list - Lista de categorías de menú.                                                          //
	//*************************************************************************************************************//
	function getCtmnListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('perf', ctxl.makeField(''));
		cntx.form.set('iden', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: ctmn/form - Formulario de categorías de menú.                                                     //
	//*************************************************************************************************************//
	function getCtmnFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('perf', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('acti', ctxl.makeField(''));
		cntx.form.set('orde', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: dtmn/list - Lista de detalle de menú.                                                             //
	//*************************************************************************************************************//
	function getDtmnListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('ctmn', ctxl.makeField(0));
		cntx.form.set('iden', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: dtmn/form - Formulario de detalle de menú.                                                        //
	//*************************************************************************************************************//
	function getDtmnFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('ctmn', ctxl.makeField(0));
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('acti', ctxl.makeField(''));
		cntx.form.set('orde', ctxl.makeField(0));
		cntx.form.set('path', ctxl.makeField(''));
		cntx.form.set('icon', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: inst/list - Lista de instalaciones.                                                               //
	//*************************************************************************************************************//
	function getInstListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('esta', ctxl.makeField(''));
		cntx.form.set('srch', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: sesi/list - Lista de sesiones.                                                                    //
	//*************************************************************************************************************//
	function getSesiListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(''));
		cntx.form.set('esta', ctxl.makeField(''));
		cntx.form.set('srch', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: mpla/list - Lista de maestro de planificación.                                                    //
	//*************************************************************************************************************//
	function getMplaListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('hora', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: btch/list - Lista de procesos Batch.                                                              //
	//*************************************************************************************************************//
	function getBtchListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: ejec/list - Lista de ejecuciones.                                                                 //
	//*************************************************************************************************************//
	function getEjecListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('fech', ctxl.makeField(new Date()));
		cntx.form.set('hora', ctxl.makeField(0));
		cntx.form.set('btAcep', ctxl.makeBtn('Aceptar'));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: plan/list - Lista de pases Batch.                                                                 //
	//*************************************************************************************************************//
	function getPlanListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('fech', ctxl.makeField(new Date()));
		cntx.form.set('proc', ctxl.makeField(true));
		cntx.form.set('btAcep', ctxl.makeBtn('Aceptar'));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: logp/list - Lista de Log.                                                                         //
	//*************************************************************************************************************//
	function getLogpListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('iden', ctxl.makeField(''));
		cntx.form.set('fech', ctxl.makeField(0));
		cntx.form.set('hora', ctxl.makeField(0));
		return cntx;
	}

}]);
