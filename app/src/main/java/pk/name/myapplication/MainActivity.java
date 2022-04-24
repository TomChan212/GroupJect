package pk.name.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {
    homeFragment homeFragment = new homeFragment();

    SharedPreferences shared_pref;
    public static final String mypref = "mypref";
    public static final String name = "mypref";
    public static final String age = "agekey";
    public static final String height = "heightkey";
    public static final String weight = "weightkey";
    public static final String BMI = "BMIkey";
    boolean secondtime = false;

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    private RelativeLayout mlayout = null;
    boolean typed = false;
    AppBarLayout barLayout;
    Toolbar toolbar;
    ImageView uwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flFragment, new homeFragment());
        fragmentTransaction.commit();

        barLayout = findViewById(R.id.appbar);
        barLayout.setVisibility(View.GONE);

        mlayout = (RelativeLayout) findViewById(R.id.mlayout);

        mlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if (typed == false) {
                    if (secondtime == true) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.flFragment, new menuFragment()).addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    try{   if( shared_pref.getString(name, "").isEmpty()||
                            shared_pref.getString(age, "").isEmpty()||
                            shared_pref.getString(height, "").isEmpty()||
                            shared_pref.getString(weight, "").isEmpty()){

                    }

                    }
                    catch (Exception exception) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.flFragment, new aboutFragment()).addToBackStack(null);
                        fragmentTransaction.commit();
                        secondtime = true;
                        Log.d("hepp","happyu");
                    }

                    barLayout.setVisibility(View.VISIBLE);
                    typed = true;
                }
                return false;


            }
        });

        uwork = findViewById(R.id.uwork_ic);
        uwork.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new menuFragment()).addToBackStack(null);
                    fragmentTransaction.commit();
                return false;
            }
        });



    }






    /*
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent e){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.Wanna_quit)).setCancelable(false).setPositiveButton(getResources().getString(R.string.quit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setNegativeButton(getResources().getString(R.string.quit_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle(getResources().getString(R.string.quit_title));
            alertDialog.show();
            return true;
        }
        else{
            return super.onKeyDown(keyCode,e);
        }
    }*/




}