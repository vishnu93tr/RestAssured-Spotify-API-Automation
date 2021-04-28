package com.spotify.oauth2.api.commons;

import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(Object playlistRootRequest,String path,String access_token){
         return given(getRequestSpec()).
                auth().oauth2(access_token).
                body(playlistRootRequest).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }
    public static Response get(String path,String access_token){
        return given(getRequestSpec()).
                auth().oauth2(access_token).
                when().
                get(path).
                then().
                spec(getResponseSpec())
                .extract().response();
    }
    public static Response update(Object playlistRootRequest,String path,String access_token){
     return  given(getRequestSpec()).
             auth().oauth2(access_token).
                body(playlistRootRequest).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

}
