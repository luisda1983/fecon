package es.ldrsoftware.corebridge;

import es.ldrsoftware.core.fwk.data.PV;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.fecon.data.PVParser;

public class PVParserBridge {

	public static PV parse(Para para) {
		return PVParser.parse(para);
	}
}