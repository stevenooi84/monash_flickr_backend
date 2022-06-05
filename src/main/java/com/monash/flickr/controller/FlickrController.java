package com.monash.flickr.controller;

import com.monash.flickr.model.Message;
import com.monash.flickr.service.FlickrService;
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
import java.io.StringReader;

import org.w3c.dom.Element;
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
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

@CrossOrigin
@RestController
@RequestMapping("api/flickr")
public class FlickrController{

    private static final Logger logger = LogManager.getLogger(FlickrController.class);
    
    @Autowired
    FlickrService flickrService;

    Connection conn;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> searchImageByTags(HttpServletRequest req, HttpServletResponse resp) {
    	String tags = req.getParameter("tags");
		Message message = new Message();
        try {
			message.setImages(flickrService.searchFlickrByTags(tags));
	        logger.info(String.format("searchImageByTags Success [%s]", tags));
            return new ResponseEntity<Message>(message, null, HttpStatus.OK);
        } catch (Exception e) {
	        logger.error("Error in fetching flickr tags", e);
            return new ResponseEntity<Message>(message, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}
