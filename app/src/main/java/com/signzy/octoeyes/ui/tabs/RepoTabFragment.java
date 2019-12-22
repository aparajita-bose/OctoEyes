package com.signzy.octoeyes.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.signzy.octoeyes.R;
import com.signzy.octoeyes.databinding.FragmentProfileTabBinding;
import com.signzy.octoeyes.databinding.FragmentRepolistTabBinding;
import com.signzy.octoeyes.ui.adapter.RepoRecyclerViewAdapter;
import com.signzy.octoeyes.ui.viewmodel.AppViewModel;

public class RepoTabFragment extends Fragment {

    public static RepoTabFragment getInstance() {
        return new RepoTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        final FragmentRepolistTabBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_repolist_tab, container, false);

        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        binding.setModel(appViewModel);
        binding.setLifecycleOwner(getActivity());

        initRecyclerView(appViewModel);

        return binding.getRoot();
    }

    private void initRecyclerView(AppViewModel appViewModel) {
        final RepoRecyclerViewAdapter adapter = new RepoRecyclerViewAdapter(appViewModel.getGithubRepos(), getActivity());
        appViewModel.getRepoAdapter().setValue(adapter);
    }
}
