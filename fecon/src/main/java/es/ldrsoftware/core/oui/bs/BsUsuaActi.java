package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaActi extends BaseBS {

	@Autowired
	BsUsuaGetk bsUsuaGetk;
	
	@Autowired
	BsUsuaSave bsUsuaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaActiArea area = (BsUsuaActiArea)a;

		//Si tenemos instalación en la entrada, desactivamos la relación
		if (area.IN.inst != 0) {
			//TODO: Sólo ADM puede desactivar una relación
			//TODO: obtener relación, validar y desactivar
		//Si no tenemos instalación en la entrada, desactivamos el usuario
		} else {
			//TODO: Sólo APM puede desactivar un usuario
			
			//Obtenemos el usuario
			BsUsuaGetkArea bsUsuaGetkArea = new BsUsuaGetkArea();
			bsUsuaGetkArea.IN.iden = area.IN.iden;
			bsUsuaGetk.executeBS(bsUsuaGetkArea);
		
			Usua usua = bsUsuaGetkArea.OUT.usua;
		
			//Validamos que exista el usuario
			validateDtoRequired(usua, CoreNotify.USUA_ACTI_USUA_NF);
		
			//Validamos que el usuario se encuentre inactivo
			validateStringEqual(usua.getActi(), LiteData.LT_EL_BOOL_NO, CoreNotify.USUA_ACTI_USUA_INAC_NO);
		
			//Activamos el usuario
			usua.setActi(LiteData.LT_EL_BOOL_SI);
		
			//Guardamos el usuario
			BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
			bsUsuaSaveArea.IN.usua = usua;
			bsUsuaSave.executeBS(bsUsuaSaveArea);
				
			area.OUT.usua = bsUsuaSaveArea.OUT.usua;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaActiArea area = (BsUsuaActiArea)a;

		//Se valida que el identificador de usuario esté informado
		validateStringRequired(area.IN.iden, CoreNotify.USUA_ACTI_IDEN_RQRD);
	}

}
