package us.brandonandrews.nclottery.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.adapters.AllGamesArrayAdapter;


public class AllGamesFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "ALL GAMES FRAGMENT";

    private View view;
    private SwipeRefreshLayout swipeContainer;

    private JSONObject jsonDataString;
    private String url = "http://172.31.99.21:8000/games/all"; // For Starbucks
//    private String url = "http://172.17.197.150:8000/games/all/"; // For hotel
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private ArrayList<String> gameList = new ArrayList<>();

    private Snackbar snackbar;

    private SharedPreferences settingsPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_games, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        this.view = view;

        settingsPrefs = this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestQueue.add(newStringRequest());
            }
        });

        requestQueue.add(newStringRequest());

        // TODO change the color of this to fit the rest of the UI
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                                               android.R.color.holo_green_light,
                                               android.R.color.holo_orange_light,
                                               android.R.color.holo_red_light);
    }

    private StringRequest newStringRequest() {
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
                updateUI(response);
                swipeContainer.setRefreshing(false);
                refreshToast();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = "Connection Error";
                snackbar = Snackbar.make(view.getRootView(), message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("REFRESH", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                swipeContainer.setRefreshing(false);
                                requestQueue.add(newStringRequest());
                            }
                        });
                snackbar.show();
                Log.e(TAG, "Error getting JSON from " + url);
                Log.e(TAG, error.toString());
            }
        });
        return stringRequest;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }
    
    private void updateUI(String jsonString) {
        try {
            jsonDataString = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.e(TAG, "Error converting result to JSON object");
        }

        ListView lvGames = (ListView) view.findViewById(R.id.lvGames);
        lvGames.setVisibility(View.INVISIBLE);
        ProgressBar progressBarMainScreen = (ProgressBar) view.findViewById(R.id.progressBarMainScreen);

        getSelectedGamesFromSettings();

        if (gameList.isEmpty()) {
            TextView tvNoGames = (TextView) view.findViewById(R.id.tvNoGames);
            tvNoGames.setVisibility(View.VISIBLE);
            progressBarMainScreen.setVisibility(View.INVISIBLE);
        } else {
            AllGamesArrayAdapter allGamesArrayAdapter = new AllGamesArrayAdapter(
                    getActivity().getApplicationContext(), gameList, jsonDataString);
            lvGames.setAdapter(allGamesArrayAdapter);

            progressBarMainScreen.setVisibility(View.INVISIBLE);
            lvGames.setVisibility(View.VISIBLE);
        }
    }

    private void getSelectedGamesFromSettings() {
        String[] games = {"pick3", "pick4", "cash5", "luckyForLife", "megaMillions", "powerball"};

        for (String game : games) {
            boolean getGame = settingsPrefs.getBoolean(game, false);
            if (getGame) {
                gameList.add(game);
            }
        }
    }

    private void refreshToast() {
        Toast.makeText(getActivity().getApplicationContext(), "Refreshed", Toast.LENGTH_SHORT).show();
    }
}
