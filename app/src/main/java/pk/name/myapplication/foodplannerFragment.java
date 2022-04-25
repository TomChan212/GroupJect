package pk.name.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pk.name.myapplication.databinding.FragmentFoodplannerBinding;
import pk.name.myapplication.databinding.FragmentHomeBinding;


public class foodplannerFragment extends Fragment implements View.OnTouchListener {
    private FragmentFoodplannerBinding binding;

    private Button btn_title,btn_done,btn_back;
    private TextView tv_day,tv_breakfast,tv_breakfast2,tv_lunch,tv_lunch2,tv_dinner,tv_dinner2,tv_suggest;
    private int day;
    private float bmi;

    SharedPreferences sharedPreferences;
    public static final String mypregerence = "mypref";
    public static final String sday = "sdayKey";
    public static final String BMI = "BMIkey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodplannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_done = root.findViewById(R.id.btn_fp_done);
        btn_back = root.findViewById(R.id.btn_fp_back);
        btn_title = root.findViewById(R.id.btn_fp_title);
        tv_day = root.findViewById(R.id.tv_fp_day);
        tv_breakfast = root.findViewById(R.id.tv_fp_breakfast);
        tv_breakfast2 = root.findViewById(R.id.tv_fp_breakfast2);
        tv_lunch = root.findViewById(R.id.btn_fp_lunch);
        tv_lunch2 = root.findViewById(R.id.btn_fp_lunch2);
        tv_dinner = root.findViewById(R.id.tv_fp_dinner);
        tv_dinner2 = root.findViewById(R.id.tv_fp_dinner2);
        tv_suggest = root.findViewById(R.id.tv_fp_suggest);

        sharedPreferences = getActivity().getSharedPreferences(mypregerence, Context.MODE_PRIVATE);

