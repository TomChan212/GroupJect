package pk.name.myapplication;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

import pk.name.myapplication.databinding.FragmentDeliverworkBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class deliverworkFragment extends Fragment implements View.OnClickListener{
    private FragmentDeliverworkBinding binding;

    Button btn_done, btn_back, btn_title,btn_play;
    MediaPlayer mediaPlayer;
    SurfaceView sv;
    SeekBar seekBar;
    TextView tv_day;
    int[] raw_index={R.raw.video1};
    int day=1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverworkBinding.inflate(inflater, container, false);
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

        btn_done=root.findViewById(R.id.done);
        btn_back=root.findViewById(R.id.back);
        btn_title=root.findViewById(R.id.title);

        btn_done.setOnClickListener(this);
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
//test
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_play:
                play();
                break;
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