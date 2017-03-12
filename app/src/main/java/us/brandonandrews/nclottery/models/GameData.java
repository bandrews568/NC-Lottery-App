package us.brandonandrews.nclottery.models;


import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.brandonandrews.nclottery.R;

import static us.brandonandrews.nclottery.R.id.swipeContainer;

public class GameData {

    private final static String TAG = "GAME DATA";

    public static HashMap<String, String> pick3(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("pick3");

        String gameTime = gameData.getString("drawing_time");
        String fullGameTime = changeGameTime(gameTime);

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> pick3Data = new HashMap<>();

        pick3Data.put("name", "pick3");
        pick3Data.put("time", fullGameTime);
        pick3Data.put("date", formattedGameDate);
        pick3Data.put("sum", sumItUp(gameNumbers));

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            pick3Data.put(ballNumber, ballValue);
        }

        return pick3Data;
    }

    public static HashMap<String, String> pick4(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("pick4");

        String gameTime = gameData.getString("drawing_time");
        String fullGameTime = changeGameTime(gameTime);

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> pick4Data = new HashMap<>();

        pick4Data.put("name", "pick4");
        pick4Data.put("time", fullGameTime);
        pick4Data.put("date", formattedGameDate);
        pick4Data.put("sum", sumItUp(gameNumbers));

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            pick4Data.put(ballNumber, ballValue);
        }

        return pick4Data;
    }

    public static HashMap<String, String> cash5(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("cash5");

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> cash5Data = new HashMap<>();

        cash5Data.put("name", "cash5");
        cash5Data.put("date", formattedGameDate);

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            cash5Data.put(ballNumber, ballValue);
        }
        
        return cash5Data;
    }

    public static void setupGameData(View convertGroup, HashMap<String, String> data) {

        String gameName = data.get("name");

        TextView tvGameDate = (TextView) convertGroup.findViewById(R.id.tvDate);

        TextView tvBallOne = (TextView) convertGroup.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) convertGroup.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) convertGroup.findViewById(R.id.tvBall3);

        // Pick3 and Pick4 only
        TextView tvSumItUp = (TextView) convertGroup.findViewById(R.id.tvSumItUp);
        TextView tvGameTime = (TextView) convertGroup.findViewById(R.id.tvTime);

        String gameTime = data.get("time");
        String gameDate = data.get("date");

        String ballOne = data.get("ball1");
        String ballTwo = data.get("ball2");
        String ballThree = data.get("ball3");

        if (gameName.equals("pick3")) {
            tvSumItUp.setText(data.get("sum"));
            tvGameTime.setText(gameTime);

        } else if (gameName.equals("pick4")) {
            TextView tvBallFour = (TextView) convertGroup.findViewById(R.id.tvBall4);
            String ballFour = data.get("ball4");

            tvSumItUp.setText(data.get("sum"));
            tvBallFour.setText(ballFour);
            tvGameTime.setText(gameTime);
        } else if (gameName.equals("cash5")) {
            TextView tvBallFour = (TextView) convertGroup.findViewById(R.id.tvBall4);
            TextView tvBallFive = (TextView) convertGroup.findViewById(R.id.tvBall5);

            String ballFour = data.get("ball4");
            String ballFive = data.get("ball5");

            tvBallFour.setText(ballFour);
            tvBallFive.setText(ballFive);
        } 

        tvGameDate.setText(gameDate);

        tvBallOne.setText(ballOne);
        tvBallTwo.setText(ballTwo);
        tvBallThree.setText(ballThree);
    }

    private static String[] formatNumberData(String numbersString) {
        return numbersString.split(",");
    }

    private static String formatDate(String date) {
        // Date format: 2017-01-18
        String[] splitDate = date.split("-");
        return splitDate[1] + "-" + splitDate[2];
    }

    private static String changeGameTime(String time) {
        if (time.equals("E")) {
            time = "Evening";
        } else {
            time = "Day";
        }
        return time;
    }

    public static String sumItUp(String[] numberArray) {
        int sumItup = 0;

        for (int i = 0; i < numberArray.length; i++) {
            sumItup += Integer.parseInt(numberArray[i]);
        }
        return Integer.toString(sumItup);
    }

    public static List<JSONObject> makeJSONArrayList(String jsonString) {
        List<JSONObject> jsonArrayList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                jsonArrayList.add(jsonobject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error makeJSONArrayList()");
        }
        return jsonArrayList;
    }

    public static List<Pick3> makeListOfPick3Drawings(List<JSONObject> jsonObjectArrayList, int count) {
        List<Pick3> pick3ObjectList = new ArrayList<>();

        for (int i =0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjectArrayList.get(i);
            Pick3 pick3 = new Pick3(currentJsonObject);
            pick3ObjectList.add(pick3);
        }
        return pick3ObjectList;
    }
}
