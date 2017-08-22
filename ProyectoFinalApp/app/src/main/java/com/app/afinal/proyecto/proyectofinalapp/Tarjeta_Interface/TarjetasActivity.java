package com.app.afinal.proyecto.proyectofinalapp.Tarjeta_Interface;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetalleClienteFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;
import com.app.afinal.proyecto.proyectofinalapp.basedatos.Clientes;

public class TarjetasActivity extends AppCompatActivity {

    Toolbar tituloTarjetas;
    TarjetasFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas);
        tituloTarjetas = (Toolbar) findViewById(R.id.tituloTarjetas);


        setSupportActionBar(tituloTarjetas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String cedula = getIntent().getStringExtra(ClientesActivity.CLIENTECEDULA);

        fragment = (TarjetasFragment)
                getSupportFragmentManager().findFragmentById(R.id.tarjetas_container);
        if (fragment == null) {
            fragment = TarjetasFragment.newInstance(cedula);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tarjetas_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
