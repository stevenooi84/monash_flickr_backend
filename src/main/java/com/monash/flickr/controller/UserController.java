package com.monash.flickr.controller;

import com.monash.flickr.model.Message;
import com.monash.flickr.service.AuthService;
import com.monash.flickr.service.UserService;
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> putUser(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			userService.createUser(email, password);
			logger.info(String.format("Create User Success [%s] %s", email, password));
			return new ResponseEntity<String>("User creation Successful", null, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(String.format("Create User Failed [%s]", email), e);
			return new ResponseEntity<String>("User creation failed", null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
