package us.brandonandrews.nclottery.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.adapters.Pick4RecyclerAdapter;
import us.brandonandrews.nclottery.models.GameData;
import us.brandonandrews.nclottery.models.Pick4;

public class Pick4ResultsFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "PICK4 RESULTS FRAGMENT";

    private Context context;
    private RecyclerView recyclerView;
    private Pick4RecyclerAdapter resultsAdapter;
    List<Pick4> pick4List;

    public String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;
    private String url = "http://172.31.99.21:8000/games/pick4"; // Starbucks
//    private String url = "http://172.17.197.150:8000/games/pick4"; // hotel

    public Pick4ResultsFragment newInstance(Context context) {
        this.context = context;
        Pick4ResultsFragment fragment = new Pick4ResultsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pick4_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        requestQueue.add(newStringRequest(url, view));
    }

    private StringRequest newStringRequest(String url, final View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonString = response;
                        jsonObjectList = GameData.makeJSONArrayList(jsonString);
                        pick4List = GameData.makeListOfPick4Drawings(jsonObjectList, 20);
                        resultsAdapter = new Pick4RecyclerAdapter(getActivity(), pick4List);
                        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPick4);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(resultsAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting json");
                // TODO display snackbar and also add swipe down to refresh
            }
        });
        return stringRequest;
    }
}
