package com.spotify.oauth2.DataBase;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.concurrent.TimeUnit;

public class DatabaseManager {
    static String cluster_url="mongodb+srv://vishnuvardhan:Ap02%40v2974@restassuredcluster.yiuty.mongodb.net/RestAssured?retryWrites=true&w=majority";
    static Document document;
    public synchronized static void ConnectToMongoDB(){

       MongoClient mongoClient=MongoClients.create(cluster_url);

        MongoDatabase database=mongoClient.getDatabase("RestAssured");
        MongoCollection<Document> configProperties=database.getCollection("ConfigProperties");
         document = configProperties.find(new Document("grant_type","refresh_token")).first();
        System.out.println("connected to Data Base");
    }

    public static String getclientId(){
        return document.getString("client_id");
    }
    public static String getClientSecret(){
        return document.getString("client_secret");
    }
    public static String getRefreshToken(){
        return document.getString("refresh_token");
    }
    public static String getUser_id(){
        return document.getString("user_id");
    }
    public static String getGrantType(){
        return document.getString("grant_type");
    }
    public static String getPlayListId(){
        return document.getString("getPlayListId");
    }
    public static String updatePlayListId(){
        return document.getString("updatePlayListId");
    }
}
