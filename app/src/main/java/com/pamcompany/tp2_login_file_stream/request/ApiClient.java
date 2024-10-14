package com.pamcompany.tp2_login_file_stream.request;

import android.content.Context;

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
        File archivo= conectar(context);

        //exists(),createNewFile() son metodos de la clase File.
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //Los flujos soportan diferentes clases de datos.
            //bytes simples, tipos de datos primitivos, caracteres y objeto
            //Un programa usa un “flujo de salida” para escribir datos a un destino "OutputStream"
            //FileOutputStream escribe bytes de datos a un fichero del sistema local.
            FileOutputStream fos= new FileOutputStream(archivo);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(usuario);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Un programa usa un “flujo de entrada” para leer datos de un origen "InputStream"
    public static Usuario leer(Context context){
        Usuario usuario= new Usuario();
        File archivo= conectar(context);

        try {
            //FileInputStream para leer datos de un archivo
            FileInputStream fis= new FileInputStream(archivo);
            ObjectInputStream ois= new ObjectInputStream(fis);
            usuario= (Usuario) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static Usuario login(Context context,String email, String password){
        Usuario usuario= leer(context);
        if (usuario.getEmail() != email || usuario.getPassword() != password ){
            usuario = null;
        }
        return usuario;
    }


}
