package com.pamcompany.tp2_login_file_stream.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pamcompany.tp2_login_file_stream.model.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    private static File directorio;

    private static File conectar(Context context){
        if (directorio == null){
            //getFilesDir retorna un File que representa el directorio interno de nuestra aplicación
            directorio = new File(context.getFilesDir(), "usuario.dat");
        }
        return directorio;
    }


    public static void guardar(Context context, Usuario usuario){
        //File archivo= conectar(context);

        File archivo= new File(context.getFilesDir(), "usuario.dat");

        try {
            //Los flujos soportan diferentes tipos de datos como bytes simples, tipos de datos primitivos, caracteres y objeto
            //FileOutputStream Nodo o canal por el que viajaran los bytes de datos al archivo que cree
            FileOutputStream nodo= new FileOutputStream(archivo);
            try (ObjectOutputStream oos = new ObjectOutputStream(nodo)) {
                oos.writeObject(usuario);
                //vaciar el buffer
                oos.flush();
            }
            nodo.close();//cierro nodo
            //Log.d("UsuarioGuardado ", usuario.getEmail());
        } catch (IOException e) {
            Toast.makeText(context, "IO Exception en metodo guardar datos", Toast.LENGTH_SHORT).show();
        }
    }


    //Un programa usa un “flujo de entrada” para leer datos de un origen "InputStream"
    public static Usuario leer(Context context){
        Usuario usuario= new Usuario();
        File directorio= context.getFilesDir();
        File archivo= new File(directorio, "usuario.dat");

        try {
            //FileInputStream para leer datos de un archivo
            FileInputStream fis= new FileInputStream(archivo);
            ObjectInputStream ois= new ObjectInputStream(fis);

            usuario= (Usuario) ois.readObject();

            Log.d("Usuario en try ApiClient leer: ", usuario.getEmail());

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "FileNotFoundException en metodo guardar datos", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(context, "IOException en metodo guardar datos", Toast.LENGTH_SHORT).show();

        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "ClassNotFoundExceptionen metodo guardar datos", Toast.LENGTH_SHORT).show();

        }
        return usuario;
    }

    public static Usuario login(Context context,String email, String password){
        Usuario usuario = null;
        usuario= leer(context);
        Log.d("Usuario login: ", email);
        if (!email.equals(usuario.getEmail()) || !password.equals(usuario.getPassword())){
            usuario = null;
        }
        else{
            Log.d("Usuario login: ", usuario.getEmail());
        }
        return usuario;
    }


}
