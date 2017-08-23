package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Utility;

public class ClientesActivity extends AppCompatActivity {


    public static final String CLIENTEID = "CLIENTEID";
    public static final String CLIENTECEDULA = "CLIENTECEDULA";

    Toolbar tituloClientesActivity;
    ClientesFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_clientes);

            tituloClientesActivity = (Toolbar) findViewById(R.id.tituloClientesActivity);
            tituloClientesActivity.setTitle(R.string.strPantallaClientes);

            setSupportActionBar(tituloClientesActivity);

            fragment = (ClientesFragment)
                    getSupportFragmentManager().findFragmentById(R.id.clientes_container);


            if (fragment == null) {
                fragment = ClientesFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.clientes_container, fragment)
                        .commit();
            }


    }


    public static void setDataGlobal(Context context, String value, String clientID)
    {
        SharedPreferences settings = context.getSharedPreferences("pantallas", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(clientID, value);
        editor.commit();
    }

    public static String getDataGlobal(Context context, String clientID)
    {
        SharedPreferences settings = context.getSharedPreferences("pantallas", MODE_PRIVATE);
        return settings.getString(clientID, "");
    }

}
