package com.elsa.checklist.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elsa.checklist.base.BaseFragment;
import com.elsa.checklist.databinding.FragmentChecklistBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChecklistFragment extends BaseFragment implements ChecklistContract.View {
    @Inject
    ChecklistContract.Presenter presenter;
    FragmentChecklistBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChecklistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        presenter.onAttach(this);
        presenter.getAll();
    }

    @Override
    public void result(ArrayList<Map<String, Object>> list) {
        binding.text.setText(String.valueOf(list));
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }
}