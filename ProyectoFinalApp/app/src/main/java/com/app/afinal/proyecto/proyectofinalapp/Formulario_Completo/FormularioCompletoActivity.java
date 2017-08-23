package com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface.FormularioDataFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class FormularioCompletoActivity extends AppCompatActivity {


    Toolbar tituloFormularioCompleto;
    FormularioCompletoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_completo);
        tituloFormularioCompleto = (Toolbar) findViewById(R.id.tituloFormularioCompleto);

        tituloFormularioCompleto.setTitle(R.string.strPantallaFormularioCompleto);

        setSupportActionBar(tituloFormularioCompleto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(ClientesActivity.CLIENTEID);
        String cedula = getIntent().getStringExtra(ClientesActivity.CLIENTECEDULA);


            fragment = (FormularioCompletoFragment)
                    getSupportFragmentManager().findFragmentById(R.id.formularioCompleto_container);
            if (fragment == null) {
                fragment = FormularioCompletoFragment.newInstance(id,cedula);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.formularioCompleto_container, fragment)
                        .commit();
            }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
