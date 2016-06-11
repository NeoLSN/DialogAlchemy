package ui.android.dialogalchemy;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by JasonYang on 2016/6/1.
 */
public interface TransmutationCircle {

    Dialog createDialog(@NonNull Context context, @NonNull Material material);

    void bindCustomView(@NonNull Dialog dialog, @NonNull Material material);
}
