package com.uri.data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClient;

import java.util.List;

// Not in use - Does not allow dependency injection
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
        initCollection();
        collection.insertOne(document);
    }

    @Override
    public void add(Iterable<Document> documents) {
        initCollection();
        collection.insertMany((List<? extends Document>) documents);
    }

    @Override
    public Iterable<Document> get(Document document) {
        initCollection();
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
    public void update(Document queryFilter, Document document) {
        initCollection();
        collection.updateMany(queryFilter, document);
    }

    @Override
    public long delete(Document document) {
        initCollection();
        var result = collection.deleteMany(document);
        return result.getDeletedCount();
    }
}
