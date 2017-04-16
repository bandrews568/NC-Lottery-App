package us.brandonandrews.nclottery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.models.MegaMillions;


public class MegaMillionsRecyclerAdapter extends RecyclerView.Adapter<MegaMillionsRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;
        public TextView tvBall1;
        public TextView tvBall2;
        public TextView tvBall3;
        public TextView tvBall4;
        public TextView tvBall5;
        public TextView tvMegaball;
        public TextView tvMultiplier;

        public ViewHolder(View viewItem) {
            super(viewItem);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
            tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
            tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
            tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
            tvBall5 = (TextView) itemView.findViewById(R.id.tvBall5);
            tvMegaball = (TextView) itemView.findViewById(R.id.tvMegaball);
            tvMultiplier = (TextView) itemView.findViewById(R.id.tvMultiplier);
        }
    }

    public List<MegaMillions> megaMillionsList;
    public Context context;

    public MegaMillionsRecyclerAdapter(Context context, List<MegaMillions> megaMillionsList) {
        this.megaMillionsList = megaMillionsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View megaMillionsView = inflater.inflate(R.layout.mega_millions_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(megaMillionsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        MegaMillions megaMillions = megaMillionsList.get(position);

        TextView tvDate = viewHolder.tvDate;
        TextView tvBall1 = viewHolder.tvBall1;
        TextView tvBall2 = viewHolder.tvBall2;
        TextView tvBall3 = viewHolder.tvBall3;
        TextView tvBall4 = viewHolder.tvBall4;
        TextView tvBall5 = viewHolder.tvBall5;
        TextView tvMegaball = viewHolder.tvMegaball;
        TextView tvMultiplier = viewHolder.tvMultiplier;

        tvDate.setText(megaMillions.getDate());
        tvBall1.setText(megaMillions.getBall1());
        tvBall2.setText(megaMillions.getBall2());
        tvBall3.setText(megaMillions.getBall3());
        tvBall4.setText(megaMillions.getBall4());
        tvBall5.setText(megaMillions.getBall5());
        tvMegaball.setText(megaMillions.getMegaball());
        tvMultiplier.setText(megaMillions.getMultiplier());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return megaMillionsList.size();
    }

    private Context getContext() {
        return context;
    }
}
