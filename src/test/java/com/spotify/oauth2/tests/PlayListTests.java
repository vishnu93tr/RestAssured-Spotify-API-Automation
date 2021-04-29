package com.spotify.oauth2.tests;


import com.spotify.oauth2.Utils.ConfigLoader;
import com.spotify.oauth2.Utils.DataLoader;
import com.spotify.oauth2.Utils.StatusCode;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.PlaylistRootRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.Utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.Utils.FakerUtils.generateName;
import static com.spotify.oauth2.api.ApplicationApi.PlaylistApi.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests {
    @Step
    public PlaylistRootRequest getPlayListBuilder(String name,String description,boolean _public){
        return PlaylistRootRequest.
                builder().
                name(name).
                description(description).
                _public(_public).build();

    }
    @Step
    public void PlayListAsserts(PlaylistRootRequest response, PlaylistRootRequest request){
        assertThat(response.getName(),equalTo(request.getName()));
        assertThat(response.getDescription(),equalTo(request.getDescription()));
        assertThat(response.get_public(),equalTo(request.get_public()));
    }
    @Step
    public void assertStatusCode(int actual,int expected){
        assertThat(actual,equalTo(expected));
    }
    @Step
    public void ErrorAsserts(ErrorRoot response,int status_code,String message){
        assertThat(response.getError().getStatus(),equalTo(status_code));
        assertThat(response.getError().getMessage(),equalTo(message));
    }

    /*This method creates a playlist for a user*/
    @Test(priority = 1,description = "create spotify playlist post API")
    @Description("This method creates a playlist for a user")
    @Severity(SeverityLevel.BLOCKER)
    public void create_playlist(){
     PlaylistRootRequest playlistRootRequest=   getPlayListBuilder(
             generateName(),
             generateDescription(),true);
     Response response=post(playlistRootRequest, ConfigLoader.getInstance().getUser());
    assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
    PlayListAsserts(response.as(PlaylistRootRequest.class),playlistRootRequest);
    }
    /*This method gets already existing play list for a particular user*/
    @Test(priority = 3,description = "get existing playlist with user id and playlist Id")
    @Description("This method gets already existing play list for a particular user")
    @Severity(SeverityLevel.CRITICAL)
    public void get_playlist(){

        PlaylistRootRequest playlistRootRequest=   getPlayListBuilder(
                "Your coolest updated Playlist","My coolest public playlist",true);

        Response response=get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());

    }
    /*This method updates already existing play list for a particular user*/
    @Test(priority = 2,description = "Update playlist with user id and playlist Id ")
    @Description("This method updates already existing play list for a particular user")
    @Severity(SeverityLevel.NORMAL)
    public void update_playlist(){
        PlaylistRootRequest playlistRootRequest=   getPlayListBuilder(
                generateName(),
                generateDescription(),true);
        Response response=update(playlistRootRequest,DataLoader.getInstance().getUpdatePlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());


    }
    /*This method doesnt create a playlist for a user due to client error*/
    @Test(priority = 4,description = "doesnt crete a playlist due to client error")
    @Description("This method doesnt create a playlist for a user due to client error")
    @Severity(SeverityLevel.NORMAL)
    public void not_create_playlist(){

        PlaylistRootRequest playlistRootRequest=   getPlayListBuilder(
                "",generateDescription(),true);
        Response response=post(playlistRootRequest,ConfigLoader.getInstance().getUser());
        assertStatusCode(response.statusCode(),StatusCode.CODE_400.getCode());
        ErrorAsserts(response.as(ErrorRoot.class),StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMessage());

    }
    /*This method doesnt create a playlist for a user due to invalid access token*/
    @Test(priority = 5,description = "doesnt crete a playlist due to invalid access token")
    @Description("This method doesnt create a playlist for a user due to invalid access token")
    @Severity(SeverityLevel.NORMAL)
    public void invalid_access_token(){
        PlaylistRootRequest playlistRootRequest=   getPlayListBuilder(
                generateName(),
                generateDescription(),true);
        Response response=post("123",playlistRootRequest,ConfigLoader.getInstance().getUser());
        assertStatusCode(response.statusCode(),StatusCode.CODE_401.getCode());
        ErrorAsserts(response.as(ErrorRoot.class),StatusCode.CODE_401.getCode(),StatusCode.CODE_401.getMessage());
    }

}
