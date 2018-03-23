package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaInac extends BaseBS {

	@Autowired
	BsUsuaGetk bsUsuaGetk;
	
	@Autowired
	BsUsuaSave bsUsuaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaInacArea area = (BsUsuaInacArea)a;

		//Si tenemos instalación en la entrada, activamos la relación
		if (area.IN.inst != 0) {
			//TODO: Sólo ADM puede activar una relación
			//TODO: obtener relación, validar y activar
		//Si no tenemos instalación en la entrada, activamos el usuario
		} else {
			//TODO: Sólo APM puede activar un usuario
			
			//Obtenemos el usuario
			BsUsuaGetkArea bsUsuaGetkArea = new BsUsuaGetkArea();
			bsUsuaGetkArea.IN.iden = area.IN.iden;
			bsUsuaGetk.executeBS(bsUsuaGetkArea);
		
			Usua usua = bsUsuaGetkArea.OUT.usua;
		
			//Validamos que exista el usuario
			validateDtoRequired(usua, CoreNotify.USUA_INAC_USUA_NF);
		
			//Validamos que el usuario se encuentre activo
			validateStringEqual(usua.getActi(), LiteData.LT_EL_BOOL_SI, CoreNotify.USUA_INAC_USUA_ACTI_NO);
		
			//Desactivamos el usuario
			usua.setActi(LiteData.LT_EL_BOOL_NO);
		
			//Guardamos el usuario
			BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
			bsUsuaSaveArea.IN.usua = usua;
			bsUsuaSave.executeBS(bsUsuaSaveArea);
				
			area.OUT.usua = bsUsuaSaveArea.OUT.usua;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaInacArea area = (BsUsuaInacArea)a;

		//Se valida que el identificador de usuario esté informado
		validateStringRequired(area.IN.iden, CoreNotify.USUA_INAC_IDEN_RQRD);
	}

}
