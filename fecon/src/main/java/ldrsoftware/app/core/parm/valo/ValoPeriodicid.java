package ldrsoftware.app.core.parm.valo;

import ldrsoftware.app.domain.HParm;

public class ValoPeriodicid extends BaseParmValo {

	private boolean mini;
	private boolean mfin;
	private int divi;
	
	public ValoPeriodicid(HParm parm) {
		
		mini = false;
		mfin = false;
		divi = 0;
		
		if (parm == null) { return;	}
		String valo = parm.getValo();
		if (valo == null) {	return;	}
		
		if (valo.length() >= 1) {
			String s = valo.substring(0, 1);
			if ("S".equals(s)) { 
				mini = true; 
			} else {
				mini = false;
			}
		}
		if (valo.length() >= 2) {
			String s = valo.substring(1, 2);
			if ("S".equals(s)) {
				mfin = true;
			} else {
				mfin = false;
			}
		}
		if (valo.length() >= 4) {
			String s = valo.substring(2, 4);
			try {
				Integer i = new Integer(s);
				divi = i;
			} catch (NumberFormatException e) {
				divi = 0;
			}
		}
	}
	
	public boolean isMini() {
		return mini;
	}
	public void setMini(boolean mini) {
		this.mini = mini;
	}
	public boolean isMfin() {
		return mfin;
	}
	public void setMfin(boolean mfin) {
		this.mfin = mfin;
	}
	public int getDivi() {
		return divi;
	}
	public void setDivi(int divi) {
		this.divi = divi;
	}
	
	
}
