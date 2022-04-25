package pk.name.myapplication;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import pk.name.myapplication.databinding.FragmentDeliverwork3Binding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class deliverworkFragment3 extends Fragment implements View.OnClickListener{
    private FragmentDeliverwork3Binding binding;

    Button btn_next, btn_back, btn_title,btn_play;
    MediaPlayer mediaPlayer;
    SurfaceView sv;
    SeekBar seekBar;
    TextView tv_day;
    int[] raw_index={R.raw.video1};
    int day=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverwork3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment


        btn_play=root.findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        sv = root.findViewById(R.id.surfaceView);
        mediaPlayer=MediaPlayer.create(getActivity(),R.raw.video1);
        seekBar=root.findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        AddSeekBarChangeListener();


        tv_day=root.findViewById(R.id.test);

        btn_next=root.findViewById(R.id.btn_next);
        btn_back=root.findViewById(R.id.back);
        btn_title=root.findViewById(R.id.title);

        btn_next.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        return root;
    }

    public void play(){
        try{
            getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
            SurfaceHolder surfaceHolder = sv.getHolder();
            surfaceHolder.setFixedSize(176, 144);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.start();
        } catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void AddSeekBarChangeListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(mediaPlayer.getDuration());
                if (fromUser) mediaPlayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_play:
                play();
                break;
            case R.id.back:
                ChangeFragment(new deliverworkFragment2());
                break;
            case R.id.btn_next:

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
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}