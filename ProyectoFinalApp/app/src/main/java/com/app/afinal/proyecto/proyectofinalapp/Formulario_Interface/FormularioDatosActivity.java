package com.app.afinal.proyecto.proyectofinalapp.Formulario_Interface;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.ClientesActivity;
import com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface.Clientes_Detalles.DetalleClienteFragment;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class FormularioDatosActivity extends AppCompatActivity {

    Toolbar tituloFormulario;
    FormularioDataFragment fragment;
    FormularioDataFragmentCompleto fragmentCompleto;
    SharedPreferences shared = getSharedPreferences("settings", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_datos);
        tituloFormulario = (Toolbar) findViewById(R.id.tituloFormulario);
        tituloFormulario.setTitle(R.string.strPantallaFormulario);

        setSupportActionBar(tituloFormulario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getStringExtra(ClientesActivity.CLIENTEID);



        String completo = "";
        if (shared != null) {
            completo = shared.getString("FORMULARIOCOMPLETO", "");
        }


        if (!completo.equalsIgnoreCase("true")) {
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

            fragmentCompleto = (FormularioDataFragmentCompleto)
                    getSupportFragmentManager().findFragmentById(R.id.formulario_container);
            if (fragmentCompleto == null) {
                fragmentCompleto = FormularioDataFragmentCompleto.newInstance(id);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.formulario_container, fragmentCompleto)
                        .commit();

            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
