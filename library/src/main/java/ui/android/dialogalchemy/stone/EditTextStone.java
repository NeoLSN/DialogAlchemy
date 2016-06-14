package ui.android.dialogalchemy.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.R;

/**
 * Created by JasonYang on 2016/6/1.
 */
public class EditTextStone implements PhilosopherStone {

    private EditText editText;
    private CharSequence text;
    private CharSequence hint;
    private Integer textColor;
    private Integer hintColor;
    private Integer inputType;
    private InputFilter[] inputFilters;
    private OnTextAcceptedListener listener;

    private EditTextStone() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_input_field;
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
        editText = (EditText) dialog.findViewById(R.id.input_field);
        editText.requestFocus();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager) dialog.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                text = s;
            }
        });

        editText.setText(text);
        editText.setHint(hint);

        if (textColor != null) {
            editText.setTextColor(textColor);
        }
        if (hintColor != null) {
            editText.setHintTextColor(hintColor);
        }
        if (inputType != null) {
            editText.setInputType(inputType);
        }
        if (inputFilters != null) {
            editText.setFilters(inputFilters);
        }
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                if (listener != null) {
                    listener.onAccepted(editText.getText().toString());
                }
                break;
        }
    }

    public void setListener(OnTextAcceptedListener listener) {
        this.listener = listener;
    }

    public static class Builder {

        private EditTextStone custom;

        public Builder() {
            custom = new EditTextStone();
        }

        public Builder setText(CharSequence text) {
            custom.text = text;
            return this;
        }

        public Builder setHint(CharSequence hint) {
            custom.hint = hint;
            return this;
        }

        public Builder setTextColor(@ColorInt int color) {
            custom.textColor = color;
            return this;
        }

        public Builder setHintColor(@ColorInt int hintColor) {
            custom.hintColor = hintColor;
            return this;
        }

        public Builder setInputType(int type) {
            custom.inputType = type;
            return this;
        }

        public Builder setFilters(InputFilter... inputFilters) {
            if (inputFilters != null) {
                custom.inputFilters = inputFilters;
            }
            return this;
        }

        public Builder setOnTextAcceptedListener(OnTextAcceptedListener listener) {
            custom.listener = listener;
            return this;
        }

        public EditTextStone build() {
            return custom;
        }
    }

    public interface OnTextAcceptedListener {

        void onAccepted(String text);
    }
}
