package com.monash.flickr.service;

import com.monash.flickr.model.Message;
import com.monash.flickr.config.DataSourceConfig;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.stream.Collectors;

@Service
public class AuthService{

	//@Autowired
	//DataSourceConfig dataSourceConfig;
	
    Connection conn;

    public boolean validateLogin(String email, String password) throws SQLException {
    	boolean validateFlag = false;
    	DataSourceConfig dataSourceConfig = new DataSourceConfig();
    	String sql = "SELECT id FROM user where email = ? and password = ?";

	   	DataSource pool = dataSourceConfig.setUpPool();
	    conn = pool.getConnection();
	    PreparedStatement p = conn.prepareStatement(sql);
	    p.setString(1, email);
	    p.setString(2, password);
	    try (ResultSet rs = p.executeQuery()) {
	      while (rs.next()) {
	    	  validateFlag = true;
	      }
	
	    } catch (SQLException e) {
	    	throw new SQLException("SQL error", e);
	    }
	
	    conn.close();
	    return validateFlag;
    }
   
    
}
