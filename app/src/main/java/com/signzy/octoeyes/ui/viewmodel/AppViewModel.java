package com.signzy.octoeyes.ui.viewmodel;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.transition.Visibility;

import com.bumptech.glide.Glide;
import com.signzy.octoeyes.data.remote.entities.GithubProfile;
import com.signzy.octoeyes.data.remote.entities.GithubRepo;
import com.signzy.octoeyes.ui.adapter.RepoRecyclerViewAdapter;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class AppViewModel extends ViewModel {

    @Setter
    @Getter
    private MutableLiveData<Drawable> fabDrawable = new MutableLiveData<>();

    @Setter
    @Getter
    private MutableLiveData<Integer> progressVisibility = new MutableLiveData<>(View.GONE);

    @Setter
    @Getter
    private MutableLiveData<String> userName = new MutableLiveData<>();

    @Setter
    @Getter
    private MutableLiveData<GithubProfile> githubProfile = new MutableLiveData<>();

    @Setter
    @Getter
    private MutableLiveData<RepoRecyclerViewAdapter> repoAdapter = new MutableLiveData<>();

    @Setter
    @Getter
    private MutableLiveData<List<GithubRepo>> githubRepos = new MutableLiveData<>();

    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView view, final String url) {
        Glide
                .with(view.getContext())
                .load(url)
                .into(view);
    }
}
