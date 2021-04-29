package com.spotify.oauth2.api.TokenManagement;

import com.spotify.oauth2.Utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.spotify.oauth2.api.SpecBuilder.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static  String access_token;
    private static Instant expiry_time;
    public synchronized static String get_Token(){
        try{
            if(access_token==null ||Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token");
                Response response=renew_token();
                access_token=response.path("access_token");
                int expiryDurationInSeconds=response.path("expires_in");
                expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds);
            }
            else {
                System.out.println("token is good to go");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return access_token;
    }

    private static Response renew_token(){


        Map<String,String> formParams= new HashMap<>();
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id",ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());

     Response response= given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
        when().
                post("/api/token").
        then().
                spec(getResponseSpec()).
                extract().response();
     if(response.statusCode()!=200) throw  new RuntimeException("ABORTING!!! as status code is"+response.statusCode());
     return response;
    }
}
