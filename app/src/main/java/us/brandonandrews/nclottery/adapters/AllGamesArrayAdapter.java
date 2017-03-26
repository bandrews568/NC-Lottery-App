package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import us.brandonandrews.nclottery.models.Games.*;
import us.brandonandrews.nclottery.models.GameData;
import us.brandonandrews.nclottery.R;

import static us.brandonandrews.nclottery.models.Games.Game.CASH5;
import static us.brandonandrews.nclottery.models.Games.Game.PICK3;
import static us.brandonandrews.nclottery.models.Games.Game.PICK4;


public class AllGamesArrayAdapter extends ArrayAdapter {

    private static final String TAG = "GAMES ARRAY ADAPTER";

    private Context context;
    private ArrayList<String> games;
    private JSONObject jsonString;

    public AllGamesArrayAdapter(Context context, ArrayList<String> games, JSONObject jsonString) {
        super(context, 0, games);
        this.context = context;
        this.games = games;
        this.jsonString = jsonString;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String game = games.get(position);
        int layoutFile;

        switch (game) {
            case "pick3":
                layoutFile = R.layout.pick3_listview;
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> pick3GameData = GameData.pick3(jsonString);
                    GameData.setupGameData(convertView, pick3GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: pick3");
                }
                break;
            case "pick4":
                layoutFile = R.layout.pick4_listview;
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> pick4GameData = GameData.pick4(jsonString);
                    GameData.setupGameData(convertView, pick4GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: pick4");
                }
                break;
            case "cash5":
                layoutFile = R.layout.cash5_listview;
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> pick5GameData = GameData.cash5(jsonString);
                    GameData.setupGameData(convertView, pick5GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: cash5");
                }
                break;
            case "luckyForLife":
                layoutFile = R.layout.lucky_for_life_listview;
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> luckyForLifeGameData = GameData.luckyForLife(jsonString);
                    GameData.setupGameData(convertView, luckyForLifeGameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: lucky_for_life");
                }
                break;
            case "powerball":
                layoutFile = R.layout.powerball_listview;
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> powerballGameData = GameData.powerball(jsonString);
                    GameData.setupGameData(convertView, powerballGameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: powerball");
                }
        }
        return convertView;
    }
}
