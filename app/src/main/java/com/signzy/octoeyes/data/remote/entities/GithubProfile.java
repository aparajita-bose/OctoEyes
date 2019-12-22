package com.signzy.octoeyes.data.remote.entities;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GithubProfile {
    private String name;
    private String bio;
    @SerializedName("public_repos")
    private int repoCount;
    @SerializedName("avatar_url")
    private String avatarUrl;
}
