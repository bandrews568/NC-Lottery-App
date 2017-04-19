package us.brandonandrews.nclottery.utils;


import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Cash5;
import us.brandonandrews.nclottery.models.LuckyForLife;
import us.brandonandrews.nclottery.models.MegaMillions;
import us.brandonandrews.nclottery.models.Pick3;
import us.brandonandrews.nclottery.models.Pick4;
import us.brandonandrews.nclottery.models.Powerball;
import us.brandonandrews.nclottery.viewholders.Cash5ViewHolder;
import us.brandonandrews.nclottery.viewholders.LuckyForLifeViewHolder;
import us.brandonandrews.nclottery.viewholders.MegaMillionsViewHolder;
import us.brandonandrews.nclottery.viewholders.Pick3ViewHolder;
import us.brandonandrews.nclottery.viewholders.Pick4ViewHolder;
import us.brandonandrews.nclottery.viewholders.PowerballViewHolder;

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

        String jackpot = "$" + String.format("%,d", gameData.getInt("jackpot"));

        HashMap<String, String> cash5Data = new HashMap<>();

        cash5Data.put("name", "cash5");
        cash5Data.put("date", formattedGameDate);
        cash5Data.put("jackpot", jackpot);

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            cash5Data.put(ballNumber, ballValue);
        }
        return cash5Data;
    }

    public static HashMap<String, String> luckyForLife(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("lucky_for_life");

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> luckyForLifeData = new HashMap<>();

        luckyForLifeData.put("name", "lucky_for_life");
        luckyForLifeData.put("date", formattedGameDate);

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            luckyForLifeData.put(ballNumber, ballValue);
        }
        return luckyForLifeData;
    }

    public static HashMap<String, String> powerball(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("powerball");

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> powerballGameData = new HashMap<>();

        powerballGameData.put("name", "powerball");
        powerballGameData.put("date", formattedGameDate);
        powerballGameData.put("powerball", gameData.getString("powerball"));
        powerballGameData.put("powerplay", gameData.getString("powerplay"));

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            powerballGameData.put(ballNumber, ballValue);
        }
        return powerballGameData;
    }

    public static HashMap<String, String> megaMillions(JSONObject jsonObject) throws JSONException {
        JSONObject gameData = jsonObject.getJSONObject("mega_millions");

        String gameDate = gameData.getString("drawing_date");
        String formattedGameDate = formatDate(gameDate);

        String rawGameNumbers = gameData.getString("drawing_numbers");
        String[] gameNumbers = formatNumberData(rawGameNumbers);

        HashMap<String, String> megaMillionsGameData = new HashMap<>();

        megaMillionsGameData.put("name", "megaMillions");
        megaMillionsGameData.put("date", formattedGameDate);
        megaMillionsGameData.put("megaball", gameData.getString("megaball"));
        megaMillionsGameData.put("multiplier", gameData.getString("multiplier"));

        for (int i = 0; i < gameNumbers.length; i++) {
            String ballNumber = "ball" + (i + 1);
            String ballValue = gameNumbers[i];
            megaMillionsGameData.put(ballNumber, ballValue);
        }
        return megaMillionsGameData;
    }

    public static void setupGameData(View convertGroup, HashMap<String, String> data) {

        String gameName = data.get("name");

        TextView tvGameDate = (TextView) convertGroup.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) convertGroup.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) convertGroup.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) convertGroup.findViewById(R.id.tvBall3);

        // Pick3 and Pick4 only
        TextView tvSumItUp = (TextView) convertGroup.findViewById(R.id.tvSum);
        TextView tvGameTime = (TextView) convertGroup.findViewById(R.id.tvTime);

        String gameTime = data.get("time");
        String gameDate = data.get("date");

        String ballOne = data.get("ball1");
        String ballTwo = data.get("ball2");
        String ballThree = data.get("ball3");

        // All games share these traits
        tvGameDate.setText(gameDate);
        tvBallOne.setText(ballOne);
        tvBallTwo.setText(ballTwo);
        tvBallThree.setText(ballThree);

        switch (gameName) {
            case "pick3":
                tvSumItUp.setText(data.get("sum"));
                tvGameTime.setText(gameTime);
                break;
            case "pick4":
                TextView tvBallFourPick4 = (TextView) convertGroup.findViewById(R.id.tvBall4);
                String ballFour = data.get("ball4");

                tvSumItUp.setText(data.get("sum"));
                tvBallFourPick4.setText(ballFour);
                tvGameTime.setText(gameTime);
                break;
            case "cash5":
                TextView tvBallFourCash5 = (TextView) convertGroup.findViewById(R.id.tvBall4);
                TextView tvBallFiveCash5 = (TextView) convertGroup.findViewById(R.id.tvBall5);
                TextView tvJackpot = (TextView) convertGroup.findViewById(R.id.tvJackpot);

                String ballFourCash5 = data.get("ball4");
                String ballFiveCash5 = data.get("ball5");
                String jackpot = data.get("jackpot");

                tvBallFourCash5.setText(ballFourCash5);
                tvBallFiveCash5.setText(ballFiveCash5);
                tvJackpot.setText(jackpot);
                break;
            case "lucky_for_life":
                TextView tvBallFourLFL = (TextView) convertGroup.findViewById(R.id.tvBall4);
                TextView tvBallFiveLFL = (TextView) convertGroup.findViewById(R.id.tvBall5);
                TextView tvBallSixLFL = (TextView) convertGroup.findViewById(R.id.tvBall6);

                String ballFourLFL = data.get("ball4");
                String ballFiveLFL = data.get("ball5");
                String ballSixLFL = data.get("ball6");

                tvBallFourLFL.setText(ballFourLFL);
                tvBallFiveLFL.setText(ballFiveLFL);
                tvBallSixLFL.setText(ballSixLFL);
                break;
            case "powerball":
                TextView tvBallFourPowerball = (TextView) convertGroup.findViewById(R.id.tvBall4);
                TextView tvBallFivePowerball = (TextView) convertGroup.findViewById(R.id.tvBall5);
                TextView tvPowerball = (TextView) convertGroup.findViewById(R.id.tvPowerball);
                TextView tvPowerplay = (TextView) convertGroup.findViewById(R.id.tvPowerplay);

                String ballFourPowerball = data.get("ball4");
                String ballFivePowerball = data.get("ball5");
                String powerball = data.get("powerball");
                String powerplay = data.get("powerplay");

                tvBallFourPowerball.setText(ballFourPowerball);
                tvBallFivePowerball.setText(ballFivePowerball);
                tvPowerball.setText(powerball);
                tvPowerplay.setText(powerplay);
                break;
            case "megaMillions":
                TextView tvBallFourMegaMillions = (TextView) convertGroup.findViewById(R.id.tvBall4);
                TextView tvBallFiveMegaMillions = (TextView) convertGroup.findViewById(R.id.tvBall5);
                TextView tvMegaball = (TextView) convertGroup.findViewById(R.id.tvMegaball);
                TextView tvMultiplier = (TextView) convertGroup.findViewById(R.id.tvMultiplier);

                String ballFourMegaMillions = data.get("ball4");
                String ballFiveMegaMillions = data.get("ball5");

                String megaball = data.get("megaball");
                String multiplier = data.get("multiplier");

                tvBallFourMegaMillions.setText(ballFourMegaMillions);
                tvBallFiveMegaMillions.setText(ballFiveMegaMillions);

                if (megaball.equals("null")) {
                    tvMegaball.setVisibility(View.INVISIBLE);
                } else {
                    tvMegaball.setText(megaball);
                }

                if (multiplier.equals("null")) {
                    tvMultiplier.setVisibility(View.INVISIBLE);
                } else {
                    tvMultiplier.setText(multiplier);
                }
                break;
        }
    }

    public static void setupPick3ViewHolder(Pick3ViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvTime().setText(data.get("time"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvSum().setText(data.get("sum"));
    }

    public static void setupPick4ViewHolder(Pick4ViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvTime().setText(data.get("time"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvBall4().setText(data.get("ball4"));
        viewHolder.getTvSum().setText(data.get("sum"));
    }

    public static void setupCash5ViewHolder(Cash5ViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvBall4().setText(data.get("ball4"));
        viewHolder.getTvBall5().setText(data.get("ball5"));
        viewHolder.getTvJackpot().setText(data.get("jackpot"));
    }

    public static void setupLuckyForLifeViewHolder(LuckyForLifeViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvBall4().setText(data.get("ball4"));
        viewHolder.getTvBall5().setText(data.get("ball5"));
        viewHolder.getTvBall6().setText(data.get("ball6"));
    }

    public static void setupMegaMillionsViewHolder(MegaMillionsViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvBall4().setText(data.get("ball4"));
        viewHolder.getTvBall5().setText(data.get("ball5"));
        viewHolder.getTvMegaball().setText(data.get("megaball"));
        viewHolder.getTvMultiplier().setText(data.get("multiplier"));
    }

    public static void setupPowerballViewHolder(PowerballViewHolder viewHolder, HashMap<String, String> data) {
        viewHolder.getTvDate().setText(data.get("date"));
        viewHolder.getTvBall1().setText(data.get("ball1"));
        viewHolder.getTvBall2().setText(data.get("ball2"));
        viewHolder.getTvBall3().setText(data.get("ball3"));
        viewHolder.getTvBall4().setText(data.get("ball4"));
        viewHolder.getTvBall5().setText(data.get("ball5"));
        viewHolder.getTvPowerball().setText(data.get("powerball"));
        viewHolder.getTvPowerplay().setText(data.get("powerplay"));
    }

    public static View setupPick3View(View view, String[] numbers) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvSumItUp = (TextView) view.findViewById(R.id.tvSum);
        TextView tvGameTime = (TextView) view.findViewById(R.id.tvTime);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvSumItUp.setText(GameData.sumItUp(numbers));

        tvGameDate.setVisibility(View.INVISIBLE);
        tvGameTime.setVisibility(View.INVISIBLE);
        return view;
    }

    public static View setupPick4View(View view, String[] numbers) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvBallFour = (TextView) view.findViewById(R.id.tvBall4);
        TextView tvSumItUp = (TextView) view.findViewById(R.id.tvSum);
        TextView tvGameTime = (TextView) view.findViewById(R.id.tvTime);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvBallFour.setText(numbers[3]);
        tvSumItUp.setText(GameData.sumItUp(numbers));

        tvGameDate.setVisibility(View.INVISIBLE);
        tvGameTime.setVisibility(View.INVISIBLE);
        return view;
    }

    public static View setupCash5View(View view, String[] numbers) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvJackpot = (TextView) view.findViewById(R.id.tvJackpot);
        TextView jackpotLabel = (TextView) view.findViewById(R.id.jackpotLabel);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvBallFour = (TextView) view.findViewById(R.id.tvBall4);
        TextView tvBallFive = (TextView) view.findViewById(R.id.tvBall5);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvBallFour.setText(numbers[3]);
        tvBallFive.setText(numbers[4]);

        tvGameDate.setVisibility(View.INVISIBLE);
        tvJackpot.setVisibility(View.INVISIBLE);
        jackpotLabel.setVisibility(View.INVISIBLE);
        return view;
    }

    public static View setupLuckyForLifeView(View view, String[] numbers, String luckyBall) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvBallFour = (TextView) view.findViewById(R.id.tvBall4);
        TextView tvBallFive = (TextView) view.findViewById(R.id.tvBall5);
        TextView tvBallSix = (TextView) view.findViewById(R.id.tvBall6);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvBallFour.setText(numbers[3]);
        tvBallFive.setText(numbers[4]);
        tvBallSix.setText(luckyBall);

        tvGameDate.setVisibility(View.INVISIBLE);
        return view;
    }

    public static View setupMegaMillionsView(View view, String[] numbers, String megaBall) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvBallFour = (TextView) view.findViewById(R.id.tvBall4);
        TextView tvBallFive = (TextView) view.findViewById(R.id.tvBall5);
        TextView tvMegaball = (TextView) view.findViewById(R.id.tvMegaball);
        TextView tvMultiplier = (TextView) view.findViewById(R.id.tvMultiplier);
        TextView multiplierLabel = (TextView) view.findViewById(R.id.multipilerLabel);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvBallFour.setText(numbers[3]);
        tvBallFive.setText(numbers[4]);
        tvMegaball.setText(megaBall);

        tvGameDate.setVisibility(View.INVISIBLE);
        tvMultiplier.setVisibility(View.INVISIBLE);
        multiplierLabel.setVisibility(View.INVISIBLE);
        return view;
    }

    public static View setupPowerballView(View view, String[] numbers, String powerball) {
        TextView tvGameDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvBallOne = (TextView) view.findViewById(R.id.tvBall1);
        TextView tvBallTwo = (TextView) view.findViewById(R.id.tvBall2);
        TextView tvBallThree = (TextView) view.findViewById(R.id.tvBall3);
        TextView tvBallFour = (TextView) view.findViewById(R.id.tvBall4);
        TextView tvBallFive = (TextView) view.findViewById(R.id.tvBall5);
        TextView tvPowerball = (TextView) view.findViewById(R.id.tvPowerball);
        TextView tvPowerplay = (TextView) view.findViewById(R.id.tvPowerplay);
        TextView powerplayLabel = (TextView) view.findViewById(R.id.powerplayLabel);

        tvBallOne.setText(numbers[0]);
        tvBallTwo.setText(numbers[1]);
        tvBallThree.setText(numbers[2]);
        tvBallFour.setText(numbers[3]);
        tvBallFive.setText(numbers[4]);
        tvPowerball.setText(powerball);

        tvGameDate.setVisibility(View.INVISIBLE);
        tvPowerplay.setVisibility(View.INVISIBLE);
        powerplayLabel.setVisibility(View.INVISIBLE);
        return view;
    }

    private static String[] formatNumberData(String numbersString) {
        return numbersString.split(",");
    }

    public static String formatDate(String date) {
        // Date format: 2017-01-18
        String[] splitDate = date.split("-");
        return splitDate[1] + "-" + splitDate[2];
    }

    public static String changeGameTime(String time) {
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

    public static List<Pick3> makeListOfPick3Drawings(List<JSONObject> jsonObjects, int count) {
        List<Pick3> pick3ObjectList = new ArrayList<>();

        for (int i =0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            Pick3 pick3 = new Pick3(currentJsonObject);
            pick3ObjectList.add(pick3);
        }
        return pick3ObjectList;
    }

    public static List<Pick4> makeListOfPick4Drawings(List<JSONObject> jsonObjects, int count) {
        List<Pick4> pick4List = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            Pick4 pick4 = new Pick4(currentJsonObject);
            pick4List.add(pick4);
        }
        return pick4List;
    }

    public static List<Cash5> makeListOfCash5Drawings(List<JSONObject> jsonObjects, int count) {
        List<Cash5> cash5List = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            Cash5 cash5 = new Cash5(currentJsonObject);
            cash5List.add(cash5);
        }
        return cash5List;
    }

    public static List<LuckyForLife> makeListOfLuckyForLifeDrawings(List<JSONObject> jsonObjects, int count) {
        List<LuckyForLife> luckyForLifeList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            LuckyForLife luckyForLife = new LuckyForLife(currentJsonObject);
            luckyForLifeList.add(luckyForLife);
        }
        return luckyForLifeList;
    }

    public static List<Powerball> makeListOfPowerballDrawings(List<JSONObject> jsonObjects, int count) {
        List<Powerball> powerballList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            Powerball powerball = new Powerball(currentJsonObject);
            powerballList.add(powerball);
        }
        return powerballList;
    }

    public static List<MegaMillions> makeListOfMegaMillionsDrawings(List<JSONObject> jsonObjects, int count) {
        List<MegaMillions> megaMillionsList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            JSONObject currentJsonObject = jsonObjects.get(i);
            MegaMillions megaMillions = new MegaMillions(currentJsonObject);
            megaMillionsList.add(megaMillions);
        }
        return megaMillionsList;
    }
}
