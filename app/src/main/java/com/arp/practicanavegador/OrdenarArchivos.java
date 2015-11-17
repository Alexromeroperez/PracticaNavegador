package com.arp.practicanavegador;

import java.io.File;
import java.util.Comparator;

/**
 * Created by Alex on 13/10/2015.
 */
public class OrdenarArchivos {
    public static Comparator<File> getComparador(){
        Comparator <File>c=new Comparator<File>() {
            @Override
            public int compare(File archivo1, File archivo2) {
                if(archivo1.isDirectory() && archivo2.isFile()){
                    return -1;
                }
                if(archivo2.isDirectory() && archivo1.isFile()){
                    return 1;
                }
                return archivo1.getName().compareTo(archivo2.getName());
            }
        };
        return c;
    }
}
