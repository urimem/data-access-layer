package com.uri.data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MongoRepositoryTest {

    // application configuration
    Properties properties;
    // Mongo client object - keeping reference to close connecion.
    MongoClient mongoClient;
    // Tested class/code
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
    @Order(1)
    void find() {
        // Query for titles containing the string "black" - should have 127
        Document regQuery = new Document();
        regQuery.append("$regex", Pattern.quote("black")); //"^(?)" + Pattern.quote("black")); starts with
        regQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("title", regQuery);

        Iterable<Document> results = movieRepository.get(findQuery);
        var ref = new Object() {
            long count = 0;
        };
        results.forEach((d) -> {
            ref.count++;});

        // Should contain xxx docs with title containing "black"
        assertEquals(127, ref.count);
    }

    private Document getTestMovie1() {
        Document newMovieDoc = new Document("_id", new ObjectId());
        newMovieDoc.append("title", "The blind swordsman")
                .append("plot", "Zatoichi the blind swordsman fight the unjustice.")
                .append("year", 1966)
                .append("type", "movie");
        //        .append("genres", asList("Action","Drama"));
        return newMovieDoc;
    }

    @Test
    @Order(2)
    // add movie and find it by title
    void addAndGet() {
        movieRepository.add(getTestMovie1());

        Iterable<Document> results = movieRepository.get(new Document("title", "The blind swordsman"));
        var resultDoc = results.iterator().next();
        if (resultDoc == null) {
            assert(false);
        }
        else {
            int year = (int)resultDoc.get("year");
            assertEquals(year, 1966);
        }
    }

    @Test
    @Order(3)
    // Delete the movie added in prev test
    void delete() {
        long count = movieRepository.delete(new Document("title", "The blind swordsman"));
        assertEquals(count, 1);
    }
}