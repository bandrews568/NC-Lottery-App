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

    private int[] gameLayouts = {R.layout.pick3_listview, R.layout.pick4_listview,
                                 R.layout.cash5_listview};
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
                layoutFile = gameLayouts[PICK3.ordinal()];
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
                layoutFile = gameLayouts[PICK4.ordinal()];
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
                layoutFile = gameLayouts[CASH5.ordinal()];
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> pick5GameData = GameData.cash5(jsonString);
                    GameData.setupGameData(convertView, pick5GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: cash5");
                }
                break;
            }

        return convertView;
    }
}
