package us.brandonandrews.nclottery.models;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import us.brandonandrews.nclottery.utils.GameData;

public class Cash5 {

    private String date;
    private String ball1;
    private String ball2;
    private String ball3;
    private String ball4;
    private String ball5;
    private String jackpot;

    public Cash5(JSONObject jsonObject) {
        try {
            this.date = GameData.formatDate(jsonObject.getString("drawing_date"));
            String[] numbers = jsonObject.getString("drawing_numbers").split(",");
            this.ball1 = numbers[0];
            this.ball2 = numbers[1];
            this.ball3 = numbers[2];
            this.ball4 = numbers[3];
            this.ball5 = numbers[4];
            this.jackpot = String.format("%,d", jsonObject.getString("jackpot"));
        } catch (JSONException e) {
            Log.e("CASH5 MODEL", "Error converting to JSON");
            e.printStackTrace();
        }
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

    public String getBall4() {
        return ball4;
    }

    public void setBall4(String ball4) {
        this.ball4 = ball4;
    }

    public String getBall5() {
        return ball5;
    }

    public void setBall5(String ball5) {
        this.ball5 = ball5;
    }

    public String getJackpot() {
        return jackpot;
    }

    public void setJackpot(String jackpot) {
        this.jackpot = jackpot;
    }
}
