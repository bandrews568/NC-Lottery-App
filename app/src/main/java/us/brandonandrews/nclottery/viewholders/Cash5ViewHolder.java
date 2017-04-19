package us.brandonandrews.nclottery.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import us.brandonandrews.nclottery.R;


public class Cash5ViewHolder extends RecyclerView.ViewHolder {

    private TextView tvDate;
    private TextView tvBall1;
    private TextView tvBall2;
    private TextView tvBall3;
    private TextView tvBall4;
    private TextView tvBall5;
    private TextView tvJackpot;

    public Cash5ViewHolder(View view) {
        super(view);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        tvBall1 = (TextView) itemView.findViewById(R.id.tvBall1);
        tvBall2 = (TextView) itemView.findViewById(R.id.tvBall2);
        tvBall3 = (TextView) itemView.findViewById(R.id.tvBall3);
        tvBall4 = (TextView) itemView.findViewById(R.id.tvBall4);
        tvBall5 = (TextView) itemView.findViewById(R.id.tvBall5);
        tvJackpot = (TextView) itemView.findViewById(R.id.tvJackpot);
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

    public TextView getTvBall4() {
        return tvBall4;
    }

    public void setTvBall4(TextView tvBall4) {
        this.tvBall4 = tvBall4;
    }

    public TextView getTvBall5() {
        return tvBall5;
    }

    public void setTvBall5(TextView tvBall5) {
        this.tvBall5 = tvBall5;
    }

    public TextView getTvJackpot() {
        return tvJackpot;
    }

    public void setTvJackpot(TextView tvJackpot) {
        this.tvJackpot = tvJackpot;
    }
}
