package pk.name.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import pk.name.myapplication.databinding.FragmentAboutBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class aboutFragment extends Fragment  implements  View.OnClickListener {
    private FragmentAboutBinding binding;

    Button btn_submit, about_back;
    EditText name_input, age_input, height_input, weight_input;
    TextView cal_bmi, tv_plan;

    SharedPreferences sharedPreferences;
    public static final String mypref = "mypref";
    public static final String name = "mypref";
    public static final String age = "agekey";
    public static final String height = "heightkey";
    public static final String weight = "weightkey";
    public static final String BMI = "BMIkey";
    public static final String plan = "plankey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        btn_submit = root.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        about_back = root.findViewById(R.id.about_back);
        about_back.setOnClickListener(this);

        name_input = root.findViewById(R.id.name_input);
        weight_input = root.findViewById(R.id.weight_input);
        height_input = root.findViewById(R.id.height_input);
        age_input = root.findViewById(R.id.age_input);
        tv_plan = root.findViewById(R.id.showPlan);

        cal_bmi = root.findViewById(R.id.cal_bmi);
        sharedPreferences = getContext().getSharedPreferences(mypref, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(BMI)) {
            cal_bmi.setText(sharedPreferences.getString(BMI, ""));
        }

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                if(     name_input.getText().toString().isEmpty() ||
                        age_input.getText().toString().isEmpty() ||
                        weight_input.getText().toString().isEmpty() ||
                        height_input.getText().toString().isEmpty()) {
                        Toast.makeText(getContext().getApplicationContext(), "Make sure all your data is completed !!", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(name, name_input.getText().toString());
                        editor.putString(age, age_input.getText().toString());
                        editor.putString(weight, weight_input.getText().toString());
                        editor.putString(height, height_input.getText().toString());
                        calBMI(Double.parseDouble(height_input.getText().toString()), Double.parseDouble(weight_input.getText().toString()));
                        showPlan();
                        editor.commit();
                        Toast.makeText(getContext().getApplicationContext(), "Your plan has arrived! Please go to Deliverwork", Toast.LENGTH_LONG).show();
                    }
                break;

            case R.id.about_back:
                ChangeFragment(new menuFragment());
                break;

        }
    }

    public void calBMI(Double hei, Double wei) {
        DecimalFormat df = new DecimalFormat("#.0");
        Double bmi_value = wei / ((hei / 100) * (hei / 100));
        String str_bmi = df.format(bmi_value).toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BMI, str_bmi);
        cal_bmi.setText(str_bmi);
        editor.commit();
    }

    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }

    public void showPlan(){
        if (Double.parseDouble(cal_bmi.getText().toString()) < 18.5) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(plan,"Mild Plan" ).commit();
        }else if (Double.parseDouble(cal_bmi.getText().toString()) <25){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(plan,"Moderate Plan" ).commit();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(plan,"Vigorous Plan" ).commit();
        }
    }
}