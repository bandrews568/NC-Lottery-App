package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import us.brandonandrews.nclottery.adapters.Pick3RecyclerAdapter;
import us.brandonandrews.nclottery.models.Pick3;
import us.brandonandrews.nclottery.utils.GameData;


public class Pick3Fragment extends Fragment {

    private static final String TAG = "PICK3 FRAGMENT";
    private static final String URL = Urls.PICK3;

    private RecyclerView recyclerView;
    private List<Pick3> pick3List;
    private ProgressBar progressBar;
    private String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;
    private Pick3RecyclerAdapter resultsAdapter;
    private SwipeRefreshLayout swipeContainer;
    private Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pick3_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarPick3);
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
        requestQueue.stop();

        // Clear the snackbar so users don't get confused when they switch screens
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Pick3");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.miFilter).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.miFilter) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_placeholder, new FilterFragment())
                    .addToBackStack(null)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(View view) {
        pick3List = GameData.makeListOfPick3Drawings(jsonObjectList, 200);
        resultsAdapter = new Pick3RecyclerAdapter(getActivity(), pick3List);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPick3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(resultsAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
