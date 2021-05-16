package com.uri.data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MongoRepositoryTest {

    Properties properties;
    MongoClient mongoClient;
    MongoRepository movieRepository;

    @BeforeAll
    void setup() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("local.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Use only one instance of MongoClient in the service
        mongoClient = MongoClients.create(properties.getProperty("mongo.connectionString"));
        // Create Repository for the movies collection
        movieRepository = new MongoRepository(mongoClient, properties.getProperty("mongo.dbname"), properties.getProperty("mongo.collectionName"));
    }

    @AfterAll
    void tearDown() {
        mongoClient.close();
    }

    @Test
    void add() {
    }

    @Test
    void get() {
    }

    @Test
    void count() {
    }

    @Test
    void delete() {
    }
}