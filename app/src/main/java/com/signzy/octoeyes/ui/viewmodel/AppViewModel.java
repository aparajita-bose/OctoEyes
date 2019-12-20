package com.signzy.octoeyes.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lombok.Setter;

public class AppViewModel extends ViewModel {
    @Setter
    @Getter
    private MutableLiveData<String> userName = new MutableLiveData<>();

    @Setter
    @Getter
    private MutableLiveData<GithubProfile> githubProfile = new MutableLiveData<>();
}
