package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Powerball;


public class PowerballRecyclerAdapter extends RecyclerView.Adapter<PowerballRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;
        public TextView tvBall1;
        public TextView tvBall2;
        public TextView tvBall3;
        public TextView tvBall4;
        public TextView tvBall5;
        public TextView tvPowerball;
        public TextView tvPowerplay;

        public ViewHolder(View viewItem) {
            super(viewItem);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
            tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
            tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
            tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
            tvBall5 = (TextView) itemView.findViewById(R.id.tvBall5);
            tvPowerball = (TextView) itemView.findViewById(R.id.tvPowerball);
            tvPowerplay = (TextView) itemView.findViewById(R.id.tvPowerplay);
        }
    }

    public List<Powerball> powerballList;
    public Context context;

    public PowerballRecyclerAdapter(Context context, List<Powerball> powerballList) {
        this.context = context;
        this.powerballList = powerballList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View powerballView = inflater.inflate(R.layout.powerball_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(powerballView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Powerball powerball = powerballList.get(position);

        TextView tvDate = viewHolder.tvDate;
        TextView tvBall1 = viewHolder.tvBall1;
        TextView tvBall2 = viewHolder.tvBall2;
        TextView tvBall3 = viewHolder.tvBall3;
        TextView tvBall4 = viewHolder.tvBall4;
        TextView tvBall5 = viewHolder.tvBall5;
        TextView tvPowerball = viewHolder.tvPowerball;
        TextView tvPowerplay = viewHolder.tvPowerplay;

        tvDate.setText(powerball.getDate());
        tvBall1.setText(powerball.getBall1());
        tvBall2.setText(powerball.getBall2());
        tvBall3.setText(powerball.getBall3());
        tvBall4.setText(powerball.getBall4());
        tvBall5.setText(powerball.getBall5());
        tvPowerball.setText(powerball.getPowerball());
        tvPowerplay.setText(powerball.getPowerplay());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return powerballList.size();
    }

    private Context getContext() {
        return context;
    }
}
