package com.pamcompany.tp2_login_file_stream.ui;

import android.app.AlertDialog;
import android.content.Context;

public class ErrorDialog {

    public static void showErrorDialog(Context context, String title, String mje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(mje)
                .setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}