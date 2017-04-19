package us.brandonandrews.nclottery.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import us.brandonandrews.nclottery.Urls;
import us.brandonandrews.nclottery.adapters.AllGamesRecyclerAdapter;


public class AllGamesFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "ALL GAMES FRAGMENT";
    private static final String URL = Urls.ALL_GAMES;

    private View view;
    private SwipeRefreshLayout swipeContainer;

    private JSONObject jsonDataString;
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
                gameList.clear();
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
        stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
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

                if (view.getRootView().isShown()) {
                    snackbar = Snackbar.make(view.getRootView(), message, Snackbar.LENGTH_INDEFINITE)
                            .setAction("REFRESH", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    swipeContainer.setRefreshing(false);
                                    requestQueue.add(newStringRequest());
                                }
                            });
                    snackbar.show();
                }
                Log.e(TAG, "Error getting JSON from " + URL);
                Log.e(TAG, error.toString());
            }
        });
        return stringRequest;
    }

    @Override
    public void onPause() {
        super.onPause();
        requestQueue.stop();

        // Clear the snackbar so users don't get confused when they switch screens
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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getSelectedGamesFromSettings();

        // No games are checked on settings menu
        if (gameList.isEmpty()) {
            // TODO put message in here when no games are selected
        } else {
            AllGamesRecyclerAdapter allGamesRecyclerAdapter =
                    new AllGamesRecyclerAdapter(gameList, jsonDataString);
            recyclerView.setAdapter(allGamesRecyclerAdapter);
            recyclerView.setVisibility(View.VISIBLE);
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
