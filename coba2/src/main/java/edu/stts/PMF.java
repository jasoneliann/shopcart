package edu.stts;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("nontransactional-datasource");
	
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
