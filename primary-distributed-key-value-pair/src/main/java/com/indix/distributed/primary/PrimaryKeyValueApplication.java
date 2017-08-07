package com.indix.distributed.primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages="com.*", exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableAsync
public class PrimaryKeyValueApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryKeyValueApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PrimaryKeyValueApplication.class, args);
    LOGGER.info("NextGen Tracking - Event Store EventStoreApplication Staretd Successfully");
  }


}
