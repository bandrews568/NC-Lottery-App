package us.brandonandrews.nclottery.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Pick4;

public class Pick4RecyclerAdapter extends RecyclerView.Adapter<Pick4RecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTime;
        public TextView tvDate;
        public TextView tvBall1;
        public TextView tvBall2;
        public TextView tvBall3;
        public TextView tvBall4;
        public TextView tvSum;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
            tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
            tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
            tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
            tvSum = (TextView) itemView.findViewById(R.id.tvSum);
        }
    }

    public List<Pick4> pick4List;
    public Context context;

    public Pick4RecyclerAdapter(Context context, List<Pick4> pick4List) {
        this.pick4List = pick4List;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pick4View = inflater.inflate(R.layout.pick4_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(pick4View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Pick4 pick4 = pick4List.get(position);

        TextView tvTime = viewHolder.tvTime;
        TextView tvDate = viewHolder.tvDate;
        TextView tvBall1 = viewHolder.tvBall1;
        TextView tvBall2 = viewHolder.tvBall2;
        TextView tvBall3 = viewHolder.tvBall3;
        TextView tvBall4 = viewHolder.tvBall4;
        TextView tvSum = viewHolder.tvSum;

        tvTime.setText(pick4.getTime());
        tvDate.setText(pick4.getDate());
        tvBall1.setText(pick4.getBall1());
        tvBall2.setText(pick4.getBall2());
        tvBall3.setText(pick4.getBall3());
        tvBall4.setText(pick4.getBall4());
        tvSum.setText(pick4.getSum());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pick4List.size();
    }

    private Context getContext() {
        return context;
    }
}
