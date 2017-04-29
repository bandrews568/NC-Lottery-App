package us.brandonandrews.nclottery.viewholders;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import us.brandonandrews.nclottery.R;
import us.brandonandrews.nclottery.fragments.Pick3Fragment;


public class Pick3ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView tvTime;
    private TextView tvDate;
    private TextView tvBall1;
    private TextView tvBall2;
    private TextView tvBall3;
    private TextView tvSum;

    public Pick3ViewHolder(View view) {
        super(view);

        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
        tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
        tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
        tvSum = (TextView) itemView.findViewById(R.id.tvSum);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Pick3Fragment pick3Fragment = new Pick3Fragment();
            activity
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_activity_fragment_placeholder, pick3Fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvBall1() {
        return tvBall1;
    }

    public void setTvBall1(TextView tvBall1) {
        this.tvBall1 = tvBall1;
    }

    public TextView getTvBall2() {
        return tvBall2;
    }

    public void setTvBall2(TextView tvBall2) {
        this.tvBall2 = tvBall2;
    }

    public TextView getTvBall3() {
        return tvBall3;
    }

    public void setTvBall3(TextView tvBall3) {
        this.tvBall3 = tvBall3;
    }

    public TextView getTvSum() {
        return tvSum;
    }

    public void setTvSum(TextView tvSum) {
        this.tvSum = tvSum;
    }
}
