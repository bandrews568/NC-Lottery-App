package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Pick3;


public class Pick3RecyclerAdapter extends RecyclerView.Adapter<Pick3RecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTime;
        public TextView tvDate;
        public TextView tvBall1;
        public TextView tvBall2;
        public TextView tvBall3;
        public TextView tvSum;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
            tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
            tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
            tvSum = (TextView) itemView.findViewById(R.id.tvSum);
        }
    }

    public List<Pick3> pick3List;
    public Context context;

    public Pick3RecyclerAdapter(Context context, List<Pick3> pick3List) {
        this.context = context;
        this.pick3List = pick3List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pick3View = inflater.inflate(R.layout.pick3_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(pick3View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Pick3 pick3 = pick3List.get(position);

        TextView tvTime = viewHolder.tvTime;
        TextView tvDate = viewHolder.tvDate;
        TextView tvBall1 = viewHolder.tvBall1;
        TextView tvBall2 = viewHolder.tvBall2;
        TextView tvBall3 = viewHolder.tvBall3;
        TextView tvSum = viewHolder.tvSum;

        tvTime.setText(pick3.getTime());
        tvDate.setText(pick3.getDate());
        tvBall1.setText(pick3.getBall1());
        tvBall2.setText(pick3.getBall2());
        tvBall3.setText(pick3.getBall3());
        tvSum.setText(pick3.getSum());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return pick3List.size();
    }

    private Context getContext() {
        return context;
    }
}
