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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private boolean Done;
    private int tmr;
    int day;
    private String currentplan;

    SharedPreferences sharedPreferences;
    public static final String mypref = "mypref";
    public static final String BMI = "BMIkey";
    public static final String work1 = "work1Key";
    public static final String work2 = "work2Key";
    public static final String work3 = "work3Key";
    public static final String todayK = "todayKey";
    public static final String sday = "sdayKey";
    public static final String done = "doneKey";
    public static final String plan = "plankey";

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
        tv_day=root.findViewById(R.id.tv_day);
        tv_plan=root.findViewById(R.id.showPlan);
        checkbox1 = root.findViewById(R.id.checkbox1);
        checkbox2 = root.findViewById(R.id.checkbox2);
        checkbox3 = root.findViewById(R.id.checkbox3);

        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        day = sharedPreferences.getInt(sday,0);

        try {
            tmr = Integer.valueOf(sharedPreferences.getString(todayK, ""));
        }
        catch (Exception e){

        }
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd");
        int todayAsString = Integer.valueOf(dateFormat.format(today));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Done = sharedPreferences.getBoolean(done,false);
        if (tmr != todayAsString){
            btn_w2.setEnabled(false);
            btn_w3.setEnabled(false);

            editor.putBoolean(work1,false).commit();
            editor.putBoolean(work2,false).commit();
            editor.putBoolean(work3,false).commit();

            if(Done == false) {
                changeDay();
                editor.putBoolean(done, true).commit();
            }

        }


        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));
        workOne = sharedPreferences.getBoolean(work1, false);
        workTwo = sharedPreferences.getBoolean(work2, false);
        workThree = sharedPreferences.getBoolean(work3, false);

        if( workOne == true){
            checkbox1.setImageResource(R.drawable.ic_baseline_check_box_24);
        }else{
            checkbox1.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
        }
        if( workTwo == true){
            checkbox2.setImageResource(R.drawable.ic_baseline_check_box_24);
        }
        else{
            checkbox2.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
        }
        if( workThree == true){
            checkbox3.setImageResource(R.drawable.ic_baseline_check_box_24);
        }
        else{
            checkbox3.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
        }

        if(workOne == false){
            btn_w2.setEnabled(false);
        }
        if(workOne == false || workTwo == false){
            btn_w3.setEnabled(false);
        }

        currentplan = sharedPreferences.getString(plan, "");
        tv_plan.setText("Plan: "+currentplan);

        tv_day.setText(String.valueOf(day)+"/7");

        tv_day.setText("Day: "+day+"/7");

        return root;
    }





    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.wButton1:
                ChangeFragment(new deliverworkFragment());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(done, false).commit();
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
    public void changeDay(){
        if (day<7) {
            day += 1;
            sharedPreferences.edit().putInt(sday, day).commit();
        }
        else {
            day = 1;
            sharedPreferences.edit().putInt(sday, day).commit();
        }
    }
}