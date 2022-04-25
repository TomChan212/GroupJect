package pk.name.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pk.name.myapplication.databinding.FragmentHomeBinding;
import pk.name.myapplication.databinding.FragmentProgressBinding;

public class progressFragment extends Fragment implements View.OnClickListener {
    private FragmentProgressBinding binding;

    Button progress_back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        progress_back = root.findViewById(R.id.progress_back);
        progress_back.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.progress_back:
                ChangeFragment(new menuFragment());
                break;
        }
    }

    private void ChangeFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}