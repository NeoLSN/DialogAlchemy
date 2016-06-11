package ui.android.dialogalchemy;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import ui.android.dialogalchemy.circle.MetalTransmutationCircle;

/**
 * Created by JasonYang on 2016/6/2.
 */
public class DialogAlchemy {

    private DialogAlchemy() {
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model) {
        return show(fragmentManager, model, new MetalTransmutationCircle());
    }

    public static Alchemist show(@NonNull FragmentManager fragmentManager,
            @NonNull Material model, String tag) {
        return show(fragmentManager, model, new MetalTransmutationCircle(), tag);
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

    private static Alchemist assignToAlchemist(Material model, TransmutationCircle circle) {
        Alchemist alchemist = Alchemist.createInstance();
        alchemist.setMaterial(model);
        alchemist.setTransmutationCircle(circle);
        return alchemist;
    }
}
