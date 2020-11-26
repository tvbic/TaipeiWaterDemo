package com.example.unitech.bmicalculator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unitech.bmicalculator.R;
import com.example.unitech.bmicalculator.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment
{
    private HomeViewModel VM;

    private FragmentHomeBinding Binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        VM = new ViewModelProvider(this).get(HomeViewModel.class);
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        Binding.setLifecycleOwner(getViewLifecycleOwner());
        Binding.setVM(VM);

        VM.ToastMsg.observe(getViewLifecycleOwner(), s -> toastMessage(s));
        VM.DialogMsg.observe(getViewLifecycleOwner(), s -> showDialogMessage(s));

        return Binding.getRoot();
    }

    private void toastMessage(String msg)
    {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showDialogMessage(String msg)
    {
        new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setNegativeButton("返回", null)
                .setPositiveButton("清除", (dialog, which) -> VM.clearData())
                .show();
    }
}