package com.arp.practicanavegador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Alex on 13/10/2015.
 */
public class Adaptador extends ArrayAdapter<File> {

    private Context contexto;
    private int recurso;
    private ArrayList<File> lista;
    private LayoutInflater i;

    public Adaptador(Context context, int resource, ArrayList<File> lista) {
        super(context, resource, lista);
        this.contexto=context;
        this.recurso=resource;
        this.lista=lista;
        i=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(vh==null){
            vh=new ViewHolder();
            convertView=i.inflate(recurso,null);
            vh.iv=(ImageView)convertView.findViewById(R.id.ivIcono);
            vh.tvNombre=(TextView)convertView.findViewById(R.id.tvNombre);
            vh.tvPermisos=(TextView)convertView.findViewById(R.id.tvPermisos);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        File f = lista.get(position);
        String permiso="";
        vh.tvNombre.setText(f.getName());
        if(f.canRead()){
            permiso+="r";
        }
        if(f.canWrite()){
            permiso+="w";
        }
        if(f.canExecute()){
            permiso+="x";
        }
        vh.tvPermisos.setText(permiso);
        if(f.isFile()){
            vh.iv.setImageResource(R.drawable.archivo);
        }else{
            vh.iv.setImageResource(R.drawable.carpeta);
        }
        return convertView;
    }

    static class ViewHolder{
        private TextView tvNombre,tvPermisos;
        private ImageView iv;
    }
}
