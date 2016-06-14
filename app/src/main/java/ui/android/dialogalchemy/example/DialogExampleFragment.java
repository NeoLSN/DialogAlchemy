package ui.android.dialogalchemy.example;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.Theme;

import ui.android.dialogalchemy.DialogAlchemy;
import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.circle.MetalTransmutationCircle;
import ui.android.dialogalchemy.example.circle.AlertDialogProTransmutationCircle;
import ui.android.dialogalchemy.example.circle.MaterialDialogsTransmutationCircle;
import ui.android.dialogalchemy.example.stone.ColorPaletteStone;
import ui.android.dialogalchemy.example.stone.SorceryStone;

/**
 * Created by JasonYang on 2016/6/7.
 */
public class DialogExampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_example, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        // Transmutation Circle demo
        Button testButton = (Button) view.findViewById(R.id.default_transmutation_circle);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        Button alertProButton = (Button) view.findViewById(R.id.alert_pro_transmutation_circle);
        alertProButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertProDialog();
            }
        });
        Button mdCircleButton = (Button) view.findViewById(R.id.md_transmutation_circle);
        mdCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMDDialog();
            }
        });

        // Custom philosopher stone demo
        Button colorPaletteButton = (Button) view.findViewById(R.id.color_palette_button);
        colorPaletteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createColorPaletteDialog();
            }
        });

        // Extension demo
        Button hackingButton = (Button) view.findViewById(R.id.hacking_button);
        hackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSorceryDialog();
            }
        });
    }

    private void createDialog() {
        Material material = createMaterial()
                .setTitle("Support-v7")
                .build();
        DialogAlchemy.show(getFragmentManager(), material, new MetalTransmutationCircle());
    }

    private void createAlertProDialog() {
        Material material = createMaterial()
                .setTitle("Alert Dialog Pro - fengdai")
                .build();
        DialogAlchemy.show(getFragmentManager(), material, new AlertDialogProTransmutationCircle());
    }

    private void createMDDialog() {
        Material material = createMaterial()
                .setTitle("MD library - afollestad")
                .build();
        DialogAlchemy.show(getFragmentManager(), material, new MaterialDialogsTransmutationCircle());
    }

    private Material.Builder createMaterial() {
        return new Material.Builder(getActivity())
                .setMessage(R.string.example_long_content)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = ((Dialog) dialog).getContext();
                        Toast.makeText(context, "Button POSITIVE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = ((Dialog) dialog).getContext();
                        Toast.makeText(context, "Button NEGATIVE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = ((Dialog) dialog).getContext();
                        Toast.makeText(context, "Button NEUTRAL", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createColorPaletteDialog() {
        Material.Builder builder = new Material.Builder(getActivity());
        builder.setTitle("Color Palette")
                .setNegativeButton(android.R.string.cancel, null)
                .setPhilosopherStone(new ColorPaletteStone());
        DialogAlchemy.show(getFragmentManager(), builder.build());
    }

    private void createSorceryDialog() {
        PhilosopherStone stone = new SorceryStone.Builder(getActivity())
                .titleColor(Color.MAGENTA)
                .titleGravity(GravityEnum.CENTER)
                .contentColor(Color.CYAN)
                .contentGravity(GravityEnum.CENTER)
                .buttonRippleColor(Color.YELLOW)
                .contentLineSpacing(2.0f)
                .theme(Theme.DARK)
                .setPhilosopherStone(new ColorPaletteStone()) // special power
                .build();

        Material.Builder builder = new Material.Builder(getActivity())
                .setTitle("Sorcery Stone")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, null)
                .setPhilosopherStone(stone);

        DialogAlchemy.show(getFragmentManager(), builder.build(),
                new MaterialDialogsTransmutationCircle());
    }
}
