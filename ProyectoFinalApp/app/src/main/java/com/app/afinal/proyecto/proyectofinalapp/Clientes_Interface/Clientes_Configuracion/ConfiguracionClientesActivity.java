package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Configuracion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetalleClienteFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class ConfiguracionClientesActivity extends AppCompatActivity {

    Toolbar configuracionTitulo;
    ConfiguracionClientesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_clientes);
        configuracionTitulo = (Toolbar) findViewById(R.id.configuracionTitulo);

        configuracionTitulo.setTitle(R.string.strPantallaConfiguracionClientes);

        setSupportActionBar(configuracionTitulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        fragment = (ConfiguracionClientesFragment)
                getSupportFragmentManager().findFragmentById(R.id.configuracionClientes_container);
        if (fragment == null) {
            fragment = ConfiguracionClientesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.configuracionClientes_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
