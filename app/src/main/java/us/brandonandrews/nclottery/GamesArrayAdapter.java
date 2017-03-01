package us.brandonandrews.nclottery;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static us.brandonandrews.nclottery.Game.PICK3;
import static us.brandonandrews.nclottery.Game.PICK4;

enum Game {
    PICK3,
    PICK4,
    CASH5,
    POWERBALL,
    MEGA_MILLIONS,
    LUCKY_FOR_LIFE
}



public class GamesArrayAdapter extends ArrayAdapter {

    private static final String TAG = "GAMES ARRAY ADAPTER";

    private int[] gameLayouts = {R.layout.pick3_listview, R.layout.pick4_listview};
    private Context context;
    private ArrayList<Game> games;
    private JSONObject jsonString;

    public GamesArrayAdapter(Context context, ArrayList<Game> games, JSONObject jsonString) {
        super(context, 0, games);
        this.context = context;
        this.games = games;
        this.jsonString = jsonString;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Game game = games.get(position);
        int layoutFile;

        // TODO Fix this later
        switch (game) {
            case PICK3:
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
            case PICK4:
                layoutFile = gameLayouts[PICK4.ordinal()];
                convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
                try {
                    HashMap<String, String> pick4GameData = GameData.pick4(jsonString);
                    GameData.setupGameData(convertView, pick4GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: pick3");
                }
                break;
            }

        return convertView;
    }
}
