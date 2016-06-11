package ui.android.dialogalchemy.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.R;

/**
 * Created by jasonyang on 2014/11/27.
 */
public class TimePickerStone implements PhilosopherStone {

    private TimePicker timePicker;
    private Integer hour;
    private Integer minute;
    private OnTimePickedListener listener;

    private TimePickerStone() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_time_picker;
    }

    @NonNull
    @Override
    public Material mergeMaterial(@NonNull Context context, @NonNull Material material) {
        return material;
    }

    @Override
    public void bindView(Dialog dialog) {
        timePicker = (TimePicker) dialog.findViewById(R.id.time_picker);
        if (hour != null) {
            timePicker.setCurrentHour(hour);
        }
        if (minute != null) {
            timePicker.setCurrentMinute(minute);
        }

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                TimePickerStone.this.hour = hourOfDay;
                TimePickerStone.this.minute = minute;
            }
        });
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                // Accept the selected content
                if (listener != null) {
                    listener.onAccepted(timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute());
                }
                break;
        }
    }

    public void setListener(OnTimePickedListener listener) {
        this.listener = listener;
    }

    public static class Builder {

        private TimePickerStone custom;

        public Builder() {
            custom = new TimePickerStone();
        }

        public Builder setHour(int hour) {
            custom.hour = hour;
            return this;
        }

        public Builder setMinute(int minute) {
            custom.minute = minute;
            return this;
        }

        public Builder setOnTimePickedListener(OnTimePickedListener listener) {
            custom.listener = listener;
            return this;
        }

        public TimePickerStone build() {
            return custom;
        }
    }

    public interface OnTimePickedListener {

        void onAccepted(int hour, int minute);
    }
}
