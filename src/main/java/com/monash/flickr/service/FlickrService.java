package com.monash.flickr.service;

import com.monash.flickr.model.Message;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class FlickrService{

    Connection conn;

    public ArrayList<String> searchFlickrByTags(String tags) throws Exception {
    	
		String uri = "https://www.flickr.com/services/feeds/photos_public.gne?tags=" + tags;
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<String> list = new ArrayList<String>();	
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(uri);

        NodeList nodeList = doc.getElementsByTagName("feed");
        Node node = nodeList.item(0);
        Element elemFeed = (Element) node;
        NodeList entryNodeList = elemFeed.getElementsByTagName("entry");
        
        for (int i = 0; i < entryNodeList.getLength(); i++) {
            Node entryNode = entryNodeList.item(i);
            Element elemEntry = (Element) entryNode;
	           if(entryNode.getNodeType() == Node.ELEMENT_NODE) {
	        	   NodeList linkNodes = elemEntry.getElementsByTagName("link");
	               for (int x = 0; x < linkNodes.getLength(); x++)
	                   if (linkNodes.item(x) instanceof Element) {
	                       Element e = (Element) linkNodes.item(x);
	                       if (e.getAttributes().getNamedItem("rel").getNodeValue().equals("enclosure")) {
	                    	   list.add(e.getAttributes().getNamedItem("href").getNodeValue());
	                       }
	                   }
	           }
        }
	    return list;
    }
   
    
}
