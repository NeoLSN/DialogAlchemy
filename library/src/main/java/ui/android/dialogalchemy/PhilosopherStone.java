package ui.android.dialogalchemy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;


/**
 * Created by JasonYang on 2016/5/31.
 */
public interface PhilosopherStone {

    @NonNull
    Material mergeMaterial(@NonNull Context context, @NonNull Material material);

    @LayoutRes
    int getLayoutResId();

    void bindView(Dialog dialog);

    void onButtonClick(DialogInterface dialogInterface, int which);
}
