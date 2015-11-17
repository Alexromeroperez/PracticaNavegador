package com.arp.practicanavegador;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Principal extends AppCompatActivity {

    private ListView lv;
    private Adaptador ad;
    private ArrayList<File> archivos;
    private File archivo;
    private String ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            ruta=b.getString("string");
            archivos = Principal.getArchivos(ruta);
            Collections.sort(archivos,OrdenarArchivos.getComparador());
        }else {
            archivos=Principal.getArchivos("/");
            Collections.sort(archivos,OrdenarArchivos.getComparador());
        }
        lv=(ListView)findViewById(R.id.lvArchivos);
        ad=new Adaptador(this,R.layout.lista_detalle,archivos);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                archivo=archivos.get(position);
                ruta=archivo.getAbsolutePath();
                Log.v("RUTA ARCHIVO",ruta);
                if(archivo.isDirectory()){
                    lanzarActividad(ruta,Principal.class);
                }else if(archivo.isFile()){
                    try {
                        FileReader fr=new FileReader(archivo);
                        BufferedReader br=new BufferedReader(fr);
                        String out="",linea;
                        while ((linea=br.readLine())!=null){
                            out+=linea;
                        }
                        br.close();
                        lanzarActividad(out,LeerArchivo.class);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void lanzarActividad(String str,Class c){
        Intent i=new Intent(Principal.this,c);
        Bundle b=new Bundle();
        b.putString("string",str);
        i.putExtras(b);
        startActivity(i);
    }

    private static ArrayList<File> getArchivos(String ruta){
        File f=new File(ruta);
        File lista[]=f.listFiles();
        ArrayList <File>carpetas=new ArrayList();
        if(lista!=null) {
            for (File fichero : lista) {
                carpetas.add(fichero);
            }
        }
        return carpetas;
    }
}
