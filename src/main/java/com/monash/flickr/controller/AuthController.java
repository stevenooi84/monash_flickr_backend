package com.monash.flickr.controller;

import com.monash.flickr.service.AuthService;
import com.monash.flickr.MonashFlickrBackendApplication;
import com.monash.flickr.config.DataSourceConfig;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@CrossOrigin
@RestController
@RequestMapping("api/login2")
public class AuthController{

    private static final Logger logger = LogManager.getLogger(AuthController.class);
    
    @Autowired
    AuthService authService;
    
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> login(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			if (authService.validateLogin(email, password)) {
		        logger.info(String.format("Login Success [%s]", email));
				return new ResponseEntity<String>("Login Successful", null, HttpStatus.OK);
			} 
	        logger.info(String.format("Login Failed [%s]", email));
			return new ResponseEntity<String>("User not found or password not matched", null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
	        logger.error("Login Error", e);
			return new ResponseEntity<String>("Login unavailable. Please contact administrator", null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
    }
   
    
}
