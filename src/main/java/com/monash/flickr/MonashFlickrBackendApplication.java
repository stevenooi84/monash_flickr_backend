package com.monash.flickr;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MonashFlickrBackendApplication {

    private static final Logger logger = LogManager.getLogger(MonashFlickrBackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MonashFlickrBackendApplication.class, args);
	}

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            System.out.println(" application.properties " + environment.getProperty("GOOGLE_APPLICATION_CREDENTIALS"));
        };
    }
}
