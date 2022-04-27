package pk.name.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;



import pk.name.myapplication.databinding.FragmentAllWorkoutBinding;


public class AllWorkoutFragment extends Fragment implements View.OnClickListener {
    private FragmentAllWorkoutBinding binding;
    Button btn_crunch, btn_pushup,btn_plank,btn_menu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        btn_crunch=root.findViewById(R.id.btn_crunch);
        btn_crunch.setOnClickListener(this);
        btn_pushup=root.findViewById(R.id.btn_pushup);
        btn_pushup.setOnClickListener(this);
        btn_plank=root.findViewById(R.id.btn_plank);
        btn_plank.setOnClickListener(this);
        btn_menu=root.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_crunch:
                ChangeFragment(new crunchFragment());
                //code for pass data to be entered
                break;
            case R.id.btn_pushup:
                ChangeFragment(new pushupFragment());
                //code for pass data to be entered
                break;
            case R.id.btn_plank:
                ChangeFragment(new plankFragment());
                //code for pass data to be entered
                break;
            case R.id.btn_menu:
                ChangeFragment(new deliverworkFragment2());
                //code for pass data to be entered
                break;
        }
    }
    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}
