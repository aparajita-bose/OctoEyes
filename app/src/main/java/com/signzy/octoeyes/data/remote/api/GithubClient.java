package com.signzy.octoeyes.data.remote.api;

import com.signzy.octoeyes.data.remote.entities.GithubRepo;
import com.signzy.octoeyes.data.remote.entities.GithubProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("users/{user}")
    Call<GithubProfile> getUser(@Path("user") final String user);

    @GET("users/{user}/repos")
    Call<List<GithubRepo>> listRepos(@Path("user") String user);
}
