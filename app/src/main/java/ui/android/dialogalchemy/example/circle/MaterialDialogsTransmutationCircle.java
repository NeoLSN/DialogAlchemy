package ui.android.dialogalchemy.example.circle;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.afollestad.materialdialogs.AlertDialogWrapper;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.TransmutationCircle;

/**
 * Created by JasonYang on 2016/6/13.
 */
public class MaterialDialogsTransmutationCircle implements TransmutationCircle {

    @Override
    public Dialog createDialog(@NonNull Context context, @NonNull Material material) {
        final PhilosopherStone stone = material.getPhilosopherStone();

        //manipulate material
        if (stone != null) {
            material = stone.mergeMaterial(context, material);
        }

        //NOT support theme, custom title, inverseBackgroundForced,
        // onItemSelectedListener, single choice with custom adapter

        final AlertDialogWrapper.Builder builder = new AlertDialogWrapper.Builder(context);

        builder.autoDismiss(true);

        if (!TextUtils.isEmpty(material.getTitle())) {
            builder.setTitle(material.getTitle());
        }
        if (!TextUtils.isEmpty(material.getMessage())) {
            builder.setMessage(material.getMessage());
        }
        if (material.getIcon() != 0) {
            builder.setIcon(material.getIcon());
        }
        if (material.getIconAttribute() != 0) {
            builder.setIconAttribute(material.getIconAttribute());
        }

        // setup choice mode
        if (material.isMultiChoice()) {
            String[] items = new String[material.getItems().length];
            for (int i = 0; i < items.length; i++) {
                items[i] = material.getItems()[i].toString();
            }
            builder.setMultiChoiceItems(items, material.getCheckedItems(),
                    material.getOnMultiChoiceClickListener());
        } else if (material.isSingleChoice()) {
            String[] items = new String[material.getItems().length];
            for (int i = 0; i < items.length; i++) {
                items[i] = material.getItems()[i].toString();
            }
            builder.setSingleChoiceItems(items, material.getCheckedItem(),
                    material.getOnClickListener());
        } else if (material.getAdapter() != null) {
            builder.setAdapter(material.getAdapter(), material.getOnClickListener());
        } else if (material.getItems() != null) {
            builder.setItems(material.getItems(), material.getOnClickListener());
        }

        // setup button
        DialogInterface.OnClickListener wrapper = new ButtonListenerWrapper(material, stone);
        if (!TextUtils.isEmpty(material.getPositiveButtonText())) {
            builder.setPositiveButton(material.getPositiveButtonText(), wrapper);
        }
        if (!TextUtils.isEmpty(material.getNegativeButtonText())) {
            builder.setNegativeButton(material.getNegativeButtonText(), wrapper);
        }
        if (!TextUtils.isEmpty(material.getNeutralButtonText())) {
            builder.setNeutralButton(material.getNeutralButtonText(), wrapper);
        }

        // stone
        if (stone != null && stone.getLayoutResId() != 0) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(stone.getLayoutResId(), null);
            builder.setView(view);
        }

        if (material.getOnCancelListener() != null) {
            builder.setOnCancelListener(material.getOnCancelListener());
        }
        if (material.getOnDismissListener() != null) {
            builder.setOnDismissListener(material.getOnDismissListener());
        }
        if (material.getOnKeyListener() != null) {
            builder.setOnKeyListener(material.getOnKeyListener());
        }
        if (material.getOnShowListener() != null) {
            builder.setOnShowListener(material.getOnShowListener());
        }
        builder.setCancelable(material.isCancelable());

        // create
        final Dialog dialog = builder.create();

        // after create
        dialog.setOnShowListener(material.getOnShowListener());
        dialog.setCanceledOnTouchOutside(material.isCanceledOnTouchOutside());

        return dialog;
    }

    @Override
    public void bindCustomView(@NonNull Dialog dialog, @NonNull Material material) {
        PhilosopherStone philosopherStone = material.getPhilosopherStone();
        if (philosopherStone != null) {
            philosopherStone.bindView(dialog);
        }
    }

    private static class ButtonListenerWrapper implements DialogInterface.OnClickListener {

        private Material material;
        private PhilosopherStone philosopherStone;

        ButtonListenerWrapper(Material material, PhilosopherStone stone) {
            this.material = material;
            this.philosopherStone = stone;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_NEGATIVE:
                    if (material.getNegativeButtonListener() != null) {
                        material.getNegativeButtonListener().onClick(dialog, which);
                    }
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    if (material.getNeutralButtonListener() != null) {
                        material.getNeutralButtonListener().onClick(dialog, which);
                    }
                    break;
                case Dialog.BUTTON_POSITIVE:
                    if (material.getPositiveButtonListener() != null) {
                        material.getPositiveButtonListener().onClick(dialog, which);
                    }
                    break;
                default:
            }

            if (philosopherStone != null) {
                philosopherStone.onButtonClick(dialog, which);
            }
        }
    }
}
