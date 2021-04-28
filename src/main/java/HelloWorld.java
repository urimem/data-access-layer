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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HelloWorld {

    public static Properties prop;
    public static final Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {
        setup();

        LOGGER.info("-------------- start --------------");
        MongoClient mongoClient = MongoClients.create(prop.getProperty("mongo.connectionString"));
        //mongoClient.
        MongoDatabase database = mongoClient.getDatabase(prop.getProperty("mongo.dbname"));
        MongoCollection<Document> collection = database.getCollection(prop.getProperty("mongo.collectionName"));

        Document movie = collection.find().first();
        System.out.println("----------");
        System.out.println(movie.toJson());
        System.out.println("----------");
        System.out.println("No' of docs in this collection: " + collection.countDocuments());

        mongoClient.close();
    }

    public static void setup() {
        try {
            // Logger setup
            FileHandler fileHandler = new FileHandler("./logfile.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            // Properties setup
            prop = new Properties();
            prop.load(new FileInputStream("local.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
