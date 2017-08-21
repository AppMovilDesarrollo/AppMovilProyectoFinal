package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.afinal.proyecto.proyectofinalapp.PantallaConfiguracion;
import com.app.afinal.proyecto.proyectofinalapp.PrincipalActivity;
import com.app.afinal.proyecto.proyectofinalapp.R;

public class ClientesActivity extends AppCompatActivity {


    public static final String EXTRA_ClIENTES_ID = "extra_clientes_id";
    public  static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(ClientesActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }

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

    public void callPantallaConfiguracion(View view) {
        Intent i= new Intent(getBaseContext(), PantallaConfiguracion.class);
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }

        }
    }

}
