package it.isac.utils.impl;

import it.isac.utils.interfaces.IComManagerImpl;

public class ComManagerFactory {
	// TODO: you can add here some imformation useful to comunicate
	static final String BASEURL = "http://localhost:8111";
	static final String NETID = "/net0";
	private static ComunicationManager CMimpl;

	public static void setCMIstance(IComManagerImpl impl) {
		CMimpl = new ComunicationManager(impl);
	}

	public static ComunicationManager getCMIstance() {
		if (CMimpl == null) {
			try {
				throw new Exception("Comunication Manager Not istantiated");
			} catch (Exception ex) {
			}
		}
		return CMimpl;
	}
}
