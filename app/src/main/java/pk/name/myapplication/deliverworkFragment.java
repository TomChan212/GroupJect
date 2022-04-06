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

    Button btn_done, btn_back;
    TextView tv_title, tv_day;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverworkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        tv_title=root.findViewById(R.id.title);
        tv_day=root.findViewById(R.id.day);

        btn_done=root.findViewById(R.id.done);
        btn_back=root.findViewById(R.id.back);

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
                //ChangeFragment(new deliverworkFragment);
                tv_day.setText("Day: button done pressed.");
                break;
        }
    }

    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}