package com.signzy.octoeyes;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.mikepenz.iconics.IconicsColor;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome;
import com.signzy.octoeyes.data.remote.api.GithubClient;
import com.signzy.octoeyes.data.remote.entities.GithubRepo;
import com.signzy.octoeyes.databinding.ActivityMainBinding;
import com.signzy.octoeyes.ui.main.TabsPagerAdapter;
import com.signzy.octoeyes.ui.viewmodel.AppViewModel;
import com.signzy.octoeyes.data.remote.entities.GithubProfile;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final GithubClient githubClient = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initUI();
    }

    private void setView() {
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        final IconicsDrawable fabDrawable = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_github)
                .color(IconicsColor.colorInt(Color.WHITE))
                .size(IconicsSize.dp(16));
        appViewModel.getFabDrawable().setValue(fabDrawable);

        binding.setModel(appViewModel);
        binding.setActivity(this);
        binding.setLifecycleOwner(this);
    }

    private void initUI() {
        initTabs();
    }

    private void initTabs() {
        final TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);

        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void onInfoClick(final View view, final AppViewModel appViewModel) {
        final String userName = appViewModel.getUserName().getValue();

        githubClient
                .getUser(userName)
                .enqueue(new Callback<GithubProfile>() {
                    @Override
                    public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                        appViewModel.getGithubProfile().setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<GithubProfile> call, Throwable t) {
                        final String text = String.format("Failed to get details for user: %s, err: %s", userName, t.getMessage());
                        Snackbar
                                .make(view, text, Snackbar.LENGTH_LONG)
                                .show();
                    }
                });

        appViewModel.getProgressVisibility().setValue(View.VISIBLE);
        githubClient
                .listRepos(userName)
                .enqueue(new Callback<List<GithubRepo>>() {
                    @Override
                    public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                        appViewModel.getGithubRepos().setValue(response.body());
                        appViewModel.getProgressVisibility().setValue(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                        final String text = String.format("Failed to get repo list for user: %s, err: %s", userName, t.getMessage());
                        Snackbar
                                .make(view, text, Snackbar.LENGTH_LONG)
                                .show();
                        appViewModel.getProgressVisibility().setValue(View.GONE);
                    }
                });
    }
}