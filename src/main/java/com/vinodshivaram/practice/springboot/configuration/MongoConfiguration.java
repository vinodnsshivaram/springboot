package com.vinodshivaram.practice.springboot.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mongodb")
public class MongoConfiguration {
    protected Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);
    private String uri;
    private String host;
    private String port;
    private String username;
    private String password;
    private String database;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Bean
    public MongoClient getMongoClient() {
        logger.info("Initializing Mongo Client");
        if (uri == null)
            throw new RuntimeException("Mongo URI is not defined in properties");
        return new MongoClient(new MongoClientURI(uri));
    }

    @Bean
    public MongoTemplate getMongoTemplate() {
        logger.info("Initializing Mongo Template");
        if (database == null)
            throw new RuntimeException("Database name is not defined in properties");
        return new MongoTemplate(getMongoClient(), database);
    }
}
