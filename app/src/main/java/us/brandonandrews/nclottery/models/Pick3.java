package us.brandonandrews.nclottery.models;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Pick3 {

    public String time;
    public String date;
    public String ball1;
    public String ball2;
    public String ball3;
    public String sum;

    public Pick3(JSONObject jsonObject) {
        try {
            this.time = jsonObject.getString("drawing_time");
            this.date = jsonObject.getString("drawing_date");
            String[] numbers = jsonObject.getString("drawing_numbers").split(",");
            this.ball1 = numbers[0];
            this.ball2 = numbers[1];
            this.ball3 = numbers[2];
            this.sum = GameData.sumItUp(numbers);
        } catch (JSONException e) {
            Log.e("PICK3-MODEL", "Error converting to JSON");
            e.printStackTrace();
        }
    }
}
