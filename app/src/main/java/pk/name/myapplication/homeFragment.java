package pk.name.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pk.name.myapplication.databinding.FragmentHomeBinding;


public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView boyrun;
    private TextView tv_continue, loading;
    private Handler mHandler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        boyrun = root.findViewById(R.id.boyrun);
        tv_continue = root.findViewById(R.id.tv_continue);
        loading = root.findViewById(R.id.tv_loading);

        boyrun.setVisibility(View.VISIBLE);
        tv_continue.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);

        mHandler.postDelayed(mLoading, 3000);

        return root;
    }

    private Runnable mLoading = new Runnable() {
        @Override
        public void run() {
            boyrun.setVisibility(View.INVISIBLE);
            tv_continue.setVisibility(View.VISIBLE);
            loading.setVisibility(View.INVISIBLE);

        }
    };

}
