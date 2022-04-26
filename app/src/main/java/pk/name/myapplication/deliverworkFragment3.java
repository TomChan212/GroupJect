package pk.name.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
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
    TextView tv_day,tv_group;
    int[] raw_index={R.raw.pushup};
    int day;
    float bmi;

    SharedPreferences sharedPreferences;
    public static final String mypref = "mypref";
    public static final String BMI = "BMIkey";
    public static final String sday = "sdayKey";
    public static final String work2 = "work2Key";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverwork3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment


        btn_play=root.findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        sv = root.findViewById(R.id.surfaceView);
        mediaPlayer=MediaPlayer.create(getActivity(),R.raw.pushup);
        seekBar=root.findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        AddSeekBarChangeListener();


        tv_day=root.findViewById(R.id.test);

        btn_next=root.findViewById(R.id.btn_next);
        btn_back=root.findViewById(R.id.back);
        btn_title=root.findViewById(R.id.title);

        btn_next.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        tv_group=root.findViewById(R.id.note);
        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));
        showGroup();

        return root;
    }

    public void showGroup(){
        if (bmi<18.5) {
            tv_group.setText("Groups of 5 (2 groups)");
        }else if (bmi<25){
            tv_group.setText("Groups of 10 (5 groups)");
        }else {
            tv_group.setText("Groups of 20 (10 groups)");
        }
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
                ChangeFragment(new deliverworkFragment4());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(work2,true).commit();
                break;
        }
    }




    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }
}