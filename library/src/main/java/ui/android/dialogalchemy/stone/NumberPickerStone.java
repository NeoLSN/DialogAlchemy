package ui.android.dialogalchemy.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.NumberPicker;
import android.widget.TextView;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.R;

/**
 * Created by jasonyang on 2014/11/10.
 */
public class NumberPickerStone implements PhilosopherStone {

    private NumberPicker numberPicker;
    private TextView textView;
    private Integer maxValue;
    private Integer minValue;
    private Integer currentValue;
    private boolean canUseKeyBoard = false;
    private CharSequence description;
    private OnNumberPickedListener listener;

    private NumberPickerStone() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_number_picker;
    }

    @NonNull
    @Override
    public Material mergeMaterial(@NonNull Context context, @NonNull Material material) {
        if (TextUtils.isEmpty(material.getPositiveButtonText())) {
            Material.Builder builder = material.rebuild(context);
            builder.setPositiveButton(android.R.string.ok, null);
            material = builder.build();
        }
        return material;
    }

    @Override
    public void bindView(Dialog dialog) {
        textView = (TextView) dialog.findViewById(R.id.description);
        numberPicker = (NumberPicker) dialog.findViewById(R.id.number_picker);

        textView.setText(description);

        numberPicker.setMaxValue(maxValue != null ? maxValue : 24);
        numberPicker.setMinValue(minValue != null ? minValue : 1);
        numberPicker.setOnLongPressUpdateInterval(100);
        numberPicker.setWrapSelectorWheel(false);
        if (currentValue != null) {
            numberPicker.setValue(currentValue);
        }
        if (!canUseKeyBoard) {
            numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        }
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                if (listener != null) {
                    listener.onAccepted(numberPicker.getValue());
                }
                break;
        }
    }

    public void setListener(OnNumberPickedListener listener) {
        this.listener = listener;
    }

    public static class Builder {

        private Context context;
        private NumberPickerStone custom;

        public Builder(Context context) {
            this.context = context;
            custom = new NumberPickerStone();
        }

        public Builder setMaxValue(int maxValue) {
            if (maxValue > 0) {
                custom.maxValue = maxValue;
            }
            return this;
        }

        public Builder setMinValue(int minValue) {
            if (minValue >= 0) {
                custom.minValue = minValue;
            }
            return this;
        }

        public Builder setCurrentValue(Integer currentValue) {
            custom.currentValue = currentValue;
            return this;
        }

        public Builder setOnNumberPickedListener(OnNumberPickedListener listener) {
            custom.listener = listener;
            return this;
        }

        public Builder setDescription(String description) {
            custom.description = description;
            return this;
        }

        public Builder setDescription(@StringRes int descriptionRes) {
            custom.description = context.getString(descriptionRes);
            return this;
        }

        public Builder setCanUseKeyBoard(boolean enable) {
            custom.canUseKeyBoard = enable;
            return this;
        }

        public NumberPickerStone build() {
            return custom;
        }
    }

    public interface OnNumberPickedListener {

        void onAccepted(int number);
    }
}
