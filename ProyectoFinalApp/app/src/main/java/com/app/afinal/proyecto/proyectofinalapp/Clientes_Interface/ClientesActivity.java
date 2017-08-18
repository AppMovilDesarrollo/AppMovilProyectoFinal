package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.afinal.proyecto.proyectofinalapp.R;

public class ClientesActivity extends AppCompatActivity {


    public static final String EXTRA_ClIENTES_ID = "extra_clientes_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.herbar);

        setSupportActionBar(toolbar);

        ClientesFragment fragment = (ClientesFragment)
                getSupportFragmentManager().findFragmentById(R.id.clientes_container);


        FragmentManager fragmentManager = getSupportFragmentManager();

        //Paso 2: Crear una nueva transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Paso 3: Crear un nuevo fragmento y añadirlo
        ClientesFragment fragmento = new ClientesFragment();
        transaction.add(R.id.clientes_container,fragmento);
        transaction.commit();
        /*
        if (fragment == null) {
            fragment = ClientesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.clientes_container, fragment)
                    .commit();
        }*/
    }

}
