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

import us.brandonandrews.nclottery.R;


public class SettingsFragment extends android.support.v4.app.Fragment {

    private SharedPreferences sharedPreferences;

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

        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.cbPick3:
                        if (cbPick3.isChecked()) {
                            editor.putString("pick3", "checked");
                        } else {
                            editor.putString("pick3", "unchecked");
                        }
                        break;
                    case R.id.cbPick4:
                        if (cbPick4.isChecked()) {
                            editor.putString("pick4", "checked");
                        } else {
                            editor.putString("pick4", "unchecked");
                        }
                        break;
                    case R.id.cbCash5:
                        if (cbCash5.isChecked()) {
                            editor.putString("cash5", "checked");
                        } else {
                            editor.putString("cash5", "unchecked");
                        }
                        break;
                    case R.id.cbLuckyForLife:
                        if (isChecked) {
                            editor.putString("luckyForLife", "checked");
                        } else {
                            editor.putString("luckyForLife", "unchecked");
                        }
                        break;
                    case R.id.cbMegaMillions:
                        if (cbMegaMillions.isChecked()) {
                            editor.putString("megaMillions", "checked");
                        } else {
                            editor.putString("megaMillions", "unchecked");
                        }
                        break;
                    case R.id.cbPowerball:
                        if (cbPowerball.isChecked()) {
                            editor.putString("powerball", "checked");
                        } else {
                            editor.putString("powerball", "unchecked");
                        }
                        break;

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
}
