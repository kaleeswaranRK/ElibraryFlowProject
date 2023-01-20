package com.elib.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProperty {

	static BasicDataSource dataSource;
	static 	Logger logger = LogManager.getLogger("ElibraryFlowProject");

	public static BasicDataSource getDataSource() {
		
		try {
			if (dataSource == null) {
				FileInputStream file = new FileInputStream(
						"C:\\Users\\User\\Desktop\\Kaleeswaran\\Springtool\\ElibraryFlowProject\\src\\main\\resources\\application.properties");
				Properties property = new Properties();
				property.load(file);
				String driver = property.getProperty("driverjdbc");
				String connectionurl = property.getProperty("connectionUrl");
				String username = property.getProperty("username");
				String password = property.getProperty("password");
				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName(driver);
				ds.setUrl(connectionurl);
				ds.setUsername(username);
				ds.setPassword(password);
				logger.info("driverjdbc = "+driver+"connectionUrl = "+connectionurl+"username = "+username+"password"+password);
				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxTotal(25);
				dataSource = ds;
				return dataSource;
			} 
			else {
				logger.info("data source already assigned");
				return dataSource;
			}

		} catch (Exception e) {
			logger.info(e);
		}
		return dataSource;
	}

	public Connection getDBConnection() {
		try {
			return getDataSource().getConnection();

		} catch (SQLException e) {
			logger.error(e);
		}
		catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
