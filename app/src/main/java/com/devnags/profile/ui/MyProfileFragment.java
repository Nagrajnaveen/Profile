package com.devnags.profile.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnags.profile.R;
import com.devnags.profile.databinding.FragmentMyProfileBinding;
import com.devnags.profile.models.User;
import com.devnags.profile.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;


public class MyProfileFragment extends Fragment {

    private static final String TAG = "MyProfileFragment";
    private FragmentMyProfileBinding binding;
    private NavController navController;
    private UserViewModel userViewModel;
    List<User> userList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyProfileBinding.inflate(getLayoutInflater(),container,false);
       if (!userList.isEmpty()) {
           binding.phoneTextView.setText(userList.get(0).getPhone());
           binding.firstNameTextView.setText(userList.get(0).getFirstName());
           binding.lastNameTextView.setText(userList.get(0).getLastName());
       }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        binding.updateProfileFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToUpdateUser();
            }
        });

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
//        Log.d(TAG, "onViewCreated: "+userViewModel.getUser().hasObservers());;
        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (!users.isEmpty()) {
                    Log.d(TAG, "onChanged: " + users.get(0).getFirstName());
                    binding.phoneTextView.setText(users.get(0).getPhone());
                     binding.firstNameTextView.setText(users.get(0).getFirstName());
                     binding.lastNameTextView.setText(users.get(0).getLastName());

                }

            }
        });
    }

    public void navigateToUpdateUser(){
        navController.navigate(R.id.action_myProfileFragment_to_updateProfileFragment);
    }



}