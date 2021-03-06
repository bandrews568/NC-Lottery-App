package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import us.brandonandrews.nclottery.Urls;
import us.brandonandrews.nclottery.adapters.LuckyForLifeRecyclerAdapter;
import us.brandonandrews.nclottery.models.LuckyForLife;
import us.brandonandrews.nclottery.utils.GameData;


public class LuckyForLifeFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "LUCKY FOR LIFE";
    private static final String URL = Urls.LUCKY_FOR_LIFE;

    private RecyclerView recyclerView;
    private LuckyForLifeRecyclerAdapter resultsAdapter;
    private SwipeRefreshLayout swipeContainer;
    private Snackbar snackbar;
    private ProgressBar progressBar;

    private List<LuckyForLife> luckyForLifeList;
    private String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lucky_for_life_resutls_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarLuckyForLife);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                                               android.R.color.holo_green_light,
                                               android.R.color.holo_orange_light,
                                               android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestQueue.add(newStringRequest(URL, view));
            }
        });

        requestQueue.add(newStringRequest(URL, view));
    }

    private StringRequest newStringRequest(final String url, final View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonString = response;
                        jsonObjectList = GameData.makeJSONArrayList(jsonString);
                        setupRecyclerView(view);
                        swipeContainer.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = "Connection Error";

                if (view.getRootView().isShown()) {
                    swipeContainer.setRefreshing(false);
                    snackbar = Snackbar.make(view.getRootView(), message, Snackbar.LENGTH_INDEFINITE)
                            .setAction("REFRESH", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    swipeContainer.setRefreshing(false);
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Lucky For Life");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.miFilter).setVisible(true);
    }

    private void setupRecyclerView(View view) {
        luckyForLifeList = GameData.makeListOfLuckyForLifeDrawings(jsonObjectList, 200);
        resultsAdapter = new LuckyForLifeRecyclerAdapter(getActivity(), luckyForLifeList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewLuckyForLife);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(resultsAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
