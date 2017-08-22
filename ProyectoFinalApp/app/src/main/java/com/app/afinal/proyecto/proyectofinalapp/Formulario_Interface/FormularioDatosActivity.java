package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetalleClienteFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class FormularioDatosActivity extends AppCompatActivity {

    Toolbar tituloFormulario;
    FormularioDataFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_clientes);
        tituloFormulario = (Toolbar) findViewById(R.id.tituloFormulario);
        tituloFormulario.setTitle(R.string.strPantallaFormulario);

        setSupportActionBar(tituloFormulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(ClientesActivity.CLIENTEID);

        fragment = (FormularioDataFragment)
                getSupportFragmentManager().findFragmentById(R.id.formulario_container);
        if (fragment == null) {
            fragment = FormularioDataFragment.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.formulario_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
