/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoapp;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author fxyong
 */
public class MongoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //initialize a list of dummy usernames
        List<String> userNameList = new ArrayList<String>();
        userNameList.add("John Tan");
        userNameList.add("John Lee");
        userNameList.add("John Leong");
        userNameList.add("John Lim");
        userNameList.add("Sam Tan");
        userNameList.add("Sam Ng");
        userNameList.add("Sam Cheong");
        
        //create connection to MongoDB Server
        Mongo mongo = new MongoClient("localhost", 27017);
        MongoClient mongoClient = new MongoClient();
        
        //retrieve a database from MongoDB. 
        //if not found, MongoDB will auto create it for us
        MongoDatabase db = mongoClient.getDatabase("User");
        
        for (String username : userNameList) {
            String[] usernameArr = username.split(" ");
            //using the "keyword" to retrieve the collection from MongoDB.
            //if not found, MongoDB will auto create it for us
            MongoCollection<Document> collection = db.getCollection(usernameArr[0]);
            
            Document document = new Document();
            document.put("name", username);
            collection.insertOne(document);
        }
        //close MongoDB connection
        mongo.close();
    }
}
