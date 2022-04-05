package pk.name.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pk.name.myapplication.databinding.FragmentMenuBinding;

public class menuFragment extends Fragment implements View.OnClickListener{

    private FragmentMenuBinding binding;
    Button btn_about,btn_progress,btn_foodplanner,btn_plan,btn_deliverwork,btn_help;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // ---------------------------------------------------find button view-----------------------------------------
        btn_about = root.findViewById(R.id.btn_about);
        btn_progress = root.findViewById(R.id.btn_progress);
        btn_foodplanner = root.findViewById(R.id.btn_foodplanner);
        btn_plan = root.findViewById(R.id.btn_plan);
        btn_deliverwork = root.findViewById(R.id.btn_deliverwork);
        btn_help = root.findViewById(R.id.btn_help);

        //----------------------------------------------------setonclicklistener-----------------------------------------
        btn_about.setOnClickListener(this);
        btn_progress.setOnClickListener(this);
        btn_foodplanner.setOnClickListener(this);
        btn_plan.setOnClickListener(this);
        btn_deliverwork.setOnClickListener(this);
        btn_help.setOnClickListener(this);




        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_about:
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new aboutFragment());
                fragmentTransaction.commit();
        }
    }
}