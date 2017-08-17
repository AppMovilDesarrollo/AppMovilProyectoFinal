package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.afinal.proyecto.proyectofinalapp.R;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ClientesFragment fragment = (ClientesFragment)
                getSupportFragmentManager().findFragmentById(R.id.clientes_container);

        if (fragment == null) {
            fragment = ClientesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.clientes_container, fragment)
                    .commit();
        }
    }

}
