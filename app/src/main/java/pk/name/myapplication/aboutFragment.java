package pk.name.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pk.name.myapplication.databinding.FragmentAboutBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class aboutFragment extends Fragment {
    private FragmentAboutBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment
        return root;
    }
}