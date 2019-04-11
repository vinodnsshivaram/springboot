package com.vinodshivaram.practice.springboot.bean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.vinodshivaram.practice.springboot.configuration.MongoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoBean {
    protected Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Autowired
    private MongoConfiguration mongoConfiguration;

    @Bean
    public MongoClient getMongoClient() {
        logger.info("Initializing Mongo Client");
        if (mongoConfiguration.getUri() == null)
            throw new RuntimeException("Mongo URI is not defined in properties");
        return new MongoClient(new MongoClientURI(mongoConfiguration.getUri()));
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
        logger.info("Initializing Mongo Template");
        if (mongoConfiguration.getDatabase() == null)
            throw new RuntimeException("Database name is not defined in properties");
        return new MongoTemplate(getMongoClient(), mongoConfiguration.getDatabase());
    }
}
