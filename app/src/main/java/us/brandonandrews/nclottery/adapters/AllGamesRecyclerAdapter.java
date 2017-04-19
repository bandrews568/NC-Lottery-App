package us.brandonandrews.nclottery.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.utils.GameData;
import us.brandonandrews.nclottery.viewholders.Cash5ViewHolder;
import us.brandonandrews.nclottery.viewholders.LuckyForLifeViewHolder;
import us.brandonandrews.nclottery.viewholders.MegaMillionsViewHolder;
import us.brandonandrews.nclottery.viewholders.Pick3ViewHolder;
import us.brandonandrews.nclottery.viewholders.Pick4ViewHolder;
import us.brandonandrews.nclottery.viewholders.PowerballViewHolder;


public class AllGamesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "ALL GAMES ADAPTER";

    private List<String> gamesList;
    private JSONObject jsonObject;

    public AllGamesRecyclerAdapter(List<String> gamesList, JSONObject jsonObject) {
        this.gamesList = gamesList;
        this.jsonObject = jsonObject;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (gamesList.get(viewType)) {
            case "pick3":
                View pick3View = inflater.inflate(R.layout.pick3_listview, parent, false);
                viewHolder = new Pick3ViewHolder(pick3View);
                break;
            case "pick4":
                View pick4View = inflater.inflate(R.layout.pick4_listview, parent, false);
                viewHolder = new Pick4ViewHolder(pick4View);
                break;
            case "cash5":
                View cash5View = inflater.inflate(R.layout.cash5_listview, parent, false);
                viewHolder = new Cash5ViewHolder(cash5View);
                break;
            case "luckyForLife":
                View luckyForLifeView = inflater.inflate(R.layout.lucky_for_life_listview, parent, false);
                viewHolder = new LuckyForLifeViewHolder(luckyForLifeView);
                break;
            case "powerball":
                View powerballView = inflater.inflate(R.layout.powerball_listview, parent, false);
                viewHolder = new PowerballViewHolder(powerballView);
                break;
            case "megaMillions":
                View megaMillionsView = inflater.inflate(R.layout.mega_millions_listview, parent, false);
                viewHolder = new MegaMillionsViewHolder(megaMillionsView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (gamesList.get(position)) {
            case "pick3":
                try {
                    Pick3ViewHolder pick3ViewHolder = (Pick3ViewHolder) holder;
                    HashMap<String, String> pick3GameData = GameData.pick3(jsonObject);
                    GameData.setupPick3ViewHolder(pick3ViewHolder, pick3GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: pick3");
                }
                break;
            case "pick4":
                try {
                    Pick4ViewHolder pick4ViewHolder = (Pick4ViewHolder) holder;
                    HashMap<String, String> pick4GameData = GameData.pick4(jsonObject);
                    GameData.setupPick4ViewHolder(pick4ViewHolder, pick4GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: pick4");
                }
                break;
            case "cash5":
                try {
                    Cash5ViewHolder cash5ViewHolder = (Cash5ViewHolder) holder;
                    HashMap<String, String> cash5GameData = GameData.cash5(jsonObject);
                    GameData.setupCash5ViewHolder(cash5ViewHolder, cash5GameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: cash5");
                }
                break;
            case "luckyForLife":
                try {
                    LuckyForLifeViewHolder luckyForLifeViewHolder = (LuckyForLifeViewHolder) holder;
                    HashMap<String, String> luckyForLifeGameData = GameData.luckyForLife(jsonObject);
                    GameData.setupLuckyForLifeViewHolder(luckyForLifeViewHolder, luckyForLifeGameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: luckyForLife");
                }
                break;
            case "megaMillions":
                try{
                    MegaMillionsViewHolder megaMillionsViewHolder = (MegaMillionsViewHolder) holder;
                    HashMap<String, String> megaMillionsGameData = GameData.megaMillions(jsonObject);
                    GameData.setupMegaMillionsViewHolder(megaMillionsViewHolder, megaMillionsGameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: megaMillions");
                }
                break;
            case "powerball":
                try {
                    PowerballViewHolder powerballViewHolder = (PowerballViewHolder) holder;
                    HashMap<String, String> powerballGameData = GameData.powerball(jsonObject);
                    GameData.setupPowerballViewHolder(powerballViewHolder, powerballGameData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error JSON: powerball");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
