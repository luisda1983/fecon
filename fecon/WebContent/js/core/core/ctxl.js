//*****************************************************************************************************************//
// Libreria de funciones auxiliares para la generación de contexto.                                                //
//*****************************************************************************************************************//
// v.01.00.00 || 03.01.2019 || Versión Inicial                                                                     //
//*****************************************************************************************************************//
app.factory("ctxl", [function() {

	//*************************************************************************************************************//
	// INTERFAZ PUBLICA CNTXLIB: Funciones que ofrece el servicio cntxLib.                                         //
	//*************************************************************************************************************//
	return {
		baseCntx   : baseCntx,
		makeField  : makeField,
		makeLabel  : makeLabel,
		makeBtn    : makeBtn,
		makeSection: makeSection,
		formField  : formField,
		formBtn    : formBtn,
		formSection: formSection,
		clearXchg  : clearXchg
	};

	//*************************************************************************************************************//
	// PUBLIC: baseCntx: Función que genera un contexto básico.                                                    //
	//*************************************************************************************************************//
	function baseCntx() {
		var vForm = new Map();
		var vData = new Map();
		var vXchg = new Map();
		var vConf = new Map();
		vConf.set('mode', '');
		vConf.set('idx1', -1);
		vConf.set('idx2', -1);
		vConf.set('back', false);
		
		cntx = {
			form: vForm,
			data: vData,
			xchg: vXchg,
			conf: vConf
		};
		return cntx;
	}

	//*************************************************************************************************************//
	// PUBLIC: makeField: Función que crea un campo de formulario.                                                 //
	//*************************************************************************************************************//
	function makeField(data) {
		var field = {
			data: data,
			show: new Boolean(false),
			read: new Boolean(false)
		};
		return field;
	}

	//*************************************************************************************************************//
	// PUBLIC: makeLabel: Función que crea una etiqueta.                                                           //
	//*************************************************************************************************************//
	function makeLabel(data) {
		var label = {
			data: data
		};
		return label;
	}

	//*************************************************************************************************************//
	// PUBLIC: makeBtn: Función que crea un botón.                                                                 //
	//*************************************************************************************************************//
	function makeBtn(data) {
		var btn = {
			data: data,
			show: new Boolean(false)
		};
		return btn;
	}

	//*************************************************************************************************************//
	// PUBLIC: makeSection: Función que crea una sección.                                                          //
	//*************************************************************************************************************//
	function makeSection(data) {
		var st = {
			show: new Boolean(false)
		};
		return st;
	}

	//*************************************************************************************************************//
	// PUBLIC: formField: Función que gestiona el cambio de estados de un campo de un formulario.                  //
	//*************************************************************************************************************//
	function formField(cntx, field, show, read) {
		cntx.form.get(field).show = show;
		cntx.form.get(field).read = read;
	}

	//*************************************************************************************************************//
	// PUBLIC: formBtn: Función que gestiona el cambio de estados de una botón de un formulario.                   //
	//*************************************************************************************************************//
	function formBtn(cntx, btn, show) {
		cntx.form.get(btn).show = show;
	}

	//*************************************************************************************************************//
	// PUBLIC: formSection: Función que gestiona el cambio de estados de una sección de un formulario.             //
	//*************************************************************************************************************//
	function formSection(cntx, st, show) {
		cntx.form.get(st).show = show;
	}

	//*************************************************************************************************************//
	// PUBLIC: clearXchg: Función que inicializa el area de intercambio.                                           //
	//*************************************************************************************************************//
	function clearXchg(cntx) {
		cntx.xchg = new Map();
	}
}]);