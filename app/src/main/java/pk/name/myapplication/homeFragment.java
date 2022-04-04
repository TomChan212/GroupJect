package pk.name.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class homeFragment extends Fragment implements View.OnTouchListener{

    menuFragment menuFragment = new menuFragment();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.flFragment:
                getParentFragmentManager().beginTransaction().replace(R.id.flFragment,menuFragment).commit();
                break;
        }
        return false;
    }
}
