package us.brandonandrews.nclottery.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.Cash5;

public class Cash5RecyclerAdapter extends RecyclerView.Adapter<Cash5RecyclerAdapter.ViewHolder> {

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvDate;
            public TextView tvBall1;
            public TextView tvBall2;
            public TextView tvBall3;
            public TextView tvBall4;
            public TextView tvBall5;
            public TextView tvJackpot;

            public ViewHolder(View itemView) {
                super(itemView);

                tvDate = (TextView) itemView.findViewById(R.id.tvDate);
                tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
                tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
                tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
                tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
                tvBall5 = (TextView) itemView.findViewById(R.id.tvBall5);
                tvJackpot = (TextView) itemView.findViewById(R.id.tvJackpot);
            }
        }

        private List<Cash5> cash5List;
        private Context context;

        public Cash5RecyclerAdapter(Context conext, List<Cash5> cash5List) {
            this.cash5List = cash5List;
            this.context = conext;
        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cash5View = inflater.inflate(R.layout.cash5_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(cash5View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cash5 cash5 = cash5List.get(position);

        TextView tvDate = holder.tvDate;
        TextView tvBall1 = holder.tvBall1;
        TextView tvBall2 = holder.tvBall2;
        TextView tvBall3 = holder.tvBall3;
        TextView tvBall4 = holder.tvBall4;
        TextView tvBall5 = holder.tvBall5;
        TextView tvJackpot = holder.tvJackpot;

        tvDate.setText(cash5.getDate());
        tvBall1.setText(cash5.getBall1());
        tvBall2.setText(cash5.getBall2());
        tvBall3.setText(cash5.getBall3());
        tvBall4.setText(cash5.getBall4());
        tvBall5.setText(cash5.getBall5());
        tvJackpot.setText(cash5.getJackpot());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return cash5List.size();
    }

    private Context getContext() {
        return context;
    }
}
