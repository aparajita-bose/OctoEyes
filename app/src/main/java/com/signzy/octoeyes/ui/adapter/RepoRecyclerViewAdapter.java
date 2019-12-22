package com.signzy.octoeyes.ui.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.ComponentActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.signzy.octoeyes.R;
import com.signzy.octoeyes.data.remote.entities.GithubRepo;
import com.signzy.octoeyes.databinding.ItemRepoBinding;

import java.util.List;

public class RepoRecyclerViewAdapter
        extends RecyclerView.Adapter<RepoRecyclerViewAdapter.ViewHolder>
        implements RepoRecyclerViewClickListener {

    private final LiveData<List<GithubRepo>> repos;
    private final ComponentActivity activity;

    public RepoRecyclerViewAdapter(
            final LiveData<List<GithubRepo>> repos,
            final ComponentActivity activity) {
        this.repos = repos;
        this.activity = activity;

        this.repos.observe(
                activity,
                githubRepos -> RepoRecyclerViewAdapter.this.notifyDataSetChanged());
    }


    @Override
    public RepoRecyclerViewAdapter.ViewHolder onCreateViewHolder(
            final ViewGroup parent,
            final int viewType) {
        final ItemRepoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_repo,
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(
            final ViewHolder holder,
            final int position) {
        if (repos.getValue() == null
                || position >= repos.getValue().size()) {
            return;
        }

        final GithubRepo githubRepo = repos.getValue().get(position);
        holder.bind(this, githubRepo);
    }


    @Override
    public int getItemCount() {
        return repos.getValue() == null? 0: repos.getValue().size();
    }

    @Override
    public void clicked(GithubRepo repo) {
        final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        customTabsIntent.launchUrl(activity, Uri.parse(repo.getHtmlUrl()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRepoBinding itemRowBinding;

        ViewHolder(final ItemRepoBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        void bind(final RepoRecyclerViewClickListener clickListener, final GithubRepo repo) {
            itemRowBinding.setRepo(repo);
            itemRowBinding.setClickListener(clickListener);
            itemRowBinding.setLifecycleOwner(activity);
            itemRowBinding.executePendingBindings();
        }
    }
}