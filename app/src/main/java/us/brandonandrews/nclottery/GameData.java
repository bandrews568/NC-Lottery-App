package us.brandonandrews.nclottery;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class GameData {

    public static HashMap<String, String> pick3(JSONObject jsonObject) throws JSONException {
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
        pick3Data.put("name", "pick3");
        pick3Data.put("time", fullGameTime);
        pick3Data.put("date", formattedGameDate);
        pick3Data.put("ball1", ball1);
        pick3Data.put("ball2", ball2);
        pick3Data.put("ball3", ball3);

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
        String ball1 = gameNumbers[0];
        String ball2 = gameNumbers[1];
        String ball3 = gameNumbers[2];
        String ball4 = gameNumbers[3];

        HashMap<String, String> pick4Data = new HashMap<>();
        pick4Data.put("name", "pick4");
        pick4Data.put("time", fullGameTime);
        pick4Data.put("date", formattedGameDate);
        pick4Data.put("ball1", ball1);
        pick4Data.put("ball2", ball2);
        pick4Data.put("ball3", ball3);
        pick4Data.put("ball4", ball4);

        return pick4Data;
    }



    private static String changeGameTime(String time) {
        if (time.equals("E")) {
            time = "Evening";
        } else {
            time = "Day";
        }
        return time;
    }

    public static void setupGameData(View convertGroup, HashMap<String, String> data) {

        String gameName = data.get("name");

        TextView tvGameTime = (TextView) convertGroup.findViewById(R.id.tvTime);
        TextView tvGameDate = (TextView) convertGroup.findViewById(R.id.tvDate);

        TextView tvBallOne = (TextView) convertGroup.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) convertGroup.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) convertGroup.findViewById(R.id.tvBall3);

        String gameTime = data.get("time");
        String gameDate = data.get("date");

        String ballOne = data.get("ball1");
        String ballTwo = data.get("ball2");
        String ballThree = data.get("ball3");

        if (gameName.equals("pick3")) {
            tvGameTime.setText(gameTime);
        } else if (gameName.equals("pick4")) {
            TextView tvBallFour = (TextView) convertGroup.findViewById(R.id.tvBall4);
            String ballFour = data.get("ball4");
            tvBallFour.setText(ballFour);
            tvGameTime.setText(gameTime);
        }

        tvGameDate.setText(gameDate);

        tvBallOne.setText(ballOne);
        tvBallTwo.setText(ballTwo);
        tvBallThree.setText(ballThree);
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
