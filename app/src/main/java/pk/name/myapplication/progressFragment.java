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
import android.widget.ProgressBar;
import android.widget.TextView;

import pk.name.myapplication.databinding.FragmentHomeBinding;
import pk.name.myapplication.databinding.FragmentProgressBinding;

public class progressFragment extends Fragment implements View.OnClickListener {
    private FragmentProgressBinding binding;

    Button progress_back;
    //For progress day
    private TextView tv_day;
    private ProgressBar mProgress;
    SharedPreferences sharedPreferences;
    public static final String sday = "sdayKey";
    public static final String mypref = "mypref";
    private int day;
    //end of progress day

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //for button back
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();        // Inflate the layout for this fragment

        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        tv_day = root.findViewById(R.id.progress_count);
        mProgress = root.findViewById(R.id.progress_bar);
        day = sharedPreferences.getInt(sday,1);
        tv_day.setText("Day: "+String.valueOf(day)+"/7");
        mProgress.setProgress(day);







        progress_back = root.findViewById(R.id.progress_back);
        progress_back.setOnClickListener(this);
        //end of button back
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.progress_back:
                ChangeFragment(new menuFragment());
                break;
        }
    }

    private void ChangeFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}