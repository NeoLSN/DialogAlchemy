package ui.android.dialogalchemy.example;

import android.app.Application;

import ui.android.dialogalchemy.DialogAlchemy;
import ui.android.dialogalchemy.circle.MetalTransmutationCircle;

/**
 * Created by JasonYang on 2016/6/14.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DialogAlchemy.setDefaultCircle(new MetalTransmutationCircle());
    }
}
