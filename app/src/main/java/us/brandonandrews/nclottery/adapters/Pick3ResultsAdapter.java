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

public class Pick3ResultsAdapter extends ArrayAdapter<Pick3> {

    public Pick3ResultsAdapter(Context context, List<Pick3> data) {
        super(context, 0, data);
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pick3 game = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pick3_listview, parent, false);

        }
        TextView gameTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView gameDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView ball1 = (TextView) convertView.findViewById(R.id.tvBall1);
        TextView ball2 = (TextView) convertView.findViewById(R.id.tvBall2);
        TextView ball3 = (TextView) convertView.findViewById(R.id.tvBall3);

        gameTime.setText(game.date);
        gameDate.setText(game.time);
        ball1.setText(game.ball1);
        ball2.setText(game.ball2);
        ball3.setText(game.ball3);

        return convertView;
    }
}
