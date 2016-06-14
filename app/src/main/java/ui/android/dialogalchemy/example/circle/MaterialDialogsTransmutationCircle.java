package ui.android.dialogalchemy.example.circle;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.TransmutationCircle;
import ui.android.dialogalchemy.example.stone.SorceryStone;

/**
 * Created by JasonYang on 2016/6/13.
 */
public class MaterialDialogsTransmutationCircle implements TransmutationCircle {
    @Override
    public Dialog createDialog(@NonNull Context context, @NonNull Material material) {

        final PhilosopherStone stone = material.getPhilosopherStone();

        final MaterialDialog.Builder builder = new MaterialDialog.Builder(context);

        builder.autoDismiss(true);

        if (!TextUtils.isEmpty(material.getTitle())) {
            builder.title(material.getTitle());
        }
        if (!TextUtils.isEmpty(material.getMessage())) {
            builder.content(material.getMessage());
        }
        if (material.getIcon() != 0) {
            builder.iconRes(material.getIcon());
        }
        if (material.getIconAttribute() != 0) {
            builder.iconAttr(material.getIconAttribute());
        }

        // setup choice mode
        if (material.isMultiChoice()) {
            final DialogInterface.OnMultiChoiceClickListener listener =
                    material.getOnMultiChoiceClickListener();
            final boolean[] checkedItems = material.getCheckedItems();
            builder.items(material.getItems());
            Integer selectedIndicesArr[] = null;
            if (checkedItems != null) {
                ArrayList<Integer> selectedIndices = new ArrayList<Integer>();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        selectedIndices.add(i);
                    }
                }
                selectedIndicesArr = selectedIndices.toArray(new Integer[selectedIndices.size()]);
            }

