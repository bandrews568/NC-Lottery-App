package us.brandonandrews.nclottery.models;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import us.brandonandrews.nclottery.utils.GameData;

public class Pick3 {

    public String time;
    public String date;
    public String ball1;
    public String ball2;
    public String ball3;
    public String sum;

    public Pick3(JSONObject jsonObject) {
        try {
            this.time = GameData.changeGameTime(jsonObject.getString("drawing_time"));
            this.date = GameData.formatDate(jsonObject.getString("drawing_date"));
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBall1() {
        return ball1;
    }

    public void setBall1(String ball1) {
        this.ball1 = ball1;
    }

    public String getBall2() {
        return ball2;
    }

    public void setBall2(String ball2) {
        this.ball2 = ball2;
    }

    public String getBall3() {
        return ball3;
    }

    public void setBall3(String ball3) {
        this.ball3 = ball3;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
