package ldrsoftware.app._installation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Install {

	private final static String appInstall = "fecon";
	private final static String[] files = {"\\src\\ldrsoftware\\<<<APP>>>\\comunication\\request", 
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\request\\BaseRequest.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\request\\LoginRequest.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response\\BaseResponse.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response\\LoginResponse.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response\\MenuResponse.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response\\TareaResponse.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\comunication\\response\\TipoTareaResponse.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\controller",
										   "\\src\\ldrsoftware\\<<<APP>>>\\controller\\BaseController.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\controller\\LoginController.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\controller\\MenuController.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\controller\\TareaController.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\mensaje",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\mensaje\\LOGMensaje.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\mensaje\\SESMensaje.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\mensaje\\TARMensaje.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\parm",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\parm\\ParmConfiguracion.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\parm\\ParmMaestro.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\core\\parm\\ParmSecuencias.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHMensDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHMenuDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHParmDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHSecuDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHSesiDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHSubmDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHTareDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHTtarDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\IHUsuaDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\DAOEntityManager.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HMensDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HMenuDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HParmDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HSecuDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HSesiDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HSubmDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HTareDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HTtarDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\dao\\impl\\HUsuaDAO.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HMens.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HMenu.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HParm.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HSecu.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HSesi.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HSubm.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HTare.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HTtar.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\domain\\HUsua.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\ILoginService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\IMenuService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\ISesionService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\ITareaService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\ITipoTareaService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\IUsuarioService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\BaseService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\LoginService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\MenuService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\SesionService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\TareaService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\TipoTareaService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\service\\impl\\UsuarioService.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\util",
										   "\\src\\ldrsoftware\\<<<APP>>>\\util\\DateTimeUtil.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\util\\Fmt.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\util\\SesiUtil.java",
										   "\\src\\ldrsoftware\\<<<APP>>>\\work",
										   "\\src\\ldrsoftware\\<<<APP>>>\\work\\WorkClass.java"
										   };
	
	public static void main(String[] args) {
		String urlCore = System.getProperty("user.dir");
		int i = urlCore.lastIndexOf("\\");
		String urlApp = urlCore.substring(0, i + 1) + appInstall;
		
		for(int j = 0; j < files.length; j++) {
			String s = files[j];
			String coreFile = urlCore + s.replace("<<<APP>>>", "app");
			s = files[j];
			String appFile = urlApp + s.replace("<<<APP>>>", appInstall);
			boolean copy = false;
			
			s = files[j];
			long appModified = 0, coreModified = 0;
			File f = new File(urlCore + s.replace("<<<APP>>>", "app"));
			if (f.exists()) {
				if (f.isDirectory()) {
				} else {
					copy = true;
					coreModified = f.lastModified();
				}
			} else {
				System.out.println("Error no existe archivo: " + s);
			}
			s = files[j];
			f = new File(urlApp + s.replace("<<<APP>>>", appInstall));
			if (f.exists()) {
				if (f.isDirectory()) {
				} else {
					appModified = f.lastModified();
				}
			} else {
				System.out.println("Error no existe archivo(d): " + s);
			}
			if (appModified != coreModified) {
				System.out.println("Version no actualizada: " + s + "(" + appModified + "," + coreModified + ")");
			}
			
			if(j<=1) {
				if (copy) {
					copyFile(coreFile, appFile);
				}
				copy = false;
			}
		}
	}
	
	private static void copyFile(String core, String app) {
		try {
			File coreFile = new File(core);
			File appFile = new File(app);

			FileReader fr = new FileReader(coreFile);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(appFile);
			PrintWriter pw = new PrintWriter(fw);
			
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("package")) {
					line = line.replace(".app.", "." + appInstall + ".");
				}
				pw.println(line);
				
			}
			pw.close();
			br.close();
			
			System.out.println("Fichero copiado: " + core);
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Error copiando archivo: " + core + "(" + e.getLocalizedMessage() + ")");
		}
	}

}
