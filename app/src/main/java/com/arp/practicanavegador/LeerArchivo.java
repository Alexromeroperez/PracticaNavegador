package com.arp.practicanavegador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LeerArchivo extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_archivo);
        tv=(TextView)findViewById(R.id.tvTexto);
        Bundle b=getIntent().getExtras();
        if(b!=null) {
            tv.setText(b.getString("string"));
        }
    }
}
