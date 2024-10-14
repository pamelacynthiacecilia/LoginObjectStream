package com.pamcompany.tp2_login_file_stream.ui;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.pamcompany.tp2_login_file_stream.ui.login.LoginMainActivity;


public class SuccessDialog {
    public static void showErrorDialog(Context context, String title, String mje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(mje)
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Aquí inicia la nueva actividad
                        Intent intent = new Intent(context, LoginMainActivity.class);
                        context.startActivity(intent);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}