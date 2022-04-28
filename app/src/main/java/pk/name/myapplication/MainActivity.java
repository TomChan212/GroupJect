package pk.name.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {
    homeFragment homeFragment = new homeFragment();

    SharedPreferences sharedPreferences;
    public static final String mypref = "mypref";
    public static final String name = "mypref";
    public static final String age = "agekey";
    public static final String height = "heightkey";
    public static final String weight = "weightkey";
    public static final String BMI = "BMIkey";

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    private RelativeLayout mlayout = null;
    private Handler mHandler = new Handler();
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

        sharedPreferences = getApplicationContext().getSharedPreferences(mypref,
                Context.MODE_PRIVATE);

        mlayout = (RelativeLayout) findViewById(R.id.mlayout);

        mHandler.postDelayed(mLoading, 3000);


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

        Button help;
        help = findViewById(R.id.btn_help);
        help.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new helpFragment()).addToBackStack(null);
                fragmentTransaction.commit();
                return false;
            }
        });


    }

    private Runnable mLoading = new Runnable() {
        @Override
        public void run() {

            mlayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if (typed == false) {
                        if (sharedPreferences.getString(name, "").isEmpty() ||
                                sharedPreferences.getString(weight, "").isEmpty() ||
                                sharedPreferences.getString(height, "").isEmpty() ||
                                sharedPreferences.getString(age, "").isEmpty() ||
                                sharedPreferences.getString(name, "").isEmpty() ||
                                sharedPreferences.getString(BMI, "").isEmpty()) {
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.flFragment, new signUpFragment());
                            fragmentTransaction.commit();
                        } else {
                            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.flFragment, new menuFragment());
                            fragmentTransaction.commit();
                        }
                        barLayout.setVisibility(View.VISIBLE);
                        typed = true;
                    }
                    return false;
                }
            });
        }
    };

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




