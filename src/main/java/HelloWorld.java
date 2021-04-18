import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;

import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) {    
        System.out.println("Hello World");

        MongoClient mongoClient = MongoClients.create("mongodb+srv://ogre:ws342edW@cluster0.o7p2u.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

    }
}
