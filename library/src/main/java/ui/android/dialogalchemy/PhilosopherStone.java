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

    @LayoutRes
    int getLayoutResId();

    @NonNull
    Material mergeMaterial(@NonNull Context context, @NonNull Material material);

    void bindView(Dialog dialog);

    void onButtonClick(DialogInterface dialogInterface, int which);
}
