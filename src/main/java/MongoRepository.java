import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClient;

public class MongoRepository implements IRepository<Document, Document> {

    private String dbName;
    private String collectionName;
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoRepository(MongoClient mongoClient, String dbName, String collectionName) {
        this.collectionName = collectionName;
        this.dbName = dbName;
        this.mongoClient = mongoClient;
    }

    private void initCollection() {
        if (collection != null)
            return;
        if ((mongoClient != null) && (dbName != null) && (collectionName != null)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            collection = database.getCollection(collectionName);
        }
    }
    @Override
    public void add(Document document) {

    }

    @Override
    public void add(Iterable<Document> documents) {

    }

    @Override
    public Iterable<Document> get(Document document) {
        initCollection();
        //collection
        return collection.find(document);
    }

    @Override
    public Iterable<Document> getAll() {
        return null;
    }

    @Override
    public long count() {
        initCollection();
        return collection.countDocuments();
    }

    @Override
    public void update(Document document) {

    }

    @Override
    public void update(Iterable<Document> documents) {

    }

    @Override
    public void delete(Document document) {

    }
}