package us.brandonandrews.nclottery.utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Scraper {

    private static final String smartPickURL =
            "https://www.lotteryinformation.us/apps/smart-pick.php?state=NC&game=NCMID3&tb_state=&tb_links=&tb_country=US&tb_lang=0&adsurl=&tbsite=&d=google.com";
    private static Document document;

    public static List<String> scrapeSmartPicks(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        final List<String> pickList = new ArrayList<>();

        requestQueue.add(new StringRequest(Request.Method.GET, smartPickURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        document = Jsoup.parse(response);

                        Elements smartPicksHotNumbers = document.getElementsByClass("fcred1");
                        for (Element pick : smartPicksHotNumbers) {
                            pickList.add(pick.text());
                        }

                        Elements smartPicks = document.getElementsByClass("fs17");
                        for (Element pick : smartPicks) {
                            pickList.add(pick.text());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }));
        return pickList;
    }
}
