import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoClientSingleton {

    private static volatile MongoClient client;

    private MongoClientSingleton() {}

    public static MongoClient getInstance() {
        if (client == null) {
            synchronized (MongoClientSingleton.class) {
                if (client == null)
                    client = MongoClients.create(HelloWorld.prop.getProperty("mongo.connectionString"));
            }
        }
        return client;
    }
}
