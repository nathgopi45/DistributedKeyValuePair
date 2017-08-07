package com.indix.distributed.keyValue.secondary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(basePackages="com.indix.distributed.*")
public class SecondaryKeyValueApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueApplication.class);

	public static void main(String[] args) {		
		SpringApplication.run(SecondaryKeyValueApplication.class, args);
		LOGGER.info("Distributed - Secondary Event handler Application Started Successfully");
	}
		
		
}
