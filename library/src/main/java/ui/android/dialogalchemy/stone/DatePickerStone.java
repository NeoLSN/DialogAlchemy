package ui.android.dialogalchemy.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import java.util.Calendar;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.R;

/**
 * Created by jasonyang on 2014/11/27.
 */
public class DatePickerStone implements PhilosopherStone {

    private DatePicker datePicker;
    private boolean showCalendarView;
    private long maxDate = System.currentTimeMillis();
    private int year;
    private int month;
    private int dayOfMonth;
    private OnDatePickedListener listener;

    private DatePickerStone() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_date_picker;
    }

    @NonNull
    @Override
    public Material mergeMaterial(@NonNull Context context, @NonNull Material material) {
        return material;
    }

    @Override
    public void bindView(Dialog dialog) {
        datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);
        datePicker.setCalendarViewShown(showCalendarView);
        datePicker.setMaxDate(maxDate);
        datePicker.init(year, month, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DatePickerStone.this.year = year;
                DatePickerStone.this.month = monthOfYear;
                DatePickerStone.this.dayOfMonth = dayOfMonth;
            }
        });
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                // Accept the selected content
                if (listener != null) {
                    Calendar c = Calendar.getInstance();
                    c.set(datePicker.getYear(), datePicker.getMonth(),
                            datePicker.getDayOfMonth());
                    long time = c.getTimeInMillis();
                    long current = System.currentTimeMillis();
                    if (current >= time) {
                        listener.onAccepted(year, month, dayOfMonth);
                    } else {
                        c.setTimeInMillis(current);
                        listener.onAccepted(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                                c.get(Calendar.DAY_OF_MONTH));
                    }
                }
                break;
        }
    }

    public void setListener(OnDatePickedListener listener) {
        this.listener = listener;
    }

    public static class Builder {

        private DatePickerStone custom;

        public Builder() {
            custom = new DatePickerStone();
        }

        public Builder setShowCalendarView(boolean showCalendarView) {
            custom.showCalendarView = showCalendarView;
            return this;
        }

        public Builder setMaxDate(long maxDate) {
            if (maxDate > 0) {
                custom.maxDate = maxDate;
            }
            return this;
        }

        public Builder updateDate(int year, int month, int dayOfMonth) {
            custom.year = year;
            custom.month = month;
            custom.dayOfMonth = dayOfMonth;
            return this;
        }

        public Builder setOnDatePickedListener(OnDatePickedListener listener) {
            custom.listener = listener;
            return this;
        }

        public DatePickerStone build() {
            return custom;
        }
    }

    public interface OnDatePickedListener {

        void onAccepted(int year, int month, int dayOfMonth);
    }
}
