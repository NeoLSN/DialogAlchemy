package ui.android.dialogalchemy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by JasonYang on 2016/6/1.
 */
public final class Material {

    @IntDef({Dialog.BUTTON_POSITIVE, Dialog.BUTTON_NEGATIVE, Dialog.BUTTON_NEUTRAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonType {
    }

    private int theme = 0;

    private CharSequence title;
    private CharSequence message;
    private int icon = 0;
    private int iconAttrId = 0;

    private int customTitle = 0;

    private CharSequence positiveButtonText;
    private DialogInterface.OnClickListener positiveButtonListener;
    private CharSequence negativeButtonText;
    private DialogInterface.OnClickListener negativeButtonListener;
    private CharSequence neutralButtonText;
    private DialogInterface.OnClickListener neutralButtonListener;

    private boolean useInverseBackground = false;
    private boolean cancelable = true;
    private boolean canceledOnTouchOutside = true;

    private DialogInterface.OnCancelListener onCancelListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnShowListener onShowListener;
    private DialogInterface.OnKeyListener onKeyListener;

    private ListAdapter adapter;
    private CharSequence[] items;
    private int checkedItem = -1;
    private boolean[] checkedItems;
    private DialogInterface.OnClickListener onClickListener;
    private DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;
    private boolean isMultiChoice;
    private boolean isSingleChoice;

    private PhilosopherStone philosopherStone;

    public int getTheme() {
        return theme;
    }

    public CharSequence getTitle() {
        return title;
    }

    public int getCustomTitle() {
        return customTitle;
    }

    public CharSequence getMessage() {
        return message;
    }

    public int getIcon() {
        return icon;
    }

    public int getIconAttribute() {
        return iconAttrId;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public boolean isUseInverseBackground() {
        return useInverseBackground;
    }

    public boolean isCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    public CharSequence getPositiveButtonText() {
        return positiveButtonText;
    }

    public DialogInterface.OnClickListener getPositiveButtonListener() {
        return positiveButtonListener;
    }

    public CharSequence getNegativeButtonText() {
        return negativeButtonText;
    }

    public DialogInterface.OnClickListener getNegativeButtonListener() {
        return negativeButtonListener;
    }

    public CharSequence getNeutralButtonText() {
        return neutralButtonText;
    }

    public DialogInterface.OnClickListener getNeutralButtonListener() {
        return neutralButtonListener;
    }

    public DialogInterface.OnCancelListener getOnCancelListener() {
        return onCancelListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public DialogInterface.OnShowListener getOnShowListener() {
        return onShowListener;
    }

    public DialogInterface.OnKeyListener getOnKeyListener() {
        return onKeyListener;
    }

    public ListAdapter getAdapter() {
        return adapter;
    }

    public CharSequence[] getItems() {
        return items;
    }

    public int getCheckedItem() {
        return checkedItem;
    }

    public boolean[] getCheckedItems() {
        return checkedItems;
    }

    public DialogInterface.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public DialogInterface.OnMultiChoiceClickListener getOnMultiChoiceClickListener() {
        return onMultiChoiceClickListener;
    }

    public AdapterView.OnItemSelectedListener getOnItemSelectedListener() {
        return onItemSelectedListener;
    }

    public boolean isMultiChoice() {
        return isMultiChoice;
    }

    public boolean isSingleChoice() {
        return isSingleChoice;
    }

    public PhilosopherStone getPhilosopherStone() {
        return philosopherStone;
    }

    public Builder rebuild(@NonNull Context context) {
        Material newMaterial = new Material();
        newMaterial.theme = this.theme;

        newMaterial.title = this.title;
        newMaterial.message = this.message;
        newMaterial.icon = this.icon;
        newMaterial.iconAttrId = this.iconAttrId;

        newMaterial.customTitle = this.customTitle;

        newMaterial.positiveButtonText = this.positiveButtonText;
        newMaterial.positiveButtonListener = this.positiveButtonListener;
        newMaterial.negativeButtonText = this.negativeButtonText;
        newMaterial.negativeButtonListener = this.negativeButtonListener;
        newMaterial.neutralButtonText = this.neutralButtonText;
        newMaterial.neutralButtonListener = this.neutralButtonListener;

        newMaterial.useInverseBackground = this.useInverseBackground;
        newMaterial.cancelable = this.cancelable;
        newMaterial.canceledOnTouchOutside = this.canceledOnTouchOutside;

        newMaterial.onCancelListener = this.onCancelListener;
        newMaterial.onDismissListener = this.onDismissListener;
        newMaterial.onShowListener = this.onShowListener;
        newMaterial.onKeyListener = this.onKeyListener;

        newMaterial.adapter = this.adapter;
        newMaterial.items = this.items;
        newMaterial.checkedItem = this.checkedItem;
        newMaterial.checkedItems = this.checkedItems;
        newMaterial.onClickListener = this.onClickListener;
        newMaterial.onMultiChoiceClickListener = this.onMultiChoiceClickListener;
        newMaterial.onItemSelectedListener = this.onItemSelectedListener;
        newMaterial.isMultiChoice = this.isMultiChoice;
        newMaterial.isSingleChoice = this.isSingleChoice;

        newMaterial.philosopherStone = this.philosopherStone;
        return new Builder(context, newMaterial);
    }

    public static class Builder {

        private Context context;
        private Material material;

        Builder(Context context, Material material) {
            this.context = context;
            this.material = material;
        }

        public Builder(Context context) {
            this.context = context;
            this.material = new Material();
        }

        public Builder(Context context, @StyleRes int theme) {
            this(context);
            material.theme = theme;
        }

        public Builder setTitle(CharSequence title) {
            material.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int titleResId) {
            material.title = context.getString(titleResId);
            return this;
        }

        public Builder setTitle(@StringRes int titleResId, Object... args) {
            material.title = context.getString(titleResId, args);
            return this;
        }

        public Builder setCustomTitle(@LayoutRes int customTitle) {
            material.customTitle = customTitle;
            return this;
        }

        public Builder setMessage(CharSequence msg) {
            material.message = msg;
            return this;
        }

        public Builder setMessage(@StringRes int msgResId) {
            material.message = context.getString(msgResId);
            return this;
        }

        public Builder setMessage(@StringRes int msgResId, Object... args) {
            material.message = context.getString(msgResId, args);
            return this;
        }

        public Builder setIcon(@DrawableRes int iconResId) {
            material.icon = iconResId;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            material.iconAttrId = attrId;
            return this;
        }

        public Builder setInverseBackgroundForced(boolean useInverseBackground) {
            material.useInverseBackground = useInverseBackground;
            return this;
        }

        public Builder setButton(@ButtonType int whichButton, @StringRes int titleResId,
                DialogInterface.OnClickListener listener) {
            String title = context.getString(titleResId);
            return setButton(whichButton, title, listener);
        }

        public Builder setButton(@ButtonType int whichButton, @NonNull CharSequence title,
                DialogInterface.OnClickListener listener) {
            switch (whichButton) {
                case Dialog.BUTTON_POSITIVE:
                    material.positiveButtonText = title;
                    material.positiveButtonListener = listener;
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    material.negativeButtonText = title;
                    material.negativeButtonListener = listener;
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    material.neutralButtonText = title;
                    material.neutralButtonListener = listener;
                    break;
            }
            return this;
        }

        public Builder removeButton(@ButtonType int whichButton) {
            switch (whichButton) {
                case Dialog.BUTTON_POSITIVE:
                    material.positiveButtonText = null;
                    material.positiveButtonListener = null;
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    material.negativeButtonText = null;
                    material.negativeButtonListener = null;
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    material.neutralButtonText = null;
                    material.neutralButtonListener = null;
                    break;
            }
            return this;
        }

        public Builder changeButtonListener(@ButtonType int whichButton,
                DialogInterface.OnClickListener listener) {
            switch (whichButton) {
                case Dialog.BUTTON_POSITIVE:
                    if (!TextUtils.isEmpty(material.positiveButtonText)) {
                        material.positiveButtonListener = listener;
                    }
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    if (!TextUtils.isEmpty(material.negativeButtonText)) {
                        material.negativeButtonListener = listener;
                    }
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    if (!TextUtils.isEmpty(material.neutralButtonText)) {
                        material.neutralButtonListener = listener;
                    }
                    break;
            }
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_NEGATIVE, textId, listener);
        }

        public Builder setNegativeButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_NEGATIVE, text, listener);
        }

        public Builder setNeutralButton(@StringRes int textId,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_NEUTRAL, textId, listener);
        }

        public Builder setNeutralButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_NEUTRAL, text, listener);
        }

        public Builder setPositiveButton(@StringRes int textId,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_POSITIVE, textId, listener);
        }

        public Builder setPositiveButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            return setButton(Dialog.BUTTON_POSITIVE, text, listener);
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
            material.onCancelListener = listener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener listener) {
            material.onDismissListener = listener;
            return this;
        }

        public Builder setOnShowListener(DialogInterface.OnShowListener listener) {
            material.onShowListener = listener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            material.onKeyListener = onKeyListener;
            return this;
        }

        public Builder setAdapter(@NonNull ListAdapter adapter,
                @NonNull DialogInterface.OnClickListener listener) {
            material.adapter = adapter;
            material.onClickListener = listener;
            return this;
        }

        public Builder setItems(@NonNull CharSequence[] items,
                @NonNull DialogInterface.OnClickListener listener) {
            material.items = items;
            material.onClickListener = listener;
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId,
                @NonNull DialogInterface.OnClickListener listener) {
            material.items = context.getResources().getTextArray(itemsId);
            material.onClickListener = listener;
            return this;
        }

        public Builder setSingleChoiceItems(@NonNull ListAdapter adapter, int checkedItem,
                @NonNull DialogInterface.OnClickListener listener) {
            material.adapter = adapter;
            material.checkedItem = checkedItem;
            material.onClickListener = listener;
            material.isSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@NonNull CharSequence[] items, int checkedItem,
                @NonNull DialogInterface.OnClickListener listener) {
            material.items = items;
            material.checkedItem = checkedItem;
            material.onClickListener = listener;
            material.isSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem,
                @NonNull DialogInterface.OnClickListener listener) {
            material.items = context.getResources().getTextArray(itemsId);
            material.checkedItem = checkedItem;
            material.onClickListener = listener;
            material.isSingleChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(@NonNull CharSequence[] items, boolean[] checkedItems,
                @NonNull DialogInterface.OnMultiChoiceClickListener listener) {
            material.items = items;
            material.checkedItems = checkedItems;
            material.onMultiChoiceClickListener = listener;
            material.isMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems,
                @NonNull DialogInterface.OnMultiChoiceClickListener listener) {
            material.items = context.getResources().getTextArray(itemsId);
            material.checkedItems = checkedItems;
            material.onMultiChoiceClickListener = listener;
            material.isMultiChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            material.onItemSelectedListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            material.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            material.canceledOnTouchOutside = cancel;
            return this;
        }

        public Builder setPhilosopherStone(PhilosopherStone philosopherStone) {
            material.philosopherStone = philosopherStone;
            return this;
        }

        public Material build() {
            return material;
        }
    }
}
