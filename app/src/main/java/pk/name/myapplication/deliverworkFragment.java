package pk.name.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pk.name.myapplication.databinding.FragmentDeliverworkBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class deliverworkFragment extends Fragment implements View.OnClickListener{
    private FragmentDeliverworkBinding binding;

    Button btn_done, btn_back, btn_title;
    TextView tv_day;
    int day=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverworkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment


        tv_day=root.findViewById(R.id.test);

        btn_done=root.findViewById(R.id.done);
        btn_back=root.findViewById(R.id.back);
        btn_title=root.findViewById(R.id.title);

        btn_done.setOnClickListener(this);
        btn_back.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                ChangeFragment(new menuFragment());
                break;
            case R.id.done:
                tv_day.setText("Day: "+dayCounter()+"/7");
                //ChangeFragment(new progressFragment());
                //code of passing the data of variable "day" to be entered
                break;
        }
    }

    private int dayCounter(){
        if (day<7){
            day+=1;
        } else {
            day=1;
        }
        return day;
    }


    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}