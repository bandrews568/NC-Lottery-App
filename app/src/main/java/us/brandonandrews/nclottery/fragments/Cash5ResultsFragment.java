package us.brandonandrews.nclottery.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
import us.brandonandrews.nclottery.adapters.Cash5RecyclerAdapter;
import us.brandonandrews.nclottery.models.Cash5;
import us.brandonandrews.nclottery.utils.GameData;


public class Cash5ResultsFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "CASH5 RESULTS FRAGMENT";

    private Context context;
    private RecyclerView recyclerView;
    private Cash5RecyclerAdapter resultsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Snackbar snackbar;
    private List<Cash5> cash5List;
    private String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;
    private String url = "http://bandrews568.pythonanywhere.com/games/cash5";

    public Cash5ResultsFragment newInstance(Context context) {
        this.context = context;
        return new Cash5ResultsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cash5_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                                                   android.R.color.holo_green_light,
                                                   android.R.color.holo_orange_light,
                                                   android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestQueue.add(newStringRequest(url, view));
            }
        });

        requestQueue.add(newStringRequest(url, view));
    }

    private StringRequest newStringRequest(final String url, final View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonString = response;
                        jsonObjectList = GameData.makeJSONArrayList(jsonString);
                        setupRecyclerView(view);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = "Connection Error";

                if (view.getRootView().isShown()) {
                    swipeRefreshLayout.setRefreshing(false);
                    snackbar = Snackbar.make(view.getRootView(), message, Snackbar.LENGTH_INDEFINITE)
                            .setAction("REFRESH", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    swipeRefreshLayout.setRefreshing(false);
                                    requestQueue.add(newStringRequest(url, view));
                                }
                            });
                    snackbar.show();
                }
                Log.e(TAG, "Error getting json");
            }
        });
        return stringRequest;
    }

    private void setupRecyclerView(View view) {
        cash5List = GameData.makeListOfCash5Drawings(jsonObjectList, 200);
        resultsAdapter = new Cash5RecyclerAdapter(getActivity(), cash5List);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(resultsAdapter);
    }
}
