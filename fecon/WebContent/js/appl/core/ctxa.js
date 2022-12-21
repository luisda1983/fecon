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
		else if (view === 'pres/cons') { return getPresConsCntx(); }
		else if (view === 'pres/mesp') { return getPresMespCntx(); }
		else if (view === 'pres/resu') { return getPresResuCntx(); }
		else if (view === 'cate/list') { return getCateListCntx(); }
		else if (view === 'cate/form') { return getCateFormCntx(); }
		else if (view === 'coes/form') { return getCoesFormCntx(); }
		else if (view === 'coes/list') { return getCoesListCntx(); }
		else if (view === 'conc/list') { return getConcListCntx(); }
		else if (view === 'conc/form') { return getConcFormCntx(); }
		else if (view === 'cont/form') { return getContFormCntx(); }
		else if (view === 'cuen/list') { return getCuenListCntx(); }
		else if (view === 'cuen/form') { return getCuenFormCntx(); }
		else if (view === 'cuen/tras') { return getCuenTrasCntx(); }
		else if (view === 'cuen/cuad') { return getCuenCuadCntx(); }
		else if (view === 'hcon/form') { return getHconFormCntx(); }
		else if (view === 'hcon/list') { return getHconListCntx(); }
		else if (view === 'trad/form') { return getTradFormCntx(); }
		else if (view === 'trad/list') { return getTradListCntx(); }
		
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
	// Contexto: pres/cons - Consulta precierre de mes.                                                            //
	//*************************************************************************************************************//
	function getPresConsCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('anua', ctxl.makeField(0));
		cntx.form.set('mesp', ctxl.makeField(0));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('esta', ctxl.makeField(''));
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
	// Contexto: coes/form - Formulario de códigos específicos.                                                    //
	//*************************************************************************************************************//
	function getCoesFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('favo', ctxl.makeField('N'));
//		cntx.form.set('trad', ctxl.makeField(0));
//		cntx.form.set('tradText', ctxl.makeField(''));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('cateText', ctxl.makeField(''));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('concText', ctxl.makeField(''));
		cntx.form.set('coes', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: coes/list - Lista de códigos específicos.                                                         //
	//*************************************************************************************************************//
	function getCoesListCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('desc', ctxl.makeField(''));
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
		cntx.form.set('tipo', ctxl.makeField(''));
		cntx.form.set('desl', ctxl.makeField(''));
		cntx.form.set('desc', ctxl.makeField(''));
		cntx.form.set('orde', ctxl.makeField(0));
		return cntx;
	}

	//*************************************************************************************************************//
	// Contexto: cont/form - Formulario de contabilidad.                                                           //
	//*************************************************************************************************************//
	function getContFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('coes', ctxl.makeField(0));
		cntx.form.set('anin', ctxl.makeField(0));
		cntx.form.set('anfi', ctxl.makeField(0));
		cntx.form.set('val1', ctxl.makeField(''));
		cntx.form.set('val2', ctxl.makeField(''));
		cntx.form.set('val3', ctxl.makeField(''));
		cntx.form.set('cate', ctxl.makeField(0));
		cntx.form.set('conc', ctxl.makeField(0));
		cntx.form.set('iden', ctxl.makeField(0)); //Traduccion
		cntx.form.set('domi', ctxl.makeField(0));
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
		cntx.form.set('repe', ctxl.makeField(false));
		cntx.form.set('stPres', ctxl.makeSection('stPres'));
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
	
	//*************************************************************************************************************//
	// Contexto: trad/form - Formulario de traducciones.                                                           //
	//*************************************************************************************************************//
	function getTradFormCntx() {
		var cntx = ctxl.baseCntx();
		cntx.form.set('iden', ctxl.makeField(0));
		cntx.form.set('nomb', ctxl.makeField(''));
		cntx.form.set('tip1', ctxl.makeField(''));
		cntx.form.set('dom1', ctxl.makeField(0));
		cntx.form.set('ide1', ctxl.makeField(''));
		cntx.form.set('obl1', ctxl.makeField('N'));
		cntx.form.set('tip2', ctxl.makeField(''));
		cntx.form.set('dom2', ctxl.makeField(0));
		cntx.form.set('ide2', ctxl.makeField(''));
		cntx.form.set('obl2', ctxl.makeField('N'));
		cntx.form.set('tip3', ctxl.makeField(''));
		cntx.form.set('dom3', ctxl.makeField(0));
		cntx.form.set('ide3', ctxl.makeField(''));
		cntx.form.set('obl3', ctxl.makeField('N'));
		cntx.form.set('desc', ctxl.makeField(''));
		return cntx;
	}
	
	//*************************************************************************************************************//
	// Contexto: trad/list - Formulario de list de traducciones.                                                   //
	//*************************************************************************************************************//
	function getTradListCntx() {
		var cntx = ctxl.baseCntx();
		return cntx;
	}
}]);
