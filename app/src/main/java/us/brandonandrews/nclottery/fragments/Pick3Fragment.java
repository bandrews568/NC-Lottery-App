package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import us.brandonandrews.nclottery.adapters.Pick3Adapter;
import us.brandonandrews.nclottery.models.Pick3;
import us.brandonandrews.nclottery.utils.GameData;


public class Pick3Fragment extends Fragment {

    private static final String TAG = "PICK3 FRAGMENT";
    private static final String URL = "http://bandrews568.pythonanywhere.com/games/pick3";

    private ProgressBar progressBar;
    private ListView listView;
    private List<Pick3> pick3List;
    private String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pick3_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        requestQueue.add(newStringRequest(URL, view));
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    private StringRequest newStringRequest(String url, final View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonString = response;
                        jsonObjectList = GameData.makeJSONArrayList(jsonString);

                        if (pick3List == null) {
                            pick3List = GameData.makeListOfPick3Drawings(jsonObjectList, 20);
                        }

                        Pick3Adapter resultsAdapter = new Pick3Adapter(getActivity(), pick3List);
                        listView = (ListView) view.findViewById(R.id.listView);
                        listView.setAdapter(resultsAdapter);
                        progressBar.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.VISIBLE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting json");
            }
        });
        return stringRequest;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
