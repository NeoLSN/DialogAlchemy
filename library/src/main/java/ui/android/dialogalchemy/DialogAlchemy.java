package ui.android.dialogalchemy;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import ui.android.dialogalchemy.circle.MetalTransmutationCircle;

/**
 * Created by JasonYang on 2016/6/2.
 */
public class DialogAlchemy {

    private static final TransmutationCircle DEFAULT_CIRCLE = new MetalTransmutationCircle();
    private static TransmutationCircle CUSTOM_CIRCLE = null;

    private DialogAlchemy() {
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model) {
        TransmutationCircle circle = CUSTOM_CIRCLE != null ? CUSTOM_CIRCLE : DEFAULT_CIRCLE;
        return show(fragmentManager, model, circle);
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, String tag) {
        TransmutationCircle circle = CUSTOM_CIRCLE != null ? CUSTOM_CIRCLE : DEFAULT_CIRCLE;
        return show(fragmentManager, model, circle, tag);
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, @NonNull TransmutationCircle circle) {
        return show(fragmentManager, model, circle, null);
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, @NonNull TransmutationCircle circle, String tag) {
        Alchemist alchemist = assignToAlchemist(model, circle);
        tag = TextUtils.isEmpty(tag) ? String.valueOf(System.currentTimeMillis()) : tag;
        alchemist.show(fragmentManager, tag);
        return alchemist;
    }

    public static Alchemist showDark(@NonNull FragmentManager fragmentManager,
            @NonNull Material model) {
        TransmutationCircle circle = CUSTOM_CIRCLE != null ? CUSTOM_CIRCLE : DEFAULT_CIRCLE;
        return showDark(fragmentManager, model, circle);
    }

    public static Alchemist showDark(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, String tag) {
        TransmutationCircle circle = CUSTOM_CIRCLE != null ? CUSTOM_CIRCLE : DEFAULT_CIRCLE;
        return showDark(fragmentManager, model, circle, tag);
    }

    public static Alchemist showDark(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, @NonNull TransmutationCircle circle) {
        return showDark(fragmentManager, model, circle, null);
    }

    public static Alchemist showDark(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, @NonNull TransmutationCircle circle, String tag) {
        Alchemist alchemist = assignToAlchemist(model, circle);
        tag = TextUtils.isEmpty(tag) ? String.valueOf(System.currentTimeMillis()) : tag;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(alchemist, tag);
        ft.commitAllowingStateLoss();
        return alchemist;
    }

    private static Alchemist assignToAlchemist(Material model, TransmutationCircle circle) {
        Alchemist alchemist = Alchemist.createInstance();
        alchemist.setMaterial(model);
        alchemist.setTransmutationCircle(circle);
        return alchemist;
    }

    public static void setDefaultCircle(TransmutationCircle circle) {
        CUSTOM_CIRCLE = circle;
    }
}
