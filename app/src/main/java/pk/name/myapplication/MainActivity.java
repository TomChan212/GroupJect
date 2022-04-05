package pk.name.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {
    homeFragment homeFragment = new homeFragment();

    private RelativeLayout mlayout = null;
    boolean typed = false;
    AppBarLayout barLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flFragment,new homeFragment());
        fragmentTransaction.commit();

        barLayout = findViewById(R.id.appbar);
        barLayout.setVisibility(View.GONE);

        mlayout = (RelativeLayout) findViewById(R.id.mlayout);
        mlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (typed == false) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new menuFragment());
                    fragmentTransaction.commit();
                    barLayout.setVisibility(View.VISIBLE);
                    typed = true;
                }
                return false;
            }
        });

    }

}