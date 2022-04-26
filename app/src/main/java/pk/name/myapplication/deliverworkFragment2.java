package pk.name.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import pk.name.myapplication.databinding.FragmentAboutBinding;
import pk.name.myapplication.databinding.FragmentDeliverwork2Binding;
import pk.name.myapplication.databinding.FragmentDeliverworkBinding;

public class deliverworkFragment2 extends Fragment implements View.OnClickListener {
    private FragmentDeliverwork2Binding binding;

    Button btn_w1, btn_w2,btn_w3,btn_menu;
    TextView tv_day,tv_plan;
    ImageView checkbox1,checkbox2,checkbox3;
    private float bmi;
    private boolean workOne;
    private boolean workTwo;
    private boolean workThree;

    SharedPreferences sharedPreferences;
    public static final String mypref = "mypref";
    public static final String BMI = "BMIkey";
    public static final String work1 = "work1Key";
    public static final String work2 = "work2Key";
    public static final String work3 = "work3Key";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverwork2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_w1=root.findViewById(R.id.wButton1);
        btn_w1.setOnClickListener(this);
        btn_w2=root.findViewById(R.id.wButton2);
        btn_w2.setOnClickListener(this);
        btn_w3=root.findViewById(R.id.wButton3);
        btn_w3.setOnClickListener(this);
        btn_menu=root.findViewById(R.id.menu);
        btn_menu.setOnClickListener(this);
        tv_day=root.findViewById(R.id.test);
        tv_plan=root.findViewById(R.id.showPlan);
        checkbox1 = root.findViewById(R.id.checkbox1);
        checkbox2 = root.findViewById(R.id.checkbox2);
        checkbox3 = root.findViewById(R.id.checkbox3);

        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);

        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));
        workOne = sharedPreferences.getBoolean(work1, false);
        workTwo = sharedPreferences.getBoolean(work2, false);
        workThree = sharedPreferences.getBoolean(work3, false);

        if( workOne == true){
            checkbox1.setImageResource(R.drawable.ic_baseline_check_box_24);
        }
        if( workTwo == true){
            checkbox2.setImageResource(R.drawable.ic_baseline_check_box_24);
        }
        if( workThree == true){
            checkbox3.setImageResource(R.drawable.ic_baseline_check_box_24);
        }

        if(workOne != true){
            btn_w2.setEnabled(false);
        }
        if(workOne != true || workTwo != true){
            btn_w3.setEnabled(false);
        }







        showPlan();

        return root;
    }

    public void showPlan(){
        if (bmi<18.5) {
            tv_plan.setText("Plan: Fitness Plan 1");
        }else if (bmi<25){
            tv_plan.setText("Plan: Fitness Plan 2");
            }else {
            tv_plan.setText("Plan: Fitness Plan 3");
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.wButton1:
                ChangeFragment(new deliverworkFragment());
                //code for pass data to be entered
                break;
            case R.id.wButton2:
                ChangeFragment(new deliverworkFragment3());
                //code for pass data to be entered
                break;
            case R.id.wButton3:
                ChangeFragment(new deliverworkFragment4());
                //code for pass data to be entered
                break;
            case R.id.menu:
                ChangeFragment(new menuFragment());
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