            builder.itemsCallbackMultiChoice(selectedIndicesArr,
                    new MaterialDialog.ListCallbackMultiChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, Integer[] which,
                                CharSequence[] text) {
                            List<Integer> whichList = Arrays.asList(which);
                            if (checkedItems != null) {
                                for (int i = 0; i < checkedItems.length; i++) {
                                    boolean oldChecked = checkedItems[i];
                                    checkedItems[i] = whichList.contains(i);
                                    if (listener != null && oldChecked != checkedItems[i]) {
                                        listener.onClick(dialog, i, checkedItems[i]);
                                    }
                                }
                            }
                            return true;
                        }
                    });
        } else if (material.isSingleChoice()) {
            final DialogInterface.OnClickListener listener = material.getOnClickListener();
            builder.items(material.getItems());
            builder.itemsCallbackSingleChoice(material.getCheckedItem(),
                    new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                                CharSequence text) {
                            if (listener != null) {
                                listener.onClick(dialog, which);
                            }
                            return true;
                        }
                    });
        } else if (material.getAdapter() != null) {
            final DialogInterface.OnClickListener listener = material.getOnClickListener();
            builder.adapter(material.getAdapter(), new MaterialDialog.ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View itemView, int which,
                        CharSequence text) {
                    if (listener != null) {
                        listener.onClick(dialog, which);
                    }
                }
            });
        } else if (material.getItems() != null) {
            builder.items(material.getItems());
            final DialogInterface.OnClickListener listener = material.getOnClickListener();
            builder.itemsCallback(new MaterialDialog.ListCallback() {
                @Override
                public void onSelection(MaterialDialog dialog, View itemView, int which,
                        CharSequence text) {
                    if (listener != null) {
                        listener.onClick(dialog, which);
                    }
                }
            });
        }

        // setup button
        ButtonListenerWrapper wrapper = new ButtonListenerWrapper(material, stone);
        builder.onAny(wrapper);
        if (!TextUtils.isEmpty(material.getPositiveButtonText())) {
            builder.positiveText(material.getPositiveButtonText());
        }
        if (!TextUtils.isEmpty(material.getNegativeButtonText())) {
            builder.negativeText(material.getNegativeButtonText());
        }
        if (!TextUtils.isEmpty(material.getNeutralButtonText())) {
            builder.neutralText(material.getNeutralButtonText());
        }

        if (material.getOnCancelListener() != null) {
            builder.cancelListener(material.getOnCancelListener());
        }
        if (material.getOnDismissListener() != null) {
            builder.dismissListener(material.getOnDismissListener());
        }
        if (material.getOnKeyListener() != null) {
            builder.keyListener(material.getOnKeyListener());
        }
        if (material.getOnShowListener() != null) {
            builder.showListener(material.getOnShowListener());
        }
        builder.cancelable(material.isCancelable());
        builder.canceledOnTouchOutside(material.isCanceledOnTouchOutside());

        // stone
        if (stone != null) {
            if (stone instanceof SorceryStone) {
                SorceryStone sorceryStone = (SorceryStone) stone;
                increasePower(builder, sorceryStone);
            } else if (stone.getLayoutResId() != 0) {
                builder.customView(stone.getLayoutResId(), true);
            }
        }

        return builder.build();
    }

    @Override
    public void bindCustomView(@NonNull Dialog dialog, @NonNull Material material) {
        PhilosopherStone philosopherStone = material.getPhilosopherStone();
        if (philosopherStone != null) {
            philosopherStone.bindView(dialog);
        }
    }

    private void increasePower(@NonNull MaterialDialog.Builder builder, @NonNull SorceryStone stone) {
        builder.autoDismiss(stone.isAutoDismiss());
        if (stone.getTitleColor() != 0) {
            builder.titleColor(stone.getTitleColor());
        }
        if (stone.getContentColor() != 0) {
            builder.contentColor(stone.getContentColor());
        }
        if (stone.getItemColor() != 0) {
            builder.itemsColor(stone.getItemColor());
        }
        if (stone.getDividerColor() != 0) {
            builder.dividerColor(stone.getDividerColor());
        }
        if (stone.getBackgroundColor() != 0) {
            builder.backgroundColor(stone.getBackgroundColor());
        }
        if (stone.getWidgetColor() != 0) {
            builder.widgetColor(stone.getWidgetColor());
        }
        if (stone.getPositiveColor() != null) {
            builder.positiveColor(stone.getPositiveColor());
        }
        if (stone.getNegativeColor() != null) {
            builder.negativeColor(stone.getNegativeColor());
        }
        if (stone.getNeutralColor() != null) {
            builder.neutralColor(stone.getNeutralColor());
        }
        if (stone.getLinkColor() != null) {
            builder.linkColor(stone.getLinkColor());
        }
        builder.titleGravity(stone.getTitleGravity());
        builder.contentGravity(stone.getContentGravity());
        builder.btnStackedGravity(stone.getBtnStackedGravity());
        builder.itemsGravity(stone.getItemsGravity());
        builder.buttonsGravity(stone.getButtonsGravity());
        builder.contentLineSpacing(stone.getContentLineSpacingMultiplier());
        if (stone.getButtonRippleColor() != 0) {
            builder.buttonRippleColor(stone.getButtonRippleColor());
        }
        builder.theme(stone.getTheme());
        if (stone.getBtnSelectorPositive() != 0) {
            builder.btnSelector(stone.getBtnSelectorPositive(), DialogAction.POSITIVE);
        }
        if (stone.getBtnSelectorNeutral() != 0) {
            builder.btnSelector(stone.getBtnSelectorNeutral(), DialogAction.NEUTRAL);
        }
        if (stone.getBtnSelectorNegative() != 0) {
            builder.btnSelector(stone.getBtnSelectorNegative(), DialogAction.NEGATIVE);
        }
        if (stone.getListSelector() != 0) {
            builder.listSelector(stone.getListSelector());
        }
        if (stone.getBtnSelectorStacked() != 0) {
            builder.btnSelectorStacked(stone.getBtnSelectorStacked());
        }
        if (stone.getLayoutResId() != 0) {
            builder.customView(stone.getLayoutResId(), stone.isWrapCustomViewInScroll());
        }
    }

    private static class ButtonListenerWrapper implements MaterialDialog.SingleButtonCallback {

        private Material material;
        private PhilosopherStone philosopherStone;

        ButtonListenerWrapper(Material material, PhilosopherStone stone) {
            this.material = material;
            this.philosopherStone = stone;
        }

        @Override
        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            switch (which) {
                case NEGATIVE:
                    if (material.getNegativeButtonListener() != null) {
                        material.getNegativeButtonListener().onClick(dialog, Dialog.BUTTON_NEGATIVE);
                    }
                    if (philosopherStone != null) {
                        philosopherStone.onButtonClick(dialog, Dialog.BUTTON_NEGATIVE);
                    }
                    break;
                case NEUTRAL:
                    if (material.getNeutralButtonListener() != null) {
                        material.getNeutralButtonListener().onClick(dialog, Dialog.BUTTON_NEUTRAL);
                    }
                    if (philosopherStone != null) {
                        philosopherStone.onButtonClick(dialog, Dialog.BUTTON_NEUTRAL);
                    }
                    break;
                case POSITIVE:
                    if (material.getPositiveButtonListener() != null) {
                        material.getPositiveButtonListener().onClick(dialog, Dialog.BUTTON_POSITIVE);
                    }
                    if (philosopherStone != null) {
                        philosopherStone.onButtonClick(dialog, Dialog.BUTTON_POSITIVE);
                    }
                    break;
                default:
            }
        }
    }
}
