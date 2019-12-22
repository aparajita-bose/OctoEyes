package com.signzy.octoeyes.data.remote.entities;

import androidx.lifecycle.ViewModel;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubRepo extends ViewModel {
    private String name;
    @SerializedName("html_url")
    private String htmlUrl;
}
