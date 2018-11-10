package com.yurentsy.watchingyou.mvp.view;

import android.support.v7.app.AlertDialog;
import android.view.View;

import com.yurentsy.watchingyou.R;

/**
 * Created by silan on 09.11.2018.
 */

public class DialogView {
    private DialogView.CompleteCallback completeCallback;

    public DialogView(View view, String title, CompleteCallback completeCallback) {
        this.completeCallback = completeCallback;
        showDialogView(view, title);
    }

    private void showDialogView(View view, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setPositiveButton(view.getResources().getString(R.string.dialog_button_ok), (dialogInterface, i) -> completeCallback.onComplete());
        builder.setNegativeButton(view.getResources().getString(R.string.dialog_button_cancel), null);
        builder.create().show();
    }

    public interface CompleteCallback {
        void onComplete();
    }
}
