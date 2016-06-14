package ui.android.dialogalchemy.example.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.util.DialogUtils;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;

/**
 * Created by JasonYang on 2016/6/13.
 */
public class SorceryStone implements PhilosopherStone {

    private PhilosopherStone stone;

    private boolean wrapCustomViewInScroll = true;

    private boolean autoDismiss = true;
    private int titleColor = 0;
    private int contentColor = 0;
    private int itemColor = 0;
    private int dividerColor = 0;
    private int backgroundColor = 0;
    private int widgetColor = 0;
    private ColorStateList positiveColor;
    private ColorStateList negativeColor;
    private ColorStateList neutralColor;
    private ColorStateList linkColor;
    private GravityEnum titleGravity = GravityEnum.START;
    private GravityEnum contentGravity = GravityEnum.START;
    private GravityEnum btnStackedGravity = GravityEnum.END;
    private GravityEnum itemsGravity = GravityEnum.START;
    private GravityEnum buttonsGravity = GravityEnum.START;
    private float contentLineSpacingMultiplier = 1.2f;
    private int buttonRippleColor = 0;
    private Theme theme = Theme.LIGHT;

    @DrawableRes
    private int btnSelectorPositive;
    @DrawableRes
    private int btnSelectorNeutral;
    @DrawableRes
    private int btnSelectorNegative;
    @DrawableRes
    private int listSelector;
    @DrawableRes
    private int btnSelectorStacked;

    private SorceryStone() {
    }

    @NonNull
    @Override
    public Material mergeMaterial(@NonNull Context context, @NonNull Material material) {
        if (stone != null) {
            material = stone.mergeMaterial(context, material);
        }
        return material;
    }

    @LayoutRes
    @Override
    public int getLayoutResId() {
        return stone != null ? stone.getLayoutResId() : 0;
    }

    @Override
    public void bindView(Dialog dialog) {
        if (stone != null) {
            stone.bindView(dialog);
        }
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        if (stone != null) {
            stone.onButtonClick(dialogInterface, which);
        }
    }

    public boolean isWrapCustomViewInScroll() {
        return wrapCustomViewInScroll;
    }

