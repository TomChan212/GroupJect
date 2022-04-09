package pk.name.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import pk.name.myapplication.databinding.FragmentAboutBinding;
import pk.name.myapplication.databinding.FragmentDeliverwork2Binding;
import pk.name.myapplication.databinding.FragmentDeliverworkBinding;

public class deliverworkFragment2 extends Fragment implements View.OnClickListener {
    private FragmentDeliverwork2Binding binding;

    Button btn_w1, btn_w2,btn_w3,btn_menu;
    TextView tv_day;

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


        return root;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.wButton1:
                ChangeFragment(new deliverworkFragment());
                //code for pass data to be entered
                break;
            case R.id.wButton2:
                ChangeFragment(new deliverworkFragment());
                //code for pass data to be entered
                break;
            case R.id.wButton3:
                ChangeFragment(new deliverworkFragment());
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
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}