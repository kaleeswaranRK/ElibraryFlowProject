package com.elib.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProperty {

	static BasicDataSource dataSource;

	public static BasicDataSource getDataSource() throws IOException {
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
				ds.setMinIdle(5);
				ds.setMaxIdle(10);
				ds.setMaxTotal(25);
				dataSource = ds;
				return dataSource;
			}
			else {
				System.out.println("data source already assigned");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return dataSource;
	}

	public Connection getDBConnection() {
		try {
			return getDataSource().getConnection();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
