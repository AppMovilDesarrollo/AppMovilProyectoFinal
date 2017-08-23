package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class DetallesClientesActivity extends AppCompatActivity {

    Toolbar tituloClientesDetallesActivity;
    DetalleClienteFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_clientes);
        tituloClientesDetallesActivity = (Toolbar) findViewById(R.id.tituloClientesDetallesActivity);


        setSupportActionBar(tituloClientesDetallesActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(ClientesActivity.CLIENTEID);

        fragment = (DetalleClienteFragment)
                getSupportFragmentManager().findFragmentById(R.id.detallesCliente_container);
        if (fragment == null) {
            fragment = DetalleClienteFragment.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detallesCliente_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
