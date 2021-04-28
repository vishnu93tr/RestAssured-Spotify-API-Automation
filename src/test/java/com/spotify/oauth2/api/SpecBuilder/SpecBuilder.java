package com.spotify.oauth2.api.SpecBuilder;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Routes.Routes.BASE_PATH;
import static com.spotify.oauth2.api.Routes.Routes.BASE_URI;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){

       return new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
               addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();
    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
}
