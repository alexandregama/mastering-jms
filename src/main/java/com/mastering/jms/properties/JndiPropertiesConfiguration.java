package com.mastering.jms.properties;

import java.util.Properties;

public class JndiPropertiesConfiguration {

	public static Properties configure() {
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		properties.setProperty("java.naming.provider.url", "http-remoting://localhost:8080");
		
		return properties;
	}
	
}