    public boolean isAutoDismiss() {
        return autoDismiss;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getContentColor() {
        return contentColor;
    }

    public int getItemColor() {
        return itemColor;
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getWidgetColor() {
        return widgetColor;
    }

    public ColorStateList getPositiveColor() {
        return positiveColor;
    }

    public ColorStateList getNegativeColor() {
        return negativeColor;
    }

    public ColorStateList getNeutralColor() {
        return neutralColor;
    }

    public ColorStateList getLinkColor() {
        return linkColor;
    }

    public GravityEnum getTitleGravity() {
        return titleGravity;
    }

    public GravityEnum getContentGravity() {
        return contentGravity;
    }

    public GravityEnum getBtnStackedGravity() {
        return btnStackedGravity;
    }

    public GravityEnum getItemsGravity() {
        return itemsGravity;
    }

    public GravityEnum getButtonsGravity() {
        return buttonsGravity;
    }

    public float getContentLineSpacingMultiplier() {
        return contentLineSpacingMultiplier;
    }

    public int getButtonRippleColor() {
        return buttonRippleColor;
    }

    public Theme getTheme() {
        return theme;
    }

    public int getBtnSelectorPositive() {
        return btnSelectorPositive;
    }

    public int getBtnSelectorNeutral() {
        return btnSelectorNeutral;
    }

    public int getBtnSelectorNegative() {
        return btnSelectorNegative;
    }

    public int getListSelector() {
        return listSelector;
    }

    public int getBtnSelectorStacked() {
        return btnSelectorStacked;
    }

    public PhilosopherStone getPhilosopherStone() {
        return stone;
    }

    public static class Builder {

        private Context context;
        private SorceryStone stone;

        public Builder(Context context) {
            this.context = context;
            this.stone = new SorceryStone();
        }

        public Builder titleColor(@ColorInt int color) {
            stone.titleColor = color;
            return this;
        }

        public Builder titleColorRes(@ColorRes int colorRes) {
            return titleColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder titleColorAttr(@AttrRes int colorAttr) {
            return titleColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder titleGravity(@NonNull GravityEnum gravity) {
            stone.titleGravity = gravity;
            return this;
        }

        public Builder contentColor(@ColorInt int color) {
            stone.contentColor = color;
            return this;
        }

        public Builder contentColorRes(@ColorRes int colorRes) {
            return contentColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder contentColorAttr(@AttrRes int colorAttr) {
            return contentColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder contentGravity(@NonNull GravityEnum gravity) {
            stone.contentGravity = gravity;
            return this;
        }

        public Builder contentLineSpacing(float multiplier) {
            stone.contentLineSpacingMultiplier = multiplier;
            return this;
        }

        public Builder dividerColor(@ColorInt int color) {
            stone.dividerColor = color;
            return this;
        }

        public Builder dividerColorRes(@ColorRes int colorRes) {
            return dividerColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder dividerColorAttr(@AttrRes int colorAttr) {
            return dividerColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder backgroundColor(@ColorInt int color) {
            stone.backgroundColor = color;
            return this;
        }

        public Builder backgroundColorRes(@ColorRes int colorRes) {
            return backgroundColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder backgroundColorAttr(@AttrRes int colorAttr) {
            return backgroundColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder positiveColor(@ColorInt int color) {
            return positiveColor(DialogUtils.getActionTextStateList(context, color));
        }

        public Builder positiveColorRes(@ColorRes int colorRes) {
            return positiveColor(DialogUtils.getActionTextColorStateList(this.context, colorRes));
        }

        public Builder positiveColorAttr(@AttrRes int colorAttr) {
            return positiveColor(DialogUtils.resolveActionTextColorStateList(this.context, colorAttr, null));
        }

        public Builder positiveColor(@NonNull ColorStateList colorStateList) {
            stone.positiveColor = colorStateList;
            return this;
        }

        public Builder negativeColor(@ColorInt int color) {
            return negativeColor(DialogUtils.getActionTextStateList(context, color));
        }

        public Builder negativeColorRes(@ColorRes int colorRes) {
            return negativeColor(DialogUtils.getActionTextColorStateList(this.context, colorRes));
        }

        public Builder negativeColorAttr(@AttrRes int colorAttr) {
            return negativeColor(DialogUtils.resolveActionTextColorStateList(this.context, colorAttr, null));
        }

        public Builder negativeColor(@NonNull ColorStateList colorStateList) {
            stone.negativeColor = colorStateList;
            return this;
        }

        public Builder neutralColor(@ColorInt int color) {
            return neutralColor(DialogUtils.getActionTextStateList(context, color));
        }

        public Builder neutralColorRes(@ColorRes int colorRes) {
            return neutralColor(DialogUtils.getActionTextColorStateList(this.context, colorRes));
        }

        public Builder neutralColorAttr(@AttrRes int colorAttr) {
            return neutralColor(DialogUtils.resolveActionTextColorStateList(this.context, colorAttr, null));
        }

        public Builder neutralColor(@NonNull ColorStateList colorStateList) {
            stone.neutralColor = colorStateList;
            return this;
        }

        public Builder buttonsGravity(@NonNull GravityEnum gravity) {
            stone.buttonsGravity = gravity;
            return this;
        }

        public Builder linkColor(@ColorInt int color) {
            return linkColor(DialogUtils.getActionTextStateList(context, color));
        }

        public Builder linkColorRes(@ColorRes int colorRes) {
            return linkColor(DialogUtils.getActionTextColorStateList(this.context, colorRes));
        }

        public Builder linkColorAttr(@AttrRes int colorAttr) {
            return linkColor(DialogUtils.resolveActionTextColorStateList(this.context, colorAttr, null));
        }

        public Builder linkColor(@NonNull ColorStateList colorStateList) {
            stone.linkColor = colorStateList;
            return this;
        }

        public Builder listSelector(@DrawableRes int selectorRes) {
            stone.listSelector = selectorRes;
            return this;
        }

        public Builder btnSelectorStacked(@DrawableRes int selectorRes) {
            stone.btnSelectorStacked = selectorRes;
            return this;
        }

        public Builder btnSelector(@DrawableRes int selectorRes) {
            stone.btnSelectorPositive = selectorRes;
            stone.btnSelectorNeutral = selectorRes;
            stone.btnSelectorNegative = selectorRes;
            return this;
        }

        public Builder btnSelector(@DrawableRes int selectorRes, @NonNull DialogAction which) {
            switch (which) {
                default:
                    stone.btnSelectorPositive = selectorRes;
                    break;
                case NEUTRAL:
                    stone.btnSelectorNeutral = selectorRes;
                    break;
                case NEGATIVE:
                    stone.btnSelectorNegative = selectorRes;
                    break;
            }
            return this;
        }

        public Builder btnStackedGravity(@NonNull GravityEnum gravity) {
            stone.btnStackedGravity = gravity;
            return this;
        }

        public Builder buttonRippleColor(@ColorInt int color) {
            stone.buttonRippleColor = color;
            return this;
        }

        public Builder buttonRippleColorRes(@ColorRes int colorRes) {
            return buttonRippleColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder buttonRippleColorAttr(@AttrRes int colorAttr) {
            return buttonRippleColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder itemsColor(@ColorInt int color) {
            stone.itemColor = color;
            return this;
        }

        public Builder itemsColorRes(@ColorRes int colorRes) {
            return itemsColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder itemsColorAttr(@AttrRes int colorAttr) {
            return itemsColor(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder itemsGravity(@NonNull GravityEnum gravity) {
            stone.itemsGravity = gravity;
            return this;
        }

        public Builder widgetColor(@ColorInt int color) {
            stone.widgetColor = color;
            return this;
        }

        public Builder widgetColorRes(@ColorRes int colorRes) {
            return widgetColor(DialogUtils.getColor(this.context, colorRes));
        }

        public Builder widgetColorAttr(@AttrRes int colorAttr) {
            return widgetColorRes(DialogUtils.resolveColor(this.context, colorAttr));
        }

        public Builder theme(@NonNull Theme theme) {
            stone.theme = theme;
            return this;
        }

        public Builder autoDismiss(boolean dismiss) {
            stone.autoDismiss = dismiss;
            return this;
        }

        public Builder wrapCustomViewInScroll(boolean wrapInScrollView) {
            stone.wrapCustomViewInScroll = wrapInScrollView;
            return this;
        }

        public Builder setPhilosopherStone(PhilosopherStone stone) {
            if (stone != null && !(stone instanceof SorceryStone)) {
                this.stone.stone = stone;
            } else {
                this.stone.stone = null;
            }
            return this;
        }

        public SorceryStone build() {
            return stone;
        }
    }
}
