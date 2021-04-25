import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import org.bson.Document;


import java.util.Arrays;

public class HelloWorld {

    private Properties prop;

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            // load a properties file for reading
            prop.load(new FileInputStream("local.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        MongoClient mongoClient = MongoClients.create(prop.getProperty("mongo.connectionString"));
        MongoDatabase database = mongoClient.getDatabase(prop.getProperty("mongo.dbname"));
        MongoCollection<Document> collection = database.getCollection(prop.getProperty("mongo.collectionName"));

        System.out.println("No' of docs in this collection: " + collection.countDocuments());
        mongoClient.close();
    }
}
