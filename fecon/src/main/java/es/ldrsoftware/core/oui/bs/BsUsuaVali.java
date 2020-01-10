package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaVali extends BaseBS {

	@Autowired
	BsUsuaGetk bsUsuaGetk;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaValiArea area = (BsUsuaValiArea)a;
		
		//Obtenemos el usuario de la BBDD
		BsUsuaGetkArea bsUsuaGetkArea = new BsUsuaGetkArea();
		bsUsuaGetkArea.IN.iden = area.IN.iden;
		bsUsuaGetk.executeBS(bsUsuaGetkArea);
		
		Usua usua = bsUsuaGetkArea.OUT.usua;
	
		//Validamos que exista el usuario
		validateDtoNotFound(usua, LiteData.LT_EL_DTO_USUA, Usua.key(area.IN.iden));

		//Validamos que el password introducido coindide
		test(false, area.IN.pass, usua.getPass(), CoreNotify.USUA_VALI_PASS_ERRO);
		
		//Validamos que el usuario se encuentre activo
		test(false, LiteData.LT_EL_BOOL_SI, usua.getActi(), CoreNotify.USUA_VALI_USUA_ACTI_NO);

		//Si el email está informado, necesitamos validar que efectivamente coincide con el del usuario
		//Función utilizada en el procesado de invitación con usuario existente
		if (data(area.IN.mail)) {
			test(false, area.IN.mail, usua.getMail(), CoreNotify.USUA_VALI_MAIL_ERRO);
		}
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaValiArea area = (BsUsuaValiArea)a;

		//Validamos que el identificador de usuario esté informado
		validateInputField(area.IN.iden, Usua.IDEN);
		
		//Validamos que el password esté informado
		validateInputField(area.IN.pass, Usua.PASS);
	}
}