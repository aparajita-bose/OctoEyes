package com.signzy.octoeyes.ui.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GithubProfile {
    private String name;
    private String bio;
    private String location;
    private Integer repoCount;
    private String avatarUrl;
}
