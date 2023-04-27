import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConexion {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConexion() {
        this.mongoClient = MongoClients.create();
        this.database = mongoClient.getDatabase("FlowerWorld");
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

}
