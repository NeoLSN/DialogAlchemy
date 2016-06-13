package ui.android.dialogalchemy.stone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;

import ui.android.dialogalchemy.Material;
import ui.android.dialogalchemy.PhilosopherStone;
import ui.android.dialogalchemy.R;

/**
 * Created by JasonYang on 2016/6/2.
 */
public class ProgressStone implements PhilosopherStone {

    @IntDef({STYLE_SPINNER, STYLE_HORIZONTAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressStyle {
    }

    public static final int STYLE_SPINNER = 0;
    public static final int STYLE_HORIZONTAL = 1;

    private Handler viewUpdateHandler;

    private int progressStyle = STYLE_SPINNER;

    private ProgressBar progressBar;
    private TextView messageView;
    private TextView progressNumber;
    private TextView progressPercent;

    private CharSequence message;
    private int max;
    private int progress;
    private int secondaryProgress;
    private int incrementProgressBy;
    private int incrementSecondaryProgressBy;
    private boolean indeterminate = true;
    private String progressNumberFormat;
    private NumberFormat progressPercentFormat;
    private Drawable indeterminateDrawable;
    private Drawable progressDrawable;

    private ProgressStone() {
        this.progressNumberFormat = "%1d/%2d";
        this.progressPercentFormat = NumberFormat.getPercentInstance();
        this.progressPercentFormat.setMaximumFractionDigits(0);
    }

    @Override
    public int getLayoutResId() {
        return progressStyle == STYLE_HORIZONTAL ?
                R.layout.dialog_progress_horizontal : R.layout.dialog_progress_spinner;
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
        if (progressStyle == STYLE_HORIZONTAL) {
            progressBar = (ProgressBar) dialog.findViewById(R.id.progress);
            progressNumber = (TextView) dialog.findViewById(R.id.progress_number);
            progressPercent = (TextView) dialog.findViewById(R.id.progress_percent);
        } else {
            progressBar = (ProgressBar) dialog.findViewById(R.id.progress);
            messageView = (TextView) dialog.findViewById(R.id.message);
        }

        if (max > 0) {
            progressBar.setMax(max);
        }
        if (progress > 0) {
            progressBar.setProgress(progress);
        }
        if (secondaryProgress > 0) {
            progressBar.setSecondaryProgress(secondaryProgress);
        }
        if (incrementProgressBy > 0) {
            progressBar.incrementProgressBy(incrementProgressBy);
        }
        if (incrementSecondaryProgressBy > 0) {
            progressBar.incrementSecondaryProgressBy(incrementSecondaryProgressBy);
        }
        if (progressDrawable != null) {
            progressBar.setProgressDrawable(progressDrawable);
        }
        if (indeterminateDrawable != null) {
            progressBar.setIndeterminateDrawable(indeterminateDrawable);
        }
        progressBar.setIndeterminate(indeterminate);

        if (progressStyle == STYLE_SPINNER) {
            messageView.setText(message);
        } else {
            viewUpdateHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    /* Update the number and percent */
                    int progress = progressBar.getProgress();
                    int max = progressBar.getMax();
                    if (progressNumberFormat != null) {
                        String format = progressNumberFormat;
                        progressNumber.setText(String.format(format, progress, max));
                    } else {
                        progressNumber.setText("");
                    }
                    if (progressPercentFormat != null) {
                        double percent = (double) progress / (double) max;
                        SpannableString tmp = new SpannableString(progressPercentFormat.format(percent));
                        tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                                0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        progressPercent.setText(tmp);
                    } else {
                        progressPercent.setText("");
                    }
                }
            };
        }

        onProgressChanged();
    }

    @Override
    public void onButtonClick(DialogInterface dialogInterface, int which) {
    }

    public void setMessage(String message) {
        this.message = message;
        if (progressStyle == STYLE_SPINNER && messageView != null) {
            messageView.setText(message);
        }
    }

    public void setMax(int max) {
        if (max > 0) {
            this.max = max;
        }
        if (progressBar != null) {
            progressBar.setMax(this.max);
            onProgressChanged();
        }
    }

    public void setProgress(int progress) {
        if (progress > 0) {
            this.progress = progress;
        }
        if (progressBar != null) {
            progressBar.setProgress(this.progress);
            onProgressChanged();
        }
    }

    public void setSecondaryProgress(int secondaryProgress) {
        if (secondaryProgress > 0) {
            this.secondaryProgress = secondaryProgress;
        }
        if (progressBar != null) {
            progressBar.setSecondaryProgress(this.secondaryProgress);
            onProgressChanged();
        }
    }

    public void setIncrementProgressBy(int incrementProgressBy) {
        if (incrementProgressBy > 0) {
            this.incrementProgressBy = incrementProgressBy;
        }
        if (progressBar != null) {
            progressBar.incrementProgressBy(this.incrementProgressBy);
        }
    }

