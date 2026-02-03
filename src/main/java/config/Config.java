package config;

import iconst.IConst;

public class Config implements IConst {

	private boolean debug;

	public Config(boolean isDefault) {
		debug = (idebug == 1);
	}
	
	public boolean isDebug() {
		return debug;
	}
}
