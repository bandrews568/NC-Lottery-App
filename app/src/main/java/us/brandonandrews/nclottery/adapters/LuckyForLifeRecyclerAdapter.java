package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.LuckyForLife;


public class LuckyForLifeRecyclerAdapter extends RecyclerView.Adapter<LuckyForLifeRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;
        public TextView tvBall1;
        public TextView tvBall2;
        public TextView tvBall3;
        public TextView tvBall4;
        public TextView tvBall5;
        public TextView tvBall6;

        public ViewHolder(View viewItem) {
            super(viewItem);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
            tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
            tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
            tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
            tvBall5 = (TextView) itemView.findViewById(R.id.tvBall5);
            tvBall6 = (TextView) itemView.findViewById(R.id.tvBall6);
        }
    }

    public List<LuckyForLife> luckyForLifeList;
    public Context context;

    public LuckyForLifeRecyclerAdapter(Context context, List<LuckyForLife> luckyForLifeList) {
        this.luckyForLifeList = luckyForLifeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View luckyForLifeView = inflater.inflate(R.layout.lucky_for_life_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(luckyForLifeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        LuckyForLife luckyForLife = luckyForLifeList.get(position);

        TextView tvDate = viewHolder.tvDate;
        TextView tvBall1 = viewHolder.tvBall1;
        TextView tvBall2 = viewHolder.tvBall2;
        TextView tvBall3 = viewHolder.tvBall3;
        TextView tvBall4 = viewHolder.tvBall4;
        TextView tvBall5 = viewHolder.tvBall5;
        TextView tvBall6 = viewHolder.tvBall6;

        tvDate.setText(luckyForLife.getDate());
        tvBall1.setText(luckyForLife.getBall1());
        tvBall2.setText(luckyForLife.getBall2());
        tvBall3.setText(luckyForLife.getBall3());
        tvBall4.setText(luckyForLife.getBall4());
        tvBall5.setText(luckyForLife.getBall5());
        tvBall6.setText(luckyForLife.getBall6());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return luckyForLifeList.size();
    }

    private Context getContext() {
        return context;
    }
}
