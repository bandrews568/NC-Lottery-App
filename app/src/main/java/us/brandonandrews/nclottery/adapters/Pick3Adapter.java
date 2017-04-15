package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Pick3;

public class Pick3Adapter extends ArrayAdapter<Pick3> {

    private int count;

    public Pick3Adapter(Context context, List<Pick3> data) {
        super(context, 0, data);
        count = data.size();
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pick3 game = super.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pick3_listview, parent, false);
        }

        TextView gameTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView gameDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView ball1 = (TextView) convertView.findViewById(R.id.tvBall1);
        TextView ball2 = (TextView) convertView.findViewById(R.id.tvBall2);
        TextView ball3 = (TextView) convertView.findViewById(R.id.tvBall3);
        TextView sumItUp = (TextView) convertView.findViewById(R.id.tvSum);

        gameTime.setText(game.time);
        gameDate.setText(game.date);
        ball1.setText(game.ball1);
        ball2.setText(game.ball2);
        ball3.setText(game.ball3);
        sumItUp.setText(game.sum);

        return convertView;
    }

    @Override
    public int getCount() {
        return count;
    }
}
