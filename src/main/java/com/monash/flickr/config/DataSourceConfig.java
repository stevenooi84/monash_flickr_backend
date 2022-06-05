package com.monash.flickr.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration
public class DataSourceConfig {

  @Value("${DB_USER}")
  private String dbUser;
  
  @Value("${DB_PASSWORD}")
  private String dbPassword;

  // @Bean
  public DataSource setUpPool() throws SQLException {
		HikariConfig config = new HikariConfig();
		String jdbcURL = String.format("jdbc:mysql:///%s", "app");
		config.setJdbcUrl(jdbcURL);
		//config.setUsername(dbUser); // e.g. "root", "mysql"
		//config.setPassword(dbPassword); // e.g. "my-password
		config.setUsername("root"); // e.g. "root", "mysql"
		config.setPassword("password"); // e.g. "my-password
		System.out.println("-----------------------------------");
		System.out.println(dbUser);
		System.out.println(dbPassword);
		config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
		config.addDataSourceProperty("cloudSqlInstance", "meta-tracker-352000:us-central1:flickr");
		
		config.addDataSourceProperty("ipTypes", "PUBLIC,PRIVATE");
		return new HikariDataSource(config);
  }
}