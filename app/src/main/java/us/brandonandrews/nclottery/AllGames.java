package us.brandonandrews.nclottery;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class AllGames extends AppCompatActivity {

    protected JSONObject jsonDataString;
    private Context context;
    private String url = "http://172.17.197.150:8000/games/all/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_all_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new JsonTask().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class JsonTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
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
                    buffer.append(line + "\n");
                    Log.d("JSON TASK", line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                Log.e("JSON TASK", "Bad URL: " + params[0]);
            } catch (IOException e) {
                Log.e("JSON TASK", "IOException");
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
                    Log.e("JSON TASK", "IOException: when closing reader");
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                jsonDataString = new JSONObject(result);
                ListView lvGames = (ListView) findViewById(R.id.lvGames);
                lvGames.setVisibility(View.INVISIBLE);

                ProgressBar progressBarMainScreen = (ProgressBar) findViewById(R.id.progressBarMainScreen);

                // TODO this should be dynamic with settings menu
                ArrayList<Game> gameList = new ArrayList<>();
                gameList.add(Game.PICK3);

                GamesArrayAdapter gamesArrayAdapter = new GamesArrayAdapter(context, gameList, jsonDataString);
                lvGames.setAdapter(gamesArrayAdapter);

                progressBarMainScreen.setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                Log.e("JSON TASK", "Error converting result to JSON object");
                e.printStackTrace();
            }
        }
    }
}
