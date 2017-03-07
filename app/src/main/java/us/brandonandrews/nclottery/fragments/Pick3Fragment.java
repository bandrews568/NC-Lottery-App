package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import us.brandonandrews.nclottery.adapters.AllGamesArrayAdapter;
import us.brandonandrews.nclottery.Game;
import us.brandonandrews.nclottery.R;

import static us.brandonandrews.nclottery.R.id.swipeContainer;


public class Pick3Fragment extends Fragment {

    private String TAG = "PICK3 FRAGMENT";

    private JSONObject jsonDataString;
    private String url = "http://172.17.197.150:8000/games/pick3/";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pick3, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        this.view = view;

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(newStringRequest());
    }

    private StringRequest newStringRequest() {
        stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
                Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                        .setAction("REFRESH", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                swipeContainer.setRefreshing(false);
                                requestQueue.add(newStringRequest());
                            }
                        })
                        .show();
                Log.e(TAG, "Error getting JSON from " + url);
                Log.e(TAG, error.toString());
            }
        });
        return stringRequest;
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

        // TODO this should be dynamic with settings menu
        ArrayList<Game> gameList = new ArrayList<>();
        gameList.add(Game.PICK3);
        gameList.add(Game.PICK4);
        gameList.add(Game.CASH5);

        AllGamesArrayAdapter allGamesArrayAdapter = new AllGamesArrayAdapter(
                getActivity().getApplicationContext(), gameList, jsonDataString);
        lvGames.setAdapter(allGamesArrayAdapter);

        progressBarMainScreen.setVisibility(View.INVISIBLE);
        lvGames.setVisibility(View.VISIBLE);
    }

    public void refreshToast() {
        Toast.makeText(getActivity().getApplicationContext(), "Refreshed", Toast.LENGTH_SHORT).show();
    }
}
