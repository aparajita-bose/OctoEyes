package com.signzy.octoeyes;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.signzy.octoeyes.databinding.ActivityMainBinding;
import com.signzy.octoeyes.ui.main.TabsPagerAdapter;
import com.signzy.octoeyes.ui.viewmodel.AppViewModel;
import com.signzy.octoeyes.ui.viewmodel.GithubProfile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        initUI();
    }

    private void setView() {
//        setContentView(R.layout.activity_main);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final AppViewModel appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
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

    public void onInfoClick(final View view, final AppViewModel mainViewModel) {

        final GithubProfile profile = new GithubProfile();
        profile.setName(mainViewModel.getUserName().getValue());
        mainViewModel.getGithubProfile().setValue(profile);

        //        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar
//                        .make(view, mainViewModel.getUserName().getValue(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null)
//                        .show();
//            }
//        });
    }
}