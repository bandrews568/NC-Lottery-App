package us.brandonandrews.nclottery.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import us.brandonandrews.nclottery.R;


public class SettingsFragment extends android.support.v4.app.Fragment {

    private SharedPreferences sharedPreferences;
    private List<CheckBox> checkBoxes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final CheckBox cbPick3 = (CheckBox) view.findViewById(R.id.cbPick3);
        final CheckBox cbPick4 = (CheckBox) view.findViewById(R.id.cbPick4);
        final CheckBox cbCash5 = (CheckBox) view.findViewById(R.id.cbCash5);
        final CheckBox cbLuckyForLife = (CheckBox) view.findViewById(R.id.cbLuckyForLife);
        final CheckBox cbPowerball = (CheckBox) view.findViewById(R.id.cbPowerball);
        final CheckBox cbMegaMillions = (CheckBox) view.findViewById(R.id.cbMegaMillions);

        checkBoxes.add(cbPick3);
        checkBoxes.add(cbPick4);
        checkBoxes.add(cbCash5);
        checkBoxes.add(cbLuckyForLife);
        checkBoxes.add(cbPowerball);
        checkBoxes.add(cbMegaMillions);

        checkIfGamesAreChecked();

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.cbPick3:
                        if (cbPick3.isChecked()) {
                            editor.putBoolean("pick3", true);
                        } else {
                            editor.putBoolean("pick3", false);
                        }
                    case R.id.cbPick4:
                        if (cbPick4.isChecked()) {
                            editor.putBoolean("pick4", true);
                        } else {
                            editor.putBoolean("pick4", false);
                        }
                    case R.id.cbCash5:
                        if (cbCash5.isChecked()) {
                            editor.putBoolean("cash5", true);
                        } else {
                            editor.putBoolean("cash5", false);
                        }
                    case R.id.cbLuckyForLife:
                        if (isChecked) {
                            editor.putBoolean("luckyForLife", true);
                        } else {
                            editor.putBoolean("luckyForLife", false);

                        }
                    case R.id.cbMegaMillions:
                        if (cbMegaMillions.isChecked()) {
                            editor.putBoolean("megaMillions", true);
                        } else {
                            editor.putBoolean("megaMillions", false);
                        }
                    case R.id.cbPowerball:
                        if (cbPowerball.isChecked()) {
                            editor.putBoolean("powerball", true);
                        } else {
                            editor.putBoolean("powerball", false);
                        }
                    default:
                        editor.commit();
                }
            }
        };

        cbPick3.setOnCheckedChangeListener(checkBoxListener);
        cbPick4.setOnCheckedChangeListener(checkBoxListener);
        cbCash5.setOnCheckedChangeListener(checkBoxListener);
        cbLuckyForLife.setOnCheckedChangeListener(checkBoxListener);
        cbMegaMillions.setOnCheckedChangeListener(checkBoxListener);
        cbPowerball.setOnCheckedChangeListener(checkBoxListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!checkBoxes.isEmpty()) {
            checkIfGamesAreChecked();
        }
    }

    private void checkIfGamesAreChecked() {
        String[] games = {"pick3", "pick4", "cash5", "luckyForLife", "megaMillions", "powerball"};
        for (int i = 0; i < checkBoxes.size(); i++) {
            // See if the user has disabled that game; default to true if they haven't manually
            // disabled that game.
            boolean gameIsChecked = sharedPreferences.getBoolean(games[i], true);
            CheckBox checkBox = checkBoxes.get(i);

            if (gameIsChecked) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
    }
}
