package pk.name.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import pk.name.myapplication.databinding.FragmentHomeBinding;
import pk.name.myapplication.databinding.FragmentPlanBinding;
public class planFragment extends Fragment  implements View.OnClickListener{
    private FragmentPlanBinding binding;

    public static final String mypref = "mypref";
    public static final String plan = "plankey";


    SharedPreferences sharedPreferences;
    Button mild_plan, mod_plan, vig_plan, back_plan;
    TextView plan_status2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        mild_plan = root.findViewById(R.id.mid_plan);
        mild_plan.setOnClickListener(this);
        mod_plan = root.findViewById(R.id.mod_plan);
        mod_plan.setOnClickListener(this);
        vig_plan = root.findViewById(R.id.vig_plan);
        vig_plan.setOnClickListener(this);
        back_plan = root.findViewById(R.id.back_plan);
        back_plan.setOnClickListener(this);



        plan_status2 = root.findViewById(R.id.plan_status2);
        sharedPreferences = getContext().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(plan)){
            plan_status2.setText(sharedPreferences.getString(plan,""));
        }

        return root;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch(view.getId()){
            case R.id.mid_plan:
                editor.putString(plan,"Mild Plan").commit();
                Toast.makeText(getContext().getApplicationContext(), "Your have changed to the Mild Plan!!",Toast.LENGTH_LONG).show();
                plan_status2.setText(sharedPreferences.getString(plan,""));
                break;

            case R.id.mod_plan:
                editor.putString(plan,"Moderate Plan").commit();
                Toast.makeText(getContext().getApplicationContext(), "Your have changed to the Moderate Plan!!",Toast.LENGTH_LONG).show();
                plan_status2.setText(sharedPreferences.getString(plan,""));
                break;

            case R.id.vig_plan:
                editor.putString(plan,"Vigorous Plan").commit();
                Toast.makeText(getContext().getApplicationContext(), "Your have changed to the Vigorous Plan!!",Toast.LENGTH_LONG).show();
                plan_status2.setText(sharedPreferences.getString(plan,""));
                break;

            case R.id.back_plan:
                ChangeFragment(new menuFragment());
                break;
        }
    }

    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}