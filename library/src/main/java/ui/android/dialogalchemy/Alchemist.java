package ui.android.dialogalchemy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JasonYang on 2016/5/31.
 */
public class Alchemist extends DialogFragment {

    private static final String OLD_STATE = "old_state";
    private static final String TRUE_NAME = "true_name";

    private static final String MATERIAL = "material";
    private static final String TRANSMUTATION_CIRCLE = "transmutation_circle";

    public static Alchemist createInstance() {
        Alchemist wrapper = new Alchemist();
        return wrapper;
    }

    private String trueName;
    private Material material;
    private TransmutationCircle circle;
    private int orientation = -1;
    private boolean onRotating = false;
    private boolean isInit = false;

    public Alchemist() {
    }

    public void setMaterial(@NonNull Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setTransmutationCircle(@NonNull TransmutationCircle circle) {
        this.circle = circle;
    }

    public TransmutationCircle getTransmutationCircle() {
        return this.circle;
    }

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            isInit = savedInstanceState.getBoolean(OLD_STATE, false);
            trueName = savedInstanceState.getString(TRUE_NAME);
            Map<String, Object> soul = GateOfTruth.getInstance().withdraw(trueName);
            material = (Material) soul.get(MATERIAL);
            circle = (TransmutationCircle) soul.get(TRANSMUTATION_CIRCLE);
        }

        if (TextUtils.isEmpty(trueName)) {
            trueName = "Alchemist_" + System.currentTimeMillis();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final PhilosopherStone stone = material.getPhilosopherStone();
        if (stone != null) {
            material = stone.mergeMaterial(getActivity(), material);
        }
        setCancelable(material.isCancelable());
        TransmutationCircle transmutationCircle = circle;
        Dialog dialog = transmutationCircle.createDialog(getActivity(), material);
        if (dialog != null) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    circle.bindCustomView((Dialog) dialog, material);
                    if (material.getOnShowListener() != null && !isInit) {
                        isInit = true;
                        material.getOnShowListener().onShow(dialog);
                    }
                }
            });

            return dialog;
        }

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onResume() {
        orientation = getActivity().getResources().getConfiguration().orientation;
        onRotating = false;

        super.onResume();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

        if (material.getOnCancelListener() != null) {
            material.getOnCancelListener().onCancel(dialog);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (material.getOnDismissListener() != null && !onRotating) {
            material.getOnDismissListener().onDismiss(dialog);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(OLD_STATE, isInit);

        int newOrientation = getActivity().getResources().getConfiguration().orientation;
        onRotating = orientation != newOrientation;

        if (onRotating) {
            outState.putString(TRUE_NAME, trueName);
            Map<String, Object> soul = new HashMap<String, Object>();
            soul.put(MATERIAL, material);
            soul.put(TRANSMUTATION_CIRCLE, circle);
            GateOfTruth.getInstance().save(trueName, soul);
        }

        super.onSaveInstanceState(outState);
    }
}
