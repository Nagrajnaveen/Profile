package com.devnags.profile.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devnags.profile.R;
import com.devnags.profile.databinding.FragmentUpdateProfileBinding;
import com.devnags.profile.models.User;
import com.devnags.profile.viewmodel.UserViewModel;


public class UpdateProfileFragment extends Fragment {

    private FragmentUpdateProfileBinding binding;
    private UserViewModel userViewModel;
    private NavController navController;
    private static final String TAG = "UpdateProfileFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                updateUser();
            }
        });

    }

    private void deleteUser() {
        userViewModel.deleteUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProfileBinding.inflate(getLayoutInflater(),container,false);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        return binding.getRoot();
    }

    private void updateUser() {
        String fname = binding.firstNameEditText.getText().toString();
        String lname = binding.lastNameEditText.getText().toString();
        String phone = binding.phoneEditText.getText().toString();
        if(TextUtils.isEmpty(fname) || fname.matches(""))
        {
            binding.firstNameEditText.setError("First Name cannot be empty");
        }

        else if(TextUtils.isEmpty(lname))
        {
            binding.lastNameEditText.setError("Last Name cannot be empty");
        }
        else if(TextUtils.isEmpty(phone) && phone.length() <= 10)
        {
            binding.phoneEditText.setError("Check the phone number");

        }
        else{
            deleteUser();
            User user = new User(fname, lname, phone);
            userViewModel.insertUser(user);
            Toast.makeText(getActivity(),"Updated Successfully",Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_updateProfileFragment_to_myProfileFragment);

        }
    }


}