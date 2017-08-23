package com.app.afinal.proyecto.proyectofinalapp.Clientes_Interface;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.app.afinal.proyecto.proyectofinalapp.R;

public class ClientesActivity extends AppCompatActivity {


    public static final String CLIENTEID = "CLIENTEID";
    public static final String CLIENTECEDULA = "CLIENTECEDULA";
    public static boolean FORMULARIOCOMPLETO = false;

    public  static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    Toolbar tituloClientesActivity;
    ClientesFragment fragment;


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

        tituloClientesActivity = (Toolbar) findViewById(R.id.tituloClientesActivity);
        tituloClientesActivity.setTitle(R.string.strPantallaClientes);

        setSupportActionBar(tituloClientesActivity);

        fragment = (ClientesFragment)
                getSupportFragmentManager().findFragmentById(R.id.clientes_container);


        if (fragment == null) {
            fragment = ClientesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.clientes_container, fragment)
                    .commit();
        }
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
