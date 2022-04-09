package pk.name.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pk.name.myapplication.databinding.FragmentHelpBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class helpFragment extends Fragment implements View.OnClickListener {
    private FragmentHelpBinding binding;

    Button help_back;
    TextView FAQ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment



        help_back = root.findViewById(R.id.help_back);
        help_back.setOnClickListener(this);
        FAQ = root.findViewById(R.id.FAQ);
        FAQ.setText(getResources().getString(R.string.FAQ1));
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help_back:
                ChangeFragment(new menuFragment());
                break;
        }
    }

    private void ChangeFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}