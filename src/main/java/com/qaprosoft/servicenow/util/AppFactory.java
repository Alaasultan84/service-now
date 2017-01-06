package com.qaprosoft.servicenow.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.qaprosoft.carina.core.foundation.utils.ParameterGenerator;

/**
 * Factory class for business loan application
 * 
 * @author anazarenko
 *
 */
public class AppFactory 
{
	private Map<String, String> application;

	/**
	 * @param path
	 *            path to property file with app params
	 *
	 * @throws {@link RuntimeException}
	 *             when can't read property
	 */
	private AppFactory(String path) 
	{
		try {
			InputStream is = ClassLoader.getSystemResource(path).openStream();
			Properties properties = new Properties();
			properties.load(is);
			is.close();
			application = new HashMap<>();
			for (Object key : properties.keySet()) {
				application.put(
						(String) key,
						(String) ParameterGenerator.process(
								properties.getProperty((String) key)));
			}			
		} catch (Exception e) {
			throw new RuntimeException("Could not read properties: " + path);
		}
	}

	/**
	 * Get new instanse of AppFactory
	 * 
	 * @param path
	 *            path to property file with app params            
	 *
	 * @return {@link AppFactory}
	 */
	public static AppFactory getInstance(String path) 
	{
		return new AppFactory(path);
	}

	/**
	 * Remove param from AppFactory
	 * 
	 * @param key
	 *            key to remove
	 *
	 * @return {@link AppFactory}
	 */
	public AppFactory remove(String key) 
	{
		application.remove(key);
		return this;
	}

	/**
	 * Add key/value to AppFactory
	 * 
	 * @param key
	 *            key to add
	 * @param value
	 *            value to add
	 *            
	 * @return {@link AppFactory}
	 */
	public AppFactory put(String key, String value) 
	{
		application.put(key, (String) ParameterGenerator.process(value));
		return this;
	}
	
	/**
	 * Get map with params from property 
	 *
	 * @return {@link Map}{@literal <}String, String{@literal >}
	 */
	public Map<String, String> create()
	{
		return application;
	}
}