    public void setIncrementSecondaryProgressBy(int incrementSecondaryProgressBy) {
        if (incrementSecondaryProgressBy > 0) {
            this.incrementSecondaryProgressBy = incrementSecondaryProgressBy;
        }
        if (progressBar != null) {
            progressBar.incrementSecondaryProgressBy(this.incrementSecondaryProgressBy);
        }
    }

    public void setIndeterminate(boolean indeterminate) {
        this.indeterminate = indeterminate;
        if (progressBar != null) {
            progressBar.setIndeterminate(this.indeterminate);
        }
    }

    public void setProgressNumberFormat(String progressNumberFormat) {
        this.progressNumberFormat = progressNumberFormat;
        onProgressChanged();
    }

    public void setProgressPercentFormat(NumberFormat progressPercentFormat) {
        this.progressPercentFormat = progressPercentFormat;
        onProgressChanged();
    }

    public void setIndeterminateDrawable(Drawable indeterminateDrawable) {
        this.indeterminateDrawable = indeterminateDrawable;
        if (progressBar != null) {
            progressBar.setIndeterminateDrawable(this.indeterminateDrawable);
        }
    }

    public void setProgressDrawable(Drawable progressDrawable) {
        this.progressDrawable = progressDrawable;
        if (progressBar != null) {
            progressBar.setProgressDrawable(this.progressDrawable);
        }
    }

    /*public CharSequence getMessage() {
        if (messageView != null) {
            message = messageView.getText().toString();
        }
        return message;
    }

    public int getMax() {
        if (progressBar != null) {
            max = progressBar.getMax();
        }
        return max;
    }

    public int getProgress() {
        if (progressBar != null) {
            progress = progressBar.getProgress();
        }
        return progress;
    }

    public int getSecondaryProgress() {
        if (progressBar != null) {
            secondaryProgress = progressBar.getSecondaryProgress();
        }
        return secondaryProgress;
    }

    public int getIncrementProgressBy() {
        return incrementProgressBy;
    }

    public int getIncrementSecondaryProgressBy() {
        return incrementSecondaryProgressBy;
    }

    public boolean isIndeterminate() {
        if (progressBar != null) {
            indeterminate = progressBar.isIndeterminate();
        }
        return indeterminate;
    }

    public String getProgressNumberFormat() {
        return progressNumberFormat;
    }

    public NumberFormat getProgressPercentFormat() {
        return progressPercentFormat;
    }

    public Drawable getIndeterminateDrawable() {
        if (progressBar != null) {
            indeterminateDrawable = progressBar.getIndeterminateDrawable();
        }
        return indeterminateDrawable;
    }

    public Drawable getProgressDrawable() {
        if (progressBar != null) {
            progressDrawable = progressBar.getProgressDrawable();
        }
        return progressDrawable;
    }*/

    private void onProgressChanged() {
        if (progressStyle == STYLE_HORIZONTAL) {
            if (viewUpdateHandler != null && !viewUpdateHandler.hasMessages(0)) {
                viewUpdateHandler.sendEmptyMessage(0);
            }
        }
    }

    public static class Builder {

        private Context context;
        private ProgressStone custom;

        public Builder(Context context) {
            this.context = context;
            this.custom = new ProgressStone();
        }

        public Builder setIndeterminate(boolean value) {
            custom.indeterminate = value;
            return this;
        }

        public Builder setProgressStyle(@ProgressStyle int style) {
            custom.progressStyle = style;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            custom.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            custom.message = context.getString(messageId);
            return this;
        }

        public Builder setMessage(@StringRes int messageId, Object... args) {
            custom.message = context.getString(messageId, args);
            return this;
        }

        public Builder setProgress(int progress) {
            custom.progress = progress;
            return this;
        }

        public Builder setMax(int max) {
            custom.max = max;
            return this;
        }

        public Builder setSecondaryProgress(int progress) {
            custom.secondaryProgress = progress;
            return this;
        }

        public Builder setIncrementProgressBy(int diff) {
            custom.incrementProgressBy = diff;
            return this;
        }

        public Builder incrementSecondaryProgressBy(int diff) {
            custom.incrementSecondaryProgressBy = diff;
            return this;
        }

        public Builder setProgressNumberFormat(String format) {
            custom.progressNumberFormat = format;
            return this;
        }

        public Builder setProgressPercentFormat(NumberFormat format) {
            custom.progressPercentFormat = format;
            return this;
        }

        public Builder setProgressDrawable(Drawable d) {
            custom.progressDrawable = d;
            return this;
        }

        public Builder setIndeterminateDrawable(Drawable d) {
            custom.indeterminateDrawable = d;
            return this;
        }

        public ProgressStone build() {
            return custom;
        }
    }
}
