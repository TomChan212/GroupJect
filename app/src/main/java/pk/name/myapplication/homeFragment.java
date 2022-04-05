package pk.name.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pk.name.myapplication.databinding.FragmentHomeBinding;


public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ConstraintLayout CL = null;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        super.onCreate(savedInstanceState);
        CL = root.findViewById(R.id.CL);

        CL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getContext().getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
                return false;
            }
        });


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
