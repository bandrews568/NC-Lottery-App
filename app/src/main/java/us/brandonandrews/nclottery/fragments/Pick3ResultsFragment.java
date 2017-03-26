package us.brandonandrews.nclottery.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;

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
import us.brandonandrews.nclottery.adapters.Pick3ResultsAdapter;
import us.brandonandrews.nclottery.models.GameData;
import us.brandonandrews.nclottery.models.Pick3;

public class Pick3ResultsFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "PICK3 RESULTS FRAGMENT";

    private Context context;
    private ListView listView;
    List<Pick3> pick3List;


    public String jsonString;
    private List<JSONObject> jsonObjectList = new ArrayList<>();
    private RequestQueue requestQueue;
    private String url = "http://172.17.197.150:8000/games/pick3/";

    public Pick3ResultsFragment newInstance(Context context) {
        this.context = context;
        Pick3ResultsFragment fragment = new Pick3ResultsFragment();
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
        return inflater.inflate(R.layout.pick3_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        requestQueue.add(newStringRequest(url, view));

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rgPick3);
        // TODO this can replaced by a recycle view
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int count = 0;

                switch(checkedId) {
                    case R.id.rbResult5:
                        count = 5;
                        break;
                    case R.id.rbResult10:
                        count = 10;
                        break;
                    case R.id.rbResult15:
                        count = 15;
                }
                pick3List = GameData.makeListOfPick3Drawings(jsonObjectList, count);
            }
        });

        // Default to 20 for the amount of results to get back
        // Change the on onClickListener above
        if (pick3List == null) {
            pick3List = GameData.makeListOfPick3Drawings(jsonObjectList, 20);
        }
    }

    private StringRequest newStringRequest(String url, final View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jsonString = response;
                        jsonObjectList = GameData.makeJSONArrayList(jsonString);
                        Pick3ResultsAdapter resultsAdapter = new Pick3ResultsAdapter(getActivity(), pick3List);
                        listView = (ListView) view.findViewById(R.id.listView);
                        listView.setAdapter(resultsAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting json");
            }
        });
        return stringRequest;
    }
}
