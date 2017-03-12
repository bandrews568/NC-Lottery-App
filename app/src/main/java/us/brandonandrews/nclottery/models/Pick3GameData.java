package us.brandonandrews.nclottery.models;


import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pick3GameData {

    private String url = "http://172.17.197.150:8000/games/pick3/";
    private StringRequest stringRequest;
    private String data;
    private List<JSONObject> gameDataList = new ArrayList<>();

    public Pick3GameData() {
        newStringRequest();
        if (data != null) {
            formatJSON(data);
        }
    }

    private void newStringRequest() {
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                data = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
    /* JSON format: array of JSON objects
        [{"drawing_time":"D","drawing_date":"2017-01-18","drawing_numbers":"2,3,6"},
         {"drawing_time":"D","drawing_date":"2017-01-17","drawing_numbers":"5,4,9"}]

     */

    public void formatJSON(String jsonString) {
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                gameDataList.add(jsonobject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
