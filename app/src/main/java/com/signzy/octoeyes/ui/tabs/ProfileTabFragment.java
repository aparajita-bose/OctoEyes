package com.signzy.octoeyes.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.signzy.octoeyes.R;
import com.signzy.octoeyes.databinding.FragmentProfileTabBinding;
import com.signzy.octoeyes.ui.viewmodel.AppViewModel;

public class ProfileTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        final FragmentProfileTabBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile_tab, container, false);

        final AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        binding.setModel(appViewModel);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    public static ProfileTabFragment getInstance() {
        return new ProfileTabFragment();
    }
}
