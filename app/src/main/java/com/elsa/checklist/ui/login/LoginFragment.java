package com.elsa.checklist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elsa.checklist.base.BaseFragment;
import com.elsa.checklist.databinding.FragmentLoginBinding;
import com.elsa.checklist.ui.MainActivity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment implements LoginContract.View {
    FragmentLoginBinding binding;
    @Inject
    LoginContract.Presenter presenter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void setUp(View view) {
        presenter.onAttach(this);
        binding.loginBtn.setOnClickListener(v -> {
            presenter.login(String.valueOf(binding.username.getText()), String.valueOf(binding.password.getText()));
        });
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getBaseActivity(), MainActivity.class);
        startActivity(intent);
        getBaseActivity().finish();
    }
}