        day = sharedPreferences.getInt(sday,1);
        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));

        tv_breakfast2.setOnTouchListener(this);
        tv_dinner2.setOnTouchListener(this);
        tv_lunch2.setOnTouchListener(this);

        btn_title.setClickable(false);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDay();

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new menuFragment());
                fragmentTransaction.commit();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flFragment, new menuFragment());
                fragmentTransaction.commit();
            }
        });

        tv_day.setText(String.valueOf(day)+"/7");

        changeDietChart();
        // Inflate the layout for this fragment
        return root;
    }

    public void changeDietChart(){
        if (bmi<18.5){
            tv_suggest.setText("Suggested calories per day: 2000-2200 kcal");
            switch (day){
                case 1:
                    tv_breakfast2.setText("3 onion stuffed parantha\n1 cup curd\n3 cashews\n4 almonds\n2 walnuts");
                    tv_lunch2.setText("1 cup moong dal/ chicken curry\n1 cup potato and caulifllower vegetable\n3 chapatti\n1/2 cup rice\nsalad");
                    tv_dinner2.setText("1 cup beans potato vegetable\n3 chapatti\nsalad");
                    break;
                case 2:
                    tv_breakfast2.setText("3 paneer stuffed besan cheela\ngreen chutney\n1 cup curd\n3 cashews\n4 almonds\n2 walnuts");
                    tv_lunch2.setText("1 cup masoor dal\n1 cup calocasia\n3 chapatti\n1/2 cup rice\n1 cup low curd\nsalad");
                    tv_dinner2.setText("1 cup carrot peas vegetable\n3 chapatti\nsalad");
                    break;
                case 3:
                    tv_breakfast2.setText("1.5 cup vegetable bread upma\n1 cup milk\n3 cashews\n4 almonds\n2 walnuts");
                    tv_lunch2.setText("1 cup rajma curry\n1 cup spinach potato\n3 chapatti\n1/2 cup rice\nsalad");
                    tv_dinner2.setText("1.5 cup parwal vegetable\n3 chapatti\nsalad");
                    break;
                case 4:
                    tv_breakfast2.setText("2 cucmber potato sandwich\n1 tsp green chutney\n1 orange juice\n3 cshews\n2 walnuts\n4 almonds");
                    tv_lunch2.setText("1 cup white chana/ fish curry\n3 chapatti\n1/2 cup rice\nsalad");
                    tv_dinner2.setText("1 cup cauliflower potato vegetable\n3 chapatti\nsalad");
                    break;
                case 5:
                    tv_breakfast2.setText("2 cup vegetable poha\n1 cup curd\n3 cashews\n4 almonds\n2 walnuts");
                    tv_lunch2.setText("1 cup chana dal\n1 cup bhindi vegetable\n3 chapatti\n1/2 cup rice\nsalad");
                    tv_dinner2.setText("1 cup peas mushroom vegetable\n3 chapatti\nsalad");
                    break;
                case 6:
                    tv_breakfast2.setText("3 vegetable suji cheela\n1 cup strawberry shake\n4 cashews\n4 almonds\n3 walnuts");
                    tv_lunch2.setText("1 cup mix dal\n1 cup soybean curry\n3 chapatti\n1/2 cup curd\nsalad");
                    tv_dinner2.setText("1 cup karela vegetable\n3 chaptti\nsalad");
                    break;
                case 7:
                    tv_breakfast2.setText("2 egg brown bread sandwich\ngreen chutney\n1 cup milk\n3 cashews\n4 almonds\n2 walnuts");
                    tv_lunch2.setText("1 cup arhar dal\n1 cup potato curry\n3 chapatti\n1/2 cup rice\n1/2 cup low fat curd\nsalad");
                    tv_dinner2.setText("1.5 cup chicken curry\n3 chapatti\nsalad");
                    break;
            }
        }else if (bmi<25){
            tv_suggest.setText("Suggested calories per day: 2100-2300 kcal");
            switch (day) {
                case 1:
                    tv_breakfast2.setText("One grapefruit\nTwo poached eggs\nTwo slices whole-grain toast\nOne cup low-fat milk\nOne cup of black coffee");
                    tv_lunch2.setText("Chicken breast (6-ounce portion)\nsalad\nGlass of water");
                    tv_dinner2.setText("One cup steamed broccoli\nOne cup of brown rice\nHalibut\nsalad\nOne glass white wine\nSparkling water with lemon");
                    break;
                case 2:
                    tv_breakfast2.setText("One whole-wheat English muffin\nOne orange\nLarge glass (12 ounces) non-fat milk\nOne cup of black coffee");
                    tv_lunch2.setText("A turkey sandwich\none cup low-sodium vegetable soup\nGlass of water");
                    tv_dinner2.setText("Five-ounce sirloin steak\none mashed potatoes\none cup cooked spinach\nOne cup green beans\nOne glass beer\nSparkling water with lemon");
                    break;
                case 3:
                    tv_breakfast2.setText("One medium bran muffin\nOne serving turkey breakfast sausage\nOne orange\nOne cup non-fat milk\nOne cup black coffee");
                    tv_lunch2.setText("Low sodium chicken noodle soup with six saltine crackers\nOne medium apple\nWater");
                    tv_dinner2.setText("8-ounce serving of turkey breast meat\nOne cup baked beans\nOne cup carrots\nOne cup kale\nOne glass of wine");
                    break;
                case 4:
                    tv_breakfast2.setText("One cup whole wheat flakes\none cup non-fat milk\nOne banana\nOne slice whole-grain toast\nOne cup of black coffee");
                    tv_lunch2.setText("Tuna wrap with one wheat flour tortilla\nOne sliced avocado\nOne cup non-fat milk");
                    tv_dinner2.setText("One serving lasagna\nSmall garden salad with tomatoes and onions topped\nOne cup non-fat milk");
                    break;
                case 5:
                    tv_breakfast2.setText("One piece of French toast\nOne scrambled\nOne serving turkey bacon\nOne cup orange juice\nOne cup black coffee");
                    tv_lunch2.setText("Veggie burger on a whole grain bun\nOne cup northern beans\nOne cup non-fat milk");
                    tv_dinner2.setText("One trout filet\nOne cup green beans\nOne cup brown rice\nOne small garden salad\nOne glass of beer\nSparkling water with lemon");
                    break;
                case 6:
                    tv_breakfast2.setText("One cup corn flakes\none cup non-fat milk\nOne banana\nOne hard-boiled egg\nOne cup black coffee");
                    tv_lunch2.setText("One cup whole wheat pasta\nMedium garden salad\nGlass of water");
                    tv_dinner2.setText("Four and one-half ounce serving of pork loin\nSmall garden salad\nOne small baked sweet potato\nOne cup asparagus\nOne glass wine\nSparkling water with lemon");
                    break;
                case 7:
                    tv_breakfast2.setText("One cup oatmeal\none-half cup non-fat milk \nTwo slices turkey bacon \nOne cup non-fat milk \nOne cup black coffee");
                    tv_lunch2.setText("Six-ounce baked chicken breast \nLarge garden salad \nOne baked sweet potato \nOne whole-wheat dinner roll \nGlass of water");
                    tv_dinner2.setText("3-ounce serving of baked salmon \nOne-half cup black beans \nOne cup Swiss chard \nOne cup brown rice \nSparkling water \nOne whole wheat dinner roll");
                    break;
            }
        }else {
            tv_suggest.setText("Suggested calories per day: 1600 kcal");
            switch (day) {
                case 1:
                    tv_breakfast2.setText("1 onion stuffed chapatti\n1/2 cup low fat curd");
                    tv_lunch2.setText("1 cup moong dal/ chicken curry\n1 chapatti\nsalad");
                    tv_dinner2.setText("1 cup beans\n1 chapatti\nsalad");
                    break;
                case 2:
                    tv_breakfast2.setText("2 besan cheela\n1/2 cup low fat curd");
                    tv_lunch2.setText("1 cup masoor dal\n1 chapatti\n1/2 up low fat curd\nsalad");
                    tv_dinner2.setText("1 cup carrot peas vegetable +1 chapatti\nsalad");
                    break;
                case 3:
                    tv_breakfast2.setText("1 cup vegetable brown bread upma\n1/2 cup low fat milk (no sugar)");
                    tv_lunch2.setText("1 cup rajma curry\n1 chapatti\nsalad");
                    tv_dinner2.setText("1 cup parwal vegetable\n1 chapatti\nsalad");
                    break;
                case 4:
                    tv_breakfast2.setText("1 cucmber hungcurd sandwich\n1/2 tsp green chutney\n1 orange");
                    tv_lunch2.setText("1 cup white chana/ fish curry\n1 chapatti\nsalad");
                    tv_dinner2.setText("1 cup cauliflower vegetable\n1 chapatti\nsalad");
                    break;
                case 5:
                    tv_breakfast2.setText("1 cup vegetable poha\n1 cup low fat curd");
                    tv_lunch2.setText("1 cup chana dal\n1 chapatti\nsalad");
                    tv_dinner2.setText("1 cup tinda vegetable\n1 chapatti\nsalad");
                    break;
                case 6:
                    tv_breakfast2.setText("1 cup low fat milk with oats\n3-4 strawberries");
                    tv_lunch2.setText("1 cup soybean curry\n1 chapatti\n1/2 cup low fat curd\nsalad");
                    tv_dinner2.setText("1 cup ghia vegetable\n1 chaptti\nsalad");
                    break;
                case 7:
                    tv_breakfast2.setText("3 egg whites\n1 toasted brown bread\n1/2 cup low fat milk (no sugar)");
                    tv_lunch2.setText("1 cup arhar dal\n1 chapatti\n1/2 cup low fat curd\nsalad");
                    tv_dinner2.setText("1 cup pumpkin\n1 chapatti\nsalad");
                    break;
            }
        }
    }

    public void changeDay(){
        if (day<7) {
            day += 1;
            sharedPreferences.edit().putInt(sday, day).commit();
        }
        else {
            day = 1;
            sharedPreferences.edit().putInt(sday, day).commit();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView imageView = new ImageView(getContext());

        if (bmi<18.5) {
            imageView.setImageResource(R.drawable.underweight_chart);
        } else if (bmi < 25) {
            imageView.setImageResource(R.drawable.healthy_chart);
        }else {
            imageView.setImageResource(R.drawable.obesity_chart);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true)
        .setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        })
        .setView(imageView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return false;
    }
}