package us.brandonandrews.nclottery.fragments;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import us.brandonandrews.nclottery.R;


public class DateFragmentDialog extends DialogFragment {

    public DateFragmentDialog() {
        // Empty on purpose
    }

    public static DateFragmentDialog newInstance() {
        return new DateFragmentDialog();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_fragment, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);

    }
}
