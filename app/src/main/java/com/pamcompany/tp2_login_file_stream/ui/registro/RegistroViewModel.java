package com.pamcompany.tp2_login_file_stream.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pamcompany.tp2_login_file_stream.model.Usuario;
import com.pamcompany.tp2_login_file_stream.request.ApiClient;

import java.io.IOException;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mjeError;
    private MutableLiveData<String> mjeSuccess;
    private MutableLiveData<Usuario> usuarioMutable;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMjeError() {
        if (mjeError == null) {
            mjeError = new MutableLiveData<>();
        }
        return mjeError;
    }

    public LiveData<String> getMjeSuccess() {
        if (mjeSuccess == null) {
            mjeSuccess = new MutableLiveData<>();
        }
        return mjeSuccess;
    }

    public LiveData<Usuario> getUsuarioMutable() {
        usuarioMutable = new MutableLiveData<>();
        return usuarioMutable;
    }

    public void registrarUsuario(String dni, String nombre, String apellido, String email, String password) {
        if (validaCampos(dni, nombre, apellido, email, password)) {
            Usuario usuario = new Usuario(Long.parseLong(dni), nombre, apellido, email, password);
            try {
                ApiClient.guardar(context, usuario);
            } catch (IOException e) {
                Toast.makeText(context, "En metodo resgistrarUsuario, IO Exception", Toast.LENGTH_SHORT).show();
            }
            mjeSuccess.setValue("Exito al guardar el registro");
        }
    }

    private boolean validaCampos(String dni, String nombre, String apellido, String email, String password) {
        // Concateno los valores en un solo String
        String datos = "Datos: " + "DNI: " + dni + ", Nombre: " + nombre + ", Apellido: " + apellido +
                ", Email: " + email + ", Password: " + password;
        Log.d("Datos", datos);

        boolean sinError = true;
        if (dni.isBlank() || nombre.isBlank() || apellido.isBlank() || email.isBlank() || password.isBlank()) {
            mjeError.setValue("Debe completar el valor de todos los campos");
            sinError = false;
        }

        return sinError;
    }

    public void leerUsuario(Intent intent) {
        // Log.d("value intent", String.valueOf(intent));
        if (intent.getBooleanExtra("UsuarioLogueado", true)) {
            Usuario usuario = ApiClient.leer(context);
            usuarioMutable.setValue(usuario);
        }

    }
}