package com.spotify.oauth2.api.ApplicationApi;

import com.spotify.oauth2.api.Routes.Routes;
import com.spotify.oauth2.api.commons.RestResource;
import com.spotify.oauth2.pojo.PlaylistRootRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Routes.Routes.PLAYLISTS;
import static com.spotify.oauth2.api.Routes.Routes.USERS;
import static com.spotify.oauth2.api.TokenManagement.TokenManager.get_Token;

public class PlaylistApi {
    @Step
    public static Response post(PlaylistRootRequest playlistRootRequest,String userId){
        return RestResource.post(playlistRootRequest, USERS +"/"+userId+PLAYLISTS,get_Token());
    }
    @Step
    public static  Response post(String token,PlaylistRootRequest playlistRootRequest,String userId){
        return RestResource.post(playlistRootRequest,USERS+"/"+userId+PLAYLISTS,token);
    }
    @Step
    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS+"/"+playlistId,get_Token());
    }
    @Step
    public static Response update(PlaylistRootRequest playlistRootRequest,String playlistId){
        return RestResource.update(playlistRootRequest,PLAYLISTS+"/"+playlistId,get_Token());
    }

}
