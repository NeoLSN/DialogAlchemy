package ui.android.dialogalchemy.example.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.example.R;

/**
 * Created by JasonYang on 2016/6/13.
 */
public class ColorPaletteStone implements PhilosopherStone {

    private int color = Color.CYAN;

    private ImageView colorPalette;
    private SeekBar aSeekBar;
    private SeekBar rSeekBar;
    private SeekBar gSeekBar;
    private SeekBar bSeekBar;

    private SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateColor();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            updateColor();
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.dialog_color_palette;
    }

    @NonNull
    @Override
    public Material mergeMaterial(@NonNull Context context, @NonNull Material material) {
        if (TextUtils.isEmpty(material.getPositiveButtonText())) {
            Material.Builder builder = material.rebuild(context);
            builder.setPositiveButton(android.R.string.ok, null);
            material = builder.build();
        }
        return material;
    }

    @Override
    public void bindView(Dialog dialog) {
        colorPalette = (ImageView) dialog.findViewById(R.id.color_palette);
        aSeekBar = (SeekBar) dialog.findViewById(R.id.a_seek_bar);
        rSeekBar = (SeekBar) dialog.findViewById(R.id.r_seek_bar);
        gSeekBar = (SeekBar) dialog.findViewById(R.id.g_seek_bar);
        bSeekBar = (SeekBar) dialog.findViewById(R.id.b_seek_bar);

        colorPalette.setBackgroundColor(color);

        aSeekBar.setProgress(Color.alpha(color));
        rSeekBar.setProgress(Color.red(color));
        gSeekBar.setProgress(Color.green(color));
        bSeekBar.setProgress(Color.blue(color));

        aSeekBar.setOnSeekBarChangeListener(listener);
        rSeekBar.setOnSeekBarChangeListener(listener);
        gSeekBar.setOnSeekBarChangeListener(listener);
        bSeekBar.setOnSeekBarChangeListener(listener);
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            Context context = ((Dialog) dialogInterface).getContext();
            Toast.makeText(context, "color is #" + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateColor() {
        color = Color.argb(aSeekBar.getProgress(), rSeekBar.getProgress(),
                gSeekBar.getProgress(), bSeekBar.getProgress());
        colorPalette.setBackgroundColor(color);
    }
}
