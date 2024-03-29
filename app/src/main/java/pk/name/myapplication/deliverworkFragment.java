package pk.name.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pk.name.myapplication.databinding.FragmentDeliverwork4Binding;
import pk.name.myapplication.databinding.FragmentDeliverworkBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class deliverworkFragment extends Fragment implements View.OnClickListener{
    private FragmentDeliverworkBinding binding;
    SharedPreferences sharedPreferences;
    public static final String sday = "sdayKey";
    public static final String mypref = "mypref";
    public static final String work1 = "work1Key";
    public static final String BMI = "BMIkey";
    public static final String todayK = "todayKey";
    public static final String plan = "plankey";

    Button btn_done, btn_back, btn_title,btn_play;
    MediaPlayer mediaPlayer;
    SurfaceView sv;
    SeekBar seekBar;
    TextView tv_day,tv_group;
    int[] raw_index={R.raw.crunch};
    int day;
    float bmi;
    String Plan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliverworkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inflate the layout for this fragment

        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        btn_play = root.findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);
        sv = root.findViewById(R.id.surfaceView);
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.crunch);
        seekBar = root.findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        AddSeekBarChangeListener();



        day = sharedPreferences.getInt(sday, 1);
        tv_day = root.findViewById(R.id.tv_day);
        tv_group=root.findViewById(R.id.note);

        btn_done = root.findViewById(R.id.btn_next);
        btn_back = root.findViewById(R.id.back);
        btn_title = root.findViewById(R.id.title);

        btn_done.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        tv_group=root.findViewById(R.id.note);
        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);
        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));
        Plan = sharedPreferences.getString(plan,"");
        showGroup();

        return root;
    }

    public void showGroup(){
        if (bmi<18.5) {
            tv_group.setText("Groups of 5 (2 groups)");
            if (Plan == "Mild Plan")
                tv_group.setText("Groups of 5 (1 groups)");
            if (Plan == "Moderate Plan")
                tv_group.setText("Groups of 5 (2 groups)");
            if (Plan == "Vigorous Plan")
                tv_group.setText("Groups of 5 (3 groups)");
        }else if (bmi<25){
            tv_group.setText("Groups of 10 (5 groups)");
            if (Plan == "Mild Plan")
                tv_group.setText("Groups of 10 (4 groups)");
            if (Plan == "Moderate Plan")
                tv_group.setText("Groups of 10 (5 groups)");
            if (Plan == "Vigorous Plan")
                tv_group.setText("Groups of 10 (6 groups)");
        }else {
            tv_group.setText("Groups of 20 (10 groups)");
            if (Plan == "Mild Plan")
                tv_group.setText("Groups of 20 (9 groups)");
            if (Plan == "Moderate Plan")
                tv_group.setText("Groups of 20 (10 groups)");
            if (Plan == "Vigorous Plan")
                tv_group.setText("Groups of 20 (11 groups)");
        }
    }

    public void play(){
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btn_play.setText("▶");
            }else{
                btn_play.setText("||");
                getActivity().getWindow().setFormat(PixelFormat.UNKNOWN);
                SurfaceHolder surfaceHolder = sv.getHolder();
                surfaceHolder.setFixedSize(176, 144);
                surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                mediaPlayer.setDisplay(surfaceHolder);
                mediaPlayer.start();
            }
        } catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void pause() {
        try{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btn_play.setText("▶");
            }
        }
        catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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

    public void init_SeekBar(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,1000);
            }
        },0);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_play:
                play();
                init_SeekBar();
                break;
            case R.id.back:
                ChangeFragment(new deliverworkFragment2());
                pause();
                break;
            case R.id.btn_next:
                pause();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(work1,true).commit();
                ChangeFragment(new deliverworkFragment3());

                Calendar calendar = Calendar.getInstance();
                Date today = calendar.getTime();

                DateFormat dateFormat = new SimpleDateFormat("dd");

                String todayAsString = dateFormat.format(today);
                editor.putString(todayK,todayAsString).commit();
                break;
        }
    }

    private void ChangeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

}