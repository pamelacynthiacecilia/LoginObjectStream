package com.pamcompany.tp2_login_file_stream.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pamcompany.tp2_login_file_stream.model.Usuario;
import com.pamcompany.tp2_login_file_stream.request.ApiClient;
import com.pamcompany.tp2_login_file_stream.ui.registro.RegistroMainActivity;

public class LoginViewModel extends AndroidViewModel {

        private Context context;
        private MutableLiveData<String> mjeEmailError;
        private MutableLiveData<String> mjePasswordError;

        public LoginViewModel(@NonNull Application application) {
            super(application);
            context = application.getApplicationContext();
        }

        public LiveData<String> getMjeEmailError() {
            if (mjeEmailError == null) {
                mjeEmailError = new MutableLiveData<>();
            }
            return mjeEmailError;
        }

        public LiveData<String> getMjePasswordError() {
            if (mjePasswordError == null) {
                mjePasswordError = new MutableLiveData<>();
            }
            return mjePasswordError;
        }


        public void ingresar(String email, String password) {

            // Resetear mensajes de error antes de la validación
            mjeEmailError.setValue(null);
            mjePasswordError.setValue(null);

            if (validarCampos(email, password)) {
                Usuario user = ApiClient.login(context, email, password);
                //Log.d("UsuarioIngresar ",user.getEmail());

                if (user != null) {
                    Intent intent = new Intent(context, RegistroMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("UsuarioLogueado", true);
                    context.startActivity(intent);

                } else {
                    mjeEmailError.setValue("email o contraseña incorrecta.");
                }
            }
        }

        private boolean validarCampos(String email, String password) {

            boolean sinError = true;
            if (email.isBlank()) {
                mjeEmailError.setValue("Debe ingresar un email valido");
                sinError = false;
            }

            if (password.isBlank()) {
                mjePasswordError.setValue("Debe ingresar contraseña!");
                sinError = false;
            }

            return sinError;
        }

}
