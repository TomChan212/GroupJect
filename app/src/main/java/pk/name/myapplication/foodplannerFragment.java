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
    public static final String mypref = "mypref";
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

        sharedPreferences = getActivity().getSharedPreferences(mypref, Context.MODE_PRIVATE);

        day = sharedPreferences.getInt(sday,1);
        bmi = Float.valueOf(sharedPreferences.getString(BMI,"10"));

        tv_breakfast2.setOnTouchListener(this);
        tv_dinner2.setOnTouchListener(this);
        tv_lunch2.setOnTouchListener(this);

        btn_title.setClickable(false);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    tv_breakfast2.setText("3 onion stuffed parantha + 1 cup curd + 3 cashews + 4 almonds + 2 walnuts");
                    tv_lunch2.setText("1 cup moong dal/ chicken curry + 1 cup potato and caulifllower vegetable + 3 chapatti + 1/2 cup rice + salad");
                    tv_dinner2.setText("1 cup beans potato vegetable + 3 chapatti + salad");
                    break;
                case 2:
                    tv_breakfast2.setText("3 paneer stuffed besan cheela + green chutney + 1 cup curd + 3 cashews + 4 almonds + 2 walnuts");
                    tv_lunch2.setText("1 cup masoor dal + 1 cup calocasia + 3 chapatti + 1/2 cup rice + 1 cup low curd + salad");
                    tv_dinner2.setText("1 cup carrot peas vegetable +3 chapatti + salad");
                    break;
                case 3:
                    tv_breakfast2.setText("1.5 cup vegetable bread upma + 1 cup milk + 3 cashews + 4 almonds + 2 walnuts");
                    tv_lunch2.setText("1 cup rajma curry + 1 cup spinach potato + 3 chapatti + 1/2 cup rice + salad");
                    tv_dinner2.setText("1.5 cup parwal vegetable + 3 chapatti + salad");
                    break;
                case 4:
                    tv_breakfast2.setText("2 cucmber potato sandwich + 1 tsp green chutney + 1 orange juice + 3 cshews + 2 walnuts + 4 almonds");
                    tv_lunch2.setText("1 cup white chana/ fish curry + 3 chapatti + 1/2 cup rice + salad");
                    tv_dinner2.setText("1 cup cauliflower potato vegetable + 3 chapatti + salad");
                    break;
                case 5:
                    tv_breakfast2.setText("2 cup vegetable poha + 1 cup curd + 3 cashews + 4 almonds + 2 walnuts");
                    tv_lunch2.setText("1 cup chana dal + 1 cup bhindi vegetable + 3 chapatti + 1/2 cup rice + salad");
                    tv_dinner2.setText("1 cup peas mushroom vegetable + 3 chapatti + salad");
                    break;
                case 6:
                    tv_breakfast2.setText("3 vegetable suji cheela + 1 cup strawberry shake + 4 cashews + 4 almonds + 3 walnuts");
                    tv_lunch2.setText("1 cup mix dal + 1 cup soybean curry + 3 chapatti + 1/2 cup curd + salad");
                    tv_dinner2.setText("1 cup karela vegetable + 3 chaptti + salad");
                    break;
                case 7:
                    tv_breakfast2.setText("2 egg brown bread sandwich + green chutney + 1 cup milk + 3 cashews + 4 almonds + 2 walnuts");
                    tv_lunch2.setText("1 cup arhar dal + 1 cup potato curry + 3 chapatti + 1/2 cup rice + 1/2 cup low fat curd + salad");
                    tv_dinner2.setText("1.5 cup chicken curry + 3 chapatti + salad");
                    break;
            }
        }else if (bmi<25){
            tv_suggest.setText("Suggested calories per day: 2100-2300 kcal");
            switch (day) {
                case 1:
                    tv_breakfast2.setText("One grapefruit + Two poached eggs + Two slices whole-grain toast + One cup low-fat milk + One cup of black coffee");
                    tv_lunch2.setText("Chicken breast (6-ounce portion) + salad + Glass of water");
                    tv_dinner2.setText("One cup steamed broccoli + One cup of brown rice + Halibut + salad + One glass white wine + Sparkling water with lemon");
                    break;
                case 2:
                    tv_breakfast2.setText("One whole-wheat English muffin + One orange + Large glass (12 ounces) non-fat milk + One cup of black coffee");
                    tv_lunch2.setText("A turkey sandwich + one cup low-sodium vegetable soup + Glass of water");
                    tv_dinner2.setText("Five-ounce sirloin steak + one mashed potatoes + one cup cooked spinach + One cup green beans + One glass beer + Sparkling water with lemon");
                    break;
                case 3:
                    tv_breakfast2.setText("One medium bran muffin + One serving turkey breakfast sausage + One orange + One cup non-fat milk + One cup black coffee");
                    tv_lunch2.setText("Low sodium chicken noodle soup with six saltine crackers + One medium apple + Water");
                    tv_dinner2.setText("8-ounce serving of turkey breast meat + One cup baked beans + One cup carrots + One cup kale + One glass of wine");
                    break;
                case 4:
                    tv_breakfast2.setText("One cup whole wheat flakes + one cup non-fat milk + One banana + One slice whole-grain toast + One cup of black coffee");
                    tv_lunch2.setText("Tuna wrap with one wheat flour tortilla + One sliced avocado + One cup non-fat milk");
                    tv_dinner2.setText("One serving lasagna + Small garden salad with tomatoes and onions topped + One cup non-fat milk");
                    break;
                case 5:
                    tv_breakfast2.setText("One piece of French toast + One scrambled + One serving turkey bacon + One cup orange juice + One cup black coffee");
                    tv_lunch2.setText("Veggie burger on a whole grain bun + One cup northern beans + One cup non-fat milk");
                    tv_dinner2.setText("One trout filet + One cup green beans + One cup brown rice + One small garden salad + One glass of beer + Sparkling water with lemon");
                    break;
                case 6:
                    tv_breakfast2.setText("One cup corn flakes + one cup non-fat milk + One banana + One hard-boiled egg + One cup black coffee");
                    tv_lunch2.setText("One cup whole wheat pasta + Medium garden salad + Glass of water");
                    tv_dinner2.setText("Four and one-half ounce serving of pork loin + Small garden salad + One small baked sweet potato + One cup asparagus + One glass wine + Sparkling water with lemon");
                    break;
                case 7:
                    tv_breakfast2.setText("One cup oatmeal + one-half cup non-fat milk + Two slices turkey bacon + One cup non-fat milk + One cup black coffee");
                    tv_lunch2.setText("Six-ounce baked chicken breast + Large garden salad + One baked sweet potato + One whole-wheat dinner roll + Glass of water");
                    tv_dinner2.setText("3-ounce serving of baked salmon + One-half cup black beans + One cup Swiss chard + One cup brown rice + Sparkling water + One whole wheat dinner roll");
                    break;
            }
        }else {
            tv_suggest.setText("Suggested calories per day: 1600 kcal");
            switch (day) {
                case 1:
                    tv_breakfast2.setText("1 onion stuffed chapatti + 1/2 cup low fat curd");
                    tv_lunch2.setText("1 cup moong dal/ chicken curry + 1 chapatti + salad");
                    tv_dinner2.setText("1 cup beans + 1 chapatti + salad");
                    break;
                case 2:
                    tv_breakfast2.setText("2 besan cheela + 1/2 cup low fat curd");
                    tv_lunch2.setText("1 cup masoor dal + 1 chapatti + 1/2 up low fat curd + salad");
                    tv_dinner2.setText("1 cup carrot peas vegetable +1 chapatti + salad");
                    break;
                case 3:
                    tv_breakfast2.setText("1 cup vegetable brown bread upma + 1/2 cup low fat milk (no sugar)");
                    tv_lunch2.setText("1 cup rajma curry + 1 chapatti + salad");
                    tv_dinner2.setText("1 cup parwal vegetable + 1 chapatti + salad");
                    break;
                case 4:
                    tv_breakfast2.setText("1 cucmber hungcurd sandwich + 1/2 tsp green chutney + 1 orange");
                    tv_lunch2.setText("1 cup white chana/ fish curry + 1 chapatti + salad");
                    tv_dinner2.setText("1 cup cauliflower vegetable + 1 chapatti + salad");
                    break;
                case 5:
                    tv_breakfast2.setText("1 cup vegetable poha + 1 cup low fat curd");
                    tv_lunch2.setText("1 cup chana dal + 1 chapatti + salad");
                    tv_dinner2.setText("1 cup tinda vegetable + 1 chapatti + salad");
                    break;
                case 6:
                    tv_breakfast2.setText("1 cup low fat milk with oats + 3-4 strawberries");
                    tv_lunch2.setText("1 cup soybean curry + 1 chapatti + 1/2 cup low fat curd + salad");
                    tv_dinner2.setText("1 cup ghia vegetable + 1 chaptti + salad");
                    break;
                case 7:
                    tv_breakfast2.setText("3 egg whites + 1 toasted brown bread + 1/2 cup low fat milk (no sugar)");
                    tv_lunch2.setText("1 cup arhar dal + 1 chapatti + 1/2 cup low fat curd + salad");
                    tv_dinner2.setText("1 cup pumpkin + 1 chapatti + salad");
                    break;
            }
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
        builder.setCancelable(false)
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