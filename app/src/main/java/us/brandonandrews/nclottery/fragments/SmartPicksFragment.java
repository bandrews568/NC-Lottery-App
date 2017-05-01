package us.brandonandrews.nclottery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.Urls;

public class SmartPicksFragment extends Fragment {

    public static final String TAG = "SMART-PICKS-FRAGMENT";

    private List<String> dataList;
    private List<TextView> smartPickTextViews;
    private Document document;
    private String gameName;
    private RequestQueue requestQueue;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getContext());
        dataList = new ArrayList<>();
        gameName = getArguments().getString("game");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_picks_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bindSmartPickTextViews();
        scrapeSmartPicks();
    }

    @Override
    public void onPause() {
        super.onPause();
        requestQueue.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        dataList.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void scrapeSmartPicks() {
        url = getURL(gameName);

        requestQueue.add(new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        document = Jsoup.parse(response);

                        Elements smartPicksHotNumbers = document.getElementsByClass("fcred1");
                        for (Element pick : smartPicksHotNumbers) {
                            dataList.add(pick.text());
                        }

                        Elements smartPicks = document.getElementsByClass("fs17");
                        for (Element pick : smartPicks) {
                            dataList.add(pick.text());
                        }

                        setupSmartPickText();
                        setupDigitsText();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error connecting to URL: " + url);
            }
        }));
    }

    private String getURL(String gameName) {
        String url = null;

        switch (gameName) {
            case "pick3":
                url = Urls.PICK3_SMART_PICK;
                break;
            case "pick4":
                url = Urls.PICK4_SMART_PICK;
                break;
            case "cash5":
                url = Urls.CASH5_SMART_PICK;
                break;
            case "luckyForLife":
                url = Urls.LUCKY_FOR_LIFE_SMART_PICK;
                break;
            case "megaMillions":
                url = Urls.MEGA_MILLIONS_SMART_PICK;
                break;
            case "powerball":
                url = Urls.POWERBALL_SMART_PICK;
                break;
        }
        return url;
    }

    private int getStartingIndex(String gameName) {
        int index = 0;

        switch (gameName) {
            case "pick3":
                index = 3;
                break;
            case "pick4":
                index = 4;
        }
        return index;
    }

    private void bindSmartPickTextViews() {
        smartPickTextViews = new ArrayList<>();
        int[] textViews = { R.id.tvSmartPickOne, R.id.tvSmartPickTwo, R.id.tvSmartPickThree,
                R.id.tvSmartPickFour, R.id.tvSmartPickFive, R.id.tvSmartPickSix, R.id.tvSmartPickSeven,
                R.id.tvSmartPickEight, R.id.tvSmartPickNine, R.id.tvSmartPickTen };

        for (int i = 0; i < textViews.length; i++) {
            smartPickTextViews.add((TextView) getView().findViewById(textViews[i]));
        }
    }

    private void setupSmartPickText() {
        int index = 3;
        for (TextView textView : smartPickTextViews) {
            textView.setText(dataList.get(index));
            index += 1;
        }
    }

    private void setupDigitsText() {
        TextView tvDigitsOne = (TextView) getView().findViewById(R.id.tvDigitsOneNumbers);
        TextView tvDigitsTwo = (TextView) getView().findViewById(R.id.tvDigitsTwoNumbers);
        TextView tvDigitsThree = (TextView) getView().findViewById(R.id.tvDigitsThreeNumbers);

        tvDigitsOne.setText(dataList.get(0));
        tvDigitsTwo.setText(dataList.get(1));
        tvDigitsThree.setText(dataList.get(2));
    }
}
