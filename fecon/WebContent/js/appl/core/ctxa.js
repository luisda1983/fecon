//*****************************************************************************************************************//
//Factory que gestiona los contextos de la aplicación.                                                             //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.factory("ctxa", ['$q', 'ctxl', function($q, ctxl) {
	
	//*************************************************************************************************************//
	// PUBLIC: getCntx: Función para la generación de vistas.                                                      //
	//*************************************************************************************************************//
	function getCntx(view) {
		
		var cntx = null;

		//Buscamos la vista para devolver su contexto.
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
		
		return cntx;
	}
		
	//*************************************************************************************************************//
	// INTERFAZ PUBLICA CTXA: Funciones que ofrece el servicio cnta.                                               //
	//*************************************************************************************************************//
	return {
		getCntx: getCntx
	};
	
	//*************************************************************************************************************//
	// Contexto: pres/anua - Lista de presupuesto anual.                                                           //
	//*************************************************************************************************************//
	function getPresAnuaCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('anua', ctxl.makeField(0));
		cntx.form.set('mesp', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('esta', ctxl.makeField(''));
		return cntx;
	}
	
	//*************************************************************************************************************//
	// Contexto: pres/mesp - Lista de presupuesto mensual.                                                         //
	//*************************************************************************************************************//
	function getPresMespCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('anua', ctxl.makeField(0));
		cntx.form.set('mesp', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('esta', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: pres/conc - Lista de presupuesto anual por concepto.                                              //
	//*************************************************************************************************************//
	function getPresConcCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('anua', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: pres/resu - Resumen de presupuesto.                                                               //
	//*************************************************************************************************************//
	function getPresResuCntx() {
		var cntx = ctxl.baseCntx();
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cate/list - Lista de categorías.                                                                  //
	//*************************************************************************************************************//
	function getCateListCntx() {
		var cntx = ctxl.baseCntx();
		return cntx;
	}
	
	//*************************************************************************************************************//
	// Contexto: cate/form - Formulario de categorías.                                                             //
	//*************************************************************************************************************//
	function getCateFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('desl', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('orde', ctxl.makeField(0));
		cntx.form.set('pres', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: conc/list - Lista de conceptos.                                                                   //
	//*************************************************************************************************************//
	function getConcListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('cate', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: conc/form - Formulario de conceptos.                                                              //
	//*************************************************************************************************************//
	function getConcFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('desl', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('orde', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cuen/list - Lista de cuentas.                                                                     //
	//*************************************************************************************************************//
	function getCuenListCntx() {
		var cntx = ctxl.baseCntx();
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cuen/form - Formulario de cuentas.                                                                //
	//*************************************************************************************************************//
	function getCuenFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('sald', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cuen/tras - Formulario de traspaso de cuentas.                                                    //
	//*************************************************************************************************************//
	function getCuenTrasCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('ctor', ctxl.makeField(0));
		cntx.form.set('ctde', ctxl.makeField(0));
		cntx.form.set('impo', ctxl.makeField(0));
		cntx.form.set('feva', ctxl.makeField(new Date));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cuen/cuad - Formulario de cuadre de cuentas.                                                      //
	//*************************************************************************************************************//
	function getCuenCuadCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('cuen', ctxl.makeField(0));
		cntx.form.set('sald', ctxl.makeField(0));
		cntx.form.set('impo', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('btCuad', ctxl.makeBtn('Cuadre'));
		cntx.form.set('stApun', ctxl.makeSection('StApunte'));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: hcon/form - Formulario de apunte.                                                                 //
	//*************************************************************************************************************//
	function getHconFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('cuen', ctxl.makeField(0));
		cntx.form.set('impo', ctxl.makeField(0));
		cntx.form.set('feva', ctxl.makeField(new Date()));
		cntx.form.set('desc', ctxl.makeField(''));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: hcon/list - Formulario de list de apuntes.                                                        //
	//*************************************************************************************************************//
	function getHconListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('anua', ctxl.makeField(0));
		cntx.form.set('mesh', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('acci', ctxl.makeField(''));
		return cntx;
	}
}]);
