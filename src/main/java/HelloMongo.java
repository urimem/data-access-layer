import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.uri.data_access.MongoRepository;
import org.bson.Document;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

public class HelloMongo {

    public static Properties prop;
    public static final Logger LOGGER = Logger.getLogger(HelloMongo.class.getName());

    public static void main(String[] args) {
        // Set logging and properties
        setup();

        LOGGER.info("------- HelloMongo.main start --------");
        /*
        // Use only one instance of MongoClient in the service
        MongoClient mongoClient = MongoClients.create(prop.getProperty("mongo.connectionString"));
        // Create Repository for the movies collection
        MongoRepository movieRepository = new MongoRepository(mongoClient, prop.getProperty("mongo.dbname"), prop.getProperty("mongo.collectionName"));

        // Query for titles containing the string "black"
        Document regQuery = new Document();
        regQuery.append("$regex", Pattern.quote("black")); //"^(?)" + Pattern.quote("black")); starts with
        regQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("title", regQuery);

        Iterable<Document> results = movieRepository.get(findQuery);
        for (Document movie : results) {
            // TODO: print only title
            System.out.println("---------- movie ----------");
            System.out.println(movie.toJson());
        }
        //System.out.println("No' of docs found: " + results.);
        //Document movie = results.iterator().next();
        System.out.println("---------- end -----------");
        //System.out.println("No' of docs in this collection: " + collection.countDocuments());

        mongoClient.close();
        */
    }

    public static void setup() {
        try {
            // Logger setup
            FileHandler fileHandler = new FileHandler("./logfile.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            java.util.logging.Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);

            // Properties setup
            prop = new Properties();
            prop.load(new FileInputStream("local.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
