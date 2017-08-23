package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo.FormularioCompletoActivity;
import com.app.afinal.proyecto.proyectofinalapp.Formulario_Completo.FormularioCompletoFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class FormularioDatosActivity extends AppCompatActivity {

    Toolbar tituloFormulario;
    FormularioDataFragment fragment;
    FormularioCompletoFragment fragmentCompletos;

    public static final int REQUEST_ADD_UPDATE_DELETE_CLIENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario_datos);
        tituloFormulario = (Toolbar) findViewById(R.id.tituloFormulario);
        tituloFormulario.setTitle(R.string.strPantallaFormulario);

        setSupportActionBar(tituloFormulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(ClientesActivity.CLIENTEID);
        String cedula = getIntent().getStringExtra(ClientesActivity.CLIENTECEDULA);


        if (!ClientesActivity.getDataGlobal(this, id).equalsIgnoreCase("true")) {
            fragment = (FormularioDataFragment)
                    getSupportFragmentManager().findFragmentById(R.id.formulario_container);
            if (fragment == null) {
                fragment = FormularioDataFragment.newInstance(id);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.formulario_container, fragment)
                        .commit();
            }
        } else {

            Toast.makeText(this,
                    "Ya la información ha sido completa", Toast.LENGTH_SHORT).show();

            Intent pantallaFormularioCompleto = new Intent(this, FormularioCompletoActivity.class);
            pantallaFormularioCompleto.putExtra(ClientesActivity.CLIENTEID, id);
            pantallaFormularioCompleto.putExtra(ClientesActivity.CLIENTECEDULA, cedula);
            startActivity(pantallaFormularioCompleto);

            this.finish();

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
