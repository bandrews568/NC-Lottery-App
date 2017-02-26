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



public class GamesArrayAdapter extends ArrayAdapter{

    private ArrayList<Game> mGamesList;
    private int[] mGameLayouts = {R.layout.pick3_listview, R.layout.pick4_listview};
    private ListView mlvGames;
    private ProgressBar mProgressBar;
    private String mGamesJSONDataString;
    private JSONObject mGamesJSONData;
//    private String url = "http://192.168.0.7:8000/games/all/";

    private String url = "http://172.17.197.150:8000/games/all/";

    public GamesArrayAdapter(Context context, ListView lv, ProgressBar progressBar,
                             ArrayList<Game> games) {
        super(context, 0, games);
        mGamesList = games;
        mlvGames = lv;
        mProgressBar = progressBar;
        new JsonTask().execute(url);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Game game = mGamesList.get(position);
        int layoutFile = -1;
        
        switch (game) {
            case PICK3:
                layoutFile = mGameLayouts[PICK3.ordinal()];
                try {
                    HashMap<String, String> pick3GameData = GameData.pick3(mGamesJSONData);
                    setupGameData(layoutFile, pick3GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("GAMES ARRAY ADAPTER", "Error JSON: pick3");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.e("GAMES ARRAY ADAPTER", "Error Null");
                }
                break;
            case PICK4:
                layoutFile = mGameLayouts[PICK4.ordinal()];
                break;
            }

        if (layoutFile != -1) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutFile, null);
            }
        return convertView;
    }

    public View setupGameData(int layoutFile, HashMap<String, String> data) {
        View layout = LayoutInflater.from(getContext()).inflate(layoutFile, null);
        TextView tvGameTime = (TextView) layout.findViewById(R.id.tvTime);
        TextView tvGameDate = (TextView) layout.findViewById(R.id.tvDate);

        String gameTime = data.get("Time");
        tvGameTime.setText(gameTime);
        System.out.println("setupGameData called");
        System.out.println(data.get("Time")); // TODO: this is working, but isn't updating the view
        return layout;
    }

    // TODO: this isn't going to work as a async task!
    public class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);

                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ConnectException e) {
                String msg = "Connection Error";
                Toast.makeText(getContext().getApplicationContext(), msg, Toast.LENGTH_SHORT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.INVISIBLE);
            mGamesJSONDataString = result;

            try {
                mGamesJSONData = new JSONObject(mGamesJSONDataString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mlvGames.setVisibility(View.VISIBLE);
        }
    }
}
