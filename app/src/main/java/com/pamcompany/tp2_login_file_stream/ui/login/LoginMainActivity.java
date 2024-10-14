package com.pamcompany.tp2_login_file_stream.ui.login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pamcompany.tp2_login_file_stream.databinding.LoginActivityMainBinding;
import com.pamcompany.tp2_login_file_stream.ui.ErrorDialog;
import com.pamcompany.tp2_login_file_stream.ui.registro.RegistroMainActivity;


public class LoginMainActivity extends AppCompatActivity {

    private LoginActivityMainBinding binding;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityMainBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        setContentView(binding.getRoot());

        vm.getMjeEmailError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null) {
                    ErrorDialog.showErrorDialog(LoginMainActivity.this, "Error", error);
                }
            }
        });
        vm.getMjePasswordError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null) {
                    ErrorDialog.showErrorDialog(LoginMainActivity.this, "Error", error);
                }
            }
        });

        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.ingresar(binding.etEmailLogin.getText().toString(), binding.etClaveLogin.getText().toString());
            }
        });

        binding.btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginMainActivity.this, RegistroMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("UsuarioLogueado",false);
                startActivity(intent);
            }
        });


    }
}