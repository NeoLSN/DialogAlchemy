package ui.android.dialogalchemy.example;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import ui.android.dialogalchemy.DialogAlchemy;
import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;

/**
 * Created by JasonYang on 2016/6/7.
 */
public class DialogTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        Button testButton = (Button) view.findViewById(R.id.test_dialog);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        Material material = new Material.Builder(getActivity())
                .setTitle("Color palette")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPhilosopherStone(new PhilosopherStone() {

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
                })
                .build();
        DialogAlchemy.show(getFragmentManager(), material);
    }
}
