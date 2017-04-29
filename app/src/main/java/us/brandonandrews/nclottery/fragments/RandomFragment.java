package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.HashSet;
import java.util.Set;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.utils.GameData;


public class RandomFragment extends Fragment {

    private Spinner spinner;
    private FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.random_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnGenerate = (Button) view.findViewById(R.id.btnGenerate);
        spinner = (Spinner) view.findViewById(R.id.randomSpinner);
        frameLayout = (FrameLayout) view.findViewById(R.id.randomFrameLayout);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupFrameLayout(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Blank on purpose
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFrameLayout(spinner.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Random");
    }

    private void setupFrameLayout(String game) {
        int layout;
        String[] numbers;
        frameLayout.removeAllViews();

        switch(game) {
            case "Pick 3":
                layout = R.layout.pick3_listview;
                numbers = generateRandomNumbers(9, 3);
                View inflatedPick3 = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData.setupPick3View(inflatedPick3, numbers));
                break;
            case "Pick 4":
                layout = R.layout.pick4_listview;
                numbers = generateRandomNumbers(9, 4);
                View inflatedPick4 = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData.setupPick4View(inflatedPick4, numbers));
                break;
            case "Cash 5":
                layout = R.layout.cash5_listview;
                numbers = generateUniqueRandomNumbers(41, 5);
                View inflatedCash5 = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData.setupCash5View(inflatedCash5, numbers));
                break;
            case "Lucky For Life":
                layout = R.layout.lucky_for_life_listview;
                numbers = generateUniqueRandomNumbers(48, 5);
                String luckyBall = generateSingleBall(18);
                View inflatedLuckyForLife = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData
                        .setupLuckyForLifeView(inflatedLuckyForLife, numbers, luckyBall));
                break;
            case "Mega Millions":
                layout = R.layout.mega_millions_listview;
                numbers = generateUniqueRandomNumbers(75, 5);
                String megaBall = generateSingleBall(15);
                View inflatedMegaMillions = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData
                        .setupMegaMillionsView(inflatedMegaMillions, numbers, megaBall));
                break;
            case "Powerball":
                layout = R.layout.powerball_listview;
                numbers = generateUniqueRandomNumbers(69, 5);
                String powerball = generateSingleBall(26);
                View inflatedPowerball = LayoutInflater.from(getContext())
                        .inflate(layout, frameLayout, false);
                frameLayout.addView(GameData
                        .setupPowerballView(inflatedPowerball, numbers, powerball));
                break;
        }
    }

    private String[] generateRandomNumbers(int max, int amount) {
        String[] numbers = new String[amount];

        for (int i = 0; i < amount; i++) {
            numbers[i] = Integer.toString((int) (Math.random() * max + 1));
        }
        return numbers;
    }

    private String[] generateUniqueRandomNumbers(int max, int amount) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < amount; i++) {
            String number = Integer.toString((int) (Math.random() * max + 1));
            if (!set.contains(number)) {
                set.add(number);
            } else {
                while (true) {
                    number = Integer.toString((int) (Math.random() * max + 1));
                    if (!set.contains(number)) {
                        set.add(number);
                        break;
                    }
                }
            }
        }
        return set.toArray(new String[set.size()]);
    }

    private String generateSingleBall(int max) {
        return Integer.toString((int) (Math.random() * max) + 1);
    }
}
