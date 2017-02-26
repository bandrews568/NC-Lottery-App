package us.brandonandrews.nclottery;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class GameData {

    public static HashMap<String, String> pick3(JSONObject jsonObject) throws JSONException,
                                                                              NullPointerException {
        JSONObject gameData = jsonObject.getJSONObject("pick3");

        String gameTime = gameData.getString("drawing_time");
        String fullGameTime = changeGameTime(gameTime);

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);
        String ball1 = gameNumbers[0];
        String ball2 = gameNumbers[1];
        String ball3 = gameNumbers[2];

        HashMap<String, String> pick3Data = new HashMap<>();
        pick3Data.put("Name", "Pick3");
        pick3Data.put("Time", fullGameTime);
        pick3Data.put("Date", formattedGameDate);
        pick3Data.put("ball1", ball1);
        pick3Data.put("ball2", ball2);
        pick3Data.put("ball3", ball3);

        return pick3Data;
    }

    private static String changeGameTime(String time) {
        if (time.equals("E")) {
            time = "Evening";
        } else {
            time = "Day";
        }
        return time;
    }

    private static String[] formatNumberData(String numbersString) {
        String[] numbers = numbersString.split(",");
        return numbers;
    }

    private static String formatDate(String date) {
        SimpleDateFormat formatDate;
        String formattedDate;

        try {
            formatDate = new SimpleDateFormat("mm-dd-yyyy");
            formattedDate = formatDate.parse(date).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("GAME DATA", "Error parsing date string");
            formattedDate = null;
        }
        return formattedDate;
    }
}
