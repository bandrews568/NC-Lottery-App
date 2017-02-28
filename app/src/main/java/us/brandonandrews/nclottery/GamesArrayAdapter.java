package us.brandonandrews.nclottery;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    private int[] gameLayouts = {R.layout.pick3_listview, R.layout.pick4_listview};
    private ArrayList<Game> games;
    private JSONObject jsonString;

    public GamesArrayAdapter(Context context, ArrayList<Game> games, JSONObject jsonString) {
        super(context, 0, games);
        this.games = games;
        this.jsonString = jsonString;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Game game = games.get(position);
        int layoutFile = -1;
        
        switch (game) {
            case PICK3:
                layoutFile = gameLayouts[PICK3.ordinal()];
                try {
                    HashMap<String, String> pick3GameData = GameData.pick3(jsonString);
                    setupGameData(layoutFile, parent, pick3GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("GAMES ARRAY ADAPTER", "Error JSON: pick3");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.e("GAMES ARRAY ADAPTER", "Error Null");
                }
                break;
            case PICK4:
                layoutFile = gameLayouts[PICK4.ordinal()];
                break;
            }

        if (layoutFile != -1) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
            }
        return convertView;
    }

    public View setupGameData(int layoutFile, ViewGroup parent,HashMap<String, String> data) {
        View layout = LayoutInflater.from(getContext()).inflate(layoutFile, parent , false);
        TextView tvGameTime = (TextView) layout.findViewById(R.id.tvTime);
        TextView tvGameDate = (TextView) layout.findViewById(R.id.tvDate);

        String gameTime = data.get("Time");
        tvGameTime.setText(gameTime);
        System.out.println("setupGameData called");
        System.out.println(data.get("Time")); // TODO: this is working, but isn't updating the view
        return layout;
    }
}
