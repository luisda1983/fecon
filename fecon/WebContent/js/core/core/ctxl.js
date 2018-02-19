//*************************************************************************************************************//
// Libreria de funciones auxiliares para la generación de contexto.                                            //
//=============================================================================================================//
// Version    | Fecha      | Comentarios de version                                                            //
// v.01.00.00 | 13.02.2018 | Primera version del SW                                                            //
//*************************************************************************************************************//
app.factory("ctxl", [function() {

	//*************************************************************************************************************//
	// INTERFAZ PUBLICA CNTXLIB: Funciones que ofrece el servicio cntxLib.                                         //
	//*************************************************************************************************************//
	return {
		baseCntx : baseCntx,
		makeField: makeField,
		makeLabel: makeLabel,
		makeBtn  : makeBtn,
		formField: formField,
		formBtn  : formBtn
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

}]);
